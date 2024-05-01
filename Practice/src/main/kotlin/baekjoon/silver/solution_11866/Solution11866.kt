package baekjoon.silver.solution_11866

private class Solution11866 {
    fun solution(n: Int, k: Int): String {
        val deque = ArrayDeque<Int>().apply {
            for (i in 1 .. n) {
                add(i)
            }
        }

        val result = mutableListOf<Int>()
        var count = 1

        while (deque.isNotEmpty()) {
            count = when (count) {
                k -> {
                    result.add(deque.removeFirst())
                    1
                }
                else -> {
                    deque.addLast(deque.removeFirst())
                    count + 1
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

    bw.append("${Solution11866().solution(n, k)}\n")
    bw.flush()

    br.close()
    bw.close()
}