package com.bestdeals.model;

public enum DealTypeEnum {

	REBATE_3PLUS, PCT, REBATE;

	public static DealTypeEnum getEnum(String text) {
		for (DealTypeEnum type : DealTypeEnum.values()) {
			if (type.name().equalsIgnoreCase(text)) {
				return type;
			}
		}
		return null;
	}

}
