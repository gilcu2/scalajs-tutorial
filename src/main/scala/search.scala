/**
 * Created by gil on 20/04/15.
 */
package rerendering

import org.scalajs.dom
import dom.html
import org.scalajs.dom.html
import _root_.scalatags.JsDom.all._
import scalajs.js.annotation.JSExport
import scalatags.JsDom.all._


@JSExport
object HelloWorld1 {
  @JSExport
  def main(target: html.Div) = {
    val listings = Seq(
      "Apple", "Apricot", "Banana", "Cherry",
      "Mango", "Mangosteen", "Mandarin",
      "Grape", "Grapefruit", "Guava"
    )

    def renderListings = ul(
      for {
        fruit <- listings
        if fruit.toLowerCase.contains(
          box.value.toLowerCase
        )
      } yield {
        val (first, mark, last) = {
          val (first, second) = fruit.splitAt(
            fruit.indexOf(box.value.toLowerCase))
          val (mark,last)=second.splitAt(box.value.length)
          (first,mark,last)
        }
        li(
          first,
          span(
            backgroundColor:="yellow",
            mark
          ),
          last
        )
      }
    ).render


    lazy val box = input(
      `type`:="text",
      placeholder:="Type here!"
    ).render

    val output = div(renderListings).render

    box.onkeyup = (e: dom.Event) => {
      output.innerHTML = ""
      output.appendChild(renderListings)
    }

    target.appendChild(
      div(
        h1("Search Box!"),
        p(
          "Type here to filter " +
            "the list of things below!"
        ),
        div(box),
        output
      ).render
    )
  }
}