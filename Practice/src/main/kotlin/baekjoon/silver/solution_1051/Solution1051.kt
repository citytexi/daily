package baekjoon.silver.solution_1051

private class Solution1051 {

    fun solution(
        n: Int,
        m: Int,
        graph: Array<IntArray>,
    ): Int {
        val maxSize = maxOf(n, m)

        var currentMax = 0

        for (row in 0 until n) {
            for (col in 0 until m) {
                for (next in 1 .. maxSize) {
                    val topLeft = graph.getOrNull(row)?.getOrNull(col) ?: continue
                    val topRight = graph.getOrNull(row)?.getOrNull(col + next) ?: continue
                    val bottomLeft = graph.getOrNull(row + next)?.getOrNull(col) ?: continue
                    val bottomRight = graph.getOrNull(row + next)?.getOrNull(col + next) ?: continue

                    if (topLeft != topRight) {
                        continue
                    }

                    if (topLeft != bottomLeft) {
                        continue
                    }

                    if (topLeft != bottomRight) {
                        continue
                    }

                    currentMax = maxOf(currentMax, next)
                }
            }
        }

        return (currentMax + 1) * (currentMax + 1)
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)
    val graph = Array(n) { br.readLine().toCharArray().map { it.digitToInt() }.toIntArray() }

    bw.append(Solution1051().solution(n, m, graph).toString())
    bw.flush()

    br.close()
    bw.close()
}