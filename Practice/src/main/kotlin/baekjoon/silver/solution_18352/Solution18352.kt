package baekjoon.silver.solution_18352

private class Solution18352 {

    fun solution(
        n: Int,
        k: Int,
        x: Int,
        graph: Array<MutableList<Int>>,
    ): String {
        val distances = IntArray(graph.size) { -1 }.apply { this[x] = 0 }

        val deque = ArrayDeque<Int>().apply { add(x) }

        while (deque.isNotEmpty()) {
            val currentNode = deque.removeFirst()

            for (next in graph[currentNode]) {
                if (distances[next] != -1) {
                    continue
                }

                deque.add(next)
                distances[next] = distances[currentNode] + 1
            }
        }

        val results = mutableListOf<Int>()

        for (i in 1 until distances.size) {
            val distance = distances[i]
            if (distance == k) {
                results.add(i)
            }
        }

        return when (results.size) {
            0 -> "-1"
            else -> results.joinToString("\n")
        }
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    // 도시의 개수 N, 도로의 개수 M, 거리 정보 K, 출발 도시의 번호 X
    val (n, m, k, x) = br.readLine().split(" ").map(String::toInt)
    val graph = Array(n + 1) { mutableListOf<Int>() }

    repeat(m) {
        val (a, b) = br.readLine().split(" ").map(String::toInt)

        graph[a].add(b)
    }

    bw.append(Solution18352().solution(n, k, x, graph))
    bw.flush()

    br.close()
    bw.close()
}
