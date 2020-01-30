import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.Line
import javafx.stage.Stage
import java.awt.Point
import java.util.*


class Main : Application() {
    private val point: Point = Point(100, 300)
    private var polygon = Vector<Point>()

    private val root = Group()
    private val scene = Scene(root, 600.0, 300.0)


    private fun drawPoint(point: Point, color: Color = Color.BLUE) {
        val circle = Circle(point.x.toDouble(), point.y.toDouble(), 5.0)
        circle.stroke = color
        root.children.add(circle)
    }


    private fun drawLine(point1: Point, point2: Point, color: Color = Color.BLACK) {
        val line = Line(point1.x.toDouble(), point1.y.toDouble(), point2.x.toDouble(), point2.y.toDouble())
        line.stroke = color
        root.children.add(line)
    }

    private fun drawPolygon(polygon: Vector<Point>) {
        for (i in 1 until polygon.size) {
            drawLine(polygon[i - 1], polygon[i], Color.BLUE)
        }
        drawLine(polygon[polygon.size - 1], polygon[0], Color.BLUE)
    }

    private fun areOverlays(newPoint: Point): Boolean {
        val line = MyLine(point, newPoint, true)
        for (i in 0 until polygon.size)
            if (line.isOnLine(polygon[i]))
                return true

        return false
    }

    private fun pointInPolygon(): Boolean {
        val newPoint = Point(point)
        newPoint.translate(1000, 1000)
        while (areOverlays(newPoint)) {
            newPoint.translate(0, 1)
            print('+')
        }
        println(point)
        println(newPoint)
        val line = MyLine(point, newPoint, true)
        drawLine(line.p1, line.p2)

        var count = 0
        for (i in 1 until polygon.size)
            if (isIntersectionPoint(MyLine(polygon[i - 1], polygon[i]), line))
                count++
        if (isIntersectionPoint(MyLine(polygon[polygon.size - 1], polygon[0]), line))
            count++

        println(count)


        if (count % 2 == 0) return false
        return true
    }

    override fun start(primaryStage: Stage?) {
        polygon.addElement(Point(100, 200))
        polygon.addElement(Point(200, 200))
        polygon.addElement(Point(300, 100))
        polygon.addElement(Point(150, 400))
        polygon.addElement(Point(50, 300))
        polygon.addElement(Point(80, 50))
        drawPolygon(polygon)
        drawPoint(point)
        println(pointInPolygon())




        primaryStage?.scene = scene
        primaryStage!!.show()

    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }
}