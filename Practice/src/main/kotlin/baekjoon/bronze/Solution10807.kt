package baekjoon.bronze

private class Solution10807 {
    fun solution(n: Int, nums: IntArray, v: Int): Int = nums.filter { it == v }.size
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val nums = br.readLine().split(" ").map(String::toInt).toIntArray()
    val v = br.readLine().toInt()

    bw.append("${Solution10807().solution(n, nums, v)}\n")
    bw.flush()

    br.close()
    bw.close()
}