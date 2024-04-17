package baekjoon.bronze

private class Solution11382 {
    fun solution(a: Long, b: Long, c: Long): Long = a + b + c
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (a, b, c) = br.readLine().split(" ").map(String::toLong)

    bw.append("${Solution11382().solution(a, b, c)}\n")
    bw.flush()

    br.close()
    bw.close()
}