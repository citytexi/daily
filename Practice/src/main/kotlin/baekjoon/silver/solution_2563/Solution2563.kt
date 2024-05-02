package baekjoon.silver.solution_2563

private class Solution2563 {

    fun solution(pairs: Array<Pair<Int, Int>>): Int {
        val visited = Array(100) { BooleanArray(100) }

        for (pair in pairs) {
            for (row in pair.first until pair.first + 10) {
                for (col in pair.second until pair.second + 10) {
                    if (visited[row][col]) {
                        continue
                    }
                    visited[row][col] = true
                }
            }
        }

        var result = 0

        for (row in visited.indices) {
            for (col in visited[row].indices) {
                if (visited[row][col]) {
                    result += 1
                }
            }
        }

        return result
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()

    val pairs = Array(n) {
        val str = br.readLine().split(" ")
        str[0].toInt() to str[1].toInt()
    }

    bw.append("${Solution2563().solution(pairs)}\n")
    bw.flush()

    br.close()
    bw.close()
}