package com.limeblast.algorithms

class DijkstraAlgorithm(val graph: Array[Vector[WeightedEdge[Int]]]) {
  var vertices_processed = List[Int]()
  lazy val shortest_path_distances: Array[Int] = new Array[Int](graph.size)
  //lazy val 

  val all_edges = (graph :\ List[WeightedEdge[Int]]())((vector, list) => {
    if (vector != null) {
      (vector :\ list)((edge, list) => {
        edge +: list
      })
    } else {
      list
    }
  })

  def findShortesPath(source: Int): Array[Int] = {
    shortest_path_distances(source) = 0
    vertices_processed = source +: vertices_processed

    while (vertices_processed.size < graph.size - 1) {
      var minimumExtra = Integer.MAX_VALUE
      var secondVertex = 0

      all_edges.filter((edge) =>
        (vertices_processed.contains(edge.vertex) && !vertices_processed.contains(edge.secondVertex))).foreach((edge) => {
        var sum = shortest_path_distances(edge.vertex) + edge.weight
        if (sum < minimumExtra) {
          minimumExtra = sum
          secondVertex = edge.secondVertex
        }
      })

      vertices_processed = secondVertex +: vertices_processed
      shortest_path_distances(secondVertex) = minimumExtra
    }

    shortest_path_distances
  }
}