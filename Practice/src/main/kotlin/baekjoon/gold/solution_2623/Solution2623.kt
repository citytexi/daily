package baekjoon.gold.solution_2623

private class Solution2623 {

    fun solution(
        n: Int,
        m: Int,
        inDegree: IntArray,
        graph: Array<MutableList<Int>>
    ): String {
        val deque = ArrayDeque<Int>()
        val sb = StringBuilder()

        for (i in 1 .. n) {
            if (inDegree[i] != 0) {
                continue
            }
            deque.add(i)
        }

        for (i in 1 .. n) {
            if (deque.isEmpty()) {
                return "0"
            }

            val current = deque.removeFirst()
            sb.append("$current\n")

            for (j in 0 until graph[current].size) {
                val other = graph[current][j]
                inDegree[other] -= 1

                if (inDegree[other] == 0) {
                    deque.add(other)
                }
            }
        }

        return sb.toString()
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)

    val inDegree = IntArray(n + 1)
    val graph = Array(n + 1) { mutableListOf<Int>() }

    for (i in 1..m) {
        val line = br.readLine().split(" ").map { it.toInt() }

        for (j in 2..line[0]) {
            inDegree[line[j]] += 1
            graph[line[j - 1]].add(line[j])
        }
    }

    bw.append("${Solution2623().solution(n, m, inDegree, graph)}\n")
    bw.flush()

    br.close()
    bw.close()
}