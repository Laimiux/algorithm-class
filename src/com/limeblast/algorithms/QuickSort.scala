package com.limeblast.algorithms

import java.util.ArrayList
import java.io.File
import java.util.Scanner

object QuickSort {
  var comparisons: Long = 0

  // problem 1 : 160446
  // problem 2 : 

  def main(args: Array[String]) {
    if (args.length < 1) {
      println("no args")
    } else {
      val file = new File(args(0))
      val scanner = new Scanner(file);

      val arrayList = new ArrayList[Int]();

      var i = 0
      while (scanner.hasNextInt()) {
        val number = scanner.nextInt()
        arrayList.add(number)
        i = i + 1
      }
      
      
      println("Start " + arrayList)

      quicksort(arrayList)

      println("End " + arrayList)

      println("COMPARISONS : " + comparisons)
    }
  }

  def quicksort(arr: ArrayList[Int]) {
    var lowBound = 0
    var highBound = arr.size - 1
    partition(arr, lowBound, highBound)
  }

  def choosePivotCase1(arr: ArrayList[Int], lowBound: Int, highBound: Int): Int = {
    return arr.get(lowBound)
  }

  def choosePivotCase2(arr: ArrayList[Int], lowBound: Int, highBound: Int): Int = {
    val pivot = arr.get(highBound)
    val first = arr.get(lowBound)

    arr.set(lowBound, pivot)
    arr.set(highBound, first)
    return pivot
  }

  def choosePivotCase3(arr: ArrayList[Int], lowBound: Int, highBound: Int): Int = {
    
    val midPosition: Int = (highBound+lowBound) / 2
    
    val first = arr.get(lowBound)
    val mid = arr.get(midPosition)
    val high = arr.get(highBound)
    
    var chosenPosition = lowBound
    
    if(first >= mid) {
    	if(mid >= high) {
    	  chosenPosition = midPosition
    	} else if (high > first) {
    	  chosenPosition = lowBound
    	} else {
    	  chosenPosition = highBound
    	}
    } else {
      if(first >= high) {
        chosenPosition = lowBound
      } else if(high > mid) {
        chosenPosition = midPosition     
      } else {
        chosenPosition = highBound
      }
    }
    
  	var chosenNumber = first

    if (chosenPosition >= lowBound) {
      chosenNumber = arr.get(chosenPosition)
      arr.set(lowBound, chosenNumber)
      arr.set(chosenPosition, first)
      
    }
  	
  	//println("Mid is " + chosenNumber + " from " + first + " " + mid + " " + high)
  	//println("Mid is at position " + chosenPosition + " from " + lowBound + " " + midPosition + " " + highBound)
    
    return chosenNumber
  }

  def partition(arr: ArrayList[Int], lowBound: Int, highBound: Int) {
    if (highBound - lowBound > 0 && lowBound < highBound) {
      comparisons += (highBound - lowBound) //-1

      val pivot = choosePivotCase1(arr, lowBound, highBound)
      //val pivot = choosePivotCase2(arr, lowBound, highBound)
      //val pivot = choosePivotCase3(arr, lowBound, highBound)

      var i = lowBound + 1

      for (j <- lowBound + 1 to highBound) {
        val compareElement = arr.get(j)
        if (pivot > compareElement) {
          val swapElement = arr.get(i)
          arr.set(i, compareElement)
          arr.set(j, swapElement)

          i = i + 1
        }
      }

      val switchVal = arr.get(i - 1)
      arr.set(i - 1, pivot)
      arr.set(lowBound, switchVal)

      // Recurse
      partition(arr, lowBound, i - 2)
      partition(arr, i, highBound)

    }
  }
}