package baekjoon.gold

import kotlin.math.min

private class Solution13549 {
    fun solution(n: Int, k: Int): Int = when {
        n > k -> n - k
        n == k -> 0
        else -> {
            val deque = ArrayDeque<Pair<Int, Int>>()
            val visited = BooleanArray(2 * k + 1)
            val countArray = IntArray(2 * k + 1) { -1 }

            deque.add(n to 0)
            visited[n] = true
            countArray[n] = 0

            while (deque.isNotEmpty()) {
                val (currentNum, currentCount) = deque.removeFirst()

                if (currentNum in countArray.indices) {
                    countArray[currentNum] = when (countArray[currentNum]) {
                        -1 -> currentCount
                        else -> min(countArray[currentNum], currentCount)
                    }
                }

                countArray.getOrNull(currentNum * 2)?.let { mul ->
                    if (mul == -1 || mul > currentCount) {
                        deque.add(currentNum * 2 to currentCount)
                    }
                }
                countArray.getOrNull(currentNum + 1)?.let { next ->
                    if (next == -1 || next > currentCount + 1) {
                        deque.add(currentNum + 1 to currentCount + 1)
                    }
                }
                countArray.getOrNull(currentNum - 1)?.let { prev ->
                    if (prev == -1 || prev > currentCount + 1) {
                        deque.add(currentNum - 1 to currentCount + 1)
                    }
                }
            }

            countArray[k]
        }
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, k) = br.readLine().split(" ").map { it.toInt() }
    val solution13549 = Solution13549()

    bw.append("${solution13549.solution(n, k)}\n")
    bw.flush()

    br.close()
    bw.close()
}