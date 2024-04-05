package baekjoon.gold

import java.math.BigInteger

/*
문제
어떤 N개의 수가 주어져 있다. 그런데 중간에 수의 변경이 빈번히 일어나고 그 중간에 어떤 부분의 합을 구하려 한다. 만약에 1,2,3,4,5 라는 수가 있고, 3번째 수를 6으로 바꾸고 2번째부터 5번째까지 합을 구하라고 한다면 17을 출력하면 되는 것이다. 그리고 그 상태에서 다섯 번째 수를 2로 바꾸고 3번째부터 5번째까지 합을 구하라고 한다면 12가 될 것이다.

입력
첫째 줄에 수의 개수 N(1 ≤ N ≤ 1,000,000)과 M(1 ≤ M ≤ 10,000), K(1 ≤ K ≤ 10,000) 가 주어진다. M은 수의 변경이 일어나는 횟수이고, K는 구간의 합을 구하는 횟수이다. 그리고 둘째 줄부터 N+1번째 줄까지 N개의 수가 주어진다. 그리고 N+2번째 줄부터 N+M+K+1번째 줄까지 세 개의 정수 a, b, c가 주어지는데, a가 1인 경우 b(1 ≤ b ≤ N)번째 수를 c로 바꾸고 a가 2인 경우에는 b(1 ≤ b ≤ N)번째 수부터 c(b ≤ c ≤ N)번째 수까지의 합을 구하여 출력하면 된다.

입력으로 주어지는 모든 수는 -263보다 크거나 같고, 263-1보다 작거나 같은 정수이다.

출력
첫째 줄부터 K줄에 걸쳐 구한 구간의 합을 출력한다. 단, 정답은 -263보다 크거나 같고, 263-1보다 작거나 같은 정수이다.
* */

private class Solution2042 {
    private lateinit var segmentTree: Array<BigInteger>
    private lateinit var numberArray: Array<BigInteger>

    fun solution(numbers: Array<BigInteger>, operations: List<Operation>) {
        numberArray = numbers
        segmentTree = Array(numbers.size * 2) { "0".toBigInteger() }

        for (i in numbers.size until segmentTree.size) {
            segmentTree[i] = numbers[i - numbers.size]
        }

        for (i in (segmentTree.size / 2) - 1 downTo 1) {
            segmentTree[i] = segmentTree[i * 2] + segmentTree[(i * 2) + 1]
        }

        val bw = System.out.bufferedWriter()

        for ((a, b, c) in operations) {
            when (a) {
                "1" -> {
                    // b(1 ≤ b ≤ N)번째 수를 c로 바꿈
                    updateTree(b.toInt() - 1, c.toBigInteger())
                }
                "2" -> {
                    // b(1 ≤ b ≤ N)번째 수부터 c(b ≤ c ≤ N)번째 수까지의 합을 구하여 출력
                    bw.append("${sumWithRange(b.toInt() - 1, c.toInt() - 1)}\n")
                    bw.flush()
                }
                else -> Unit
            }
        }
        bw.close()
    }

    private fun updateTree(index: Int, value: BigInteger) {
        var treeIndex = index + (segmentTree.size / 2)

        numberArray[index] = value
        segmentTree[treeIndex] = value
        while (treeIndex > 1) {
            treeIndex /= 2
            segmentTree[treeIndex] = segmentTree[treeIndex * 2] + segmentTree[(treeIndex * 2) + 1]
        }
    }

    private fun sumWithRange(start: Int, end: Int): BigInteger {
        var left = start + (segmentTree.size / 2)
        var right = end + (segmentTree.size / 2)

        var result = "0".toBigInteger()

        while (left <= right) {
            if (left % 2 == 1) {
                result += segmentTree[left]
                left += 1
            }
            if (right % 2 == 0) {
                result += segmentTree[right]
                right-= 1
            }
            left /= 2
            right /= 2
        }

        return result
    }

    data class Operation(
        val a: String,
        val b: String,
        val c: String
    )
}

private fun main() {
    val br = System.`in`.bufferedReader()

    val solution2042 = Solution2042()

    val (n, m, k) = br.readLine().split(" ").map { it.toInt() }
    val numbers = Array(n) { BigInteger(br.readLine()) }
    val operations = mutableListOf<Solution2042.Operation>()

    repeat(m + k) {
        val (a, b, c) = br.readLine().split(" ")
        operations.add(Solution2042.Operation(a, b, c))
    }

    solution2042.solution(numbers, operations)

    br.close()
}