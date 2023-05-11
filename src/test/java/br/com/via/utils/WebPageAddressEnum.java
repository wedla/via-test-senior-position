package br.com.via.utils;

public enum WebPageAddressEnum {
	HOME_PAGE("http://lojaebac.ebaconline.art.br/");
	
	private String url;
	
	private WebPageAddressEnum(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}
