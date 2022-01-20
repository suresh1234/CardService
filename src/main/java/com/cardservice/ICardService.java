package com.cardservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cardservice.pojo.CardInfo;
/**
 * Rest end point for card service interface.
 * 
 * @author mac_osx
 *
 */
@RestController
@RequestMapping("/cardservice")
public interface ICardService {
	
	@RequestMapping(value = "/addCard", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> addCard(CardInfo cardInfo);
	
	@RequestMapping(value = "/getAllCards", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getAllCards();

}
