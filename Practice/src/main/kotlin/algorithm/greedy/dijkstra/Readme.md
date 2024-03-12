## 최단 경로 찾기 (Shortest path problem)

- 주어진 가중치 그래프에서 어느 한 출발점에서 또 다른 도착점까지의 최단 경로를 찾는 문제

## 다익스트라 (Dijkstra) 알고리즘

- 주어진 출발점에서 시작
- 출발점으로부터 최단 거리가 확정되지 않은 점들 중에서 출발점으로부터 가장 가까운 점을 추가하고, 그 점의 최단 거리를 확정

### Pseudo code

- ShortestPath(G, s)
- Input: 가중치 그래프 G = (V, E), |V| = n, |E| = m
- Output: 출발점 s로부터 (n - 1)개의 점까지 각각 최단 거리를 저장한 배열 D

```kotlin
1. 배열 D를 ∞로 초기화시킨다. 단, D[s] = 0으로 초기화한다.
		// 배열 D[v]에는 출발점 s로부터 점 v까지의 거리가 저장된다.
2. while (s로부터의 최단 거리가 확정되지 않은 점이 있으면) {
3. 현재까지 s로부터 최단 거리가 확정되지 않은 각 점 v에 대해서 최소의 D[v]의 값을 가진 점 vMin을 선택하고,
     출발점 s로부터 점 vMin까지의 최단 거리 D[vMin]을 확정한다.
4. s로부터 현재보다 짧은 거리로 점 vMin을 통해 우회 가능한 각 점 w에 대해서 D[w]를 갱신한다.
5. }
6. return D
```

### Time Complexity

- while 루프가 (n - 1)번 반복
- vMin을 찾는데 걸리는 시간 O(n)
- D[w] 갱신에 소요되는 시간 O(n)
- 따라서 O(n - 1) * {O(n) + O(n)} = O(n^2)

### Example

```kotlin
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
```