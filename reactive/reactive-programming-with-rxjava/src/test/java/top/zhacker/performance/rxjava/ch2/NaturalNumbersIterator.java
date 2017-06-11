package top.zhacker.performance.rxjava.ch2;

import java.math.BigInteger;
import java.util.Iterator;

class NaturalNumbersIterator implements Iterator<BigInteger> {

	private BigInteger current = BigInteger.ZERO;

	public boolean hasNext() {
		return true;
	}

	@Override
	public BigInteger next() {
		current = current.add(BigInteger.ONE);
		return current;
	}
}
