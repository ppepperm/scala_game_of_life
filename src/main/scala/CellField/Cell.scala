package CellField

import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.{Red, White}

class Cell(
  private var state :Int = 0,
  private var nextState: Int = 0,
  val Color :Color = White){

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
