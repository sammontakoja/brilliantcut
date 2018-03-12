import brilliantcut.Cut
import brilliantcut.Node
import brilliantcut.Tree
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FreeSpec

class TreeSpec : FreeSpec({

  "Chunk sizeLeft 23 with cut sizes 7, 11 and 17" - {

    val cuts = listOf(Cut(7), Cut(11), Cut(17))

    fun cutTree(): Tree {
      return Tree(23, cuts)
    }

    "rootNode sizeLeft is 23" {
      cutTree().rootNode.sizeLeft shouldBe 23
    }

    "level 1 contains 3 children" {
      cutTree().rootNode.children.size shouldBe 3
    }

    "tree contains 5 leaf nodes" {
      cutTree().leafs.size shouldBe 5
    }

    "rootNode has children children 7_16, 11_12 and 17_6)" {
      val rootNode = cutTree().rootNode
      rootNode.children.find { it.nodesCut == Cut(7) } shouldBe Node(Cut(7), 16, rootNode, cuts, rootNode.leaves)
      rootNode.children.find { it.nodesCut == Cut(11) } shouldBe Node(Cut(11), 12, rootNode, cuts, rootNode.leaves)
      rootNode.children.find { it.nodesCut == Cut(17) } shouldBe Node(Cut(17), 6, rootNode, cuts, rootNode.leaves)
    }

    "left branch" - {

      "node 7_16 has children children 7_9 and 11_5)" {
        val rootNode = cutTree().rootNode
        val node_7_16 = rootNode.children.find { it == Node(Cut(7), 16, rootNode, cuts, rootNode.leaves) }
        node_7_16?.children?.find { it.nodesCut == Cut(7) } shouldBe Node(Cut(7), 9, node_7_16, cuts, rootNode.leaves)
        node_7_16?.children?.find { it.nodesCut == Cut(11) } shouldBe Node(Cut(11), 5, node_7_16, cuts, rootNode.leaves)
      }

      "node 7_9 has children node 7_2)" {
        val rootNode = cutTree().rootNode
        val node_7_16 = rootNode.children.find { it == Node(Cut(7), 16, rootNode, cuts, rootNode.leaves) }
        val node_7_9 = node_7_16?.children?.find { it == Node(Cut(7), 9, node_7_16, cuts, rootNode.leaves) }
        node_7_9?.children?.find { it.nodesCut == Cut(7) } shouldBe Node(Cut(7), 2, node_7_9, cuts, rootNode.leaves)
      }

    }

    "center branch" - {

      "node 11_12 has children children 7_5 and 11_1)" {
        val rootNode = cutTree().rootNode
        val node_11_12 = rootNode.children.find { it == Node(Cut(11), 12, rootNode, cuts, rootNode.leaves) }
        node_11_12?.children?.find { it.nodesCut == Cut(7) } shouldBe Node(Cut(7), 5, node_11_12, cuts, rootNode.leaves)
        node_11_12?.children?.find { it.nodesCut == Cut(11) } shouldBe Node(Cut(11), 1, node_11_12, cuts, rootNode.leaves)
      }

    }

    "right branch" - {

      "node 17_6 has no children" {
        val rootNode = cutTree().rootNode
        val node_17_6_children = rootNode.children
            .find { it == Node(Cut(17), 6, rootNode, cuts, rootNode.leaves) }
            ?.children
        node_17_6_children?.size shouldBe 0
      }

    }


  }


})