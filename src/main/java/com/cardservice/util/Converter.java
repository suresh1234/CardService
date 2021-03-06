package com.cardservice.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.cardservice.entity.CardInfoEntity;
import com.cardservice.pojo.CardInfo;

@Service
public class Converter {
	/**
	 * Method converts DB entity to the BO object.
	 * @param listOfEntity
	 * @return List of CardInfo
	 */
	public List<CardInfo> convertEntityToBo(List<CardInfoEntity> listOfEntity) {
		List<CardInfo> result = new ArrayList<>();
		if (Objects.nonNull(listOfEntity)) {
			listOfEntity.forEach(t -> {
				CardInfo cardInfo = new CardInfo();
				cardInfo.setId(t.getId()).setName(t.getName()).setCreditCard(t.getCardNo()).setBalance(t.getBalance())
						.setLimit(t.getCardLimit());
				result.add(cardInfo);
			});
		}
		return result;
	}
	/**
	 * Method converts BO to DB entity.
	 * @param cardInfo
	 * @return CardInfoEntity
	 */
	public CardInfoEntity covertBoToEntity(CardInfo cardInfo) {
		CardInfoEntity entity = null;
		if (Objects.nonNull(cardInfo)) {
			entity = new CardInfoEntity();
			entity.setBalance(Objects.isNull(cardInfo.getBalance())?new BigDecimal(0):cardInfo.getBalance());
			entity.setCardLimit(cardInfo.getLimit());
			entity.setCardNo(cardInfo.getCreditCard().trim());
			entity.setName(cardInfo.getName());
		}
		return entity;

	}

}
