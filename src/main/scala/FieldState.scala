case class FieldState (val field: Array[Array[Cell]])
{

  def newState(): FieldState = {

    for{
      j <- 0 to field.length
      i <- 0 to field(0).length
    }{
      val count = getCount(i, j)
      val cell = field(j)(i)
      field(j)(i) = (cell.getState, count) match {
        case (1, 2) => cell.setNextState(1)
        case (1, 3) => cell.setNextState(1)
        case (0, 3) => cell.setNextState(1)
        case _ => cell.setNextState(0)
      }
    }

    for {
      j <- 0 to field.length
      i <- 0 to field(0).length
    }{
      field(j)(i) = field(j)(i).updateState()
    }

    FieldState(field)
  }

  private def getCount(i: Int, j: Int): Int = {
    var count = 0
    for {
      i1 <- i - 1 to i + 1
      j1 <- j - 1 to j + 1
    } {
      if (j1 >= 0 && j1 < field.length && i1 >= 0 && i1 < field(j).length)
        count += field(j1)(i1).getState
    }
    count
  }

}
