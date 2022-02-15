package week8;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NumberOfAtomsTest {

	@Test
	void test1() {
		NumberOfAtoms obj= new NumberOfAtoms();
		String res = obj.getFormula("Mg3(OH2)2");
		assertEquals(res,"H4Mg3O2");
	}

	@Test
	void test2() {
		NumberOfAtoms obj= new NumberOfAtoms();
		String res = obj.getFormula("Mg(OH)2");
		assertEquals(res,"H2MgO2");
	}
	
	@Test
	void test3() {
		NumberOfAtoms obj= new NumberOfAtoms();
		String res = obj.getFormula("H2O");
		assertEquals(res,"H2O");
	}


}