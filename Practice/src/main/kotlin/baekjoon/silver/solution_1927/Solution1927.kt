package baekjoon.silver.solution_1927

import java.util.PriorityQueue

private class Solution1927 {
    private val priorityQueue = PriorityQueue<Int>()

    fun add(x: Int) {
        priorityQueue.add(x)
    }

    fun remove(): Int = when (priorityQueue.size) {
        0 -> 0
        else -> priorityQueue.poll()
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val solution1927 = Solution1927()

    repeat(br.readLine().toInt()) {
        when (val commend = br.readLine().toInt()) {
            0 -> bw.append("${solution1927.remove()}\n")
            else -> solution1927.add(commend)
        }
    }

    bw.flush()

    br.close()
    bw.close()
}