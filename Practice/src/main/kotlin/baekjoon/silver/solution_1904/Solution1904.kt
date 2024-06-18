package baekjoon.silver.solution_1904

private class Solution1904 {

    fun solution(n: Int): Int {
        val arr = IntArray(n + 1) {
            when (it) {
                1 -> 1
                2 -> 2
                else -> 0
            }
        }

        for (i in 3..n) {
            arr[i] = (arr[i - 1] + arr[i - 2]) % 15746
        }


        return arr[n]
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()

    bw.append("${Solution1904().solution(n)}\n")
    bw.flush()

    br.close()
    bw.close()
}