package baekjoon.bronze

private class Solution18108 {
    fun solution(year: Int): Int = year - 543
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    bw.append("${Solution18108().solution(br.readLine().toInt())}\n")
    bw.flush()

    br.close()
    bw.close()
}