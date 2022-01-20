package com.cardservice.service;

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

import com.cardservice.entity.CardInfoEntity;
import com.cardservice.jparepo.CardRepository;
import com.cardservice.pojo.CardInfo;
import com.cardservice.util.Converter;
import com.cardservice.util.Validator;

@ExtendWith(MockitoExtension.class)
@MockitoSettings( strictness =  Strictness.LENIENT)
public class CardServiceImplTest {
	private CardInfo cardinfo;
	private List<CardInfoEntity> cardEntityList = new ArrayList<>();
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
		initEntity();
	}
	
	private  void initEntity() {
		CardInfoEntity cardEntity = new CardInfoEntity();
		cardEntity.setId(2l);
		cardEntity.setName("John Deo");
		cardEntity.setBalance(new BigDecimal(0));
		cardEntity.setCardLimit(new BigDecimal(5000));
		cardEntityList.add(cardEntity);
	}
	

	@Test
	public void testSave() {
		Mockito.when(cardRepository.save(Mockito.any())).thenReturn(null);
		Mockito.when(validator.validate(Mockito.any())).thenCallRealMethod();
		Mockito.when(converter.covertBoToEntity(Mockito.any())).thenCallRealMethod();
		cardService.save(cardinfo);
	}
	
	@Test
	public void testGetAll() {
		Mockito.when(cardRepository.findAll()).thenReturn(cardEntityList);
		Mockito.when(converter.convertEntityToBo(Mockito.any())).thenCallRealMethod();
		List<CardInfo> cardInfo =  cardService.getAll();
		assertEquals(cardInfo.get(0).getName(), cardEntityList.get(0).getName());
		assertEquals(cardInfo.get(0).getCreditCard(), cardEntityList.get(0).getCardNo());
		assertEquals(cardInfo.get(0).getBalance(), cardEntityList.get(0).getBalance());
	}

}
