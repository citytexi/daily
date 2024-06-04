package baekjoon.gold.solution_2096

private class Solution2096 {

    fun solution(
        n: Int,
        map: Array<IntArray>
    ): String {
        val maxMap = Array(n) { row -> IntArray(3) { col -> map[row][col] } }
        val minMap = Array(n) { row -> IntArray(3) { col -> map[row][col] } }

        for (i in 1 until n) {
            maxMap[i][0] = maxOf(maxMap[i - 1][0] + maxMap[i][0], maxMap[i - 1][1] + maxMap[i][0])
            maxMap[i][1] = maxOf(maxMap[i - 1][0] + maxMap[i][1], maxOf(maxMap[i - 1][1] + maxMap[i][1], maxMap[i - 1][2] + maxMap[i][1]))
            maxMap[i][2] = maxOf(maxMap[i - 1][2] + maxMap[i][2], maxMap[i - 1][1] + maxMap[i][2])

            minMap[i][0] = minOf(minMap[i - 1][0] + minMap[i][0], minMap[i - 1][1] + minMap[i][0])
            minMap[i][1] = minOf(minMap[i - 1][0] + minMap[i][1], minOf(minMap[i - 1][1] + minMap[i][1], minMap[i - 1][2] + minMap[i][1]))
            minMap[i][2] = minOf(minMap[i - 1][2] + minMap[i][2], minMap[i - 1][1] + minMap[i][2])
        }

        val maxResult = maxOf(maxMap[n - 1][0], maxOf(maxMap[n - 1][1], maxMap[n - 1][2]))
        val minResult = minOf(minMap[n - 1][0], minOf(minMap[n - 1][1], minMap[n - 1][2]))

        return "$maxResult $minResult"
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()

    val map = Array(n) { br.readLine().split(" ").map(String::toInt).toIntArray() }

    bw.append(Solution2096().solution(n, map))
    bw.flush()

    br.close()
    bw.close()
}