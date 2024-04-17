package baekjoon.bronze

private class Solution2525 {
    fun solution(a: Int, b: Int, c: Int): String {
        val time = when (60 * a + b + c) {
            in 0 until 1440 -> (60 * a + b + c)
            else -> (60 * a + b + c) - 1440
        }

        return "${time / 60} ${time % 60}"
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (a, b) = br.readLine().split(" ").map(String::toInt)
    val c = br.readLine().toInt()

    bw.append("${Solution2525().solution(a, b, c)}\n")
    bw.flush()

    br.close()
    bw.close()
}