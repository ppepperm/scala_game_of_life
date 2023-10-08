import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.Red

class Cell(
  private val x :Int,
  private val y :Int,
  private var state :Boolean = true,
  private var nextState :Boolean = false,
  private val Color :Color = Red){

  def getState: Boolean = state
  def getNextState: Boolean = nextState

  def setState(state: Boolean): Unit = {
    this.state = state
  }

  def setNextState(state: Boolean): Unit = {
    this.nextState = state
  }

}
