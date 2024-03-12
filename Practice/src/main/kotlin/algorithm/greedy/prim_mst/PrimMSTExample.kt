package algorithm.greedy.prim_mst


private class PrimMSTExample(
    val v: Int,
    val e: Int,
    val edgeList: Array<Info>
) {

    fun primMST() {
        val startIndex = (1 .. v).random()

        val D = IntArray(v + 1) {
            when (it) {
                startIndex -> 0
                else -> Int.MAX_VALUE
            }
        }

        edgeList.forEach {
            if (it.a == startIndex || it.b == startIndex) {
                when (it.a) {
                    startIndex -> D[it.b] = it.weight
                    else -> D[it.a] = it.weight
                }
            }
        }

        val T = mutableListOf(startIndex)



        while (T.size < v) {
            var vMinNode = 0
            for (i in 1 until  D.size) {
                if (D[vMinNode] > D[i] && i !in T) {
                    vMinNode = i
                }
            }

            T.add(vMinNode)

            for (edge in edgeList) {
                if (edge.a in T && edge.b in T) {
                    continue
                }
                if (edge.a == vMinNode || edge.b == vMinNode) {
                    when (edge.a) {
                        vMinNode -> if (D[edge.b] > edge.weight) {
                            D[edge.b] = edge.weight
                        }
                        else -> if (D[edge.a] > edge.weight) {
                            D[edge.a] = edge.weight
                        }
                    }
                }
            }
        }

        println(D.filter { it != Int.MAX_VALUE })
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
    val example = PrimMSTExample(
        v = 6,
        e = 10,
        edgeList = arrayOf(
            Info(1, 2, 3),
            Info(1, 4, 2),
            Info(1, 5, 4),
            Info(2, 3, 1),
            Info(2, 4, 4),
            Info(2, 6, 2),
            Info(3, 6, 1),
            Info(4, 5, 5),
            Info(4, 6, 7),
            Info(5, 6, 9)
        )
    )

    example.primMST()
}