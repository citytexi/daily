package baekjoon.silver

private class Solution15655 {
    private val stringBuilder = StringBuilder()

    fun solution(
        n: Int,
        m: Int,
        nums: IntArray
    ): String {
        stringBuilder.clear()

        dfs(m, nums.sortedArray(), BooleanArray(nums.size), mutableListOf())

        return stringBuilder.toString()
    }

    private fun dfs(
        m: Int,
        nums: IntArray,
        visited: BooleanArray,
        current: MutableList<Int>,
        index: Int = 0,
        depth: Int = 0
    ) {
        if (depth == m) {
            stringBuilder.append("${current.joinToString(" ")}\n")
            return
        }

        for (i in index until nums.size) {
            if (!visited[i] && nums[i] > current.getOrElse(index - 1) { 0 }) {
                visited[i] = true
                current.add(nums[i])
                dfs(m, nums, visited, current, index + 1, depth + 1)
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

    bw.append("${Solution15655().solution(n, m, nums)}\n")
    bw.flush()

    br.close()
    bw.close()
}