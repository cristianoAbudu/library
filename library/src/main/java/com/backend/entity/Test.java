package com.backend.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Test {
	public static void main(String[] args) {
		Integer in = 1;
		Integer in2 = Integer.valueOf("2");
		int intet = in + in2;



		Double dn = 1d;
		Double dn2 = Double.valueOf("2");
		double doudble = dn + dn2;

		BigInteger adn = new BigInteger("1234567890");
		BigInteger adn2 = BigInteger.valueOf(987654321);
		BigInteger adoudble = adn.add(adn2);

		BigDecimal badn = new BigDecimal("1.23");
		BigDecimal badn2 = BigDecimal.valueOf(4.56);
		BigDecimal badoudble = badn.multiply(badn2);

		Integer count = new Integer(10);

	}
}
