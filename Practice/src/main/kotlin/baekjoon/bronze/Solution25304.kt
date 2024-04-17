package baekjoon.bronze

private class Solution25304 {
    fun solution(x: Int, arr: Array<Pair<Int, Int>>): String = when (arr.sumOf { it.first * it.second }) {
        x -> "Yes"
        else -> "No"
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val x = br.readLine().toInt()
    val arr = Array(br.readLine().toInt()) { br.readLine().split(" ").let { it[0].toInt() to it[1].toInt() } }

    bw.append("${Solution25304().solution(x, arr)}\n")
    bw.flush()

    br.close()
    bw.close()
}