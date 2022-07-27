package recfun

object RecFun extends RecFunInterface:

  def main(args: Array[String]): Unit =
    /* println("Pascal's Triangle")
    for row <- 0 to 10 do
      for col <- 0 to row do
        print(s"${pascal(col, row)} ")
      println() */
    println("Parentheses Balancing")
    print(s"${countChange(4, List(1,2))} ")
    println()

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (r == 0 || r == 1 || c == 0 || c == r) 1
    else pascal(c , r - 1) + pascal(c - 1, r - 1)
  }

  /**
   * Exercise 2
   */
  /* def balance(chars: List[Char]): Boolean = {
     def verify(h: Char, t: List[Char], bool: Boolean) : Boolean = {

      println(((h.equals('(') && bool == true) || (h.equals(')') && bool == false) || (h.equals(')') && bool == true)))
      
       if((h.equals('(') && bool == true) || (h.equals(')') && bool == false) || (h.equals(')') && bool == true))
         if (t.isEmpty) !bool 
         else verify(t.head, t.tail, !bool)
       else
         if (t.isEmpty) bool
         else verify(t.head, t.tail, bool)
    }

      if(chars.isEmpty) true
      else verify(chars.head, chars.tail, true)
  } */

  def balance(chars: List[Char]): Boolean = {
    def verify(h: Char, t: List[Char], openParentisis: Int) : Boolean = {
      if t.isEmpty then
        if(h == '(')
          false
        else if(h == ')')
          openParentisis - 1 == 0
        else
          openParentisis == 0
      else if (h == '(')
          verify(t.head, t.tail, openParentisis + 1)
      else if(h == ')')
          verify(t.head, t.tail, openParentisis - 1)
      else
          verify(t.head, t.tail, openParentisis)
    }

    verify(chars.head, chars.tail, 0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if money == 0 then 1
    else if money < 0 || coins.isEmpty then 0 
    else countChange(money - coins.head, coins) + countChange(money, coins.tail)
  }
