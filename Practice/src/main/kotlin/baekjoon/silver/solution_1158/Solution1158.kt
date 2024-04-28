package baekjoon.silver.solution_1158

private class Solution1158 {

    fun solution(n: Int, k: Int): String {
        val deque = ArrayDeque<Int>()

        for (i in 1 .. n) {
            deque.add(i)
        }

        var count = 1
        val result = mutableListOf<Int>()
        while (deque.isNotEmpty()) {
            when (count) {
                k -> {
                    result.add(deque.removeFirst())
                    count = 1
                }
                else -> {
                    deque.addLast(deque.removeFirst())
                    count += 1
                }
            }
        }

        return "<${result.joinToString(", ")}>"
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, k) = br.readLine().split(" ").map(String::toInt)

    bw.append("${Solution1158().solution(n, k)}\n")
    bw.flush()

    br.close()
    bw.close()
}