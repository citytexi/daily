# 삽입 정렬 (Insertion Sort)
- 배열을 정렬된 부분 (앞부분)과 정렬 안 된 부분 (뒷부분)으로 나눔
- 정렬 안 된 부분의 가장 왼쪽 원소를 정렬된 부분의 적절한 위치에 삽입하여 정렬되도록 하는 과정을 반복

## Pseudo code

- InsertionSort
- Input: 크기가 n인 배열 A
- Output: 정렬된 배열 A

```kotlin
1. for i = 1 to n - 1 {
2.     CurrentElement = A[i] // 정렬 안된 부분의 가장 왼쪽원소
3.     j = i – 1   // 정렬된 부분의 가장 오른쪽 원소로부터 왼쪽 방향으로 삽입할 곳을 탐색하기 위하여 
4.     while (j >= 0) and (A[j] > CurrentElement) {
5.           A[j + 1] = A[j]   // 자리 이동
6.           j = j - 1
7.        }
8.     A[j + 1] = CurrentElement
9.   }
10. return A
```

## Example code

```kotlin
private class InsertionSortExample {
    fun solution(a: IntArray) {
        val sortedA = insertionSort(a)
        println(sortedA.toList())
    }

    private fun insertionSort(a: IntArray): IntArray {
        for (i in 1 until a.size) {
            println("$i pass start, a = ${a.toList()}")
            val currentElement = a[i]
            var j = i - 1
            while (j >= 0 && a[j] > currentElement) {
                a[j + 1] = a[j]
                j -= 1
            }
            a[j + 1] = currentElement
            println("$i pass end, a = ${a.toList()}")
            println("정렬된 부분, a = ${a.sliceArray(0 ..  i).toList()}")
            println("정렬 안된 부분, a = ${a.sliceArray(i + 1 until  a.size).toList()}")
            println()
        }

        return a
    }
}

private fun main() {
    val example = InsertionSortExample()
    val a = intArrayOf(40, 10, 50, 90, 20, 80, 30, 60)
    example.solution(a)
}
```

## Time Complexity

- O(n(n - 1)) / 2 x O(1) = O(n^2) x O(1) = O(n^2)
- 최선의 경우 = O(n)
    - 이미 정렬된 경우