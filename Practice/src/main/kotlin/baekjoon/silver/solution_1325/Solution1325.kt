package baekjoon.silver.solution_1325

private class Solution1325 {

    fun solution(
        n: Int,
        graph: Array<MutableList<Int>>
    ): String {
        val results = IntArray(graph.size)

        for (i in 1..n) {
            val visited = BooleanArray(n + 1).apply { this[i] = true }

            val deque = ArrayDeque<Int>().apply { add(i) }

            while (deque.isNotEmpty()) {
                val target = deque.removeFirst()

                graph[target].forEach { nextNode ->
                    if (!visited[nextNode]) {
                        visited[nextNode] = true
                        deque.add(nextNode)
                        results[nextNode]++
                    }
                }
            }
        }

        val max = results.max()
        val sb = StringBuilder()

        results.forEachIndexed { index, value ->
            if (value == max) {
                sb.append(index).append(" ")
            }
        }

        return sb.toString()
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)
    val graph = Array(n + 1) { mutableListOf<Int>() }

    repeat(m) {
        val (a, b) = br.readLine().split(" ").map(String::toInt)
        graph[a].add(b)
    }

    bw.append(Solution1325().solution(n, graph))
    bw.flush()

    br.close()
    bw.close()
}
