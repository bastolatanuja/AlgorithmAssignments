package week1;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ExpressionOperatorsTest {

	@Test
	void test1() {
		ExpressionOperators obj = new ExpressionOperators(15,new int[]{3,4,3});
		assertEquals(obj.output,"3+4*3 3*4+3 ");
		
	}
	@Test
	void test2() {
		ExpressionOperators obj = new ExpressionOperators(6,new int[]{1,2,3});
		assertEquals(obj.output,"1+2+3 1*2*3 ");
		
	}
	@Test
	void test3() {
		ExpressionOperators obj = new ExpressionOperators(20,new int[]{7,2});
		assertEquals(obj.output,"There cannot be any combination of +,-,* with input array : [7, 2] to reach target : 20");
		
	}

}