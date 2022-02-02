package week3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SmallestPositiveIntegerTest {

	@Test
	void test1() {
		SmallestPositiveInteger obj = new SmallestPositiveInteger();
		int res = obj.findSmallest("1698");
		assertEquals(res,2);
	}
	@Test
	void test2() {
		SmallestPositiveInteger obj = new SmallestPositiveInteger();
		int res = obj.findSmallest("689345");
		assertEquals(res,1);
	}

}