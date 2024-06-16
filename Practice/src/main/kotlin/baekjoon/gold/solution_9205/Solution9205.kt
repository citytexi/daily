package baekjoon.gold.solution_9205

import kotlin.math.abs

private class Solution9205 {

    fun solution(
        n: Int,
        graph: Array<IntArray>
    ): String {
        val deque = ArrayDeque<Pair<Int, Int>>()
        val visited = BooleanArray(n + 2)
        visited[0] = true

        val start = graph[0]
        val end = graph[n + 1]

        deque.add(start[0] to start[1])

        while (deque.isNotEmpty()) {
            val current = deque.removeFirst()

            if (current.first == end[0] && current.second == end[1]) {
                return "happy"
            }

            for (i in 1 until n + 2) {
                if (visited[i]) {
                    continue
                }

                val (nextX, nextY) = graph[i]

                if (abs(current.first - nextX) + abs(current.second - nextY) > 1000) {
                    continue
                }


                deque.add(nextX to nextY)
                visited[i] = true
            }
        }

        return "sad"
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val t = br.readLine().toInt()
    val solution9205 = Solution9205()

    repeat(t) {
        val n = br.readLine().toInt()
        val graph = Array(n + 2) { br.readLine().split(" ").map(String::toInt).toIntArray() }

        bw.append("${solution9205.solution(n, graph)}\n")
    }
    bw.flush()

    br.close()
    bw.close()
}