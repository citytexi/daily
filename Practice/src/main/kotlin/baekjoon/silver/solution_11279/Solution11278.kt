package baekjoon.silver.solution_11279

import java.util.PriorityQueue

private class Solution11278 {
    private val maxHeap = PriorityQueue<Int> { o1, o2 -> -(o1 - o2) }

    fun add(num: Int) {
        maxHeap.add(num)
    }

    fun print(): Int = when (maxHeap.isEmpty()) {
        true -> 0
        false -> maxHeap.poll()
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val solution11278 = Solution11278()

    repeat(br.readLine().toInt()) {
        when (val value = br.readLine().toInt()) {
            0 -> bw.append("${solution11278.print()}\n")
            else -> solution11278.add(value)
        }
    }
    bw.flush()

    br.close()
    bw.close()
}