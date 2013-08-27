package com.limeblast.algorithms

import scala.collection.mutable.PriorityQueue

class MedianMaintainer {
  val lowHeap: PriorityQueue[Int] = {
    implicit object Ord extends Ordering[Int] {
      def compare(x: Int, y: Int): Int = x.compare(y)
    }
    new PriorityQueue[Int]()
  }
  val highHeap = {
    implicit object Ord extends Ordering[Int] {
      def compare(x: Int, y: Int): Int = y.compare(x)
    }
    new PriorityQueue[Int]()
  }

  def add(number: Int) {
    if (lowHeap.size > highHeap.size) {
      // Number should go to high heap
      if (lowHeap.head > number) {
        highHeap += lowHeap.dequeue
        lowHeap += number
      } else {
        highHeap += number
      }
    } else if (lowHeap.size == highHeap.size) {
      lowHeap.headOption match {
        case Some(i: Int) => 
          if (number > lowHeap.head) {
        	  highHeap += number
          } else {
        	  lowHeap += number
          }
        case _ => lowHeap += number
      }

    } else {
      if (highHeap.head < number) {
        lowHeap += highHeap.dequeue
        highHeap += number
      } else {
        lowHeap += number
      }
    }

  }

  def getMedian(): Int = {
    if (lowHeap.size >= highHeap.size) {
      lowHeap.head
    } else {
      highHeap.head
    }
  }
}