package baekjoon.gold.solution_1744

import java.util.PriorityQueue

private class Solution1744 {

    fun solution(
        positiveNum: PriorityQueue<Int>,
        negativeNum: PriorityQueue<Int>,
        containZero: Boolean
    ): Int {

        var result = 0

        result += handle(positiveNum)

        if (positiveNum.isNotEmpty()) {
            result += positiveNum.poll()
        }

        result += when {
            negativeNum.size == 1 && containZero -> 0
            else -> handle(negativeNum)
        }

        if (negativeNum.isNotEmpty()) {
            if (!containZero) {
                result += negativeNum.poll()
            }
        }

        return result
    }

    private fun handle(queue: PriorityQueue<Int>): Int = when (queue.size) {
        0 -> 0
        1 -> queue.poll()
        else -> {
            var result = 0

            while (queue.size >= 2) {
                val first = queue.poll()
                val second = queue.poll()

                val sum = first + second
                val mul = first * second

                result += if (sum > mul) {
                    sum
                } else {
                    mul
                }
            }

            result
        }
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val positiveNum = PriorityQueue<Int> { v1, v2 -> v2 - v1 }
    val negativeNum = PriorityQueue<Int>()
    var containZero = false

    repeat(n) {
        val num = br.readLine().toInt()

        when {
            num == 0 -> containZero = true
            num > 0 -> positiveNum.offer(num)
            else -> negativeNum.offer(num)
        }
    }

    bw.append("${Solution1744().solution(positiveNum, negativeNum, containZero)}\n")
    bw.flush()

    br.close()
    bw.close()
}