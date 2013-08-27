package com.limeblast.algorithms

import java.util.Scanner
import java.io.File
import java.util.ArrayList

object CountInversions{
  
  def main(args: Array[String]) {
	  if(args.length < 1) {
	    println("no args")
	  } else {
	    println(args(0))
	    val file = new File(args(0))
	    val scanner = new Scanner(file);
	    
	   val _array = new Array[Int](100000)
	    //val _array = new Array[Int](6)

	    //val arrayList = new ArrayList[Int]();
	    
	    var i = 0
	    while(scanner.hasNext()) {
	      val number = scanner.nextInt()
	      _array(i) = number
	      i = i + 1
	      
	      //arrayList.add(number)
	    }
	    
	   // _array map (x=> print(x + " "))
	    
	    println("------------------------------")
	    
	   // val inversions = countInversions(arrayList)
	    
	    val (inversions, sorted) = countInversions(_array)
	    println("inversion count is " + inversions)
	    println("------------------------------")
	    
	    
	    //sorted map (x=>(print(x + " ")))
	  }
	  
	  
  }
  
  def countSplits(first: Array[Int], second: Array[Int]): (Long, Array[Int]) = {
	var splits = 0
	
	var size = first.length + second.length
	var merged = new Array[Int](size)
	
	var i, j = 0
    for (k <- 0 to size - 1) {
      if (i >= first.length) {
        merged(k) = second(j)
        j += 1
      } else if (j >= second.length) {
        merged(k) = first(i)
        i += 1
      } else {
        val fromFirst = first(i)
        val fromSecond = second(j)

        if (fromFirst <= fromSecond) {
          merged(k) = fromFirst
          i += 1
        } else {
          if(i <= first.length) {
        	  splits = splits + (first.length - i)
        	  println("splits is " + splits)
          }
          merged(k) = fromSecond
          j += 1
        }
      }
    }
	
	
	(splits, merged)
  }
  
  
  def countInversions(arr: Array[Int]): (Long, Array[Int])= {
        // Base case
    if (arr.length < 2) {
    	return (0, arr)
    }

    // Split the array in half
    val half = arr.length / 2
    val halves = arr.splitAt(half)
    

    val (leftCount, sortedFirst) = countInversions(halves._1)
    val (rightCount, sortedSecond) = countInversions(halves._2)

    val (splitCount, merged) = countSplits(sortedFirst, sortedSecond)
    
    val totalCount = leftCount + rightCount + splitCount

    return (totalCount, merged) 
  }
  
  

}