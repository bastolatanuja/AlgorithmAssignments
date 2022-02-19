package week9AndFollowing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;

public class Algo {
	
	//Creating a class to store a route i:e source distance and destination
	static class Location{
		String place;
		int distance;
		String from;
		
		public Location(String place,int distance,String from) {
			this.place = place;
			this.distance = distance;
			this.from = from;
		}
	}
	
	//Assuming some routes may be disconnected we make a hashset to store connected routes only
	static HashSet<String> connectedRoutes = new HashSet<String>();
	
	//using dijikstra algorithm to find shortest path between source and destination
	public static String dijikstra(ArrayList<Location> list,String source,String destination) {
		
		//if given same destination and source we return from same place
		if(source.endsWith(destination)) {
			return "You are already at " + source;
		}
		
		//creating hasmap to store visited status of each place and another to store distance from source
		HashMap<String,Boolean> visited = new HashMap<String,Boolean>();
		HashMap<String,Integer> distance = new HashMap<String,Integer>();
		HashMap<String,String> path = new HashMap<String,String>();
		
		
		//calling bfs first to identify all the connected places
		bfs(list, source, destination);
		
		//looping through connected routes and initializing each of them as unvisited and 
		//distance from source as infinity or in case of integer , max value
		 Iterator<String> hitr = connectedRoutes.iterator();
		 while(hitr.hasNext()) {
			 String temp = hitr.next();
			 
			 	visited.put(temp, false);
	    	  distance.put(temp,Integer.MAX_VALUE);
			 
		 }
		 
		 //handling case when only one place is entered and no rout is avaialbe
		   if(connectedRoutes.size()<=1) {
			   return "no routes available for your search";
		   }
		   
		   //putting distance of source from source as zero
	         distance.put(source, 0);
		
	    //creating a variable to store the closest place in each iteration
	    //setting first time as true 
	    String closestPlace="";
		boolean firsttime = true;
		
		//according to dijikstra algorithm the loop must run untill all the nodes are visited
		//in our case when in the visited hashmap all keys are marked true
		//values().stream().distinct().count() gives the distinct number of values
		//when all the values are false we still need to enter the true hence firsttime is marked true
		//after the loop gets started one by one all the places will be visited i:e visited hashmap values will be true
		//and since firsttime is already false the loop will stop once all the visited hashmap keys are true 
		while(visited.values().stream().distinct().count()>1 || firsttime) {
			//firsttime marked false after the loop is entered for the first time
			firsttime=false;
			
			//calling the function to get the closest unvisited place
			 closestPlace = findClosestPlace(distance, visited);
			ListIterator<Location> liter= list.listIterator();
			
			//looping to find the neighbour 
			while(liter.hasNext()) {
				 Location temp = liter.next();
				 	//when neighbour found
					 if(temp.place.equals(closestPlace) && connectedRoutes.contains(temp.from) && connectedRoutes.contains(temp.place)) {
						 //get the new distance which is the previous distance plus the distance with the neighbour
						 int newDistance = distance.get(closestPlace)+temp.distance;
						 //if the new distance is less it means this route is shorter so we update distance and add to path
						 if(newDistance<distance.get(temp.from)) {
							 distance.put(temp.from,newDistance);
							 path.put(temp.from, closestPlace); 
						 } 
				 }
				
					 //same in case of return route
					 if(temp.from.equals(closestPlace) && connectedRoutes.contains(temp.from) && connectedRoutes.contains(temp.place)) {
						 int newDistance = distance.get(closestPlace)+temp.distance;
						 if(newDistance<distance.get(temp.place)) {
							 distance.put(temp.place,newDistance);
							 path.put(temp.place, closestPlace); 
						 } 
					 }
			}
			//at the end when all of its neighbours are visited we mark it as visited node
			visited.put(closestPlace, true);	
		}
		//calling find path function to return the path as string 
		String res = findPath(source, destination, path,list);
		return res;
		
	}
	//find path function to return the actual route information between paces with distance
	public static String findPath(String source,String destination,HashMap<String,String> path,ArrayList<Location> list) {
	
	 
	
		String tempStr = destination;
		String res =  destination;
		int length =0;
		//toop to find the distance between the consequetive places in the list path
		while(!tempStr.equals(source)) {
			
			ListIterator<Location> litr= list.listIterator();
			 while(litr.hasNext()){
		    	  Location temp = litr.next();
		    	 
		    	 //if route found, get their distance 
		    	 if(temp.from.equals(tempStr) && temp.place.equals(path.get(tempStr))) {
		    		 length = temp.distance;
		    	 }
		    	  
		      }
			//keep adding it untill the source is found
			tempStr = path.get(tempStr);
			if(!tempStr.equals(source)) {
				
				res = tempStr + " --- "+length +" --- " + res;
			}
		}
		// finally add source to the begining of the resulting string
		res = source + " --- "+length+ " --- " + res;
		return res;
		
	}
	
