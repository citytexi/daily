package data_structure.tree.dfs

private class DFSExample {
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

        resultList.clear()
        visited = BooleanArray(nodeSize + 1)
        dfsUsingRecursion(sortedEdges)
        println(resultList)

        resultList.clear()
        visited = BooleanArray(nodeSize + 1)
        dfsUsingStack(sortedEdges)
        println(resultList)
    }

    private fun dfsUsingRecursion(
        edges: Array<List<Int>>,
        node: Int = 1
    ) {
        resultList.add(node)
        for (next in edges[node]) {
            if (!visited[next]) {
                visited[next] = true
                dfsUsingRecursion(edges, next)
                visited[next] = false
            }
        }
    }

    private fun dfsUsingStack(
        edges: Array<List<Int>>,
        node: Int = 1
    ) {
        val deque = ArrayDeque<Int>()
        deque.add(node)

        while (deque.isNotEmpty()) {
            val last = deque.removeLast()
            resultList.add(last)
            for (next in edges[last]) {
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

    val example = DFSExample()

    example.solution(
        nodeSize = nodeSize,
        edgeSize = edgeSize,
        edges = edges
    )

    br.close()
}