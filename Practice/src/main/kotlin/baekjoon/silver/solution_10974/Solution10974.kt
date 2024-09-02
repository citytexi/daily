package baekjoon.silver.solution_10974

private class Solution10974 {

    fun solution(n: Int): String {
        val sb = StringBuilder()

        dfs(0, n, BooleanArray(n + 1), mutableListOf(), sb)

        return sb.toString()
    }

    private fun dfs(
        depth: Int,
        n: Int,
        visited: BooleanArray,
        result: MutableList<Int>,
        sb: StringBuilder,
    ) {
        if (depth >= n) {
            sb.append("${result.joinToString(" ")}\n")
            return
        }

        for (i in 1 .. n) {
            if (visited[i]) {
                continue
            }
            result.add(i)
            visited[i] = true
            dfs(depth + 1, n, visited, result, sb)
            result.removeLast()
            visited[i] = false
        }
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    bw.append(Solution10974().solution(br.readLine().toInt()))
    bw.flush()

    br.close()
    bw.close()
}