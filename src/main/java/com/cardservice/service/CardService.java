package com.cardservice.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardservice.jparepo.CardRepository;
import com.cardservice.pojo.CardInfo;
import com.cardservice.pojo.ErrorHolder;
import com.cardservice.util.Converter;
import com.cardservice.util.Validator;

@Service
public class CardService {
	@Autowired
	private CardRepository cardRepository;
	@Autowired
	private Converter converter;
	@Autowired
	private Validator validator;
	
	public ErrorHolder save(CardInfo cardInfo) {
		ErrorHolder erroHolder = validator.validate(cardInfo);
		 if(Objects.isNull(erroHolder)) {
			 cardRepository.save(converter.covertBoToEntity(cardInfo)); 
		 }
		return erroHolder;
	}
	
	public List<CardInfo> getAll(){
		return converter.convertEntityToBo(cardRepository.findAll());
	}
	

}
