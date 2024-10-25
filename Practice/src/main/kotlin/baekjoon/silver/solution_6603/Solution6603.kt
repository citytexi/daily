package baekjoon.silver.solution_6603

private class Solution6603 {

    fun solution(s: IntArray): String {

        val sb = StringBuilder()
        val visited = BooleanArray(s.size)

        dfs(0, 0, s, visited, sb)

        sb.append("\n")

        return sb.toString()
    }

    private fun dfs(
        depth: Int,
        lastIndex: Int,
        s: IntArray,
        visited: BooleanArray,
        sb: StringBuilder
    ) {
        if (depth == 6) {
            for (i in s.indices) {
                if (visited[i]) {
                    sb.append(s[i]).append(" ")
                }
            }
            sb.append("\n")
        }

        for (i in lastIndex until s.size) {
            visited[i] = true
            dfs(depth + 1, i + 1, s, visited, sb)
            visited[i] = false
        }
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val solution6603 = Solution6603()

    while (true) {
        var n = 0
        val s = br.readLine()
            .split(" ")
            .filterIndexed { index, c ->
                when (index) {
                    0 -> {
                        n = c.toInt()
                        false
                    }

                    else -> true
                }
            }
            .map(String::toInt)
            .toIntArray()

        if (n == 0) {
            break
        }

        bw.append(solution6603.solution(s))
    }

    bw.flush()

    br.close()
    bw.close()
}
