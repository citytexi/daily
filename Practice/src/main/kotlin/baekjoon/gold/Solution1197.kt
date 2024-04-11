package baekjoon.gold

import java.util.PriorityQueue

private class Solution1197 {
    private lateinit var parent: IntArray

    fun solution(
        v: Int,
        edges: Array<Edge>
    ): Long {
        parent = IntArray(v + 1) { it }

        val deque = PriorityQueue<Edge>()
        deque.addAll(edges)

        var result = 0L
        while (deque.isNotEmpty()) {
            val first = deque.poll()
            if (union(first.a, first.b)) {
                println("first.weight = ${first.weight}")
                result += first.weight
            }
        }

        return result
    }

    private fun find(num: Int): Int = when (num) {
        parent[num] -> num
        else -> {
            parent[num] = find(parent[num])
            parent[num]
        }
    }

    private fun union(x: Int, y: Int): Boolean {
        val findX = find(x)
        val findY = find(y)

        return when (findX) {
            findY -> false
            else -> {
                parent[findX] = findY
                true
            }
        }
    }

    data class Edge(
        val a: Int,
        val b: Int,
        val weight: Int
    ): Comparable<Edge> {
        override fun compareTo(other: Edge): Int = this.weight.compareTo(other.weight)
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (v, e) = br.readLine().split(" ").map(String::toInt)
    val graph = mutableListOf<Solution1197.Edge>()

    repeat(e) {
        val (start, end, weight) = br.readLine().split(" ").map(String::toInt)
        graph.add(Solution1197.Edge(start, end, weight))
    }

    bw.append("${Solution1197().solution(v, graph.toTypedArray())}\n")
    bw.flush()

    br.close()
    bw.close()
}