package week2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LinkedListSumPositiveTest {

	@Test
	void test1() {
		int k=3;
		
		List arr[]= new List[k];
		
		arr[0] = new List(1);
		arr[0].next = new List(6);
		arr[0].next.next = new List(-10);
		
		arr[1] = new List(5);
		arr[1].next = new List(3);
		arr[1].next.next = new List(9);
		
		arr[2] = new List(7);
		arr[2].next = new List(-9);
		arr[2].next.next = new List(-3);
		
		arr[0]=LinkedListSumPositive.merge(arr, k);
        arr[0]=LinkedListSumPositive.sort(arr[0]);
        String result=LinkedListSumPositive.display(arr[0]);
        assertEquals(result, "9 7 6 5 3 1 -3 -9 -10 ");
        

	}
	@Test
	void test2() {
		
		int k=3;
		
		List arr[]= new List[k];
		
		arr[0] = new List(5);
		arr[0].next = new List(7);
		arr[0].next.next = new List(8);
		arr[0].next.next.next = new List(9);
		
		arr[1] = new List(1);
		arr[1].next = new List(2);
		arr[1].next.next = new List(3);
		arr[1].next.next.next = new List(6);
		
		arr[2] = new List(-5);
		arr[2].next = new List(-10);
		arr[2].next.next = new List(10);
		arr[2].next.next.next = new List(11);
		
		arr[0]=LinkedListSumPositive.merge(arr, k);
        arr[0]=LinkedListSumPositive.sort(arr[0]);
        String result=LinkedListSumPositive.display(arr[0]);
        assertEquals(result, "11 10 9 8 7 6 5 3 2 1 -5 -10 ");
	
		
		
	}


}