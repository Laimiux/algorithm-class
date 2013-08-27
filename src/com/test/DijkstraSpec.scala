package com.test

import org.scalatest._

class DjikstraSpec extends FlatSpec {

  "djikstraData file" should "not throw any exceptions" in {
    val file = "dijkstraData.txt"
    val graph = com.limeblast.algorithms.FileToGraph.dijkstraFile(file) 
  }
  
  "findShortestPath" should "find the shortest path of from vertex 1" in {
    val file = "djikstratestcase.txt"
    val arr = com.limeblast.algorithms.FileToGraph.dijkstraFile(file)
    val djikstra = new com.limeblast.algorithms.DijkstraAlgorithm(arr)
    val shortestPath = djikstra.findShortesPath(1)
    
    assert(shortestPath(1) === 0)
    assert(shortestPath(2) === 45)
    assert(shortestPath(3) === 10)
    assert(shortestPath(4) === 25)
    assert(shortestPath(5) === 45)
    assert(shortestPath(6) === 0)
  }
  
  "findShortestPath" should "find the shortest path from vertex 13 to node 66, 93, 109, 119, 129" in {
    val file = "dijkstraData.txt"
    val graph = com.limeblast.algorithms.FileToGraph.dijkstraFile(file)
    
    val dijkstra = new com.limeblast.algorithms.DijkstraAlgorithm(graph)
    val shortestPath = dijkstra.findShortesPath(13)
    
    assert(shortestPath(66) === 3910)
    assert(shortestPath(93) === 5700)
    assert(shortestPath(109) === 2838)
    assert(shortestPath(119) === 3243)
    assert(shortestPath(129) === 3070)
  }
  
  "find shortest path of dijkstraData" should "find the shortest path from node 1 to 7,37,59,82,99,115,133,165,188,197" in {
    val file = "dijkstraData.txt"
    val graph = com.limeblast.algorithms.FileToGraph.dijkstraFile(file)
    
    val dijkstra = new com.limeblast.algorithms.DijkstraAlgorithm(graph)
    val shortestPath = dijkstra.findShortesPath(1)
    
    val list = Map(7 -> 2599,37 -> 2610,59 -> 2947,82 -> 2052,
        99 -> 2367,115 -> 2399,133 -> 2029,165->2442,188-> 2505,197 -> 3068)
    
    list foreach (tuple => {
    	assert(shortestPath(tuple._1) === tuple._2)
    })

  }
}