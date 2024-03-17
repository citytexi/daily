package data_structure.tree.bfs


private class BFSExample {
    private lateinit var visited: BooleanArray
    private val resultList = mutableListOf<Int>()

    fun solution(
        nodeSize: Int,
        edgeSize: Int,
        edges: Array<MutableList<Int>>
    ) {
        val sortedEdges = edges.map { it.sorted() }.toTypedArray()
//        for (i in 1 until  sortedEdges.size) {
//            println("node = $i, child = ${sortedEdges[i]}")
//        }

        visited = BooleanArray(nodeSize + 1)
        resultList.clear()
        bfs(sortedEdges)
        println(resultList)

    }

    private fun bfs(
        edges: Array<List<Int>>,
        node: Int = 1
    ) {
        val deque = ArrayDeque<Int>()
        deque.add(node)

        while (deque.isNotEmpty()) {
            val first = deque.removeFirst()
            resultList.add(first)
            for (next in edges[first]) {
                if (!visited[next]) {
                    visited[next] = true
                    deque.add(next)
                }
            }
        }
    }
}

/*
9 8
1 2
1 8
2 3
2 6
3 4
3 5
6 7
8 9
*/

private fun main() {
    val br = System.`in`.bufferedReader()

    val (nodeSize, edgeSize) = br.readLine().split(" ").map { it.toInt() }

    val edges = Array<MutableList<Int>>(nodeSize + 1) { mutableListOf() }

    repeat(edgeSize) {
        val (a, b) = br.readLine().split(" ").map { it.toInt() }
        edges[a].add(b)
    }

    val example = BFSExample()

    example.solution(
        nodeSize = nodeSize,
        edgeSize = edgeSize,
        edges = edges
    )

    br.close()
}