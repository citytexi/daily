package baekjoon.gold.solution_11657

private class Solution11657 {

    fun solution(
        n: Int,
        graph: MutableList<Triple<Int, Int, Long>>
    ) {
        val distance = LongArray(n + 1) { Long.MAX_VALUE }

        distance[1] = 0

        var isCycle = false

        for (i in 1 .. n) {
            for (current in graph) {
                val (start, end, value) = current
                val nextValue = distance[start] + value

                if (distance[start] == Long.MAX_VALUE) {
                    continue
                }

                if (distance[end] <= nextValue) {
                    continue
                }

                distance[end] = nextValue

                if (i == n) {
                    isCycle = true
                }
            }
        }

        val bw = System.out.bufferedWriter()
        when (isCycle) {
            true -> bw.append("-1\n")
            false -> {
                for (i in 2 .. n) {
                    if (distance[i] == Long.MAX_VALUE) {
                        distance[i] = -1
                    }
                    bw.append("${distance[i]}\n")
                }
            }
        }
        bw.flush()
        bw.close()
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()

    val (n, m) = br.readLine().split(" ").map(String::toInt)

    val graph = mutableListOf<Triple<Int, Int, Long>>()
    repeat(m) {
        val (a, b, c) = br.readLine().split(" ")
        graph.add(Triple(a.toInt(), b.toInt(), c.toLong()))
    }

    Solution11657().solution(n, graph)

    br.close()
}