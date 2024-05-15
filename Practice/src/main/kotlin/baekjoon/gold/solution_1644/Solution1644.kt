package baekjoon.gold.solution_1644

import kotlin.math.sqrt

private class Solution1644 {

    fun solution(num: Long): Long {
        val primeSum = getPrimeSum(num.toInt())
        var result = 0L
        var left = 0
        var right = 0

        while (right < primeSum.size) {
            val sum = primeSum[right] - primeSum[left]

            when {
                sum == num -> {
                    result += 1
                    left += 1
                    right += 1
                }
                sum < num -> right += 1
                else -> left += 1
            }
        }

        return result
    }

    private fun getPrimeSum(n: Int): List<Long> {
        val isPrime = BooleanArray(n + 1) { true }
        val sqrt = sqrt(n.toDouble()).toInt()
        isPrime[0] = false
        isPrime[1] = false

        for (i in 2..sqrt) {
            if (!isPrime[i]) {
                continue
            }

            for (j in i * i..isPrime.lastIndex step i) {
                isPrime[j] = false
            }
        }

        val resultList = mutableListOf(0L)

        for (i in isPrime.indices) {
            if (isPrime[i]) {
                resultList.add(resultList.last() + i)
            }
        }

        return resultList
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    bw.append("${Solution1644().solution(br.readLine().toLong())}\n")
    bw.flush()

    br.close()
    bw.close()
}