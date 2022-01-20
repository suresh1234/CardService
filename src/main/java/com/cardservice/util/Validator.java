package com.cardservice.util;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.cardservice.pojo.CardInfo;
import com.cardservice.pojo.ErrorHolder;

@Service
public class Validator {
	
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
	
	public static String getSpaceTrimmedCardNumber(String cardNumber) {
		return cardNumber.replaceAll("\\s+", "");
	}
	
	public static boolean isNullOrEmpty(String s) {
		if (s == null || s.trim().isEmpty()) {
			return true;
		}
		return false;
	}
	
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
			return new ErrorHolder().setErroMessage("Card number is  not valid").setStatus(false);
		}
		return null;
	}

	private ErrorHolder validateCardInput(CardInfo cardInfo) {
        boolean result = getSpaceTrimmedCardNumber(cardInfo.getCreditCard()).matches("[0-9]+");
        if(!result) {
			return new ErrorHolder().setErroMessage("Card number does not contain all  numeric value").setStatus(false);
        }
		return null;
	}

	private ErrorHolder validateCardLength(CardInfo cardInfo) {
		String cardNo = getSpaceTrimmedCardNumber(cardInfo.getCreditCard());
		int length = cardNo.length();
		if(!(length == 13 || length == 16 || length == 19)) {
			return new ErrorHolder().setErroMessage("Card number is not is correct length.").setStatus(false);

		}
		return null;
	}

	private ErrorHolder valideCommonElements(CardInfo cardInfo) {
		if (isNullOrEmpty(cardInfo.getCreditCard()) || isNullOrEmpty(cardInfo.getName())
				|| Objects.isNull(cardInfo.getLimit())) {
			return new ErrorHolder().setErroMessage("Either Card Number/Name/ Limit is not provided").setStatus(false);
		}
		return null;
	}
	
	

}