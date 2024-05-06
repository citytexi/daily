package baekjoon.gold.solution_1238

import java.util.*

private class Solution1238 {

    fun solution(
        x: Int,
        nodes: List<PriorityQueue<Node>>
    ): Int {
        val results = IntArray(nodes.size) { 0 }

        for (i in results.indices) {
            results[i] += dijkstra(i, x - 1, nodes)
            results[i] += dijkstra(x - 1, i, nodes)
        }

        return results.max()
    }

    private fun dijkstra(
        start: Int,
        end: Int,
        nodes: List<PriorityQueue<Node>>
    ): Int {
        val queue: PriorityQueue<Node> = PriorityQueue()
        val weights = IntArray(nodes.size) { Int.MAX_VALUE }
        val visited = BooleanArray(nodes.size) { false }

        queue.add(Node(start, 0))
        weights[start] = 0

        while (queue.isNotEmpty()) {
            val current = queue.poll().index

            if (visited[current]) {
                continue
            }

            visited[current] = true

            for (node in nodes[current]) {
                if (weights[node.index] > weights[current] + node.weight) {
                    weights[node.index] = weights[current] + node.weight
                    queue.add(Node(node.index, weights[node.index]))
                }
            }
        }

        return weights[end]
    }

    data class Node(
        val index: Int,
        val weight: Int
    ): Comparable<Node> {
        override fun compareTo(other: Node): Int = this.weight - other.weight
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val nodes: MutableList<PriorityQueue<Solution1238.Node>> = mutableListOf()

    val (n, m, x) = br.readLine().split(" ").map(String::toInt)

    repeat(n) {
        nodes.add(PriorityQueue())
    }

    repeat(m) {
        val (start, end, w) = br.readLine().split(" ").map(String::toInt)
        nodes[start - 1].add(Solution1238.Node(end - 1, w))
    }

    bw.append("${Solution1238().solution(x, nodes)}\n")
    bw.flush()

    br.close()
    bw.close()
}