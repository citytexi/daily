package baekjoon.gold.solution_2629

import kotlin.math.abs

private class Solution2629 {

    fun solution(
        weightCount: Int,
        weights: IntArray,
        sampleCount: Int,
        samples: IntArray,
    ): String {
        val weightDp = Array(weightCount + 1) { BooleanArray(40_001) }

        val deque = ArrayDeque<Pair<Int, Int>>().apply { add(0 to 0) }

        while (deque.isNotEmpty()) {
            val (currentCount, currentWeight) = deque.removeFirst()

            if (weightDp[currentCount][currentWeight]) {
                continue
            }

            weightDp[currentCount][currentWeight] = true

            if (currentCount == weightCount) {
                continue
            }

            deque.add((currentCount + 1) to (currentWeight + weights[currentCount]))
            deque.add((currentCount + 1) to currentWeight)
            deque.add((currentCount + 1) to abs(currentWeight - weights[currentCount]))
        }

        val sb = StringBuilder()

        for (i in 0 until sampleCount) {
            sb.append(
                when (weightDp[weightCount][samples[i]]) {
                    true -> "Y"
                    false -> "N"
                }
            ).append(" ")
        }

        return sb.toString()
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val weightCount = br.readLine().toInt()
    val weights = br.readLine().split(" ").map(String::toInt).toIntArray()
    val sampleCount = br.readLine().toInt()
    val samples = br.readLine().split(" ").map(String::toInt).toIntArray()

    bw.append(Solution2629().solution(weightCount, weights, sampleCount, samples))
    bw.flush()

    br.close()
    bw.close()
}