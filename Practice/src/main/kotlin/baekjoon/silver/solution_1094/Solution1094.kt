package baekjoon.silver.solution_1094

private class Solution1094 {

    fun solution(x: Int): Int = x.toString(2)
        .count { it == '1' }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val x = br.readLine().toInt()

    bw.append("${Solution1094().solution(x)}\n")
    bw.flush()

    br.close()
    bw.close()
}