package week9AndFollowing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import week9AndFollowing.Algo.Location;

class AlgoTest {

	@Test
	void test1() throws NumberFormatException, IOException {
		ArrayList<Location> locationList = new ArrayList<Location>();
		File csvFile = new File("C:\\\\Users\\\\HP\\\\eclipse-workspace\\\\algorithmAssignment\\\\src\\\\week9AndFollowing\\\\location_details.csv");
		BufferedReader br = new BufferedReader(new FileReader(csvFile));
		String line = "";
		
		try {
		
			while((line = br.readLine())!=null) {
				
				String[] eachCellData = line.split(",");
				Location somewhere = new Location(eachCellData[0],Integer.parseInt(eachCellData[1]), eachCellData[2]);
				locationList.add(somewhere);
				
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		String shortestPath = Algo.dijikstra(locationList,"kathmandu","jhapa");
		assertEquals(shortestPath,"kathmandu --- 800 --- damak --- 400 --- jhapa");
	}
	
	@Test
	void test2() throws NumberFormatException, IOException {
		ArrayList<Location> locationList = new ArrayList<Location>();
		File csvFile = new File("C:\\\\\\\\Users\\\\\\\\HP\\\\\\\\eclipse-workspace\\\\\\\\algorithmAssignment\\\\\\\\src\\\\\\\\week9AndFollowing\\\\\\\\location_details.csv");
		BufferedReader br = new BufferedReader(new FileReader(csvFile));
		String line = "";
		
		try {
		
			while((line = br.readLine())!=null) {
				
				String[] eachCellData = line.split(",");
				Location somewhere = new Location(eachCellData[0],Integer.parseInt(eachCellData[1]), eachCellData[2]);
				locationList.add(somewhere);
				
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<String> allPaths = Algo.bfs(locationList,"jhapa","kathmandu");
		ArrayList<String> allPathsTest = new ArrayList<String>();
		allPathsTest.add("jhapa --- 1500 --- kathmandu");
		allPathsTest.add("jhapa --- 400 --- damak --- 800 --- kathmandu");
		//allPathsTest.add("jhapa --- 300 --- bhaktapur --- 50 --- pokhara --- 110 --- kathmandu");
		
		assertArrayEquals(allPaths.toArray(), allPathsTest.toArray());
		
	}
	
	
	

}