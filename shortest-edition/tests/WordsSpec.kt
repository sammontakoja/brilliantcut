import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FreeSpec
import shortestedition.Words
import java.io.File

class WordsSpec : FreeSpec({

    "Words.txt split into 10 words in descending order by word length" {

        val content = "Kustaa-Jooseppi oli aina kokenut olevansa antilooppi. Niinpä hän nytkin laukkasi tuulispään lailla – kohti kukkeana siintävää merta."
        File("Words.txt").writeText(content)

        println("Kustaa-Jooseppi".length)
        println("antilooppi.".length)
        println("tuulispään".length)
        println("siintävää".length)
        println("kukkeana".length)
        println("olevansa".length)
        println("laukkasi".length)
        println("kokenut".length)
        println("merta.".length)
        println("nytkin".length)
        println("lailla".length)
        println("Niinpä".length)
        println("kohti".length)
        println("aina".length)
        println("oli".length)
        println("hän".length)
        println("–".length)

        val words = Words.textFileLinesToWords("Words.txt")

        words.nextSize(1) shouldBe 1
        words.nextWord(1) shouldBe "–"
        words.nextSize(15) shouldBe 15
        words.nextWord(15) shouldBe "Kustaa-Jooseppi"
        words.nextSize(11) shouldBe 11
        words.nextWord(15) shouldBe "antilooppi."
        words.nextWord(15) shouldBe "tuulispään"
        words.nextWord(15) shouldBe "siintävää"
        words.nextWord(15) shouldBe "olevansa"
        words.nextWord(15) shouldBe "laukkasi"
        words.nextWord(15) shouldBe "kukkeana"
        words.nextWord(15) shouldBe "kokenut"
        words.nextWord(15) shouldBe "Niinpä"
        words.nextWord(15) shouldBe "nytkin"
        words.nextWord(15) shouldBe "lailla"
        words.nextWord(15) shouldBe "merta."
        words.nextWord(15) shouldBe "kohti"
        words.nextWord(4) shouldBe "aina"
        words.nextWord(3) shouldBe "oli"
        words.nextWord(3) shouldBe "hän"

    }

    "test_data_15_lines.txt split into 8 words" {

        val words = Words.textFileLinesToWords("test_data_15_lines.txt")

        println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".length)
        println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb".length)
        println("cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc".length)
        println("dddddddddddddddddddddddddddddddddddddddd".length)
        println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee".length)
        println("fffffffffffffffffff".length)
        println("gggg".length)
        println("h".length)

        words.nextWord(81) shouldBe "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        words.nextWord(75) shouldBe "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"
        words.nextWord(60) shouldBe "cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc"
        words.nextWord(40) shouldBe "dddddddddddddddddddddddddddddddddddddddd"
        words.nextWord(39) shouldBe "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
        words.nextWord(19) shouldBe "fffffffffffffffffff"
        words.nextWord(4) shouldBe "gggg"
        words.nextWord(1) shouldBe "h"
    }


})