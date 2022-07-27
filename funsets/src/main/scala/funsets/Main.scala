package funsets

object Main extends App:
  import FunSets.*
  println(forall(x => x > 10, x => x > 9))
