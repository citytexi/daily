# 쉘 정렬 (Shell sort)

- 삽입 정렬의 경우 맨 마지막 원소가 가장 작은 원소면 모든 숫자를 옮겨야함
- 이러한 단점을 보완
    - 배열 뒷부분의 작은 숫자를 앞부분으로 빠르게 이동
    - 동시에 앞부분의 큰 숫자는 뒷부분으로 이동
    - 마지막에는 삽입 정렬


- 각 간격 별로 그룹을 설정하여 정렬
- 마지막에는 무조건 간격을 1로 설정하고 정렬

## Pseudo code

- ShellSort
- Input: 크기가 n인 배열 A
- Output: 정렬된 배열 A

```kotlin
1. for gap h = [h0 > h1 >... > hk = 1] // 큰 gap부터 차례로
2.    for i = h to n - 1 { 
3.         CurrentElement = A[i]
4.         j = i
5.        while (j >= h) and (A[j - h] > CurrentElement) {
6.              A[j] = A[j - h]
7.               j = j - h
8.          }
9.         A[j] = CurrentElement
10.  }
11. return 배열 A
```

## Example code

```kotlin
private class ShellSortExample {
    fun solution(a: IntArray) {
        val sorted = shellSort(a)
        println(sorted.toList())
    }

    private fun shellSort(a: IntArray): IntArray {
        val h = (a.size / 3 downTo 1)
        for (gap in h) {
            println("gap = $gap start, a = ${a.toList()}\n")
            for (i in gap until a.size) {
                println("group$i start, a = ${a.toList()}")
                val currentElement = a[i]
                var j = i
                while (j >= gap && a[j - gap] > currentElement) {
                    a[j] = a[j - gap]
                    j -= gap
                }
                a[j] = currentElement
                println("group$i end, a = ${a.toList()}\n")
            }
            println("gap = $gap end, a = ${a.toList()}\n")
        }

        return a
    }
}

private fun main() {
    val example = ShellSortExample()
    val a = intArrayOf(30, 60, 90, 10, 40, 80, 40, 20, 10, 60, 50, 30, 40, 90, 80)
    example.solution(a)
}
```

## Time Complexity

- 최악의 경우 O(n^2)
- 간격 선정에 따라서 좌우됨
- 히바드(Hibbard)의 간격
    - 2^k - 1 (즉, 2^k - 1, ⋯, 15, 7, 5, 3, 1)을 사용하면, O(n1.5)
- 많은 실험을 통한 쉘 정렬의 시간복잡도는 O(n1.25)으로 알려지고 있다
- 지금까지 알려진 가장 좋은 성능을 보인 간격
    - 1, 4, 10, 23, 57, 132, 301, 701, 1750 (Marcin Ciura)