package baekjoon.gold.solution_1766

import java.util.PriorityQueue

private class Solution1766 {

    fun solution(
        n: Int,
        graph: Array<MutableList<Int>>,
        inDegree: IntArray
    ): String {
        val sb = StringBuilder()

        val priorityQueue = PriorityQueue<Int>()

        for (i in 1 .. n) {
            if (inDegree[i] == 0) {
                priorityQueue.offer(i)
            }
        }

        while (priorityQueue.isNotEmpty()) {
            val solve = priorityQueue.poll()

            graph[solve].forEach {
                inDegree[it] -= 1
                if (inDegree[it] == 0) {
                    priorityQueue.offer(it)
                }
            }

            sb.append("$solve ")
        }

        return sb.toString()
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)
    val graph = Array(n + 1) { mutableListOf<Int>() }
    val inDegree = IntArray(n + 1)

    repeat(m) {
        val (a, b) = br.readLine().split(" ").map(String::toInt)
        graph[a].add(b)
        inDegree[b] += 1
    }

    bw.write(Solution1766().solution(n, graph, inDegree))
    bw.flush()

    br.close()
    bw.close()
}