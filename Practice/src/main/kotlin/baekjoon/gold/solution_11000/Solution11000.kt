package baekjoon.gold.solution_11000

import java.util.*

private class Solution11000 {
    private val list = mutableListOf<Pair<Int, Int>>()
    private val room = PriorityQueue<Int>()

    fun addPair(s: Int, t: Int) {
        list.add(s to t)
    }

    fun solution(): Int {
        list.sortWith { p1, p2 -> p1.first - p2.first }

        val n = list.size

        for (i in 0 until n) {
            when (i) {
                0 -> room.add(list[i].second)
                else -> {
                    when {
                        room.peek() <= list[i].first -> {
                            room.poll()
                            room.add(list[i].second)
                        }
                        else -> room.add(list[i].second)
                    }
                }
            }
        }

        return room.size
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val solution11000 = Solution11000()

    repeat(n) {
        val (s, t) = br.readLine().split(" ").map(String::toInt)
        solution11000.addPair(s, t)
    }

    bw.append("${solution11000.solution()}\n")
    bw.flush()

    br.close()
    bw.close()
}