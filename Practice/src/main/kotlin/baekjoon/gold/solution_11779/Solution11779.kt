package baekjoon.gold.solution_11779

import java.util.*
import kotlin.collections.ArrayDeque

private class Solution11779 {

    fun solution(
        n: Int,
        m: Int,
        graph: Array<MutableList<Pair<Int, Int>>>,
        start: Int,
        end: Int
    ): String {
        val prev = IntArray(graph.size) { -1 }
        val result = dijkstra(start, end, graph, prev)

        val sb = StringBuilder()
        sb.append("$result\n")

        var count = 1
        var current = end
        val deque = ArrayDeque<Int>()

        while (current != start) {
            count += 1
            deque.add(current)
            current = prev[current]
        }

        sb.append("$count\n")
        sb.append("$start ")
        while (deque.isNotEmpty()) {
            sb.append("${deque.removeLast()} ")
        }

        return sb.toString()
    }

    private fun dijkstra(
        start: Int,
        end: Int,
        graph: Array<MutableList<Pair<Int, Int>>>,
        prev: IntArray
    ): Int {
        var result = 0

        val queue: PriorityQueue<Pair<Int, Int>> = PriorityQueue(compareBy { it.second })
        val weights = IntArray(graph.size) { Int.MAX_VALUE }
        queue.offer(start to 0)
        weights[start] = 0

        while (queue.isNotEmpty()) {
            val (currentNode, currentWeight) = queue.poll()

            when {
                currentNode == end -> {
                    result = currentWeight
                    break
                }
                currentWeight > weights[currentNode] -> continue
            }

            for ((nextNode, nextWeight) in graph[currentNode]) {
                if (weights[nextNode] <= weights[currentNode] + nextWeight) {
                    continue
                }

                weights[nextNode] = weights[currentNode] + nextWeight
                prev[nextNode] = currentNode
                queue.offer(nextNode to weights[nextNode])
            }
        }

        return result
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val m = br.readLine().toInt()
    val graph = Array(n + 1) { mutableListOf<Pair<Int, Int>>() }

    repeat(m) {
        val (start, end, cost) = br.readLine().split(" ").map(String::toInt)
        graph[start].add(end to cost)
    }

    val (start, end) = br.readLine().split(" ").map(String::toInt)

    bw.write(Solution11779().solution(n, m, graph, start, end))
    bw.flush()

    br.close()
    bw.close()
}