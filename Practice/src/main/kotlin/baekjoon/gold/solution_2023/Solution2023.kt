package baekjoon.gold.solution_2023


private class Solution2023 {
    private val sb = StringBuilder()

    fun solution(n: Int): String {
        sb.clear()

        val dp = IntArray(n) { -1 }

        backTracking(0, n, dp)

        return sb.toString()
    }

    private fun backTracking(
        depth: Int,
        n: Int,
        dp: IntArray
    ) {
        if (depth == n) {
            if (isPrime(depth - 1, dp)) {
                sb.append(dp.joinToString("")).append("\n")
            }
            return
        }

        val start = when (depth) {
            0 -> 2
            else -> 0
        }

        for (i in start until 10) {
            dp[depth] = i

            if (!isPrime(depth, dp)) {
                continue
            }
            backTracking(depth + 1, n, dp)
        }
    }

    private fun isPrime(
        index: Int,
        dp: IntArray
    ): Boolean {
        var sum = 0

        for (i in 0 .. index) {
            sum = sum * 10 + dp[i]

            for (j in 2 until sum) {
                if (sum % j == 0) {
                    return false
                }
            }
        }

        return true
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()

    bw.append(Solution2023().solution(n))
    bw.flush()

    br.close()
    bw.close()
}