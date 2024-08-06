package baekjoon.gold.solution_2170

import java.util.*

private class Solution2170 {

    fun solution(lines: PriorityQueue<Line>): String {
        val first = lines.poll()
        var start = first.x
        var end = first.y
        var result = 0

        while (!lines.isEmpty()) {
            val current = lines.poll()

            if (current.x > end) {
                result += end - start
                start = current.x
                end = current.y
                continue
            }

            if (current.y > end) {
                end = current.y
            }
        }
        result += end - start

        val sb = StringBuilder().apply {
            append(result)
        }

        return sb.toString()
    }

    data class Line(
        val x: Int,
        val y: Int
    ) : Comparable<Line> {

        override fun compareTo(other: Line): Int = when (this.x) {
            other.x -> other.y - this.y
            else -> this.x - other.x
        }

    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val pq = PriorityQueue<Solution2170.Line>()

    repeat(n) {
        val (x, y) = br.readLine().split(" ").map(String::toInt)
        pq.offer(Solution2170.Line(x, y))
    }

    bw.append(Solution2170().solution(pq))
    bw.flush()

    br.close()
    bw.close()
}