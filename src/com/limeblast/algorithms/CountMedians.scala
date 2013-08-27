package com.limeblast.algorithms

import scala.collection.mutable.PriorityQueue

object CountMedians {

  def main(args: Array[String]): Unit = {
    val file = "Median.txt"
      

    var medianArr = Array[Int]()
    val source = scala.io.Source.fromFile(file)

    val lines = source.getLines
    
    val maintainer = new MedianMaintainer()
    
    var sum = 0
    
    lines foreach(line => {
      val value = line.toInt
      
      maintainer.add(value)
      sum += maintainer.getMedian      
    })

    source.close()
    
    
    println("Sum " + sum + " remainder " + sum % 10000) 
    //medianArr foreach (elem => print(elem + " "))
    println("")

  }

}