package baekjoon.gold.solution_1516

import java.util.*
import kotlin.collections.ArrayDeque

private class Solution1516 {

    fun solution(
        n: Int,
        nodes: MutableList<Order>,
        graph: Array<MutableList<Order>>
    ): String {
        val results = IntArray(n + 1)

        val deque = ArrayDeque<Order>()

        for (i in 1..n) {
            if (nodes[i].inDegree == 0) {
                deque.add(nodes[i])
                results[i] = nodes[i].time
            }
        }

        while (deque.isNotEmpty()) {
            val currentNode = deque.removeFirst()

            for (sub in graph[currentNode.num]) {
                sub.inDegree -= 1
                results[sub.num] = maxOf(results[sub.num], results[currentNode.num] + sub.time)
                if (sub.inDegree == 0) {
                    deque.add(sub)
                }
            }
        }

        val sb = StringBuilder()

        for (i in 1 .. n) {
            sb.append("${results[i]}\n")
        }

        return sb.toString()
    }

    data class Order(
        val num: Int,
        val time: Int,
        var inDegree: Int = 0,
    )
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()

    val nodes = mutableListOf(Solution1516.Order(0, 0))
    val graph = Array(n + 1) { mutableListOf<Solution1516.Order>() }

    for (i in 1 .. n) {
        val st = StringTokenizer(br.readLine())
        nodes.add(Solution1516.Order(i, st.nextToken().toInt()))
        var next = st.nextToken().toInt()

        while (next != -1) {
            graph[next].add(nodes[i])
            nodes[i].inDegree += 1
            next = st.nextToken().toInt()
        }
    }

    bw.write(Solution1516().solution(n, nodes, graph))
    bw.flush()

    br.close()
    bw.close()
}