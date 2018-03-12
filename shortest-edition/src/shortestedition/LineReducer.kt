package shortestedition

import java.io.File

fun main(args: Array<String>) {

    val words = Words.textFileLinesToWords(args[0])

    while (words.left()) {
        words.buildLine(words.nextWord(80)).let { builtLine ->
            File("reduced_lines.txt").appendText(builtLine + "\n")
        }
    }
}

object Words {

    private val sizeWordMap = mutableMapOf<Int, MutableList<String>>()

    fun textFileLinesToWords(inputFile: String): Words {
        File(inputFile).forEachLine { line ->
            line.split(" ").forEach { word ->
                if (!sizeWordMap.containsKey(word.length))
                    sizeWordMap.put(word.length, mutableListOf())
                sizeWordMap[word.length]!!.add(word)
            }
        }
        return this
    }

    tailrec fun buildLine(line: String): String {
        val spaceLeft = 80 - "\n".length - line.length
        val nextPossibleSize = nextSize(spaceLeft)
        nextPossibleSize ?: return line
        return buildLine(line + " " + nextWord(spaceLeft))
    }

    fun left(): Boolean {
        return !sizeWordMap.isEmpty()
    }

    fun nextWord(maxLength: Int): String {
        val nextWord = sizeWordMap[nextSize(maxLength)]!!.removeAt(0)
        if (sizeWordMap[nextSize(maxLength)]!!.isEmpty())
            sizeWordMap.remove(nextSize(maxLength))
        return nextWord
    }

    fun nextSize(maxLength: Int): Int? {
        return sizeWordMap
                .filterKeys { key -> key <= maxLength }
                .map { word -> word.key }
                .max()
    }
}