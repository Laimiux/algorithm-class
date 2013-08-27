package com.limeblast.algorithms

import java.util.Hashtable
import scala.collection.JavaConversions._
import scala.collection.mutable.HashSet

class Sum2HashTable(hashtable: HashSet[Long]) {

  
  
  def hasTwoSum(t: Int): Boolean = {
    
    for(value <- hashtable if(value < t)) {
    	val another = t - value
    	if(another != value && hashtable.contains(another))
    	  return true
    }
    /*
    for(value <- values if(value < t)) {
      val another = t - value
      if(another != value && hashtable.contains(another))
    	 return true
    }
    * 
    */ 
  
    
    /*
    for(i <- 1 to t-1 if(hashtable.contains(i))) {
      val another = t - i
      if(another != i && hashtable.contains(another))
        return true
    }
    */
    return false
  }
  
  def countTwoSums(from: Int, _to: Int): Int = {
    var counter = 0
    
    for(i <- from to _to) {
      if(hasTwoSum(i))
        counter += 1
    }
    
      
    counter
  }  
  
  
}