package baekjoon.bronze

private class Solution25314 {
    fun solution(num: Int): String = MutableList(num / 4) { "long" }.apply { add("int") }.joinToString(" ")
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    bw.append("${Solution25314().solution(br.readLine().toInt())}\n")
    bw.flush()

    br.close()
    bw.close()
}