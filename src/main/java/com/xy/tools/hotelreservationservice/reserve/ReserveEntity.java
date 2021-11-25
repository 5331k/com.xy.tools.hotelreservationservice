package com.xy.tools.hotelreservationservice.reserve;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class ReserveEntity {

	public ReserveEntity(int startDay, int endDay) {
		this.startDay = startDay;
		this.endDay = endDay;
	}

	@Min(value = 0, message = "Reservation start day shout be greater or equal to 0")
	@Max(value = 364, message = "Reservation start day shout be less or equal to 364")
	private int startDay;

	@Min(value = 0, message = "Reservation end day shout be greater or equal to 0")
	@Max(value = 364, message = "Reservation end day shout be less or equal to 364")
	private int endDay;

	public int getStartDay() {
		return startDay;
	}

	public int getEndDay() {
		return endDay;
	}
}
