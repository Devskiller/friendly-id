package com.devskiller.friendly_id;

import com.devskiller.friendly_id.spi.BigIntegerPairingProvider;

public class ShiftingPairingProvider implements BigIntegerPairingProvider {

	@Override
	public BigIntegerPairing create() {
		return new ElegantPairing();
	}
}
