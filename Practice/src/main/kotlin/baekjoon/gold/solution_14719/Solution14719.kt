package baekjoon.gold.solution_14719

private class Solution14719 {

    fun solution(
        h: Int,
        w: Int,
        graph: Array<IntArray>
    ): Int {
        var result = 0

        for (row in 0 until h) {
            var isSave = false
            var count = 0

            var col = 0
            while (col < w) {
                val current = graph[row][col]

                when (isSave) {
                    true -> when (current) {
                        0 -> count += 1
                        else -> {
                            result += count
                            count = 0
                        }
                    }

                    false -> if (current == 1) {
                        isSave = true
                    }
                }

                col += 1
            }
        }

        return result
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (h, w) = br.readLine().split(" ").map(String::toInt)
    val graph = Array(h) { IntArray(w) }
    val inputs = br.readLine().split(" ").map(String::toInt)

    for (col in 0 until w) {
        for (row in 0 until inputs[col]) {
            graph[row][col] = 1
        }
    }

    bw.append("${Solution14719().solution(h, w, graph)}\n")
    bw.flush()

    br.close()
    bw.close()
}