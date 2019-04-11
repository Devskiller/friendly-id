package com.devskiller.friendly_id;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import com.devskiller.friendly_id.spi.BigIntegerPairingProvider;
import com.devskiller.friendly_id.spi.ProviderNotFoundException;

public class Pairing {

	private static final String DEFAULT_PROVIDER = "com.devskiller.friendly_id.spi.ShiftingPairingProvider";

	//All providers
	public static List<BigIntegerPairingProvider> providers() {
		List<BigIntegerPairingProvider> services = new ArrayList<>();
		ServiceLoader<BigIntegerPairingProvider> loader = ServiceLoader.load(BigIntegerPairingProvider.class);
		loader.forEach(services::add);
		return services;
	}

	//Default provider
	public static BigIntegerPairingProvider provider() {
		return provider(DEFAULT_PROVIDER);
	}

	//provider by name
	public static BigIntegerPairingProvider provider(String providerName) {
		ServiceLoader<BigIntegerPairingProvider> loader = ServiceLoader.load(BigIntegerPairingProvider.class);
		for (BigIntegerPairingProvider provider : loader) {
			if (providerName.equals(provider.getClass().getName())) {
				return provider;
			}
		}
		throw new ProviderNotFoundException("Big IntegerPairing provider " + providerName + " not found");
	}
}
