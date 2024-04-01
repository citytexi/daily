# **세그먼트 트리(Segment Tree)**

- 배열 간격에 대한 정보를 이진 트리에 저장하는 자료구조
- 여러 개의 데이터가 존재할 때 특정 구간의 합(최솟값, 최댓값, 곱 등)을 구하는 데 사용하는 자료구조

## 세그먼트 트리의 특징

- 이진 트리 기반
    - 각 노드는 left, right 노드 두 개만의 자식을 가질 수 있음
- 구간 정보 저장
    - 각 노드는 자신이 나타내는 구간의 정보를 저장

## 배열에서 특정 구간의 합을 구하는 방법

- 배열을 이용하여 선형적으로 구하기
- 트리 구조를 이용하여 구하기

### 배열을 이용하여 선형적으로 구하기

- 배열 A

- 배열 A에서 Index 2 .. 8 까지의 합 구하기

- A(2) + A(3) + … + A(8) 이므로 범위에 속하는 원소의 개수가 n개인 경우 시간 복잡도는 O(n)

### 트리 구조를 이용하여 구하기

- 트리 A

- 이러한 세그먼트 트리가 존재할 경우 구간 합을 구하는 시간은 O(logn)

## 세그먼트 트리 구현

- 구간 합 저장 방식
    - Root 55는 Index 0 .. 9 까지의 구간 합
    - Left Node 22는 Index 0 .. 4 까지의 구간 합
    - Right Node 33은 Index 5 .. 9 까지의 구간합
- 세그먼트 트리의 경우 Root가 Index 1로 시작
    - 1부터 시작할 경우 2를 곱하면 Left Node, 2를 곱하고 + 1을 할 경우 Right Node 지칭

### InitSegmentTree

```kotlin
private lateinit var originalArray: IntArray
private lateinit var segmentTree: IntArray
 
private fun initSegmentTree() {
    segmentTree = IntArray(originalArray.size * 2)

    for (i in originalArray.size until segmentTree.size) {
        // 맨 아래 자식 노드 채우기
        segmentTree[i] = originalArray[i - originalArray.size]
    }

    // [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 3, 6, 7, 2, 5, 8, 10, 1, 9]
    println("${segmentTree.toList()}\n")

    for (i in (segmentTree.size / 2) - 1 downTo 1) {
        println("$i, ${segmentTree[i * 2]} + ${segmentTree[(i * 2) + 1]}")
        segmentTree[i] = segmentTree[i * 2] + segmentTree[(i * 2) + 1]
    }

    // [0, 55, 35, 20, 28, 7, 13, 7, 18, 10, 4, 3, 6, 7, 2, 5, 8, 10, 1, 9]
    println("${segmentTree.toList()}\n")
}
```

- 시간복잡도 O(n)
- 결과값: [0, 55, 35, 20, 28, 7, 13, 7, 18, 10, 4, 3, 6, 7, 2, 5, 8, 10, 1, 9]

### UpdateSegmentTreeByIndex

```kotlin
private fun updateSegmentTreeByIndex(
    index: Int,
    value: Int
) {
    var treeIndex = index + (segmentTree.size / 2)

    originalArray[index] = value
    segmentTree[treeIndex] = value
    while (treeIndex > 1) {
        treeIndex /= 2
        segmentTree[treeIndex] = segmentTree[treeIndex * 2] + segmentTree[(treeIndex * 2) + 1]
    }

    println("segmentTree: ${segmentTree.toList()}")
}
```

- 시간복잡도 O(logn)
- updateSegmentTreeByIndex(0, 5) 결과 값
    - [0, 56, 36, 20, 28, 8, 13, 7, 18, 10, 5, 3, 6, 7, 2, 5, 8, 10, 1, 9]

### 구간합 구하기

```kotlin
private fun sumWithRange(
    start: Int,
    end: Int
): Int {
    var left = start + (segmentTree.size / 2)
    var right = end + (segmentTree.size / 2)

    var result = 0

    while (left <= right) {
        if (left % 2 == 1) {
            println("left = $left, value = ${segmentTree[left]}")
            result += segmentTree[left]
            left += 1
        }
        if (right % 2 == 0) {
            println("right = $right, value = ${segmentTree[right]}")
            result += segmentTree[right]
            right -= 1
        }
        left /= 2
        right /= 2
    }

    return result
}
```

- 시간 복잡도 O(logn)