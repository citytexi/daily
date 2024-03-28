package baekjoon.gold

import kotlin.math.min

private class Solution1107 {
    fun solution(n: Int, list: List<Int>): Int {
        val (currentResult, currentCount) = isAcceptRemoteControl(n, list)
        val result = if (currentResult) {
            currentCount
        } else {
            var endCount = Int.MAX_VALUE

            var count = 1
            val current = n
            while (list.isNotEmpty()) {
                val prev = current - count
                if (prev >= 0) {
                    val (prevResult, prevCount) = isAcceptRemoteControl(prev, list)

                    if (prevResult) {
                        endCount = prevCount + count
                        break
                    }
                }

                val next = current + count

                val (nextResult, nextCount) = isAcceptRemoteControl(next, list)

                if (nextResult) {
                    endCount = nextCount + count
                    break
                }

                count += 1
            }

            endCount
        }




        var min = 0
        var current = 100
        while (min < result && current != n) {
            current = if (n > current) {
                min += 1
                current + 1
            } else {
                min += 1
                current - 1
            }
        }

        return min(min, result)
    }

    private fun isAcceptRemoteControl(num: Int, list: List<Int>): Pair<Boolean, Int> {
        var isAccept = true
        var count = 0

        val str = num.toString()

        for (i in str.indices) {
            val char = str[i]
            if (char.digitToInt() !in list) {
                isAccept = false
                break
            }
            count += 1
        }

        return isAccept to count
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val m = br.readLine().toInt()
    val list = MutableList(10) { it }
    when (m) {
        0 -> Unit
        else -> br.readLine().split(" ").forEach {
            list.remove(it.toInt())
        }
    }

    val solution1107 = Solution1107()
    bw.append("${solution1107.solution(n, list)}\n")
    bw.flush()

    br.close()
    bw.close()
}