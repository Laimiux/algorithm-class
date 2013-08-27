package com.limeblast.algorithms

import scala.util.Random

object Mergesort {

  def main(args: Array[String]) {
    
    val randomGen = new Random()
    
    val array = new Array[Int](10000)
    
    for(t <- 0 to array.length - 1) {
      array(t) = randomGen.nextInt(100000)
    }
    
    array map (x => print(x + " "))
    
    println("")
    
    val sorted = sort(array)

    sorted map (x => print(x + " "))
  }

  // Merge two arrays in a sorted order
  def merge(first: Array[Int], second: Array[Int]): Array[Int] = {
    var i = 0
    var j = 0

    val n = first.length + second.length

    val merged = new Array[Int](n)

    for (k <- 0 to n - 1) {
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
          merged(k) = fromSecond
          j += 1
        }
      }
    }
    merged
  }

  def sort(arr: Array[Int]): Array[Int] = {

    // Base case
    if (arr.length <= 1) {
      return arr
    }

    // Split the array in half
    val half = arr.length / 2
    val halves = arr.splitAt(half)

    val sortedFirst = sort(halves._1)
    val sortedSecond = sort(halves._2)

    val sorted = merge(sortedFirst, sortedSecond)

    sorted
  }
}