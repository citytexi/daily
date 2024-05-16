package baekjoon.silver.solution_14889

import kotlin.math.abs

private class Solution14889 {
    private var result = Int.MAX_VALUE

    fun solution(
        n: Int,
        s: Array<IntArray>
    ): Int {
        result = Int.MAX_VALUE

        val all = IntArray(n) { it + 1 }

        dfs(
            index = 0,
            depth = 0,
            n = n,
            current = mutableListOf(),
            s = s,
            all = all,
            visited = BooleanArray(n)
        )

        return result
    }

    private fun dfs(
        index: Int,
        depth: Int,
        n: Int,
        current: MutableList<Int>,
        s: Array<IntArray>,
        all: IntArray,
        visited: BooleanArray
    ) {
        if (depth == n / 2) {
            var startTeamScore = 0
            var linkTeamScore = 0

            for (i in 0 until n - 1) {
                for (j in i + 1 until n) {
                    when {
                        visited[i] && visited[j] -> startTeamScore += s[i][j] + s[j][i]
                        !visited[i] && !visited[j] -> linkTeamScore += s[i][j] + s[j][i]
                    }
                }
            }

            result = minOf(result, abs(startTeamScore - linkTeamScore))

            return
        }

        for (i in index until all.size) {
            if (visited[i]) {
                continue
            }
            visited[i] = true
            current.add(all[i])
            dfs(i, depth + 1, n, current, s, all, visited)
            current.removeLast()
            visited[i] = false
        }
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val s = Array(n) { br.readLine().split(" ").map(String::toInt).toIntArray() }

    bw.append("${Solution14889().solution(n, s)}\n")
    bw.flush()

    br.close()
    bw.close()
}