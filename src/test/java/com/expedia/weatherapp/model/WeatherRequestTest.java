package com.expedia.weatherapp.model;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.Assert.assertEquals;


/**
 * JUnit test cases for Weather Request, covering all the request validations.
 * Created by Nilesh Sahni on 08/22/2014.
 */
public class WeatherRequestTest {

    private static Validator validator;
    private static final String NULL_MSG = "may not be null";
    private static final String RANGE_MSG = "must be between 10000 and 99999";

    private static final Integer ZIP_CODE_VALID = 12345;
    private static final Integer ZIP_CODE_RANGE_1 = 123;
    private static final Integer ZIP_CODE_RANGE_2 = 123456;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        WeatherRequestTest.validator = factory.getValidator();
    }

    /**
     * Valid Zip Code validation.
     * @throws Exception
     */
    @Test
    public void testZipCodeIsValidl() throws Exception {
        WeatherRequest request = new WeatherRequest();
        request.setZipCode(WeatherRequestTest.ZIP_CODE_VALID);
        Set<ConstraintViolation<WeatherRequest>> constraintViolations =
                validator.validate( request );

        assertEquals(0, constraintViolations.size());
    }

    /**
     * Null Zip Code validation.
     * @throws Exception
     */
    @Test
    public void testZipCodeIsNull() throws Exception {
        WeatherRequest request = new WeatherRequest();
        Set<ConstraintViolation<WeatherRequest>> constraintViolations =
                validator.validate( request );
        assertEquals(1, constraintViolations.size());
        assertEquals(WeatherRequestTest.NULL_MSG, constraintViolations.iterator().next().getMessage());
    }

    /**
     * Zip Code out of range validation.
     * @throws Exception
     */
    @Test
    public void testZipCodeRange() throws Exception {
        WeatherRequest request = new WeatherRequest();
        request.setZipCode(WeatherRequestTest.ZIP_CODE_RANGE_1);
        Set<ConstraintViolation<WeatherRequest>> constraintViolations =
                validator.validate( request );
        assertEquals(1, constraintViolations.size());
        assertEquals(WeatherRequestTest.RANGE_MSG, constraintViolations.iterator().next().getMessage());
    }

    /**
     * Zip Code out of range and no. of digits validation.
     * @throws Exception
     */
    @Test
    public void testZipCodeRangeDigits() throws Exception {
        WeatherRequest request = new WeatherRequest();
        request.setZipCode(WeatherRequestTest.ZIP_CODE_RANGE_2);
        Set<ConstraintViolation<WeatherRequest>> constraintViolations =
                validator.validate( request );
        assertEquals(2, constraintViolations.size());
    }
}