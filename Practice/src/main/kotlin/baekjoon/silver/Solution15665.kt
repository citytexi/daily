package baekjoon.silver

private class Solution15665 {
    private val stringBuilder = StringBuilder()
    private val resultSet = hashSetOf<String>()

    fun solution(
        n: Int,
        m: Int,
        nums: IntArray
    ): String {
        stringBuilder.clear()
        resultSet.clear()

        dfs(m, nums.sortedArray(), mutableListOf())

        return stringBuilder.toString()
    }

    private fun dfs(
        m: Int,
        nums: IntArray,
        current: MutableList<Int>,
        depth: Int = 0
    ) {
        if (depth == m) {
            val result = current.joinToString(" ")
            if (!resultSet.contains(result)) {
                stringBuilder.append("$result\n")
                resultSet.add(result)
            }
            return
        }

        for (i in nums.indices) {
            current.add(nums[i])
            dfs(m, nums, current, depth + 1)
            current.removeLast()
        }
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)
    val nums = br.readLine().split(" ").map(String::toInt).toIntArray()

    bw.append("${Solution15665().solution(n, m, nums)}\n")
    bw.flush()

    br.close()
    bw.close()
}