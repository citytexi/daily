package baekjoon.gold.solution_1939

import java.util.*

private class Solution1939 {

    fun solution(
        n: Int,
        graph: Array<MutableList<Pair<Int, Int>>>,
        start: Int,
        end: Int,
    ): Int {
        val visited = BooleanArray(n + 1).apply { this[start] = true }
        var result = Int.MAX_VALUE

        val pq = PriorityQueue<Pair<Int,Int>> { a, b ->
            b.second.compareTo(a.second)
        }.apply { add(start to Int.MAX_VALUE) }


        while (pq.isNotEmpty()) {
            val current = pq.poll()

            result = minOf(result, current.second)
            if (current.first == end) {
                break
            }
            visited[current.first] = true
            for(next in graph[current.first]){
                if(visited[next.first]) {
                    continue
                }
                pq.add(next.first to next.second)
            }
        }

        return result
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

    val (start, end) = br.readLine().split(" ").map(String::toInt)

    bw.append(Solution1939().solution(n, graph, start, end).toString())
    bw.flush()

    br.close()
    bw.close()
}
