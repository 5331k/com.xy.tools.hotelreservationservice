package com.xy.tools.hotelreservationservice.reserve.algo;

import java.util.List;

import com.xy.tools.hotelreservationservice.persistence.entity.Reservation;
import com.xy.tools.hotelreservationservice.reserve.ReserveEntity;

public class BasicReservationHandler implements ReservationHandler {

	private boolean roomReservations[][];

	public BasicReservationHandler(int totalRooms) {
		roomReservations = new boolean[totalRooms + 1][TOTAL_DAYS];
	}

	/**
	 * For each possible room where start day is free Check all the respective days
	 * until the end day
	 * 
	 * @param reserveEntity
	 */
	@Override
	public int performReservation(ReserveEntity reserveEntity) {
		final int startDay = reserveEntity.getStartDay();
		final int endDay = reserveEntity.getEndDay();
		final int totalRooms = roomReservations.length;
		for (int roomNumber = 1; roomNumber < totalRooms; roomNumber++) {
			// it means room number i is free on startDay
			if (roomReservations[roomNumber][startDay] == false) {
				// now check if all subsequent days are free
				if (areAllDaysFree(roomNumber, startDay, endDay)) {
					markAllDaysReserved(roomNumber, startDay, endDay);
					return roomNumber;
				}
			}
		}
		return RESERVATION_DECLINED;
	}

	@Override
	public boolean areAllDaysFree(int roomNumber, int startDay, int endDay) {
		for (int i = startDay + 1; i <= endDay; i++) {
			if (roomReservations[roomNumber][i] == true)
				return false;
		}
		return true;
	}

	@Override
	public void loadReservations(List<Reservation> reservations) {
		for (Reservation reservation : reservations) {
			for (int i = reservation.getStartDay(); i <= reservation.getEndDay(); i++) {
				roomReservations[reservation.getRoomNumber()][i] = true;
			}
		}

	}

	@Override
	public void markAllDaysReserved(int roomNumber, int startDay, int endDay) {
		for (int i = startDay; i <= endDay; i++) {
			roomReservations[roomNumber][i] = true;
		}
	}

}
