package com.cardservice.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.cardservice.ICardService;
import com.cardservice.pojo.CardInfo;
import com.cardservice.pojo.ErrorHolder;
import com.cardservice.service.CardService;

/**
 * Implementation class for the rest service.
 * 
 * @author mac_osx
 *
 */

@Service
public class CardServiceRestImpl implements ICardService{
	
	@Autowired
	private CardService cardService;
	/**
	 * Method is  for adding credit card into the system.
	 */
	@Override
	public ResponseEntity<?> addCard(@RequestBody CardInfo cardInfo) {
		ErrorHolder erroHolder = cardService.save(cardInfo);
		if(Objects.isNull(erroHolder)) {
			return new ResponseEntity<>(cardInfo, HttpStatus.OK);
		}
		return new ResponseEntity<>(erroHolder, HttpStatus.BAD_REQUEST);
		
	}
	/**
	 * Method for getting all  the cards present in the system.
	 */
	@Override
	public ResponseEntity<?> getAllCards() {
		return new ResponseEntity<>(cardService.getAll(), HttpStatus.OK);
		
	}

}
