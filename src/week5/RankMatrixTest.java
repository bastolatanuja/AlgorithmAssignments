package week5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RankMatrixTest {

	@Test
	void test1() {
		int arr[][] = new int[][]{{20,-21,14},{-19,4,19},{22,-47,24},{-19,4,19}};
		
		int sorted[] = RankMatrix.sortMartix(arr);
		
		int rankMat[][]=RankMatrix.assignRank(arr, sorted);
		assertArrayEquals(rankMat,new int[][] {{4,2,3},{1,3,4},{5,1,6},{1,3,4}});
	}
	
	@Test
	void test2() {
		int arr[][] = new int[][]{{7,7},{7,7}};
		
		int sorted[] = RankMatrix.sortMartix(arr);
		
		int rankMat[][]=RankMatrix.assignRank(arr, sorted);
		assertArrayEquals(rankMat,new int[][] {{1,1},{1,1}});
	}

}