package baekjoon.gold.solution_10830

private class Solution10830 {

    fun solution(
        n: Int,
        b: Long,
        matrix: Array<LongArray>
    ): String {
        val result = getPowerMatrix(b, matrix)

        val sb = StringBuilder()

        for (row in result) {
            sb.append("${row.joinToString(" ")}\n")
        }

        return sb.toString()
    }

    private fun getPowerMatrix(
        b: Long,
        matrix: Array<LongArray>,
    ): Array<LongArray> = when(b) {
        1L -> {
            val temp = Array(matrix.size) { LongArray(matrix.size) }

            for (row in temp.indices) {
                for (col in temp[row].indices) {
                    temp[row][col] = matrix[row][col] % 1000L
                }
            }

            temp
        }
        else -> {
            val half = getPowerMatrix(b / 2, matrix)

            when {
                b % 2 == 0L -> multiMatrix(half, half)
                else -> multiMatrix(multiMatrix(half, half), matrix)
            }
        }
    }

    private fun multiMatrix(
        a: Array<LongArray>,
        b: Array<LongArray>
    ): Array<LongArray> {
        val size = a.size
        val temp = Array(size) { LongArray(size) }

        for (i in 0 until size) {
            for (j in 0 until size) {
                for (k in 0 until size) {
                    temp[i][j] += a[i][k] * b[k][j]
                    temp[i][j] %= 1000L
                }
            }
        }

        return temp
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, b) = br.readLine().split(" ")
    val matrix = Array(n.toInt()) { br.readLine().split(" ").map(String::toLong).toLongArray() }

    bw.write(Solution10830().solution(n.toInt(), b.toLong(), matrix))
    bw.flush()

    br.close()
    bw.close()
}