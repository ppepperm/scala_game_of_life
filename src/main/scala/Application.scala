
import CellField.{Cell, FieldState}
import scalafx.application.{JFXApp3, Platform}
import scalafx.beans.property.{IntegerProperty, ObjectProperty}
import scalafx.scene.Scene
import scalafx.scene.paint.Color._

import scala.concurrent.Future

object Application extends JFXApp3 {
  val w = 1280
  val h = 920

  def initField(w: Int, h: Int): FieldState = {
    val field = Array.ofDim[Cell](h/10, w/10)
    for {
      j <- field.indices
      i <- field(0).indices
    } {
      field(j)(i) = new Cell()
    }
    //hardcode
    field(10)(10) = new Cell(1, 0)
    field(10)(11) = new Cell(1, 0)
    field(11)(10) = new Cell(1, 0)
    //
    FieldState(field)
  }

  import scala.concurrent.ExecutionContext.Implicits.global

  def gameLoop(update: () => Unit): Unit =
    Future {
      update()
      Thread.sleep(80)
    }.flatMap(_ => Future(gameLoop(update)))


  override def start(): Unit = {
    val state = ObjectProperty(initField(w, h))
    val frame = IntegerProperty(0)

    frame.onChange{
      state.update(state.value.newState())
    }

    stage = new JFXApp3.PrimaryStage {
      width = w
      height = h
      scene = new Scene {
        fill = Black
        content = state.value.rectangles
        state.onChange(Platform.runLater {
          content = state.value.rectangles
        })
      }
    }
    gameLoop(() => frame.update(frame.value + 1))
  }
}