package com.limeblast.algorithms

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Stack
import scala.annotation.tailrec

object GraphAlgorithms {

  //@tailrec
  def getAdjacentVertices(graph: Vector[(Int, Int)], vertex: Int): List[Int] = {
   val init = List[Int]()
   (init /: graph)((list, item) => {
    	if(item._1 == vertex) (list ::: List(item._2))
    	else (list)
    })
  }
  
  //@tailrec
  def dfs(graph: Vector[(Int, Int)], vertex: Int, explor: List[Int] = List[Int]()): List[Int] = {
    val explored: List[Int] = explor ::: List(vertex)
    
    
    def dfsInner(graph: Vector[(Int, Int)], vertex: Int, explored: List[Int]): List[Int] = {
      	val adjacentVerteces = getAdjacentVertices(graph, vertex)
      	(explored /: adjacentVerteces)((list, adjVertex) => 
      	  	if(!(list contains adjVertex)) dfsInner(graph, adjVertex, list ::: List(adjVertex))
      	  	else list
      	  )	
    }


    dfsInner(graph, vertex, explored)
  }

  def dfs_reverse(edges: Vector[(Int, Int)], vertex: Int) = {

  }
  


  def get_sccs(edges: Vector[(Int, Int)]): ArrayBuffer[Long] = {
    val buffer = new ArrayBuffer[Long]()

    
    
    var S = new Stack[Int]()

    val explored = dfs(edges, 1)

    explored.foreach(vert => println(vert))

    buffer
  }

}