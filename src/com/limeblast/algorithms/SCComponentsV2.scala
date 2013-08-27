package com.limeblast.algorithms

import scala.collection.mutable.ArrayBuffer

import scala.collection.mutable.Map

class SCComponentsV2(val graph: Array[Vector[Int]]) {
  val max = graph.size - 1
  println("max " + max)

  // Number of nodes processed so far
  // For finishing times in 1st pass
  var t = 0
  // keep track of leaders
  // for 2nd pass
  // current source vertex
  var s: Int = null.asInstanceOf[Int]

  //var leaders: Map[Int, Int] = Map[Int, Int]()
  var finishingTime: Map[Int, Int] = Map[Int, Int]()

  var explored = new Array[Boolean](max + 1)
  //var explored = Vector[Int]()

  def get_sccs() {
    get_sccs(graph)
    println("GET SCCS finished")

    // Reset values
    explored = new Array[Boolean](max + 1)
    //leaders = Map[Int, Int]()
    t = 0
    s = null.asInstanceOf[Int]

    val original = reverse_graph_with_finishing_time()
    val sizes = get_sccs(original)

    sizes.sortWith((one, two) => one > two).take(10).foreach(count => print(count + ", "))
    println("")

  }

  def get_sccs(graph: Array[Vector[Int]]): ArrayBuffer[Int] = {
    println("Start get scc with max " + max)
    val buffer = new ArrayBuffer[Int]()

    for (key <- (max to 1 by -1)) {
      if (!explored(key)) {
        s = key
        val nodes = DFS(graph, key)
        buffer.append(nodes)
        //println("should get called " + nodes)
      }
    }
    println("GET SCCS finished with")
    buffer
  }

  def DFS(graph: Array[Vector[Int]],
    vertex: Int, count: Int = 0): Int = {

    var nodesCounted = count

    explored(vertex) = true

    // leaders += vertex -> s

    nodesCounted += 1

    val adjacents = graph(vertex)

    if (adjacents != null) {
      for (item <- adjacents if (!explored(item))) {
        nodesCounted = DFS(graph, item, nodesCounted)
      }
    }

    t += 1
    finishingTime += vertex -> t

    //println("node " + vertex + " has " + nodesCounted)
    nodesCounted
  }

  def reverse_graph_with_finishing_time(): Array[Vector[Int]] = {
    var init = new Array[Vector[Int]](max + 1)
    for (i <- 1 to max) {
      val adjacents = graph(i)
      if (adjacents != null) {
        for (item <- adjacents) {
          val vertex1 = finishingTime(item)
          val vertex2 = finishingTime(i)
          val oldVertex = init(vertex1)
          if (oldVertex != null) {
            init.update(vertex1, vertex2 +: oldVertex)
          } else {
            init.update(vertex1, Vector(vertex2))
          }
        }
      }
    }

    init
  }

}