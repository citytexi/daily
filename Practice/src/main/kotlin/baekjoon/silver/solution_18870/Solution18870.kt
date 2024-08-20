package baekjoon.silver.solution_18870

private class Solution18870 {

    fun solution(
        n: Int,
        x: LongArray
    ): String {
        val hashMap = HashMap<Long, Int>().apply {
            var index = 0
            x.distinct().sorted().forEach { this[it] = index++ }
        }

        val sb = StringBuilder()
        for (i in 0 until n) {
            sb.append("${hashMap[x[i]]} ")
        }

        return sb.toString()
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val x = br.readLine().split(" ").map(String::toLong).toLongArray()

    bw.append(Solution18870().solution(n, x))
    bw.flush()

    br.close()
    bw.close()
}