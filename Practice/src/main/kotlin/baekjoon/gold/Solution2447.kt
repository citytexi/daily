package baekjoon.gold

private class Solution2447 {
    private val stringBuilder = StringBuilder()

    fun solution(num: Int) {
        stringBuilder.clear()

        num.box()

        val bw = System.out.bufferedWriter()
        bw.append(stringBuilder)
        bw.flush()
        bw.close()
    }

    private fun Int.box() {
        for (row in 0 until this) {
            for (col in 0 until this) {
                checkStar(row, col, this / 3)
            }
            stringBuilder.append("\n")
        }
    }

    private fun checkStar(
        row: Int,
        col: Int,
        num: Int
    ) {
        when {
            ((row / num) % 3 == 1) && ((col / num) % 3 == 1) -> stringBuilder.append(" ")
            num / 3 == 0 -> stringBuilder.append("*")
            else -> checkStar(row, col, num / 3)
        }
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()

    val num = br.readLine().toInt()

    Solution2447().solution(num)

    br.close()
}