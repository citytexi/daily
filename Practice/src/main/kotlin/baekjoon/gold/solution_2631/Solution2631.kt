package baekjoon.gold.solution_2631

private class Solution2631 {

    fun solution(
        n: Int,
        arr: IntArray
    ): Int {
        val dp = IntArray(n) { 1 }

        for (i in arr.indices) {
            for (j in i + 1 until arr.size) {
                if (arr[i] >= arr[j]) {
                    continue
                }

                dp[j] = maxOf(dp[j], dp[i] + 1)
            }
        }

        return when (dp.isEmpty()) {
            true -> n
            false -> n - dp.max()
        }
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val arr = IntArray(n) { br.readLine().toInt() }

    bw.append("${Solution2631().solution(n, arr)}\n")
    bw.flush()

    br.close()
    bw.close()
}