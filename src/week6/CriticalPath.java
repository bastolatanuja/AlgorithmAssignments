package week6;

import java.util.HashMap;


public class CriticalPath {


	
	//function to find all the visited points as given in the input array
	static int[][] findVisitedPoints(int arr[][]) {
		
		//creating a matrix of 10*10 size for numbers from 0-9 as both row and columns
		int visited[][]=new int[10][10];
		
		//initializing each cell with 0 so that later if a cell value is changed to 1 it means it is visited
		for(int i=0;i<visited.length;i++) {
			for(int j=0;j<visited[0].length;j++) {
				visited[i][j]=0;
			}
		}
		
		
		//looping through the input array to get the points and marking those point numbers as visited in the
		//visited matrix
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[0].length;j++) {
				if(j>0) {
					visited[arr[i][j-1]][arr[i][j]]=1;
					visited[arr[i][j]][arr[i][j-1]]=1;
				}
			}
		}

		
		
		
	
		return visited;
		
	}
	
	//function which returns the actual critical points required
	static HashMap<Integer, Integer> findCriticalPaths(int visited[][]) {
		
		//creating hashmap to dynamically store the critical points
		HashMap<Integer, Integer> criticalPoints = new HashMap<Integer, Integer>();
		
		//since the if condition after the 2nd for loop needs the j value but it is outside the scope 
		//we create a variable to store the value of j 
		int temp=0;
		
		//while looping through the visited matrix if there is a row which has only 1 occurance of 1
		//i:e a row which is visited only once then it means there is a relationship with only those related
		//i and j points implying that the i & j value are the very critical points we are looking for
		for(int i=0;i<visited.length;i++) {
			int count=0;
			for(int j=0;j<visited[0].length;j++) {
				if(visited[i][j]==1) {
					count++;
					temp=j;
				}
			}
			if(count==1) {
				criticalPoints.put(temp, i);
			}
		}
		return criticalPoints;
	}
	
	public static void main(String[] args) {
		
		int visited[][]=findVisitedPoints(new int[][] {{0,1},{1,6},{0,3},{0,2},{2,3},{2,5}});
		HashMap<Integer, Integer> criticalPoints = findCriticalPaths(visited);
		
		criticalPoints.entrySet().forEach(entry -> {
		    System.out.println("{"+entry.getKey() + ", " + entry.getValue()+"}");
		});
	}
}