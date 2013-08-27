package com.limeblast.algorithms

import scala.collection.mutable.HashSet



//import scala.collection.mutable.HashTable



object FileToHash {
	def getHash(fileName: String): HashSet[Long] = {
	  val table = new HashSet[Long]()
	  
	  val source = scala.io.Source.fromFile(fileName)
	  val lines = source.getLines()
	  
	  lines foreach (line => {
		  val value = line.toLong
		  table.add(value)
	  })
	  
	  
	  source.close()
	  
	  table
	}
}