package softeer.lv1

private class Solution7695 {
    fun solution(subways: Array<Subway>): String =  subways.sortedBy { it.y }
        .toList()
        .let { "${it[0].x} ${it[0].y}" }
}

private data class Subway(
    val x: Int = 0,
    val y: Int = 0
)

private fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val subways = Array(n) { Subway() }
    for (i in 0 until n) {
        val (x, y) = readLine().split(" ").map { it.toInt() }
        subways[i] = Subway(x = x, y = y)
    }

    val solution = Solution7695()
    println(solution.solution(subways))
}