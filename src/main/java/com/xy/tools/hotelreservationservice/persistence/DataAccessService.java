package com.xy.tools.hotelreservationservice.persistence;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xy.tools.hotelreservationservice.config.HotelConfig;
import com.xy.tools.hotelreservationservice.exception.NumberOfRoomsExceededException;
import com.xy.tools.hotelreservationservice.persistence.entity.Reservation;
import com.xy.tools.hotelreservationservice.persistence.entity.Room;
import com.xy.tools.hotelreservationservice.persistence.repository.ReservationRepository;
import com.xy.tools.hotelreservationservice.persistence.repository.RoomRepository;

@Service
public class DataAccessService {

	private static final Logger logger = LogManager.getLogger(DataAccessService.class);

	@Autowired
	HotelConfig hotelConfig;

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	ReservationRepository reservationRepository;

	public boolean saveRooms(int totalRooms) {
		if (totalRooms > hotelConfig.getMaxRooms()) {
			final String message = "Number of rooms limit exceeded! , please specify total rooms <= "
					+ hotelConfig.getMaxRooms();
			logger.error(message);
			throw new NumberOfRoomsExceededException(message);
		}
		for (int i = 1; i <= totalRooms; i++) {
			roomRepository.save(new Room(i));
		}
		return true;
	}

	public int getTotalRooms() {
		// as based on requirements and real case , rooms will always fit in an integer,
		// so its not a worry
		// if there was a risk of casting then logic have been changed appropriately
		return (int) roomRepository.count();
	}

	public List<Reservation> loadAllReservations() {
		if (reservationRepository.count() > 0)
			return reservationRepository.findAll();
		return new ArrayList<>();
	}

	public void saveReservation(Reservation reservation) {
		reservationRepository.save(reservation);
	}

	public void deleteAll() {
		reservationRepository.deleteAll();
		roomRepository.deleteAll();
		logger.info("All rooms and reservations are deleted");
	}

}
