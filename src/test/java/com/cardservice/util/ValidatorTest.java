package com.cardservice.util;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cardservice.pojo.CardInfo;
import com.cardservice.pojo.ErrorHolder;


public class ValidatorTest {
	
	private Validator validator = new Validator();
	
	private CardInfo cardinfo;
	
	@BeforeEach
	public void setup() {
		cardinfo = new CardInfo();
		cardinfo.setCreditCard("4201254714918721").setName("Jean Smith").setLimit(new BigDecimal(500));
	}
	
	@Test
	public void testValidInputs() {
		ErrorHolder errorHolder = validator.validate(cardinfo);
		assertNull(errorHolder);
	}
	
	@Test
	public void testNameIsNull() {
		cardinfo = cardinfo.setName("");
		ErrorHolder errorHolder = validator.validate(cardinfo);
		assertFalse(errorHolder.getStatus());
		assertEquals(errorHolder.getErroMessage(), Constants.COMMON_FIELD_ERROR);
	}
	@Test
	public void testCardNoisNull() {
		cardinfo = cardinfo.setCreditCard(null);
		ErrorHolder errorHolder = validator.validate(cardinfo);
		assertFalse(errorHolder.getStatus());
		assertEquals(errorHolder.getErroMessage(), Constants.COMMON_FIELD_ERROR);
	}
	
	@Test
	public void testLimitIsNull() {
		cardinfo = cardinfo.setLimit(null);
		ErrorHolder errorHolder = validator.validate(cardinfo);
		assertFalse(errorHolder.getStatus());
		assertEquals(errorHolder.getErroMessage(), Constants.COMMON_FIELD_ERROR);
	}
	
	@Test
	public void testCardNoisNonNumeric() {
		cardinfo = cardinfo.setCreditCard("899900huh89898");
		ErrorHolder errorHolder = validator.validate(cardinfo);
		assertFalse(errorHolder.getStatus());
		assertEquals(errorHolder.getErroMessage(), Constants.CARD_NOT_NUMERIC);
	}
	
	@Test
	public void testCardNoGreaterThen19() {
		cardinfo = cardinfo.setCreditCard("8978767656545367876898");
		ErrorHolder errorHolder = validator.validate(cardinfo);
		assertFalse(errorHolder.getStatus());
		assertEquals(errorHolder.getErroMessage(), Constants.CARD_LENGTH_ERROR);
	}
	
	@Test
	public void testCardNoIsInvalid() {
		cardinfo = cardinfo.setCreditCard("4201254714918722");
		ErrorHolder errorHolder = validator.validate(cardinfo);
		assertFalse(errorHolder.getStatus());
		assertEquals(errorHolder.getErroMessage(), Constants.CAR_NO_NOT_VALID);
	}


}
