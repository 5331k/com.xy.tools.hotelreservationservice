package com.xy.tools.hotelreservationservice.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xy.tools.hotelreservationservice.process.ReservationProcessor;
import com.xy.tools.hotelreservationservice.reserve.ReserveEntity;

@RestController
@RequestMapping("/hotelxy")
public class ReservationController {

	private static final Logger logger = LogManager.getLogger(ReservationController.class);

	private ReservationProcessor resarvationProcessor = new ReservationProcessor();

	@PutMapping(value = "createrooms")
	public ResponseEntity<String> createRooms(@RequestParam(name = "rooms") int totalRooms) {
		boolean result = resarvationProcessor.createNewRooms(totalRooms);
		if (result)
			return new ResponseEntity<>("Rooms successfully created!", HttpStatus.OK);
		else
			return new ResponseEntity<>("Rooms not created, either limit exceeded or something went wrong",
					HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping(value = "reserverooms")
	public ResponseEntity<String> reserve(@RequestParam(name = "startDay") int startDay,
			@RequestParam(name = "endDay") int endDay) {
		boolean result = resarvationProcessor.reserveRoom(new ReserveEntity(startDay, endDay));
		if (result)
			return new ResponseEntity<>("Reservation request accepted!", HttpStatus.OK);
		else
			return new ResponseEntity<>("Reservation request declined", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