	//function to find the closese neighbour
	public static String findClosestPlace(HashMap<String,Integer> distance,HashMap<String,Boolean> visited) {
		int leastDistance = Integer.MAX_VALUE;
		String closestPlace = "";
	
		//looping through the distance hashmap to get the smallest distance
		for(Map.Entry<String,Integer> temp : distance.entrySet()) {
			//for every distance checking weather it has been already visited or not
			if(!visited.get(temp.getKey()) && connectedRoutes.contains(temp.getKey())) {
				//if not visited update the distance to compare if smallest or not
				if(leastDistance>temp.getValue()) {
					//if smallest update the closest place variable 
					leastDistance = temp.getValue();
					closestPlace = temp.getKey();
				}
			}
		}
		
		return closestPlace;
	}	
	
  //bfs algorithm to find all possible paths between source and destination
	public static ArrayList<String> bfs(ArrayList<Location> list,String source,String destination) {
		//list to store all the routes
		ArrayList<String> finalPaths = new ArrayList<String>();
		//if source and destination is same place then no need to move further so we return
		if(source.equals(destination)) {
			finalPaths.add("You are already at " +source);
		}
		//list to store path in each iteration
		ArrayList<String> path = new ArrayList<String>();
		//queue to keep adding and removing the routes we will have calculated so far
		Queue<ArrayList<String>> queue = new LinkedList<ArrayList<String>>();
		//another list to hold the path in form of list
		ArrayList<ArrayList<String>> allPaths = new ArrayList<ArrayList<String>>();
		int flag= 0 ;
		
		//starting from source we directly add source to path and path to queue
		path.add(source);
		queue.offer(path);
		
		//accourding to bfs algorithm the loop has to run untill the queue is empty
		//queue holds the possible paths and once we find destinatio we pop in and add it to the allpaths list to store
		//which means by the time when queue is empty we will have found all the possible routes 
		//hence we stop 
		while(!queue.isEmpty()) {
			//we get the first path in the queue
			path = queue.poll();
			String last = path.get(path.size()-1);
			
			//if the last string of the queue is destination then we will have found our one route
			//so we add it to the all paths list
			if(last.equals(destination)){
				allPaths.add(path);
			}
			//creating list to get all its neighbours since we need to find all possible routes
			ArrayList<String> allNeighbours = new ArrayList<String>();
			ListIterator<Location> litr= list.listIterator();
			while(litr.hasNext()){
		    	  Location temp = litr.next();
		    	  if(temp.from.equals(last)) {
		    		  flag=1;
		    		  allNeighbours.add(temp.place);
		    	  }
		    	  
			}
			//for every place we find its unvisited neighbour
			
			for(int i=0;i<allNeighbours.size();i++) {
				//if found it means we have to update the path so far so we create the path and push it to the queue
				if(isNotVisited(allNeighbours.get(i), path)) {
					ArrayList<String> newPath = new ArrayList<String>(path);
					newPath.add(allNeighbours.get(i));
					queue.offer(newPath);
				}
			}
		}
		 
		//now we have all possible routes in allpaths in the form of list with source in the begining and destination
		//at last and all connecting places in between since arraylist is an ordered collection
		//now we need to get the distance between each consequetive places and make a list of route string
		for(int i=0;i<allPaths.size();i++) {
			String s = "";
			   ArrayList<String> listr= allPaths.get(i);
			   ListIterator<String> tempitr = listr.listIterator();
			   String prev = "";
			   while(tempitr.hasNext()) {
				   String temp = tempitr.next();
				   
				   if(prev!="") {
					   String distance = "";
					   ListIterator<Location> li = list.listIterator();
					   while(li.hasNext()) {
						   Location templocation = li.next();
						   
						   if(templocation.from.equals(prev) && templocation.place.equals(temp)) {
							   distance = ""+templocation.distance;
						   }
					   }
					   s+= " --- "+distance+" --- ";
				   }
				   s+=temp ;
				   	connectedRoutes.add(temp);
					   prev = temp; 
				   
			   }
			   //at the end of each loop we will have found a path in string form with places and distance between each of them and we finally return
			   finalPaths.add(s);
		   }
		return(finalPaths);
	   
	     
	}

	//function which checks if a place is already used in the path 
	public static boolean isNotVisited(String place,ArrayList<String> path) {
		
		for(int i=0;i<path.size();i++) {
			if(path.get(i).equals(place)) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		
		ArrayList<Location> locationList = new ArrayList<Location>();
		File csvFile = new File("C:\\Users\\HP\\eclipse-workspace\\algorithmAssignment\\src\\week9AndFollowing\\location_details.csv");
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
		
	 

	     
	      
		String shortestPath = dijikstra(locationList,"kathmandu","jhapa");
		
		System.out.println("Shortest Path ");
		System.out.println(shortestPath);
		
		ArrayList<String> allPaths = bfs(locationList,"jhapa","kathmandu");
		System.out.println("All Possible Paths ");
		for(int i=0;i<allPaths.size();i++) {
			System.out.println(allPaths.get(i));
		}
		
	}
	
	}