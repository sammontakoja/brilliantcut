package brilliantcut

import com.google.gson.JsonParser


class Parser {

  fun toGems(input: String): List<Gem> {

    return JsonParser().parse(input).asJsonObject.entrySet()
        .map {
          val gemName = it.key

          val gemContentObject = it.value.asJsonObject

          val cuts = gemContentObject.getAsJsonArray("cuts")
              .map {
                val cutSize = it.asJsonObject.get("size").asInt
                val cutValue = it.asJsonObject.get("value").asInt
                Cut(cutSize, cutValue)
              }.toList()

          val rawChunks = gemContentObject.getAsJsonArray("rawChunks")
              .map {
                it.asInt
              }.toList()

          Gem(gemName, cuts, rawChunks)
        }.toList()
  }
}