import brilliantcut.GemCutter
import io.kotlintest.matchers.beLessThan
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FreeSpec
import java.io.File
import kotlin.system.measureTimeMillis

class GemCutterSpec : FreeSpec({

  "Maximum profit should be 101648" {
    GemCutter().maximumProfit(File("cuts.json").readText()) shouldBe 101648
  }

  "Maximum profit calculation should not take over 200 milliseconds" {
    val measureTimeMillis = measureTimeMillis { GemCutter().maximumProfit(File("cuts.json").readText()) }
    measureTimeMillis shouldBe beLessThan(200)
  }

})