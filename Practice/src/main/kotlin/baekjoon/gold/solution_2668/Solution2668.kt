package baekjoon.gold.solution_2668

private class Solution2668 {

    fun solution(arr: IntArray): String {

        val visited = BooleanArray(arr.size)
        val result = mutableListOf<Int>()

        for (i in 1 until arr.size) {
            visited[i] = true
            dfs(i, i, result, arr, visited)
            visited[i] = false
        }

        val sb = StringBuilder()

        sb.append("${result.size}\n")

        result.forEach { sb.append("$it\n") }

        return sb.toString()
    }

    private fun dfs(
        start: Int,
        target: Int,
        result: MutableList<Int>,
        arr: IntArray,
        visited: BooleanArray,
    ) {
        val current = arr[start]

        if (!visited[current]) {
            visited[current] = true
            dfs(current, target, result, arr, visited)
            visited[current] = false
        }

        if (current == target) {
            result.add(target)
        }
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val arr = IntArray(n + 1) { if (it == 0) 0 else br.readLine().toInt() }

    bw.append(Solution2668().solution(arr))
    bw.flush()

    br.close()
    bw.close()
}