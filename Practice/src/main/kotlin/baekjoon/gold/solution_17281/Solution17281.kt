package baekjoon.gold.solution_17281

private class Solution17281 {
    private var result = 0

    fun solution(
        n: Int,
        graph: Array<IntArray>
    ): Int {
        result = 0
        val members = IntArray(9).apply { this[3] = 1 }
        val visited = BooleanArray(9).apply { this[3] = true }

        dfs(2, graph, members, visited)

        return result
    }

    private fun dfs(
        depth: Int,
        graph: Array<IntArray>,
        members: IntArray,
        visited: BooleanArray
    ) {
        if (depth == 10) {
            result = maxOf(result, play(graph, members))

            return
        }

        for (i in 0 until  9) {
            if (visited[i]) {
                continue
            }

            visited[i] = true
            members[i] = depth
            dfs(depth + 1, graph, members, visited)
            visited[i] = false
        }
    }

    private fun play(
        graph: Array<IntArray>,
        members: IntArray
    ): Int {
        var sum = 0
        var index = 0

        for (row in graph.indices) {
            var inningScore = 0
            var inningOutCount = 0

            val base = BooleanArray(3)

            while (inningOutCount < 3) {
                when (graph[row][members[index] - 1]) {
                    4 -> {
                        if (base[2]) {
                            inningScore += 1
                            base[2] = false
                        }
                        if (base[1]) {
                            inningScore += 1
                            base[1] = false
                        }
                        if (base[0]) {
                            inningScore += 1
                            base[0] = false
                        }
                        inningScore += 1
                    }

                    3 -> {
                        if (base[2]) {
                            inningScore += 1
                            base[2] = false
                        }
                        if (base[1]) {
                            inningScore += 1
                            base[1] = false
                        }
                        if (base[0]) {
                            inningScore += 1
                            base[0] = false
                        }
                        base[2] = true
                    }

                    2 -> {
                        if (base[2]) {
                            inningScore += 1
                            base[2] = false
                        }
                        if (base[1]) {
                            inningScore += 1
                            base[1] = false
                        }
                        if (base[0]) {
                            base[0] = false
                            base[2] = true
                        }
                        base[1] = true
                    }

                    1 -> {
                        if (base[2]) {
                            inningScore += 1
                            base[2] = false
                        }
                        if (base[1]) {
                            base[1] = false
                            base[2] = true
                        }
                        if (base[0]) {
                            base[0] = false
                            base[1] = true
                        }
                        base[0] = true
                    }

                    else -> inningOutCount += 1
                }

                index = (index + 1) % 9
            }

            sum += inningScore
        }

        return sum
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val graph = Array(n) { br.readLine().split(" ").map(String::toInt).toIntArray() }

    bw.append("${Solution17281().solution(n, graph)}\n")
    bw.flush()

    br.close()
    bw.close()
}