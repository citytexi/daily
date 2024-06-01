package baekjoon.gold.solution_1504

import java.util.*

private class Solution1504 {

    fun solution(
        n: Int,
        graph: Array<MutableList<Pair<Int, Int>>>,
        v1: Int,
        v2: Int
    ): Int {
        val firstOneTrack = dijkstra(1, v1, graph, IntArray(n + 1) { Int.MAX_VALUE })
        val firstTwoTrack = dijkstra(v1, v2, graph, IntArray(n + 1) { Int.MAX_VALUE })
        val firstThreeTrack = dijkstra(v2, n, graph, IntArray(n + 1) { Int.MAX_VALUE })

        val firstResult = if (firstOneTrack == Int.MAX_VALUE || firstTwoTrack == Int.MAX_VALUE || firstThreeTrack == Int.MAX_VALUE) {
            Int.MAX_VALUE
        } else {
            firstOneTrack + firstTwoTrack + firstThreeTrack
        }

        val secondOneTrack = dijkstra(1, v2, graph, IntArray(n + 1) { Int.MAX_VALUE })
        val secondTwoTrack = dijkstra(v2, v1, graph, IntArray(n + 1) { Int.MAX_VALUE })
        val secondThreeTrack = dijkstra(v1, n, graph, IntArray(n + 1) { Int.MAX_VALUE })

        val secondResult = if (secondOneTrack == Int.MAX_VALUE || secondTwoTrack == Int.MAX_VALUE || secondThreeTrack == Int.MAX_VALUE) {
            Int.MAX_VALUE
        } else {
            secondOneTrack + secondTwoTrack + secondThreeTrack
        }


        return when (val value = minOf(firstResult, secondResult)) {
            Int.MAX_VALUE -> -1
            else -> value
        }
    }

    private fun dijkstra(
        start: Int,
        end: Int,
        graph: Array<MutableList<Pair<Int, Int>>>,
        distanceDp: IntArray
    ): Int {
        val queue = PriorityQueue<Pair<Int, Int>>(
            compareBy { it.second }
        ).apply {
            add(start to 0)
        }

        distanceDp[start] = 0

        while(queue.isNotEmpty()){
            val (currentNode, currentWeight) = queue.poll()

            if (distanceDp[currentNode] < currentWeight) {
                continue
            }

            if (currentNode == end) {
                break
            }

            for ((nextNode, nextWeight) in graph[currentNode]) {
                if (currentWeight + nextWeight >= distanceDp[nextNode]) {
                    continue
                }
                distanceDp[nextNode] = currentWeight + nextWeight
                queue.add(nextNode to distanceDp[nextNode])
            }
        }

        return distanceDp[end]
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, e) = br.readLine().split(" ").map(String::toInt)
    val graph = Array(n + 1) { mutableListOf<Pair<Int, Int>>()  }

    repeat(e) {
        val (a, b, c) = br.readLine().split(" ").map(String::toInt)

        graph[a].add(b to c)
        graph[b].add(a to c)
    }

    val (v1, v2) = br.readLine().split(" ").map(String::toInt)

    bw.append("${Solution1504().solution(n, graph, v1, v2)}\n")
    bw.flush()

    br.close()
    bw.close()
}