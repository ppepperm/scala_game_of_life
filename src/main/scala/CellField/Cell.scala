package CellField

import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.Red

class Cell(
  private var state :Int = 0,
  private var nextState: Int = 0,
  val Color :Color = Red){

  def this() = {
    this(0, 0, Red)
  }

  def getState: Int = state

  def getNextState: Int = nextState

  def setNextState(state: Int): Cell = {
    this.nextState = state
    this
  }

  def updateState(): Cell = {
    this.state =this.nextState
    this.nextState = 0
    this
  }

}
