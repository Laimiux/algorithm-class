package com.limeblast.algorithms

import scala.collection.mutable.ArrayBuffer

import scala.collection.mutable.Map

class SCComponents(val graph: scala.collection.mutable.Map[Int, ArrayBuffer[Int]]) {
  

  // Number of nodes processed so far
  // For finishing times in 1st pass
  var t = 0
  // keep track of leaders
  // for 2nd pass
  // current source vertex
  var s: Int = null.asInstanceOf[Int]

  var leaders: Map[Int, Int] = Map[Int, Int]()
  var finishingTime: Map[Int, Int] = Map[Int, Int]()

  var explored = List[Int]()

  def DFS(graph: scala.collection.mutable.Map[Int, ArrayBuffer[Int]],
    vertex: Int, count: Int = 0): Int = {

    var nodesCounted = count
    explored = vertex :: explored
    // leaders += vertex -> s

    nodesCounted += 1

    graph.get(vertex) match {
      case Some(list) => {
        for (item <- list) {

          if (!explored.contains(item)) {

            // Why does it contain this??
            /*
            for (exploredVert <- explored if (item == exploredVert)) {
              println("This should never ever ever happen " + item + " is in explored" + "and " + exploredVert)
              println("The whole explored " + explored)

            }
            * 
            */
            nodesCounted = DFS(graph, item, nodesCounted)
          }
        }

      }
      case None => //println("This is odd vertex entry not found " + vertex)

    }

    t += 1
    finishingTime += vertex -> t
    //println("vertex " + vertex +" and finishing time " + t)
    //println("node " + vertex + " has " + nodesCounted)
    return nodesCounted
  }

  def get_sccs() {
    get_sccs(graph)
    println("GET SCCS finished")

    // Reset values
    explored = List[Int]()
    leaders = Map[Int, Int]()
    t = 0
    s = null.asInstanceOf[Int]

    val original = reverse_graph_with_finishing_time()
    val sizes = get_sccs(original) //.sortBy()

    sizes.sortWith((one, two) => one > two).take(10).foreach(count => print(count + ", "))
    println()

  }

  def get_sccs(graph: Map[Int, ArrayBuffer[Int]]): ArrayBuffer[Int] = {
    val size = graph.keySet.max
    println("Starting get scc with size " + size)
    val buffer = new ArrayBuffer[Int]()
/*
    val keys = graph.keySet.toList.sortWith((one, two) => one > two)
    for (key <- keys) {
      if (!explored.contains(key)) {
        s = key
        val nodes = DFS(graph, key)
        buffer.append(nodes)
      } else {
      }
    }
    * 
    */
    
    for (i <- (size to 1 by -1)) {
      if (!explored.contains(i)) {
        s = i
        val nodes = DFS(graph, i)
        buffer.append(nodes)
      } else {
      }
    }

    buffer
  }

  def reverse_graph_with_finishing_time(): scala.collection.mutable.Map[Int, ArrayBuffer[Int]] = {
    var init = scala.collection.mutable.Map[Int, ArrayBuffer[Int]]()
    (init /: graph)((newMap, value) => {
      value._2.foreach(newVertex => {
        val finishTimeVertex = finishingTime(newVertex)
        val secondVertex = finishingTime(value._1)
        newMap.get(finishTimeVertex) match {
          case Some(buffer) => {
            buffer.append(secondVertex)
            newMap += finishTimeVertex -> buffer
          }
          case None => {
            val buffer = new ArrayBuffer[Int]()
            buffer.append(secondVertex)
            newMap += finishTimeVertex -> buffer
          }
        }
      })
      newMap
    })

  }

}