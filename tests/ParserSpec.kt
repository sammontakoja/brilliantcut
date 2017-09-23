import brilliantcut.Cut
import brilliantcut.Gem
import brilliantcut.Parser
import io.kotlintest.matchers.beLessThan
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FreeSpec
import java.io.File
import kotlin.system.measureTimeMillis

class ParserSpec : FreeSpec({

  "Input converted into" - {

    val input = """
      {
      "diamond": {
        "cuts": [
          {"size": 7, "value": 7},
          {"size": 11, "value": 14},
          {"size": 17, "value": 25}
        ],
        "rawChunks": [19, 32]
      },
      "sapphire": {
        "cuts": [
          {"size": 3, "value": 3},
          {"size": 5, "value": 6},
          {"size": 7, "value": 8},
          {"size": 11, "value": 15}
        ],
        "rawChunks": [29, 13, 14]
      },
      "ruby": {
        "cuts": [
          {"size": 4, "value": 5},
          {"size": 5, "value": 8},
          {"size": 6, "value": 11},
          {"size": 7, "value": 14}
        ],
        "rawChunks": [21,14,18]
      }
    }
      """

    "diamond with correct values" {
      Parser().toGems(input).get(0) shouldBe Gem(
          "diamond",
          listOf(Cut(7, 7), Cut(11, 14), Cut(17, 25)),
          listOf(19, 32)
      )
    }
    "sapphire with correct values" {
      Parser().toGems(input).get(1) shouldBe Gem(
          "sapphire",
          listOf(Cut(3, 3), Cut(5, 6), Cut(7, 8), Cut(11, 15)),
          listOf(29, 13, 14)
      )
    }
    "ruby with correct values" {
      Parser().toGems(input).get(2) shouldBe Gem(
          "ruby",
          listOf(Cut(4, 5), Cut(5, 8), Cut(6, 11), Cut(7, 14)),
          listOf(21, 14, 18)
      )
    }
  }

  "Parsing cuts.json with lot of row chunk should not take longer than 50 milliseconds" {
    val measuredTime = measureTimeMillis { Parser().toGems(File("cuts.json").readText()) }
    measuredTime shouldBe beLessThan(50)
  }

})