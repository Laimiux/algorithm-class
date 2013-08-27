package com.limeblast.algorithms

import java.io.File
import java.util.Scanner



object GraphContractionAlgorithm {
  lazy val mainGraph = new Graph()
  
  def main(args: Array[String]): Unit = {
	  if (args.length < 1) {
      println("no args")
    } else {
      val file = new File(args(0))
      val scanner = new Scanner(file);
      
      while(scanner.hasNextLine()) {
        val line = scanner.nextLine()        
        val lowerScanner = new Scanner(line)
        
        val vertex = lowerScanner.nextInt()
        while(lowerScanner.hasNextInt()) {
        	mainGraph.addEdge(vertex, lowerScanner.nextInt())
        }
      }
    }
	  
	var minCuts = 100000000
	
	for(i <- 0 to 200) {
		val graph = mainGraph.clone()
		
		val newCut = Graph.kurgerMinCut(graph)
		if(newCut < minCuts ) {
		  minCuts = newCut
		}
		println(newCut)
	}
	
	println(minCuts)
  }

}