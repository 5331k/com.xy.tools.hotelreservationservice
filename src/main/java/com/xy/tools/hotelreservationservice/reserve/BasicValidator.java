package com.xy.tools.hotelreservationservice.reserve;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BasicValidator {

	private static final Logger logger = LogManager.getLogger(BasicValidator.class);

	private ValidatorFactory factory;
	private Validator validator;

	public BasicValidator() {
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	public boolean validateReserveEntity(ReserveEntity reserveEntity) {
		boolean validationSuccess = true;
		Set<ConstraintViolation<Object>> violations = validator.validate(reserveEntity);
		for (ConstraintViolation<Object> violation : violations) {
			validationSuccess = false;
			logger.error(violation.getMessage());
		}
		if (reserveEntity.getStartDay() > reserveEntity.getEndDay()) {
			validationSuccess = false;
			logger.error("Start day should always be less or equal to end day");
		}
		
		return validationSuccess;
	}

}
