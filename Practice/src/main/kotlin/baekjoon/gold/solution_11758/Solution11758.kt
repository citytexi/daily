package baekjoon.gold.solution_11758

private class Solution11758 {

    fun solution(
        first: Pair<Int, Int>,
        second: Pair<Int, Int>,
        third: Pair<Int, Int>
    ): Int {
        val a = first.first * second.second + second.first * third.second + third.first * first.second
        val b = first.second * second.first + second.second * third.first + third.second * first.first

        return when {
            a-b > 0 -> 1
            a-b < 0 -> -1
            else -> 0
        }
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (x1, y1) = br.readLine().split(" ").map(String::toInt)
    val (x2, y2) = br.readLine().split(" ").map(String::toInt)
    val (x3, y3) = br.readLine().split(" ").map(String::toInt)

    bw.append("${Solution11758().solution(x1 to y1, x2 to y2, x3 to y3)}\n")
    bw.flush()

    br.close()
    bw.close()
}