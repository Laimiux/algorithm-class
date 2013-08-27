package com.limeblast.algorithms

//import java.util.HashMap

import scala.collection.mutable.Map
import scala.collection.mutable.Set
import scala.util.Random
import java.lang.IllegalStateException
import scala.collection.mutable.ArrayBuffer

object Graph {
  def kurgerMinCut(gra: Graph): Int = {
    val graph = gra.getGraph
    
    while (graph.size > 2) {
      Graph.randomlyCollapseGraph(graph)

    }

    var size = 0
    graph.keySet.foreach(key => {
      graph.get(key) match {
        case Some(arr) => size = arr.size
        case _ =>
      }
    })

    val edges = Graph.getAllEdges(graph)
    println(edges)

    size
  }

  def addEdge(graph: Map[Int, ArrayBuffer[Int]], vertex: Int, otherPoint: Int) {
    graph.get(vertex) match {
      case Some(arr) => {
        arr.append(otherPoint)
        graph.put(vertex, arr)
      }
      case None => {
        val buffer = new ArrayBuffer[Int]()
        buffer += otherPoint
        graph.put(vertex, buffer)

      }
    }
  }

  def removeEdge(graph: Map[Int, ArrayBuffer[Int]], vertex: Int, otherPoint: Int) {

    graph.get(vertex) match {
      case Some(set) => {
        var index = set.indexOf(otherPoint)
        while (index >= 0) {
          set.remove(index)
          index = set.indexOf(otherPoint)
        }

      }
      case None => println("Vertex " + vertex + " not found")
    }
  }

  def getAllEdges(graph: Map[Int, ArrayBuffer[Int]]): ArrayBuffer[(Int, Int)] = {
    val edges = ArrayBuffer[(Int, Int)]()

    graph.keySet.foreach(key => {
      graph.get(key) match {
        case Some(set) => {
          for (number <- set) {
            edges.append((key, number))
          }
        }
        case None => throw new IllegalStateException("Every key should have a set")
      }
    })

    edges
  }

  private def randomlyCollapseGraph(graph: Map[Int, ArrayBuffer[Int]]) {
    val edges: ArrayBuffer[(Int, Int)] = Graph.getAllEdges(graph)
    val random = new Random()
    val edgePos = random.nextInt(edges.size)

    val (vertex, edge) = edges(edgePos)

    //println("Vertex " + vertex + " edge " + edge)
    // Remove association with edge
    Graph.removeEdge(graph, vertex, edge)

    graph.remove(edge) match {
      case Some(arr) => {
        //println("Vertex " + edge + " old elements " + arr)
        arr.foreach(element => {
          if (element != vertex) {
            Graph.addEdge(graph, vertex, element)
            Graph.removeEdge(graph, element, edge)
            Graph.addEdge(graph, element, vertex)

          }
        })

      }
      case None => throw new IllegalStateException("Edge " + edge + " was not in the keyset " + graph.keySet)
    }

  }
}

class Graph {
  val vertices = Map.empty[Int, ArrayBuffer[Int]]

  def addEdge(vertex: Int, edge: Int) {
    vertices.get(vertex) match {
      case Some(arr) => arr.append(edge)
      case None => {
        val buffer = new ArrayBuffer[Int]
        buffer += edge
        vertices.put(vertex, buffer)
      }
    }

  }

  override def clone(): Graph = {
    val graph = new Graph
    vertices.keySet.foreach(key => {
      vertices.get(key) match {
        case Some(arr) => {
          arr.foreach(element => {
            graph.addEdge(key, element)
          })

        }
        case None =>
      }
    })
    graph
  }


  def getGraph: Map[Int, ArrayBuffer[Int]] = vertices

  override def toString(): String = {
    var string = ""
    val keySet = vertices.keySet
    for (key <- keySet) {
      string += key + " = "
      vertices.get(key) match {
        case Some(set) => string += set + " \n"
        case None => string += "\n"
      }
    }

    string
  }
}