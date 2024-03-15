# 깊이 우선 탐색 (Depth First Search)

- 트리나 그래프에서 최대한 깊숙하게 탐색한 이후 가장 최근의 갈림길로 돌아가 다른 노드를 탐색

## DFS 구현 방법

### 재귀 호출을 이용한 구현

```kotlin
private fun dfs(
    edges: Array<MutableList<Int>>,
    node: Int = 1
) {
    resultList.add(node)
    for (next in edges[node]) {
        if (!visited[next]) {
            visited[node] = true
            dfs(edges, next)
            visited[node] = false
        }
    }
}
```

### 스택을 이용한 구현

```kotlin
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
```