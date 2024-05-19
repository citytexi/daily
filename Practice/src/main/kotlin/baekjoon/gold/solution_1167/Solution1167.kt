package baekjoon.gold.solution_1167

private class Solution1167 {
    private var result = Int.MIN_VALUE
    private var last = 0

    fun solution(
        n: Int,
        graph: Array<Node>
    ): Int {
        result = Int.MIN_VALUE

        dfs(1, 0, n, graph, BooleanArray(n + 1))
        dfs(last, 0, n, graph, BooleanArray(n + 1))

        return result
    }

    private fun dfs(
        index: Int,
        current: Int,
        n: Int,
        graph: Array<Node>,
        visited: BooleanArray
    ) {
        visited[index] = true

        if (result < current) {
            result = current
            last = index
        }

        for ((subNodeIndex, distance) in graph[index].subNodes) {
            if (visited[subNodeIndex]) {
                continue
            }
            dfs(
                index = subNodeIndex,
                current = current + distance,
                n = n,
                graph = graph,
                visited = visited
            )
        }
    }


    data class Node(
        val num: Int,
        val subNodes: MutableList<Pair<Int, Int>> = mutableListOf()
    )
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val graph = Array(n + 1) { Solution1167.Node(it) }

    repeat(n) {
        val inputs = br.readLine().split(" ")
        val current = inputs[0].toInt()

        var index = 1

        while (inputs[index].toInt() != -1) {
            graph[current].subNodes.add(inputs[index].toInt() to inputs[index + 1].toInt())

            index += 2
        }
    }

    bw.append("${Solution1167().solution(n, graph)}\n")
    bw.flush()

    br.close()
    bw.close()
}

/*
4
1 2 5 3 9 -1
2 1 5 -1
3 1 9 4 8 -1
4 3 8 -1

22

6
1 2 3 -1
2 1 3 5 3 3 5 -1
3 2 5 4 7 -1
4 3 7 -1
5 2 3 6 5 -1
6 5 5 -1

20

4
1 2 7 3 2 -1
2 1 7 -1
3 1 2 4 3 -1
4 3 3 -1

12

5
1 2 7 3 2 5 10 -1
2 1 7 -1
3 1 2 4 3 -1
4 3 3 -1
5 1 10 -1

17

* */


