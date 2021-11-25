package com.xy.tools.hotelreservationservice.process;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.xy.tools.hotelreservationservice.config.ApplicationContextProvider;
import com.xy.tools.hotelreservationservice.persistence.DataAccessService;
import com.xy.tools.hotelreservationservice.persistence.entity.Reservation;
import com.xy.tools.hotelreservationservice.reserve.ReservationService;
import com.xy.tools.hotelreservationservice.reserve.Reserve;
import com.xy.tools.hotelreservationservice.reserve.ReserveEntity;
import com.xy.tools.hotelreservationservice.reserve.algo.BasicReservationHandler;
import com.xy.tools.hotelreservationservice.reserve.algo.ReservationHandler;

public class ReservationProcessor {

	private static final Logger logger = LogManager.getLogger(ReservationProcessor.class);
	private static final int DEFAULT_ROOM_SIZE = 1000;

	ReservationHandler reservationFinder;

	DataAccessService dataAccessService;

	Reserve reservationService;

	public ReservationProcessor() {
		this.dataAccessService = ApplicationContextProvider.getApplicationContext().getBean(DataAccessService.class);
		init();
	}

	private void init() {
		loadDatabase();
		this.reservationService = new ReservationService(this.reservationFinder, this.dataAccessService);
	}

	/**
	 * This method is for the concept that when application will start
	 * it will immediately load data from database into data structure so
	 * reservation finding algorithm can compute fast
	 */
	private void loadDatabase() {
		final int totalRooms = dataAccessService.getTotalRooms();
		if (totalRooms > 0) {
			logger.info("Data found in the database so loading it to in memory...");
			reservationFinder = new BasicReservationHandler(totalRooms);
			List<Reservation> reservations = dataAccessService.loadAllReservations();
			if (reservations.size() > 0) {
				reservationFinder.loadReservations(reservations);
			}
		} else {
			this.reservationFinder = new BasicReservationHandler(DEFAULT_ROOM_SIZE);
			logger.info("No data found in database so creating rooms with default size of : {}", DEFAULT_ROOM_SIZE);
		}
	}

	/**
	 * create fresh room, via destroying prior resources bound to old size of rooms
	 * 
	 * @param totalRooms
	 */
	public boolean createNewRooms(int totalRooms) {
		logger.info("Deleting all previous and default initializations to create fresh rooms for new reservations");
		destroyAll();
		boolean result = this.dataAccessService.saveRooms(totalRooms);
		if (result != false) {
			this.reservationFinder = new BasicReservationHandler(totalRooms);
			this.reservationService = new ReservationService(this.reservationFinder, this.dataAccessService);
		}
		return result;
	}

	public boolean reserveRoom(ReserveEntity reserveEntity) {
		boolean result = reservationService.reserveRoom(reserveEntity);
		return result;
	}

	private void destroyAll() {
		dataAccessService.deleteAll();
		this.reservationFinder = null;
		this.reservationService = null;
	}
}
