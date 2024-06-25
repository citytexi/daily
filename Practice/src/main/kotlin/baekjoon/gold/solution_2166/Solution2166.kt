package baekjoon.gold.solution_2166

import kotlin.math.abs

private class Solution2166 {

    fun solution(
        n: Int,
        list: MutableList<Pair<Long, Long>>
    ): String {
        list.add(list[0])

        var xSum = 0L
        var ySum = 0L

        for (i in 0 until n){
            xSum += list[i].first * list[i + 1].second
            ySum += list[i].second * list[i + 1].first
        }

        val result = abs((xSum - ySum))
        return "${result shr 1}.${(result and 1) * 5}"
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val list = mutableListOf<Pair<Long,Long>>()

    repeat(n){
        val (x, y) = br.readLine().split(" ").map(String::toLong)
        list.add(x to y)
    }

    bw.append(Solution2166().solution(n, list))
    bw.flush()
    
    br.close()
    bw.close()
}