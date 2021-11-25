package com.xy.tools.hotelreservationservice.process;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.xy.tools.hotelreservationservice.reserve.ReserveEntity;

@SpringBootTest
public class TestReservationProcess {

	// Example 1
	@Test
	public void requestOutsidePlanningPeriodTest() {
		ReservationProcessor reservationProcessor = new ReservationProcessor();
		final int TOTAL_ROOMS = 1;
		reservationProcessor.createNewRooms(TOTAL_ROOMS);
		assertEquals(false, reservationProcessor.reserveRoom(new ReserveEntity(-4, 2)));
		assertEquals(false, reservationProcessor.reserveRoom(new ReserveEntity(200, 400)));
	}

	// Example 2
	@Test
	public void requestsAcceptedTest() {
		ReservationProcessor reservationProcessor = new ReservationProcessor();
		final int TOTAL_ROOMS = 3;
		reservationProcessor.createNewRooms(TOTAL_ROOMS);
		assertEquals(true, reservationProcessor.reserveRoom(new ReserveEntity(0, 5)));
		assertEquals(true, reservationProcessor.reserveRoom(new ReserveEntity(7, 13)));
		assertEquals(true, reservationProcessor.reserveRoom(new ReserveEntity(3, 9)));
		assertEquals(true, reservationProcessor.reserveRoom(new ReserveEntity(5, 7)));
		assertEquals(true, reservationProcessor.reserveRoom(new ReserveEntity(6, 6)));
		assertEquals(true, reservationProcessor.reserveRoom(new ReserveEntity(0, 4)));
	}

	// Example 3
	@Test
	public void requestsAcceptedThanDeclinedTest() {
		ReservationProcessor reservationProcessor = new ReservationProcessor();
		final int TOTAL_ROOMS = 3;
		reservationProcessor.createNewRooms(TOTAL_ROOMS);
		assertEquals(true, reservationProcessor.reserveRoom(new ReserveEntity(1, 3)));
		assertEquals(true, reservationProcessor.reserveRoom(new ReserveEntity(2, 5)));
		assertEquals(true, reservationProcessor.reserveRoom(new ReserveEntity(1, 9)));
		assertEquals(false, reservationProcessor.reserveRoom(new ReserveEntity(0, 15)));
	}

	// Example 4
	@Test
	public void requestsAcceptedThanDeclinedThanAcceptTest() {
		ReservationProcessor reservationProcessor = new ReservationProcessor();
		final int TOTAL_ROOMS = 3;
		reservationProcessor.createNewRooms(TOTAL_ROOMS);
		assertEquals(true, reservationProcessor.reserveRoom(new ReserveEntity(1, 3)));
		assertEquals(true, reservationProcessor.reserveRoom(new ReserveEntity(0, 15)));
		assertEquals(true, reservationProcessor.reserveRoom(new ReserveEntity(1, 9)));
		assertEquals(false, reservationProcessor.reserveRoom(new ReserveEntity(2, 5)));
		assertEquals(true, reservationProcessor.reserveRoom(new ReserveEntity(4, 9)));
	}

	// Example 5
	@Test
	public void complexRequestsTest() {
		ReservationProcessor reservationProcessor = new ReservationProcessor();
		final int TOTAL_ROOMS = 2;
		reservationProcessor.createNewRooms(TOTAL_ROOMS);
		assertEquals(true, reservationProcessor.reserveRoom(new ReserveEntity(1, 3)));
		assertEquals(true, reservationProcessor.reserveRoom(new ReserveEntity(0, 4)));
		assertEquals(false, reservationProcessor.reserveRoom(new ReserveEntity(2, 3)));
		assertEquals(true, reservationProcessor.reserveRoom(new ReserveEntity(5, 5)));
		assertEquals(false, reservationProcessor.reserveRoom(new ReserveEntity(4, 10)));
		assertEquals(true, reservationProcessor.reserveRoom(new ReserveEntity(10, 10)));
		assertEquals(true, reservationProcessor.reserveRoom(new ReserveEntity(6, 7)));
		assertEquals(true, reservationProcessor.reserveRoom(new ReserveEntity(8, 10)));
		assertEquals(true, reservationProcessor.reserveRoom(new ReserveEntity(8, 9)));
	}
}
