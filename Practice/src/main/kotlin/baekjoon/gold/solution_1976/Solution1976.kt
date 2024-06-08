package baekjoon.gold.solution_1976

private class Solution1976(
    private val n: Int,
    private val m: Int,
    private val parent: IntArray = IntArray(n + 1) { it }
) {

    fun solution(routes: IntArray): String {
        val start = parent[routes[0]]

        var result = true
        for(i in 1 until  m) {
            if (parent[routes[i]] != start) {
                result = false
                break
            }
        }

        return when (result) {
            true -> "YES\n"
            false -> "NO\n"
        }
    }

    private fun find(num: Int): Int = when (num) {
        parent[num] -> num
        else -> {
            parent[num] = find(parent[num])
            parent[num]
        }
    }

    fun union(
        x: Int,
        y: Int
    ) {
        val findX = find(x)
        val findY = find(y)

        if (findX > findY) {
            parent[findX] = findY
        } else {
            parent[findY] = findX
        }
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val m = br.readLine().toInt()
    val solution1976 = Solution1976(n, m)

    for (i in 1 .. n) {
        val inputs = br.readLine().split(" ").map(String::toInt)

        for (j in 1 .. n) {
            if (inputs[j - 1] == 1) {
                solution1976.union(i, j)
            }
        }
    }

    val routes = br.readLine().split(" ").map(String::toInt).toIntArray()

    bw.append(solution1976.solution(routes))
    bw.flush()

    br.close()
    bw.close()
}
