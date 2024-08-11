package baekjoon.gold.solution_17406

private class Solution17406 {
    private var answer = Int.MAX_VALUE

    fun solution(
        n: Int,
        m: Int,
        k: Int,
        map: Array<IntArray>,
        operations: Array<Triple<Int, Int, Int>>
    ): Int {
        val visited = BooleanArray(k)
        answer = Int.MAX_VALUE
        dfs(0, mutableListOf(), n, m, k, map, operations, visited)

        return answer
    }

    private fun dfs(
        depth: Int,
        result: MutableList<Int>,
        n: Int,
        m: Int,
        k: Int,
        map: Array<IntArray>,
        operations: Array<Triple<Int, Int, Int>>,
        visited: BooleanArray
    ) {
        if (depth == k) {
            val tempMap = Array(n){ row -> IntArray(m){ col-> map[row][col] } }

            for (r in result) {
                val operation = operations[r]
                rotate(operation, tempMap)
            }

            for (row in 0 until n) {
                var sum = 0
                for (col in 0 until m) {
                    sum += tempMap[row][col]
                }
                answer = minOf(answer, sum)
            }
            return
        }

        for (i in 0 until k) {
            if (visited[i]) {
                continue
            }
            visited[i] = true
            result.add(i)
            dfs(depth + 1, result, n, m, k, map, operations, visited)
            visited[i] = false
            result.removeLast()
        }
    }

    private fun rotate(
        operation: Triple<Int, Int, Int>,
        map: Array<IntArray>
    ){
        val row = operation.first - 1
        val col = operation.second - 1

        for (i in 1 .. operation.third) {
            val temp = map[row - i][col - i]

            for (j in row - i until row + i) {
                map[j][col - i] = map[j + 1][col - i]
            }

            for (j in col - i until col + i) {
                map[row + i][j] = map[row + i][j + 1]
            }

            for (j in row + i downTo row - i + 1) {
                map[j][col + i] = map[j - 1][col + i]
            }

            for (j in col + i downTo col - i + 2) {
                map[row - i][j] = map[row - i][j - 1]
            }

            map[row - i][col - i + 1] = temp
        }
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m, k) = br.readLine().split(" ").map(String::toInt)
    val map = Array(n) { br.readLine().split(" ").map(String::toInt).toIntArray() }
    val operations = Array(k) {
        val (r, c, s) = br.readLine().split(" ").map(String::toInt)
        Triple(r, c, s)
    }

    bw.append(Solution17406().solution(n, m, k, map, operations).toString())
    bw.flush()

    br.close()
    bw.close()
}