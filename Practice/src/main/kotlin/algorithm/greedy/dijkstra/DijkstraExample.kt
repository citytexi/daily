package algorithm.greedy.dijkstra


private class DijkstraExample(
    val v: Int,
    val e: Int,
    val edgeList: Array<Info>
) {
    fun dijkstra(s: String) {
        val D = HashMap<String, Int>()

        for (edge in edgeList) {
            if (!D.containsKey(edge.a)) {
                when (edge.a) {
                    s -> D[edge.a] = 0
                    else -> D[edge.b] = Int.MAX_VALUE
                }
            }
            if (!D.containsKey(edge.b)) {
                when (edge.b) {
                    s -> D[edge.b] = 0
                    else -> D[edge.b] = Int.MAX_VALUE
                }
            }
        }

        var current = s
        val T = mutableListOf(s)

        while (T.size < v) {
            edgeList.forEach {
                if (it.a == current || it.b == current) {
                    val otherNode = if (it.a == current) {
                        it.b
                    } else {
                        it.a
                    }

                    if (D.getOrDefault(otherNode, 0) > D.getOrDefault(current, 0) + it.weight) {
                        D[otherNode] = D.getOrDefault(current, 0) + it.weight
                    }
                }
            }

            var vMinDistance = Int.MAX_VALUE
            var vMinNode = s
            val iter = D.iterator()
            while (iter.hasNext()) {
                val (node, distance) = iter.next()

                if (node !in T) {
                    if (distance > D.getOrDefault(current, 0) && distance < vMinDistance) {
                        vMinDistance = distance
                        vMinNode = node
                    }
                }
            }

            T.add(vMinNode)
            current = vMinNode
        }

        println(D)
    }
}

private data class Info(
    val a: String,
    val b: String,
    val weight: Int
): Comparable<Info> {
    override fun compareTo(other: Info): Int = this.weight.compareTo(other.weight)
}

private fun main() {
    val example = DijkstraExample(
        v = 10,
        e = 14,
        edgeList = arrayOf(
            Info("서울", "원주", 15),
            Info("서울", "천안", 12),
            Info("천안", "대전", 10),
            Info("천안", "논산", 4),
            Info("논산", "대전", 3),
            Info("논산", "광주", 13),
            Info("광주", "부산", 15),
            Info("대전", "대구", 10),
            Info("원주", "대구", 7),
            Info("원주", "강릉", 21),
            Info("대구", "포항", 19),
            Info("대구", "부산", 9),
            Info("강릉", "포항", 25),
            Info("포항", "부산", 5)
        )
    )

    example.dijkstra("서울")
}