package com.xy.tools.hotelreservationservice.exception;

public class NumberOfRoomsExceededException extends RuntimeException {

	private static final long serialVersionUID = 1L;

    public NumberOfRoomsExceededException(String message) {
	super(message);
    }
    
    public NumberOfRoomsExceededException(String message, Throwable cause) {
	super(message, cause);
    }
}
