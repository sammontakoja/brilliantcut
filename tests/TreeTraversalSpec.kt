import brilliantcut.Cut
import brilliantcut.Tree
import brilliantcut.CutResult
import brilliantcut.TreeTraversal
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FreeSpec

class TreeTraversalSpec : FreeSpec({

  "Trees with" - {

    "diamond cuts size and value" - {

      val diamondCuts = listOf(Cut(7, 7), Cut(11, 14), Cut(17, 25))

      "gem size 23 produce max profit 27 with cuts 11,11 and 1 waste" {
        val cutResult = TreeTraversal().goThough(Tree(23, diamondCuts))
        cutResult shouldBe CutResult(income = 14+14, waste = 1, cuts = mutableListOf(11, 11))
        cutResult.profit() shouldBe 27
      }

      "gem size 7 produce max profit 7 with cut 7 and zero waste" {
        val cutResult = TreeTraversal().goThough(Tree(7, diamondCuts))
        cutResult shouldBe CutResult(income = 7, waste = 0, cuts = mutableListOf(7))
        cutResult.profit() shouldBe 7
      }

      "gem size 17 produce max profit 25 with cut 17 and zero waste" {
        val cutResult = TreeTraversal().goThough(Tree(17, diamondCuts))
        cutResult shouldBe CutResult(income = 25, waste = 0, cuts = mutableListOf(17))
        cutResult.profit() shouldBe 25
      }

      "gem size 12 produce max profit 13 with cut 11 and 1 waste" {
        val cutResult = TreeTraversal().goThough(Tree(12, diamondCuts))
        cutResult shouldBe CutResult(income = 14, waste = 1, cuts = mutableListOf(11))
        cutResult.profit() shouldBe 13
      }
    }

    "sapphire cuts size and value" - {

      val sapphireCuts = listOf(Cut(3, 3), Cut(5, 6), Cut(7, 8), Cut(11, 15))

      "gem size 5 produce max profit 6 with cut 5 and zero waste" {
        val cutResult = TreeTraversal().goThough(Tree(5, sapphireCuts))
        cutResult shouldBe CutResult(income = 6, waste = 0, cuts = mutableListOf(5))
        cutResult.profit() shouldBe 6
      }

      "gem size 22 produce max profit 30 with cuts 11,11 and zero waste" {
        val cutResult = TreeTraversal().goThough(Tree(22, sapphireCuts))
        cutResult shouldBe CutResult(income = 15+15, waste = 0, cuts = mutableListOf(11,11))
        cutResult.profit() shouldBe 30
      }

      "gem size 16 produce max profit 21 with cuts 11,5 and zero waste" {
        val cutResult = TreeTraversal().goThough(Tree(16, sapphireCuts))
        cutResult shouldBe CutResult(income = 15+6, waste = 0, cuts = mutableListOf(5,11))
        cutResult.profit() shouldBe 21
      }

    }
  }


})