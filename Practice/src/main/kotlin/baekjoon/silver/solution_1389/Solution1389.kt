package baekjoon.silver.solution_1389

private class Solution1389 {

    fun solution(
        n: Int,
        graph: Array<IntArray>,
    ): Int {

        for (k in 1 .. n) {
            for (i in 1 .. n) {
                if (i == k) {
                    continue
                }
                for (j in 1 .. n) {
                    if (j == k || j == i) {
                        continue
                    }
                    val ij = graph[i][j]
                    val ik = graph[i][k]
                    val kj = graph[k][j]

                    val sum = if (ik == Int.MAX_VALUE || kj == Int.MAX_VALUE) {
                        Int.MAX_VALUE
                    } else {
                        ik + kj
                    }

                    graph[i][j] = minOf(ij, sum)
                }
            }
        }

        var min = Int.MAX_VALUE
        var index = 0

        for (i in 1 .. n) {
            var sum = 0

            for (j in 1 .. n) {
                val value = graph[i][j]
                if (value == Int.MAX_VALUE) {
                    continue
                }

                sum += value
            }

            if (min > sum) {
                min = sum
                index = i
            }
        }

        return index
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)
    val graph = Array(n + 1) { IntArray(n + 1) { Int.MAX_VALUE } }

    repeat(m) {
        val (a, b) = br.readLine().split(" ").map(String::toInt)
        graph[a][b] = 1
        graph[b][a] = 1
    }

    bw.append("${Solution1389().solution(n, graph)}\n")
    bw.flush()

    br.close()
    bw.close()
}