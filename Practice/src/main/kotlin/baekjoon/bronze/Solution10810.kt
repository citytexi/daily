package baekjoon.bronze

private class Solution10810 {
    fun solution(n: Int, inputs: Array<IntArray>): String {
        val result = IntArray(n)

        for ((i, j, k) in inputs) {
            for (index in i - 1 until j) {
                result[index] = k
            }
        }

        return result.joinToString(" ")
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)
    val inputs = Array(m) {
        val input = br.readLine().split(" ").map(String::toInt)
        IntArray(3) { input[it] }
    }

    bw.append("${Solution10810().solution(n, inputs)}\n")
    bw.flush()

    br.close()
    bw.close()
}