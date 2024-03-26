# 선택 정렬 (Selection Sort)

- Input 배열의 원소 중 최솟값을 선택하여 0번 자리로, 0번 원소를 제외한 나머지 중 최솟값을 선택하여 배열의 1번 자리로 이동시키며 정렬


## Pseudo code

- SelectionSort
- Input: 크기가 n인 배열 A
- Output: 정렬된 배열 A

```kotlin
1. for i = 0 to n - 2 {
2.     min = i
3.     for j = i + 1 to n - 1 {    // A[i]~A[n-1]에서 최솟값을 찾기
4.         if (A[j] < A[min]) 
5.               min = j
6.          }
7.      swap(A[i], A[min])    // min이 최솟값이 있는 원소의 인덱스
8.   }
9. return 배열 A
```

## Example code

```kotlin
private class SelectionSortExample {
    fun solution(a: IntArray) {
        val sorted = selectionSort(a)
        println(sorted.toList())
    }

    private fun selectionSort(a: IntArray): IntArray {
        for (i in 0 until a.size - 1) {
            var min = i
            for (j in i + 1 until a.size) {
                if (a[j] < a[min]) {
                    min = j
                }
            }

            val temp = a[i]
            a[i] = a[min]
            a[min] = temp

            println("${i + 1} pass end, a = ${a.toList()}")
        }

        return a
    }
}

private fun main() {
    val example = SelectionSortExample()
    val a = intArrayOf(40, 10, 50, 90, 20, 80, 30, 60)
    example.solution(a)
}
```

## Time Complexity

- O(n(n - 1)) / 2 x O(1) = O(n^2) x O(1) = O(n^2)
- 입력에 민감하지 않음 (input insensitive)
    - 거의 정렬, 역으로 정렬, 랜덤 구성 등 조건을 따지지 않고 항상 일정한 시간 복잡도