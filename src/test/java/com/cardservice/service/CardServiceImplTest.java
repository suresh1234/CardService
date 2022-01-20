package com.cardservice.service;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.cardservice.jparepo.CardRepository;
import com.cardservice.pojo.CardInfo;
import com.cardservice.util.Converter;
import com.cardservice.util.Validator;

@ExtendWith(MockitoExtension.class)
@MockitoSettings( strictness =  Strictness.LENIENT)
public class CardServiceImplTest {
	private CardInfo cardinfo;
	@InjectMocks
	private CardService cardService;
	@Mock
	private CardRepository cardRepository;	
	@Mock
	private Converter converter;
	@Mock
	private Validator validator;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		cardinfo = new CardInfo();
		cardinfo.setCreditCard("4201254714918721").setName("Jean Smith").setLimit(new BigDecimal(500));
	}

	@Test
	public void testSave() {
		Mockito.when(cardRepository.save(Mockito.any())).thenReturn(null);
		Mockito.when(validator.validate(Mockito.any())).thenCallRealMethod();
		Mockito.when(converter.covertBoToEntity(Mockito.any())).thenCallRealMethod();
		cardService.save(cardinfo);
	}

}
