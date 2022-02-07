package week4;


import java.util.ArrayList;
import java.util.Collections;
public class StraightLineLength {
	
	//Function to find all collinear points
		public static ArrayList<ArrayList<int[]>> findAllCollinearPoints(int arr[][]) {
			
			if(arr.length<2) {
				System.out.println("Only one point is not enought to find length");
				return null;
			}
			
			//an arraylist to save all the groups of co-ordinates that are co-linnear
			ArrayList<ArrayList<int[]>> sameline = new ArrayList<ArrayList<int[]>>();
			
			//looping through the input array to get first point
			for(int i=0;i<arr.length;i++) {
				//looping through the array to get second point
				for(int j=i+1;j<arr.length;j++) {
					//creating an inner array list to locally store points that lie on the same line
					ArrayList<int[]> points =new ArrayList<int []>();
					//since the first two points will always fall in the same line adding them into the 
					//temporary arraylist
					points.add(arr[i]);
					points.add(arr[j]);
					//looping through the array to get third element
					for(int k=j+1;k<arr.length;k++) {
						
						//creating variables for ease of use
						int x1=arr[i][0];
						int y1=arr[i][1];
						
						int x2=arr[j][0];
						int y2=arr[j][1];
						
						int x = arr[k][0];
						int y = arr[k][1];
						
						//points are collinear if the satisfy the equation of straight line i:e
						//
						//      (y-y1)=m(x-x1)
						//
						//      m= (y2-y1)/(x2-x1)
						//since m can be infinity in case of x2-x1=0
						//so we avoid infinity and cross multiply while putting in the equation of straight line
						//so the final equation becomes
						//
						//		(y-y1)(x2-x1) = (y2-y1)(x-x1)
						
						
						//if the points satisfy the equation then it can be said that they are collinear
						//then we add the point to the colinnear points arraylist
						if( (y-y1)*(x2-x1) ==  (y2-y1)*(x-x1)  ) {
							points.add(arr[k]);	
						}	
					}
					//by the end of the loop we will have a list of points which fall under the same straight
					//line formed by using the first two points 
					//now we add it to the outer arraylist so that we dont loose our progress
					//and when the loop restarts the new inner arraylist will be started 
					sameline.add(points);
				}
				
			}
			//finally we return the complete arraylist of points passing through same straight line
			return sameline;
	}

	//function to identify the line which has maximum number of points in it
	public static ArrayList<int[]> findMaxPointsInALine(ArrayList<ArrayList<int[]>> pointsInSameLine) {
		if(pointsInSameLine==null) {
			return null;
		}
			int max=0;
			//looping through the saved arraylist of points to find maximum number of points
			for(int i=0;i<pointsInSameLine.size();i++) {
				int count = pointsInSameLine.get(i).size();
				if (count>max) {
					max=count;
				}
			}
			
			//looping again to pinpoint the line 
			for(int i=0;i<pointsInSameLine.size();i++) {
				if (pointsInSameLine.get(i).size()==max) {
					//return the maximim no of points which are collinear
					return pointsInSameLine.get(i);
				}
			}
			return null;
			
	}

	//function to find the length of the straightline
	public  static double findLengthOfLine(ArrayList<int[]> points){
		if(points == null) {
			return 0;
		}
		
		//list to store all the possible length using the collinear points
		ArrayList<Double> lengths = new ArrayList<Double>();
		
		//looping to find the length of a line using every other two co-ordinates saved in the arraylist 
		for(int i=0;i<points.size();i++) {
			for(int j=i+1;j<points.size();j++) {
				// creating variables for ease of use
				int x2=points.get(j)[0];
				int y2=points.get(j)[1];
				
				int x1=points.get(i)[0];
				int y1=points.get(i)[1];
				
				//using distance formula to find the length
				double distance = Math.sqrt(  (Math.pow((x2-x1), 2))+ (Math.pow((y2-y1), 2))  );
				lengths.add(distance);
			}
			
				
			
		}
		//finally extracting the max length
		double maxLength = Collections.max(lengths);
		return maxLength;
	}


		
		public static void main(String[] args) {
			int num[][] = { {1,2},{5,5} ,{1,4}, {2,3}, {3,2}, {4,1}, {3,5} };
			
			ArrayList<ArrayList<int[]>> allCollinearPoints = findAllCollinearPoints(num);
			ArrayList<int[]> maxPointsInSameLine = findMaxPointsInALine(allCollinearPoints);
			double length = findLengthOfLine(maxPointsInSameLine);
			System.out.println(Math.round(length*1000.0)/1000.0 );
			
		}
	}