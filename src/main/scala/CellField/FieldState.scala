package CellField

import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.White
import scalafx.scene.Node
import scalafx.scene.shape.{Line, Rectangle}


case class FieldState (field: Array[Array[Cell]], w: Int = 1280, h: Int = 920)
{
  private val lines: List[Node] = {
    var list = List[Node]()
    for (x <- 0 to w by 20 )
      list = new Line{
        stroke=White
        strokeWidth = 1
        startX = x
        startY = 0
        endX = x
        endY = h
      } +: list

    for (y <- 0 to h by 20 )
      list = new Line {
        stroke=White
        strokeWidth = 1
        fill = White
        startX = 0
        startY = y
        endX = w
        endY = y
      } +: list
    list
  }

  def newState(): FieldState = {

    for{
      j <- field.indices
      i <- field(0).indices
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
      j <- field.indices
      i <- field(0).indices
    }{
      field(j)(i) = field(j)(i).updateState()
    }

    FieldState(field.clone())
  }

  def addCell(x: Double, y :Double): FieldState = {
    val i = x.toInt/20
    val j = y.toInt/20
    field(j)(i) = new Cell(1, 0)

    FieldState(field.clone(), w, h)
  }

  def rectangles: List[Node] = {
    var list= List[Node]()
    for {
      j <- field.indices
      i <- field(0).indices
    } {
      if (field(j)(i).getState == 1) {
        list = square(i * 20, j * 20, field(j)(i).Color) +: list

      }
    }
    lines ::: list
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
    width = 20
    height = 20
    fill = color
  }

}
