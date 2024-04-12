package baekjoon.silver

private class Solution15654 {
    private val stringBuilder = StringBuilder()

    fun solution(
        n: Int,
        m: Int,
        nums: IntArray
    ): String {
        stringBuilder.clear()

        dfs(0, m, nums.sortedArray(), BooleanArray(nums.size), mutableListOf())

        return stringBuilder.toString()
    }

    private fun dfs(
        depth: Int,
        m: Int,
        nums: IntArray,
        visited: BooleanArray,
        current: MutableList<Int>
    ) {
        if (depth == m) {
            stringBuilder.append("${current.joinToString(" ")}\n")
            return
        }

        for (i in nums.indices) {
            if (!visited[i]) {
                visited[i] = true
                current.add(nums[i])
                dfs(depth + 1, m, nums, visited, current)
                current.removeLast()
                visited[i] = false
            }
        }
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)
    val nums = br.readLine().split(" ").map(String::toInt).toIntArray()


    bw.append("${Solution15654().solution(n, m, nums)}\n")
    bw.flush()

    br.close()
    bw.close()
}