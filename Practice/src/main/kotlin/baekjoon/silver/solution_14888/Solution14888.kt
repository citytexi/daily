package baekjoon.silver.solution_14888

import kotlin.math.max
import kotlin.math.min

private class Solution14888 {
    private var max = -10_0000_0000L
    private var min = 10_0000_0000L

    fun solution(
        a: IntArray,
        commends: IntArray
    ): Pair<Long, Long> {
        val operations = mutableListOf<Operation>()

        for (i in commends.indices) {
            val currentOperationCount = commends[i]

            repeat(currentOperationCount) {
                operations.add(
                    when (i) {
                        1 -> Operation.MINUS
                        2 -> Operation.MUL
                        3 -> Operation.DIV
                        else -> Operation.PLUS
                    }
                )
            }
        }

        dfs(1, a.first(), a, operations, BooleanArray(operations.size))

        return max to min
    }

    private fun dfs(
        count: Int,
        current: Int,
        a: IntArray,
        operations: List<Operation>,
        visited: BooleanArray
    ) {
        if (count == a.size) {
            max = max(max, current.toLong())
            min = min(min, current.toLong())
            return
        }

        for (i in operations.indices){
            if (visited[i]) {
                continue
            }
            visited[i] = true
            dfs(
                count = count + 1,
                current =  when (operations[i]) {
                    Operation.PLUS -> current + a[count]
                    Operation.MINUS -> current - a[count]
                    Operation.MUL -> current * a[count]
                    Operation.DIV -> current / a[count]
                },
                a = a,
                operations = operations,
                visited = visited
            )
            visited[i] = false
        }

    }

    enum class Operation {
        PLUS,
        MINUS,
        MUL,
        DIV
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val a = br.readLine().split(" ").map(String::toInt).toIntArray()
    val commends = br.readLine().split(" ").map(String::toInt).toIntArray()

    val result = Solution14888().solution(a, commends)
    bw.append("${result.first}\n")
    bw.append("${result.second}\n")
    bw.flush()

    br.close()
    bw.close()
}