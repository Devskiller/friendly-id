[![Build Status](https://travis-ci.org/Devskiller/friendly-id.svg?branch=master)](https://travis-ci.org/Devskiller/friendly-id)

friendly ID
==

Library to convert uuid to url friendly IDs basing on base62
--

Usage
--

	Url62.create();
	// 7NLCAyd6sKR7kDHxgAWFPG

	Url62.decode("7NLCAyd6sKR7kDHxgAWFPG");
	// c3587ec5-0976-497f-8374-61e0c2ea3da5

	Url62.encode(UUID.fromString("c3587ec5-0976-497f-8374-61e0c2ea3da5"));
	// 7NLCAyd6sKR7kDHxgAWFPG

Note
--
	
* *Id* `00cafe` is equal to `cafe` - leading zeros are ignored.
* *UUID* is a 128-bit number, so *id* also can store only 128-bit number