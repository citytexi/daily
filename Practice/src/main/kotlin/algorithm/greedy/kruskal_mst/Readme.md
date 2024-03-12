- 가중치가 가장 작은 선분이 사이클을 만들지 않을 때만 그리디하게 선분 추가

### Pseudo code

- KruskalMST(G)
- Input: 가중치 그래프 G = (V, E), |V| = n, |E| = m
- Output: 최소 신장 트리 T

```kotlin
1. 가중치의 오름차순으로 선분들을 정렬한다. 정렬된 선분 리스트를 L이라고 하자.
2. T = ∅             // 트리 T를 초기화시킨다.
3. while ( T의 선분 수 < n-1 ) {
4.     L에서 가장 작은 가중치를 가진 선분 e를 가져오고, e를 L에서 제거한다.
5.     if (선분 e가 T에 추가되어 사이클을 만들지 않으면)
6.          e를 T에 추가시킨다.
7.      else        // e가 T에 추가되어 사이클이 만들어지는 경우
8.          e를 버린다.
    }
9. return 트리 T    // T는 최소 신장 트리이다.
```

### Time Complexity

- O(mlogm)

## **서로소 집합 (Disjoint-set)**

- 서로 공통된 원소를 가지고 있지 않은 두 개 이상의 집합

### Operations

- MakeSet(x)
    - 주어진 요소만 포함된 집합
- Find(x)
    - x를 포함한 집합의 대표 요소(루트)를 반환하는 연산
- Union(x, y)
    - 각각 x, y가 포함된 두 집합을 합치는 연산

### Pseudo code

- MakeSet(x)

```kotlin
function MakeSet(x) is
    if x is not already in the forest then
        x.parent := x
        x.size := 1 // if nodes store size
    end if
end function
```

- Find(x)

```kotlin
function Find(x) is
    root := x
    while root.parent ≠ root do
        root := root.parent
    end while
    while x.parent ≠ root do
        parent := x.parent
        x.parent := root
        x := parent
    end while
    return root
end function
```

- Union(x, y)

```kotlin
function Union(x, y) is
    x := Find(x)
    y := Find(y)
    if x = y then
        return // x and y are already in the same set
    end if
    if x.size < y.size then
        (x, y) := (y, x)
    end if
    y.parent := x 
    x.size := x.size + y.size
end function

```

### Time Complexity

- MakeSet → N개의 node를 추가 O(n)
- Find → Parent 갱신이 없다면 O(n), 갱신이 있다면 amortized 분석을 통해 Θ(𝛼(𝑛))
    - α(n)은 inverse Ackemann function으로 매우 작은 값
- Union → Find와 동일

## Disjoint-Set을 이용한 **Kruskal MST**

### Pseudo code

```kotlin
algorithm Kruskal(G) is
    F:= ∅
    for each v ∈. G.V do
        MAKE-SET(v)
    for each (u, v) in. G.E ordered by weight(u, v), increasing do
        if FIND-SET(u) ≠ FIND-SET(v) then
            F:= F ∪ {(u, v)} ∪ {(v, u)}
            UNION(FIND-SET(u), FIND-SET(v))
    return F 
```

### Example

```kotlin
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

    println(example.kruskalMST())
}
```