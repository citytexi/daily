# 힙 정렬 (Heap Sort)

- 힙 자료 구조를 이용하는 정렬 알고리즘
- 오름차순의 정렬을 위해 최대힙(maximum heap)을 구성
- 힙의 루트에는 가장 큰수가 저장됨

## Pseudo code

- HeapSort
- Input: 입력이 A[1] .. A[n]까지 저장된 배열 A
- Output: 정렬된 배열 A

```kotlin
1. 배열 A의 숫자에 대해서 힙 자료 구조를 만든다.
2. heapSize = n    // 힙의 크기를 조절하는 변수
3. for i = 1 to n - 1
4.     A[1] ↔ A[heapSize]    // 루트와 힙의 마지막 노드 교환
5.     heapSize = heapSize - 1    // 힙의 크기를 1 감소
6.     DownHeap()
7. return 배열 A
```

## Example Code

```kotlin
private class HeapSortExample {
    fun solution(arr: IntArray): IntArray {
        heapSort(arr)
        return arr
    }

    private fun heapSort(a: IntArray) {
        var heapSize = a.lastIndex

        for (i in 1 until a.lastIndex) {
            println("entry i = $i ${a.toList()}")
            swap(1, heapSize, a)
            println("swap i = $i ${a.toList()}")
            heapSize -= 1
            downHeap(heapSize, a)
            println("downHeap i = $i ${a.toList()}\n")
        }
    }

    private fun swap(a: Int, b: Int, arr: IntArray) {
        val temp = arr[b]
        arr[b] = arr[a]
        arr[a] = temp
    }

    private fun downHeap(
        heapSize: Int,
        arr: IntArray,
        root: Int = 1
    ) {
        val left = root * 2
        val right = root * 2 + 1

        val leftValue = arr.getOrNull(left)
        val rightValue = arr.getOrNull(right)

        if (left !in 1 .. heapSize || right !in 1 .. heapSize) {
            return
        }

        when (rightValue) {
            null -> {
                // 왼쪽은 null or nonNull
                if (leftValue != null) {
                    if (arr[root] < leftValue) {
                        swap(root, left, arr)
                        downHeap(heapSize, arr, left)
                    }
                }
            }
            else -> {
                if (leftValue!! > rightValue)  {
                    if (arr[root] < leftValue) {
                        swap(root, left, arr)
                        downHeap(heapSize, arr, left)
                    }
                } else {
                    if (arr[root] < rightValue) {
                        swap(root, right, arr)
                        downHeap(heapSize, arr, right)
                    }
                }
            }
        }
    }
}

private fun main() {
    val array = intArrayOf(0, 90, 60, 80, 50, 30, 70, 10, 20, 40)

    val bw = System.out.bufferedWriter()
    bw.append("${HeapSortExample().solution(array).toList()}\n")
    bw.flush()
    bw.close()
}
```

## Time Complexity

- Heap 만들기 O(n)
- for 반복 n - 1
- DownHeap O(logn)
- 따라서 O(n) + (n - 1) * O(logn) = O(nlogn)