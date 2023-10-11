package CellField

import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle


case class FieldState (field: Array[Array[Cell]])
{

  def newState(): FieldState = {

    for{
      j <- field.indices
      i <- field(0).indices
    }{
      val count = getCount(i, j)
      val cell = field(j)(i)
      if (j == 11 && i == 10){
        //println(cell.getState + " " + count)
        val newCell = cell.setNextState(1)
        //println(newCell.getState + " " + newCell.getState)
      }
      field(j)(i) = (cell.getState, count) match {
        case (1, 2) => cell.setNextState(1)
        case (1, 3) => cell.setNextState(1)
        case (0, 3) => cell.setNextState(1)
        case _ => cell.setNextState(0)
      }
    }

    for {
      j <- field.indices
      i <- field(0).indices
    }{
      field(j)(i) = field(j)(i).updateState()
    }

    FieldState(field.clone())
  }

  def addCell(x: Double, y :Double): FieldState = {
    val i = x.toInt/10
    val j = y.toInt/10
    field(j)(i) = new Cell(1, 0)

    FieldState(field.clone())
  }

  def rectangles: List[Rectangle] = {
    var list= List[Rectangle]()
    for {
      j <- field.indices
      i <- field(0).indices
    } {
      if (field(j)(i).getState == 1) {
       list = square(i * 10, j * 10, field(j)(i).Color) +: list
      }
    }
    list
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
    count -= field(j)(i).getState
    count
  }

  private def square(xr: Double, yr: Double, color: Color) = new Rectangle {
    x = xr
    y = yr
    width = 10
    height = 10
    fill = color
  }

}
