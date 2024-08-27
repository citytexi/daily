package baekjoon.silver.solution_10819

import kotlin.math.abs

private class Solution10819 {
    private var max = 0

    fun solution(
        n: Int,
        a: IntArray
    ): Int {
        max = 0

        dfs(mutableListOf(), a, BooleanArray(n))

        return max
    }

    private fun dfs(
        result: MutableList<Int>,
        a: IntArray,
        visited: BooleanArray
    ) {
        if (result.size == a.size) {
            max = maxOf(max, cal(result))
            return
        }

        for (i in a.indices) {
            if (visited[i]) {
                continue
            }
            visited[i] = true
            result.add(a[i])
            dfs(result, a, visited)
            result.removeLast()
            visited[i] = false
        }
    }

    private fun cal(result: MutableList<Int>): Int {
        var answer = 0

        for (i in 2 .. result.size) {
            answer += abs(result[i - 2] - result[i - 1])
        }

        return answer
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val a = br.readLine().split(" ").map(String::toInt).toIntArray()

    bw.append("${Solution10819().solution(n, a)}\n")
    bw.flush()

    br.close()
    bw.close()
}