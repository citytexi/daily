package algorithm.greedy.kruskal_mst

private class KruskalMSTExample(
    val v: Int,
    val e: Int,
    val edgeList: Array<Info>
) {
    private lateinit var parent: IntArray

    fun kruskalMST() {
        parent = IntArray(v + 1) { it }
        val sortedList = edgeList.sorted()

        for ((a, b, weight) in sortedList) {
            val aCondition = find(a)
            val bCondition = find(b)

            if (aCondition != bCondition) {
                union(a, b)
            }
            println("$a $b $weight $aCondition $bCondition")
        }

        println(sortedList.map { it.toInfo() })
        println(parent.contentToString())
    }

    private fun union(x: Int, y: Int) {
        val findX = find(x)
        val findY = find(y)

        if (findX > findY) {
            parent[findX] = findY
        } else {
            parent[findY] = findX
        }
    }

    private fun find(x: Int): Int = when(val value = parent[x]) {
        x -> x
        else -> find(value)
    }
}

private data class Info(
    val a: Int,
    val b: Int,
    val weight: Int
): Comparable<Info> {
    fun toInfo(): String = "${nodeNumberToString(a)} <- $weight -> ${nodeNumberToString(b)}"

    private fun nodeNumberToString(node: Int): String = when (node) {
        1 -> "a"
        2 -> "b"
        3 -> "c"
        4 -> "d"
        5 -> "e"
        6 -> "f"
        else -> throw Exception()
    }

    override fun compareTo(other: Info): Int = this.weight.compareTo(other.weight)
}

private fun main() {
    val example = KruskalMSTExample(
        v = 6,
        e = 10,
        edgeList = arrayOf(
            Info(1, 2, 8),
            Info(1, 4, 2),
            Info(1, 5, 4),
            Info(2, 3, 1),
            Info(2, 4, 4),
            Info(2, 6, 2),
            Info(3, 6, 1),
            Info(4, 6, 7),
            Info(4, 5, 3),
            Info(5, 6, 9),
        )
    )

    example.kruskalMST()
}