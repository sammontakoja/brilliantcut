package brilliantcut

data class CutResult(var income:Int=0, var waste:Int = 0, val cuts: MutableList<Int>) {
  fun profit():Int {return income - waste}
}
