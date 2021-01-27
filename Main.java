

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;


import java.util.PriorityQueue;

class Main {


public static void insertItem(int number,PriorityQueue<Integer> lowers,PriorityQueue<Integer> highers){
  //if lowers is empty or number is less than lowers peek
  if(lowers.size()==0||number<lowers.peek()){
    lowers.add(number);
  }else{
    highers.add(number);
  }
}

public static void rebalance(PriorityQueue<Integer> lowers,PriorityQueue<Integer> highers){
//lets bigger heap = whichever heap is larger
  PriorityQueue<Integer> biggerHeap = lowers.size() > highers.size() ? lowers : highers;
  PriorityQueue<Integer> smallerHeap = lowers.size() > highers.size() ? highers : lowers;
  //Compare size of two heaps - want to be as close as possible to same size
  //if off by at least 2 then rebalance
  if(biggerHeap.size()-smallerHeap.size() >= 2){
    //poll = pulls off top element, removes, and returns it
    smallerHeap.add(biggerHeap.poll());
  }
}


//look at 2 heap sizes, if they are different sizes get the larger value in the bigger heap
public static double getMedian(PriorityQueue<Integer> lowers,PriorityQueue<Integer> highers){
    PriorityQueue<Integer> biggerHeap = lowers.size() > highers.size() ? lowers : highers;
    PriorityQueue<Integer> smallerHeap = lowers.size() > highers.size() ? highers : lowers;

    if(biggerHeap.size() == smallerHeap.size()){
      //if even size
      return (double)(biggerHeap.peek()+smallerHeap.peek())/2;
    }else{
      //if odd
      return biggerHeap.peek();
    }
}




public static double[] getMedians(int[] array){
		// for lower half of numbers
    //PriorityQueue normally store the lowest number at the top, we need to override the compare and multiply by -1 to make it a max heap
		PriorityQueue<Integer> lowers = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return -1 * o1.compareTo(o2); 
			};
		});

    PriorityQueue<Integer> highers = new PriorityQueue<Integer>();
    double[]medians = new double[array.length];

    for(int i = 0; i<array.length;i++){
      //get number out of the array
      int number = array[i];
      //insert number into the heap
      insertItem(number,lowers,highers);
      //rebalance heap - so each heap has half the numbers
      rebalance(lowers,highers);
      //get median
      medians[i]=getMedian(lowers,highers);
      System.out.println("my median is " + medians[i]);
      System.out.println("my lowers are :" + lowers.toString());
      System.out.println("my highers are :" + highers.toString());
    }
    return medians; 
  }

  public static void main(String[] args) {

    int[] testInt = {1,2,3,4,5,6,7,8};
    double[] testarr = new double[testInt.length];
    testarr = getMedians(testInt);

  }
}