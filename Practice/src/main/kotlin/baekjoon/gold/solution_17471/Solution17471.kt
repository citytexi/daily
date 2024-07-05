package baekjoon.gold.solution_17471

import kotlin.math.abs

private class Solution17471 {
    private var result = Int.MAX_VALUE

    fun solution(
        n: Int,
        countGraph: IntArray,
        graph: Array<MutableList<Int>>
    ): Int {
        val list = mutableListOf<Int>()

        for (i in 1 .. n / 2) {
            dfs(1, i, n, list, countGraph, graph)
        }

        return when (result) {
            Int.MAX_VALUE -> -1
            else -> result
        }
    }

    private fun dfs(
        depth: Int,
        end: Int,
        n: Int,
        list: MutableList<Int>,
        countGraph: IntArray,
        graph: Array<MutableList<Int>>
    ) {
        if (end == 0) {
            bfs(n, list, countGraph, graph)
            return
        }

        for (i in depth .. n) {
            list.add(i)
            dfs(i + 1, end - 1, n, list, countGraph, graph)
            list.removeLast()
        }
    }

    private fun bfs(
        n: Int,
        list: MutableList<Int>,
        countGraph: IntArray,
        graph: Array<MutableList<Int>>
    ) {
        val firstGroup = MutableList(list.size) { list[it] }
        val secondGroup = mutableListOf<Int>()

        for (i in 1 .. n) {
            if (!firstGroup.contains(i)) {
                secondGroup.add(i)
            }
        }

        val deque = ArrayDeque<Int>()

        val first = firstGroup.removeFirst()
        var firstCount = countGraph[first]
        deque.add(first)

        while (!deque.isEmpty()) {
            val current = deque.removeFirst()

            for (next in graph[current]) {
                if (!firstGroup.contains(next)) {
                    continue
                }

                firstGroup.remove(next)
                firstCount += countGraph[next]
                deque.add(next)
            }
        }

        if (!firstGroup.isEmpty()) {
            return
        }

        val second = secondGroup.removeFirst()
        var secondCount = countGraph[second]
        deque.add(second)

        while (!deque.isEmpty()) {
            val current = deque.removeFirst()

            for (next in graph[current]) {
                if (!secondGroup.contains(next)) {
                    continue
                }
                secondGroup.remove(next)
                secondCount += countGraph[next]
                deque.add(next)
            }
        }

        if (!secondGroup.isEmpty()) {
            return
        }

        result = minOf(result, abs(firstCount - secondCount));
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.`out`.bufferedWriter()

    val n = br.readLine().toInt()
    val countGraph = br.readLine()
        .split(" ")
        .map(String::toInt)
        .toMutableList()
        .apply {
            add(0, 0)
        }.toIntArray()
    val graph = Array(n + 1) { mutableListOf<Int>() }

    for (i in 0 until n) {
        val inputs = br.readLine().split(" ").map(String::toInt)
        val count = inputs[0]

        for (j in 0 until count) {
            graph[i + 1].add(inputs[j + 1])
        }
    }

    bw.append("${Solution17471().solution(n, countGraph, graph)}")
    bw.flush()

    br.close()
    bw.close()
}