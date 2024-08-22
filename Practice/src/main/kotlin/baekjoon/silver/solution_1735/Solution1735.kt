package baekjoon.silver.solution_1735

private class Solution1735 {

    fun solution(
        a: Pair<Int, Int>,
        b: Pair<Int, Int>
    ): String {
        val bottom = lcm(a.second, b.second)
        val topSum = a.first * (bottom / a.second) + b.first * (bottom / b.second)

        val gcd = gcd(topSum, bottom)
        val result = topSum / gcd to bottom / gcd

        val sb = StringBuilder()
        sb.append("${result.first} ${result.second}")

        return sb.toString()
    }

    private fun gcd(
        a: Int,
        b: Int
    ): Int = when (b) {
        0 -> a
        else -> gcd(b, a % b)
    }

    private fun lcm(
        a: Int,
        b: Int
    ): Int = a * b / gcd(a, b)

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (a1, a2) = br.readLine().split(" ").map(String::toInt)
    val (b1, b2) = br.readLine().split(" ").map(String::toInt)

    bw.append(Solution1735().solution(a1 to a2, b1 to b2))
    bw.flush()

    br.close()
    bw.close()
}