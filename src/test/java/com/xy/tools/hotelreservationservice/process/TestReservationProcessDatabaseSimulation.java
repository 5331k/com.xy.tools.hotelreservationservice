package com.xy.tools.hotelreservationservice.process;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.xy.tools.hotelreservationservice.persistence.DataAccessService;
import com.xy.tools.hotelreservationservice.persistence.entity.Reservation;
import com.xy.tools.hotelreservationservice.reserve.ReserveEntity;

@SpringBootTest
public class TestReservationProcessDatabaseSimulation {

	@Autowired
	DataAccessService dataAccessService;

	@Test
	public void readFromDatabaseInStartThanDeclineTest() {
		// saving some dummy data so when we launch application, reservation data
		// already gets loaded from database

		final int TOTAL_ROOMS = 3;

		// saving 3 rooms
		dataAccessService.saveRooms(TOTAL_ROOMS);

		// doing some valid reservations in database
		dataAccessService.saveReservation(new Reservation(1, 1, 3));
		dataAccessService.saveReservation(new Reservation(2, 2, 5));
		dataAccessService.saveReservation(new Reservation(3, 1, 9));

		ReservationProcessor reservationProcessor = new ReservationProcessor();
		// as data is already loaded from database in above step, so room this request
		// should fail
		boolean result = reservationProcessor.reserveRoom(new ReserveEntity(0, 15));
		assertEquals(false, result);
	}
}
