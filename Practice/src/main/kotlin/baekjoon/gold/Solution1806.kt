package baekjoon.gold

private class Solution1806 {

    fun solution(s: Long, numbers: LongArray): Int {
        var currentMinLength = 100_001

        var startIndex = 0
        var endIndex = 0
        var rangeSum = 0L

        while (endIndex < numbers.size) {
            rangeSum += numbers[endIndex]

            while (rangeSum >= s) {
                if (currentMinLength > endIndex - startIndex) {
                    currentMinLength = endIndex - startIndex
                }
                rangeSum -= numbers[startIndex]
                startIndex += 1
            }

            endIndex += 1
        }


        return when (currentMinLength) {
            100_001 -> 0
            else -> currentMinLength + 1
        }
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val solution1806 = Solution1806()

    val (n, s) = br.readLine().split(" ")
    val numbers = br.readLine().split(" ").map { it.toLong() }.toLongArray()

    bw.append("${solution1806.solution(s.toLong(), numbers)}\n")
    bw.flush()

    br.close()
    bw.close()
}