package brilliantcut

class Calculator {

  fun profitsCombined(gems: List<Gem>): Int {
    return gems.map {
      val gemCuts = it.cuts
      val calculatedChunkProfits = mutableMapOf<Int,Int>()
      it.rawChunks
          .map {
            if (calculatedChunkProfits.containsKey(it))
              calculatedChunkProfits.getValue(it)
            else {
              val treeRepresentingAllPossibleCuts = Tree(chunkSize = it, cuts = gemCuts)
              val maxProfitFromChunk = TreeTraversal().goThough(treeRepresentingAllPossibleCuts).profit()
              calculatedChunkProfits.put(it, maxProfitFromChunk)
              maxProfitFromChunk
            }
          }.sum()
    }.sum()
  }

}