package com.xy.tools.hotelreservationservice.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "reserve", schema = "master")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long reservationId;

	@JoinColumn(name = "roomNumber", nullable = false, foreignKey = @ForeignKey(name = "FK_roomNumber"))
	private int roomNumber;
	@Min(value = 0)
	private int startDay;
	@Max(value = 64)
	private int endDay;

	public Reservation() {
	}

	public Reservation(int roomNumber, int startDay, int endDay) {
		super();
		this.roomNumber = roomNumber;
		this.startDay = startDay;
		this.endDay = endDay;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getStartDay() {
		return startDay;
	}

	public void setStartDay(int startDay) {
		this.startDay = startDay;
	}

	public int getEndDay() {
		return endDay;
	}

	public void setEndDay(int endDay) {
		this.endDay = endDay;
	}
}
