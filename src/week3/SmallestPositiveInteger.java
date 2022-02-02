package week3;

public class SmallestPositiveInteger {
	
	//creating an array of all possible positive integers
	int numbers[] =  {1,2,3,4,5,6,7,8,9};
	
	//function for finding smallest positive number
	public  int findSmallest(String num) {
		
		//splitting the string to an array
		String[] a = num.split("");
		
		//replacing the numbers[] array with 0 in the places with the numbers present in the string array
		for(int i = 0 ; i<a.length; i++) {
			int n = Integer.parseInt(a[i]);
			numbers[n-1]=0;
			
		}
		
		//looping through the number[] array and printing the first non zero element which is the answer
		for(int i=0;i<numbers.length;i++) {
			if(numbers[i]>0) {
				return numbers[i];
			
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		SmallestPositiveInteger obj = new SmallestPositiveInteger();
		SmallestPositiveInteger object = new SmallestPositiveInteger();
		int res = obj.findSmallest("1689");
		int results = object.findSmallest("689345");
		System.out.println(res);
		System.out.println(results);
	}
}