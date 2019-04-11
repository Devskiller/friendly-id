package com.devskiller.friendly_id.spi;

import com.devskiller.friendly_id.BigIntegerPairing;
import com.devskiller.friendly_id.ShiftingPairing;

public class ShiftingPairingProvider implements BigIntegerPairingProvider {

	@Override
	public BigIntegerPairing create() {
		return new ShiftingPairing();
	}
}
