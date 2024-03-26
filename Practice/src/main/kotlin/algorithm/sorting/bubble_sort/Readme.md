# 버블 정렬 (Bubble Sort)

- 이웃하는 숫자를 비교하여 작은 수를 앞쪽으로 이동시키는 과정을 반복하여 정렬

## Pseudo code

- BubbleSort
- Input: 크기 n 배열 A
- Output: 정렬된 배열 A

```kotlin
1. for pass = 1 to n - 1
2.   for i = 1 to n - pass
3.     if (A[i - 1] > A[i])  // 위의 원소가 아래의 원소보다 크면
4.       swap(A[i - 1], A[i])   // 서로 자리를 스왑
5. return 배열 A
```

## Example code

```kotlin
private class BubbleSortExample {
    fun solution(a: IntArray) {
        val sortedA = bubbleSort(a)
        println(sortedA.toList())
    }

    private fun bubbleSort(a: IntArray): IntArray {
        for (pass in 1 until  a.size) {
            for (i in 1 .. a.size - pass) {
                if (a[i - 1] > a[i]) {
                    val temp = a[i - 1]
                    a[i - 1] = a[i]
                    a[i] = temp
                }
            }
            println("$pass end, a = ${a.toList()}")
        }

        return a
    }
}

private fun main() {
    val example = BubbleSortExample()
    val a = intArrayOf(40, 10, 50, 90, 20, 80, 30, 60)
    example.solution(a)
}
```

## Time Complexity

- O(n(n - 1)) / 2 x O(1) = O(n^2) x O(1) = O(n^2)