package baekjoon.gold.solution_1339

import kotlin.math.pow

private class Solution1339(
    private val map: HashMap<Char, Int> = hashMapOf()
) {

    fun putSolution(str: String) {
        var lastIndex = str.lastIndex

        for (i in str.indices) {
            val current = str[i]
            val temp = 10.0.pow(lastIndex)

            when (val value = map[current]) {
                null -> map[current] = temp.toInt()
                else -> map[current] = value + temp.toInt()
            }

            lastIndex -= 1
        }
    }

    fun solution(): Int {
        var result = 0
        var num = 9

        val list  = map.toList().sortedByDescending { it.second }

        for (i in 0 until map.size) {
            result += list[i].second * num
            num -= 1
        }

        return result
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val solution = Solution1339()

    repeat(n) {
        solution.putSolution(br.readLine())
    }

    bw.write("${solution.solution()}\n")
    bw.flush()

    br.close()
    bw.close()
}