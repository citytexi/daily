package baekjoon.gold.solution_1655

import java.util.PriorityQueue

private class Solution1655 {
    private val maxQueue = PriorityQueue<Int>()
    private val minQueue = PriorityQueue(Comparator<Int> { o1, o2 -> - (o1 - o2) })

    fun solution(num: Int): String {
        val stringBuilder = StringBuilder()

        when {
            minQueue.isEmpty() -> {
                minQueue.add(num)
                stringBuilder.append("${num}\n")
            }
            maxQueue.isEmpty() -> {
                if (minQueue.peek() > num) {
                    maxQueue.add(minQueue.poll())
                    minQueue.add(num)
                } else {
                    maxQueue.add(num)
                }
                stringBuilder.append("${minQueue.peek()}\n")
            }
            else -> {
                val min = minQueue.peek()
                val max = maxQueue.peek()

                when {
                    num > max -> {
                        if (minQueue.size <= maxQueue.size) {
                            maxQueue.poll()
                            minQueue.add(max)
                        }
                        maxQueue.add(num)
                    }
                    num in min .. max -> when {
                        minQueue.size > maxQueue.size -> maxQueue.add(num)
                        else -> minQueue.add(num)
                    }
                    else -> {
                        if (minQueue.size > maxQueue.size) {
                            minQueue.poll()
                            maxQueue.add(min)
                        }
                        minQueue.add(num)
                    }
                }

                stringBuilder.append("${minQueue.peek()}\n")
            }
        }

        return stringBuilder.toString()
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val solution1655 = Solution1655()

    repeat(br.readLine().toInt()) {
        bw.append(solution1655.solution(br.readLine().toInt()))
    }
    bw.flush()

    br.close()
    bw.close()
}