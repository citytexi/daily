package baekjoon.gold.solution_5052

import java.util.*

private class Solution5052 {

    fun solution(
        n: Int,
        priorityQueue: PriorityQueue<String>
    ): String {

        while (priorityQueue.size > 1) {
            val left = priorityQueue.poll()
            val right = priorityQueue.peek()

            val size = if (left.length > right.length) {
                right.length
            } else {
                left.length
            }

            if (left == right.substring(0, size)) {
                return "NO\n"
            }
        }

        return "YES\n"
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val solution5052 = Solution5052()
    val t = br.readLine().toInt()

    repeat(t) {
        val n = br.readLine().toInt()
        val priorityQueue = PriorityQueue<String>()

        repeat(n) {
            val number = br.readLine()

            priorityQueue.add(number)
        }

        bw.append(solution5052.solution(n, priorityQueue))
    }
    bw.flush()

    br.close()
    bw.close()
}