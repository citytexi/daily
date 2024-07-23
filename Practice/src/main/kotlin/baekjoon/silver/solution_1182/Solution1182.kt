package baekjoon.silver.solution_1182

private class Solution1182 {
    private var result = 0

    fun solution(
        n: Int,
        s: Int,
        arr: IntArray
    ): Int {
        result = 0

        dfs(0, 0, 0, n, s, arr)

        return when (s) {
            0 -> result - 1
            else -> result
        }
    }

    private fun dfs(
        depth: Int,
        index: Int,
        sum: Int,
        n: Int,
        s: Int,
        arr: IntArray
    ) {
        if (sum == s) {
            result += 1
        }

        if (depth == n) {
            return
        }

        for (i in index until n) {
            dfs(
                depth = depth + 1,
                index = i + 1,
                sum = sum + arr[i],
                n = n,
                s = s,
                arr = arr
            )
        }

    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, s) = br.readLine().split(" ").map(String::toInt)
    val arr = br.readLine().split(" ").map(String::toInt).toIntArray()

    bw.append("${Solution1182().solution(n, s, arr)}\n")
    bw.flush()

    br.close()
    bw.close()
}