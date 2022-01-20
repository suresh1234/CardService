package com.cardservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cardservice.impl.CardServiceRestImpl;
import com.cardservice.pojo.CardInfo;
import com.cardservice.pojo.ErrorHolder;
import com.cardservice.service.CardService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings( strictness =  Strictness.LENIENT)
public class CardServiceRestImplTest {
	private CardInfo cardinfo;
	private List<CardInfo> listOfCardInfo = new ArrayList<>();
	@InjectMocks
	private CardServiceRestImpl cardRestImpl;	
	@Mock
	private CardService cardService;	
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		cardinfo = new CardInfo();
		cardinfo.setCreditCard("4201254714918721").setName("Jean Smith").setLimit(new BigDecimal(500));
		listOfCardInfo.add(cardinfo);
	}
	
	@Test
	public void testPostValidRequest() {
		Mockito.when(cardService.save(cardinfo)).thenReturn(null);
		ResponseEntity<?> response =  cardRestImpl.addCard(cardinfo);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		
	}
	
	@Test
	public void testPostInValidRequest() {
		Mockito.when(cardService.save(cardinfo))
				.thenReturn(new ErrorHolder().setStatus(false).setErroMessage("Error"));
		ResponseEntity<?> response = cardRestImpl.addCard(cardinfo);
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);

	}
	
	@Test
	public void testGetMethod() {
		Mockito.when(cardService.getAll()).thenReturn(listOfCardInfo);
		ResponseEntity<?> response = cardRestImpl.getAllCards();
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		@SuppressWarnings("unchecked")
		List<CardInfo>  cards = (List<CardInfo>) response.getBody();
		assertEquals(cards.get(0).getName(), listOfCardInfo.get(0).getName());
		assertEquals(cards.get(0).getCreditCard(), listOfCardInfo.get(0).getCreditCard());
	}

}
