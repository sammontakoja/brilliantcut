import brilliantcut.Calculator
import brilliantcut.Cut
import brilliantcut.Gem
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FreeSpec

class CalculatorSpec : FreeSpec({

  "Gems overall profit is 120. ((27 + 25) + (30 + 21) + (16))" {

    val diamonds = Gem(
        "diamond",
        listOf(Cut(7, 7), Cut(11, 14), Cut(17, 25)),
        listOf(23, 17) // profits 27 + 25
    )

    val sapphires = Gem(
        "sapphire",
        listOf(Cut(3, 3), Cut(5, 6), Cut(7, 8), Cut(11, 15)),
        listOf(22, 16) // profits 30 + 21
    )

    val rubys = Gem(
        "ruby",
        listOf(Cut(4, 5), Cut(5, 8), Cut(6, 11), Cut(7, 14)),
        listOf(10, 10) // profit 16 + 16
    )

    Calculator().profitsCombined(listOf(diamonds, sapphires, rubys)) shouldBe (27 + 25) + (30 + 21) + (16 + 16)
  }

})