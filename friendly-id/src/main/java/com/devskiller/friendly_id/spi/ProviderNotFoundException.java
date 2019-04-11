package com.devskiller.friendly_id.spi;

public class ProviderNotFoundException  extends RuntimeException{

	public ProviderNotFoundException() {
		super();
	}

	public ProviderNotFoundException(String message) {
		super(message);
	}
}
