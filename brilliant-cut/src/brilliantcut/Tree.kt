package brilliantcut

class Tree(chunkSize:Int, cuts:List<Cut>) {
  val leafs = mutableListOf<Node>()
  val rootNode: Node = Node(Cut(0,0), chunkSize, null, cuts, leafs)
}
