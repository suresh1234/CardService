package com.cardservice.util;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.cardservice.pojo.CardInfo;
import com.cardservice.pojo.ErrorHolder;

@Service
public class Validator {
	/**
	 * Method is responsible for validating the input parameters.  
	 * @param cardInfo
	 * @return ErrorHolder
	 */
	public ErrorHolder validate(CardInfo cardInfo) {
		ErrorHolder holder = null;
		if(Objects.nonNull(cardInfo)) {
			holder = valideCommonElements(cardInfo);
			if(Objects.isNull(holder)) {
				holder = validateCardLength(cardInfo);
			}
			if(Objects.isNull(holder)) {
				holder = validateCardInput(cardInfo);
			}
			if(Objects.isNull(holder)) {
				holder = validateCardNo(cardInfo);
			}
		}
		
		return holder;
	}
	/**
	 * Method trims any spaces present in the the card number either in start/end or in between.
	 * @param cardNumber
	 * @return
	 */
	public static String getSpaceTrimmedCardNumber(String cardNumber) {
		return cardNumber.replaceAll("\\s+", "");
	}
	/**
	 * Method for null and empty check of string.
	 * @param s
	 * @return
	 */
	public static boolean isNullOrEmpty(String s) {
		if (s == null || s.trim().isEmpty()) {
			return true;
		}
		return false;
	}
	/**
	 * Method is responsible for validating the card number based on Luhn algorigthm.
	 * https://en.wikipedia.org/wiki/Luhn_algorithm
	 * 
	 * @param cardNumber
	 * @return boolean
	 */
	public boolean validateCardNoLunnAlgo(String  cardNumber) {
		int sum = 0;
		boolean alternate = false;
		for (int i = cardNumber.length() - 1; i >= 0; i--) {
			int n = Integer.parseInt(cardNumber.substring(i, i + 1));
			if (alternate) {
				n *= 2;
				if (n > 9) {
					n = (n % 10) + 1;
				}
			}
			sum += n;
			alternate = !alternate;
		}
		return (sum % 10 == 0);	
	}
	
	private ErrorHolder validateCardNo(CardInfo cardInfo) {
		if(!validateCardNoLunnAlgo(getSpaceTrimmedCardNumber(cardInfo.getCreditCard()))) {
			return new ErrorHolder().setErroMessage(Constants.CAR_NO_NOT_VALID).setStatus(false);
		}
		return null;
	}

	private ErrorHolder validateCardInput(CardInfo cardInfo) {
        boolean result = getSpaceTrimmedCardNumber(cardInfo.getCreditCard()).matches("[0-9]+");
        if(!result) {
			return new ErrorHolder().setErroMessage(Constants.CARD_NOT_NUMERIC).setStatus(false);
        }
		return null;
	}

	private ErrorHolder validateCardLength(CardInfo cardInfo) {
		String cardNo = getSpaceTrimmedCardNumber(cardInfo.getCreditCard());
		int length = cardNo.length();
		if(length > 19) {
			return new ErrorHolder().setErroMessage(Constants.CARD_LENGTH_ERROR).setStatus(false);

		}
		return null;
	}

	private ErrorHolder valideCommonElements(CardInfo cardInfo) {
		if (isNullOrEmpty(cardInfo.getCreditCard()) || isNullOrEmpty(cardInfo.getName())
				|| Objects.isNull(cardInfo.getLimit())) {
			return new ErrorHolder().setErroMessage(Constants.COMMON_FIELD_ERROR).setStatus(false);
		}
		return null;
	}
	
	

}
