import java.awt.Point

class MyLine(startPoint: Point, endPoint: Point, var ray: Boolean = false) {
    var p1 = startPoint
    var p2 = endPoint

    fun getTriple(): Triple<Int, Int, Int> {
        return Triple(p2.y - p1.y, p1.x - p2.x, p1.y * p2.x - p1.x * p2.y)
    }

    fun isOnLine(point: Point): Boolean {
        if ((point.x - p1.x) * (p2.y - p1.y) == (p2.x - p1.x) * (point.y - p1.y))
            return true
        return false
    }

}


fun isIntersectionPoint(line1: MyLine, line2: MyLine): Boolean {
    val t1 = line1.getTriple()
    val t2 = line2.getTriple()
    if (t1.first * t2.second - t2.first * t1.second == 0) return false

    val x = 1.0 * (t2.third * t1.second - t1.third * t2.second) / (t1.first * t2.second - t2.first * t1.second)
    var y = 1.0 * (t2.first * t1.third - t1.first * t2.third) / (t1.first * t2.second - t2.first * t1.second)


    if (!line1.ray) {
        if ((x < line1.p2.x && x < line1.p1.x) || (x > line1.p1.x && x > line1.p2.x))
            return false
    } else
        if ((x < line1.p1.x).xor(line1.p2.x < line1.p1.x)) return false

    if (!line2.ray) {
        if ((x < line2.p2.x && x < line2.p1.x) || (x > line2.p1.x && x > line2.p2.x))
            return false
    } else
        if ((x < line2.p1.x).xor(line2.p2.x < line2.p1.x)) return false

    return true
}