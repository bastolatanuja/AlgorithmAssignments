package week4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class StraightLineLengthTest {

	@Test
	void test() {
		
		int num[][] = { {1,2}, {5,5}, {1,4}, {2,3}, {3,2}, {4,1}, {3,5} };
		
		ArrayList<ArrayList<int[]>> allCollinearPoints = StraightLineLength.findAllCollinearPoints(num);
		ArrayList<int[]> maxPointsInSameLine = StraightLineLength.findMaxPointsInALine(allCollinearPoints);
		double length = StraightLineLength.findLengthOfLine(maxPointsInSameLine);
		assertEquals(length,Math.sqrt(18));
		
	}

}