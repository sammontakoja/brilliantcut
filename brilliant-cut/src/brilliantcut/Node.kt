package brilliantcut

data class Node(val nodesCut: Cut, val sizeLeft: Int, val fatherNode: Node?, val allCuts: List<Cut>, val leaves:MutableList<Node>) {

  val children: List<Node> = subNodesWithSomethingToCut()

  private fun subNodesWithSomethingToCut(): List<Node> {

    val possibleCuts = allCuts.filter { sizeLeft - it.size >= 0 }

    if (possibleCuts.isEmpty()) {
      leaves.add(this)
      return emptyList()
    }

    return possibleCuts.map {
      val possibleCutSize = it.size
      val profitPerSize = allCuts.find { it.size == possibleCutSize }?.profit
      val fatherNode = this
      Node(Cut(possibleCutSize, profitPerSize), sizeLeft - it.size, fatherNode, allCuts, leaves)
    }
  }

}