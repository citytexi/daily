package baekjoon.silver.solution_2477

private class Solution2477 {

    fun solution(
        k: Int,
        graph: Array<Node>,
    ): Int {

        for (i in 3 until 12) {
            if (graph[i].direction == graph[i - 2].direction && graph[i - 1].direction == graph[i - 3].direction) {
                return (graph[i + 1].length * graph[i + 2].length - graph[i - 2].length * graph[i - 1].length) * k
            }
        }


        return -1
    }

    data class Node(
        val direction: Int,
        val length: Int,
    )
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val k = br.readLine().toInt()

    val graph = Array(12) { Solution2477.Node(0, 0) }

    repeat(6) {
        val (direction, length) = br.readLine().split(" ").map(String::toInt)

        graph[it] = Solution2477.Node(direction, length)
        graph[it + 6] = Solution2477.Node(direction, length)
    }

    bw.append(Solution2477().solution(k, graph).toString())
    bw.flush()

    br.close()
    bw.close()
}