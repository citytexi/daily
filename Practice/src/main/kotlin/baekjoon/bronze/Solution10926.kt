package baekjoon.bronze

private class Solution10926 {
    fun solution(str: String): String = "$str??!"
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    bw.append("${Solution10926().solution(br.readLine())}\n")
    bw.flush()

    br.close()
    bw.close()
}