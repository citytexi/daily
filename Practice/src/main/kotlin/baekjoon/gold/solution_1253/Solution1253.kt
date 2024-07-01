package baekjoon.gold.solution_1253

private class Solution1253 {

    fun solution(
        n: Int,
        nums: LongArray
    ): Int {
        val sortedNums = nums.sorted()

        var result = 0

        for (i in sortedNums.indices) {
            var left = 0
            var right = n - 1

            val num = sortedNums[i]
            while (left < right) {
                val sum = sortedNums[left] + sortedNums[right]

                when {
                    num == sum -> when (i) {
                        left -> left += 1
                        right -> right -= 1
                        else -> {
                            result += 1
                            break
                        }
                    }
                    num > sum -> left += 1
                    else -> right -= 1
                }
            }
        }

        return result
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val nums = when (n) {
        1 -> LongArray(1) { br.readLine().toLong() }
        else -> br.readLine().split(" ").map(String::toLong).toLongArray()
    }

    bw.append("${Solution1253().solution(n, nums)}\n")
    bw.flush()

    br.close()
    bw.close()
}