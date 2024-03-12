- 임의의 점 하나를 선택한 후, (n - 1)개의 선분을 하나씩 추가시켜 트리 제작

### Pseudo code

- PrimMST(G)
- Input: 가중치 그래프 G = (V, E), |V| = n, |E| = m
- Output: 최소 신장 트리 T

```kotlin
1. 그래프 G에서 임의의 점 p를 시작점으로 선택하고, D[p] = 0으로 놓는다.
	// 배열 D[v]는 T에 있는 점 u와 v를 연결하는 선분의 최소 가중치를 저장하기 위한 요소
2. for (점 p가 아닌 각 점 v에 대하여) { // 배열 D의 초기화
3.     if (선분 (p, v)가 그래프에 있으면)
4.         D[v] = 선분 (p, v)의 가중치
5.     else
6.         D[v] = ∞
7. }
8. T = {p} // 초기에 트리 T는 점 p만을 가진다
9. while (T에 있는 점의 수 < n) {
10. T에 속하지 않은 각 점 v에 대하여, D[v]가 최소인 점 vMin과 연결된 선분 (u, vMin)을 T에 추가한다.
    // 단, u는 T에 속한 점이고, 점 vMin도 T에 추가된다.
11. for (T에 속하지 않은 각 점 w에 대해서) {
12.     if (선분 (vMin, w)의 가중치 < D[w])
13.         D[w] = 선분 (vMin, w)의 가중치 // D[w]를 갱신
14. }
15. return T // T는 최소 신장 트리
```

### Time Complexity

- while 루프가 (n - 1)번 반복
- while 루프 1회당 D[v]가 최소인 점 vMin을 찾는 것에 O(n)이 걸림
- 따라서 O(n - 1) * O(n) = O(n^2)

### Example

```kotlin
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
```