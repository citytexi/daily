package baekjoon.gold.solution_1647

import java.util.*

private class Solution1647 {

    fun solution(
        n: Int,
        graph: Array<MutableList<Pair<Int, Int>>>
    ): Int {
        val visited = BooleanArray(n + 1)
        val queue = PriorityQueue<Pair<Int,Int>> { o1, o2 ->
            o1.second - o2.second
        }

        var count = 0
        var result = 0
        var max = 0

        queue.add(1 to 0)

        while (queue.isNotEmpty()) {
            val (current, cost) = queue.poll()

            if (visited[current])
                continue

            visited[current] = true
            count += 1
            result += cost

            if (max < cost) {
                max = cost
            }

            if (count == n) {
                break
            }

            graph[current].filterNot { visited[it.first] }.toCollection(queue)
        }

        return result - max
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)
    val graph = Array(n + 1) { mutableListOf<Pair<Int, Int>>() }

    repeat(m) {
        val (a, b, c) = br.readLine().split(" ").map(String::toInt)
        graph[a].add(b to c)
        graph[b].add(a to c)
    }

    bw.append("${Solution1647().solution(n, graph)}\n")
    bw.flush()

    br.close()
    bw.close()
}