package baekjoon.gold.solution_1005

private class Solution1005 {
    fun solution(
        n: Int,
        d: IntArray,
        inDegree: IntArray,
        graph: Array<MutableList<Int>>,
        w: Int
    ): Int {
        val deque = ArrayDeque<Int>()
        val result = IntArray(n) {
            if (inDegree[it] == 0) {
                deque.add(it)
            }
            d[it]
        }

        while (deque.isNotEmpty()) {
            val current = deque.removeFirst()

            for (next in graph[current]) {
                result[next] = maxOf(result[next], result[current] + d[next])
                inDegree[next] -= 1
                if (inDegree[next] == 0) {
                    deque.add(next)
                }
            }
        }

        return result[w - 1]
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    repeat(br.readLine().toInt()) {
        val (n, k) = br.readLine().split(" ").map(String::toInt)
        val inDegree = IntArray(n)
        val d = br.readLine().split(" ").map(String::toInt).toIntArray()
        val graph = Array<MutableList<Int>>(n) { mutableListOf() }
        repeat(k) {
            val (x, y) = br.readLine().split(" ").map(String::toInt)
            graph[x - 1].add(y - 1)
            inDegree[y - 1] += 1
        }
        val w = br.readLine().toInt()

        bw.append("${Solution1005().solution(n, d, inDegree, graph, w)}\n")
    }
    bw.flush()

    br.close()
    bw.close()
}