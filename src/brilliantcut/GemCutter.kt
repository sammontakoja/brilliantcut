package brilliantcut

import java.io.File
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {

  fun givenFileAsText(): String {
    return try {
      return File(args[0]).readText()
    } catch (e: Exception) {
      throw Exception("Failure! First argument should be json file containing gem cut information.", e)
    }
  }

  val measuredTime = measureTimeMillis {
    val maximumProfit = GemCutter().maximumProfit(input = givenFileAsText())
    println("Combined profit for given gem chunks is $maximumProfit.")
  }

  println("Json parsed, trees created and traversed in $measuredTime milliseconds.")
}

class GemCutter {
  fun maximumProfit(input: String): Int {
    val gems = Parser().toGems(input)
    return Calculator().profitsCombined(gems)
  }
}