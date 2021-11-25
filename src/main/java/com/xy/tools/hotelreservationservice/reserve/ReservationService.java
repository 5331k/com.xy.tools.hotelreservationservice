package com.xy.tools.hotelreservationservice.reserve;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.xy.tools.hotelreservationservice.persistence.DataAccessService;
import com.xy.tools.hotelreservationservice.persistence.entity.Reservation;
import com.xy.tools.hotelreservationservice.reserve.algo.ReservationHandler;

public class ReservationService implements Reserve {

	private static final Logger logger = LogManager.getLogger(ReservationService.class);

	protected BasicValidator basicValidator;

	protected ReservationHandler reservationFinder;

	protected DataAccessService dataAccessService;

	public ReservationService(ReservationHandler reservationFinder, DataAccessService dataAccessService) {
		this.basicValidator = new BasicValidator();
		this.reservationFinder = reservationFinder;
		this.dataAccessService = dataAccessService;
	}

	@Override
	public boolean reserveRoom(ReserveEntity reserveEntity) {
		final boolean validationResult = basicValidator.validateReserveEntity(reserveEntity);
		if (!validationResult) {
			logger.error("Validation failed");
			return false;
		}
		int roomNumber = reservationFinder.performReservation(reserveEntity);

		if (roomNumber != ReservationHandler.RESERVATION_DECLINED) {
			logger.info("Request Accepted!");
			dataAccessService.saveReservation(
					new Reservation(roomNumber, reserveEntity.getStartDay(), reserveEntity.getEndDay()));
			logger.info("Accepted reservation successfully saved into database");
			return true;
		} else {
			logger.info("Request Declined");
			return false;
		}
	}

}
