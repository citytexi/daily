package baekjoon.gold

private class Solution2252 {

    fun solution(
        n: Int,
        m: Int,
        conditions: Array<Pair<Int, Int>>
    ): String {
        val graph = Array(n + 1) { mutableListOf<Int>() }
        val degree = IntArray(n + 1)

        for ((a, b) in conditions) {
            graph[a].add(b)
            degree[b] += 1
        }

        val deque = ArrayDeque<Int>()

        for (i in 1 .. n) {
            when (degree[i]) {
                0 -> deque.add(i)
                else -> Unit
            }
        }

        val result = mutableListOf<Int>()

        while (deque.isNotEmpty()) {
            val first = deque.removeFirst()
            result.add(first)

            for (sub in graph[first]) {
                degree[sub] -= 1
                if (degree[sub] == 0) {
                    deque.add(sub)
                }
            }
        }

        return result.joinToString(" ")
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)
    val conditions = Array(m) {
        val (a, b) = br.readLine().split(" ").map(String::toInt)
        a to b
    }

    bw.append("${Solution2252().solution(n, m, conditions)}\n")
    bw.flush()

    br.close()
    bw.close()
}