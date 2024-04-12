package baekjoon.silver

private class Solution15664 {
    private val stringBuilder = StringBuilder()
    private val resultSet = hashSetOf<String>()

    fun solution(
        n: Int,
        m: Int,
        nums: IntArray
    ): String {
        stringBuilder.clear()
        resultSet.clear()

        dfs(m, nums.sortedArray(), BooleanArray(nums.size), mutableListOf())

        return stringBuilder.toString()
    }

    private fun dfs(
        m: Int,
        nums: IntArray,
        visited: BooleanArray,
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

        for (i in depth until nums.size) {
            if (!visited[i] && nums[i] >= ((current.getOrNull(depth - 1)) ?: 0)) {
                visited[i] = true
                current.add(nums[i])
                dfs(m, nums, visited, current, depth + 1)
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

    bw.append("${Solution15664().solution(n, m, nums)}\n")
    bw.flush()

    br.close()
    bw.close()
}