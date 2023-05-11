package br.com.via.utils;

public enum ButtonNameEnum {
	BUY_BUTTON_LABEL("COMPRAR");

	private String label;
	
	private ButtonNameEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
