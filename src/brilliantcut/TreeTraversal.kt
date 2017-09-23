package brilliantcut

class TreeTraversal {

  fun goThough(tree: Tree): CutResult {

    val nodePathsFromLeafsToRoot = tree.leafs.map { pathTravelsar(it, listOf()) }

    val profitLists = nodePathsFromLeafsToRoot
        .map {
          val pathProfit = CutResult(cuts = mutableListOf())
          it.forEach {
            pathProfit.cuts.add(it.nodesCut.size)
            it.nodesCut.profit?.let { pathProfit.income += (it) }
          }
          pathProfit.waste += tree.rootNode.sizeLeft - pathProfit.cuts.sum()
          pathProfit
        }

    return profitLists
        .sortedBy { it.profit() }
        .last()
  }

  private tailrec fun pathTravelsar(node: Node, nodes: List<Node>): List<Node> {
    if (node.fatherNode == null)
      return nodes
    else
      return pathTravelsar(node.fatherNode, nodes.plus(node))
  }
}