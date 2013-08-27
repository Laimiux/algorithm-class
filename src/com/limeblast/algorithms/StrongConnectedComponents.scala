package com.limeblast.algorithms

import java.io.File
import java.util.Scanner

import scala.collection.mutable.LinearSeq

object StrongConnectedComponents {

  def main(args: Array[String]): Unit = {
    if (args.length < 1) {
      println("No args")
    } else {

      var graph = new Graph

      var edges: Array[Vector[Int]] = Array()


      time({
        val source = scala.io.Source.fromFile(args(0))
        val lines = source.getLines

        edges = (lines :\ edges)((line, arr) => {
          val splitted = line.split(" +")
          val one = splitted(0).toInt
          val two = splitted(1).toInt

          if (arr.size > one) {
            val vector = arr(one)
            if(vector != null) {
              arr.update(one, two +: vector)
            } else {
              arr.update(one, Vector(two))
            }
            
            arr
          } else {
            val array = new Array[Vector[Int]](one + 1)
            array.update(one, Vector[Int](two))
            arr ++ array
          }
        })
        source.close
      })

      println("Array size is " + edges.size)

      var scc: SCComponentsV2 = null
      time({
        scc = new SCComponentsV2(edges)
      })

      time({
        println("Running sccs")
        scc.get_sccs()
      })

      /*
      //Create reverse graph from the file
       time({
        val source = scala.io.Source.fromFile(args(0))
        val lines = source.getLines

        lines.foreach(line => {
          val splitted = line.split(" +")
          graph.addEdge(splitted(1).toInt, splitted(0).toInt)
        })

        source.close
      })
      
      
      lazy val scc = new SCComponents(graph.getGraph)

 
      
      time({
        scc.get_sccs()
      })
      * 
      */
    }

  }

  def time[A](a: => A) = {
    val now = System.nanoTime
    val result = a
    val micros = (System.nanoTime - now) / 1000
    println("%d microseconds".format(micros))
    result
  }

}