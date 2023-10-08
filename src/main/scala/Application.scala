
import scalafx.application.{JFXApp3, Platform}
import scalafx.beans.property.{IntegerProperty, ObjectProperty}
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle

import scala.concurrent.Future
import scala.util.Random

object Application extends JFXApp3 {
  val w = 1280
  val h = 920

  def initField(w: Int, h: Int): FieldState = {
    val field = Array.ofDim[Cell](h/10, w/10)
    FieldState(field)
  }

  def square(xr: Double, yr : Double, color: Color) = new Rectangle{
    x = xr
    y = yr
    width = 10
    height = 10
    fill = color
  }


  override def start(): Unit = {

    stage = new JFXApp3.PrimaryStage {
      width = w
      height = h
      scene = new Scene {
        fill = Black
        content = square(10, 10, Red)
      }
    }

  }
}