# QuickSort
- 문제를 2개의 부분 문제로 분할
    - 각 부분 문제의 크기가 일정하지 않음

### Idea

- 피봇 (pivot)을 임의로 설정하여 피봇보다 작은 숫자는 피봇 기준 왼쪽편으로, 피봇보다 큰 숫자는 피봇 기준 오른쪽으로 위치하도록 분할하고 피봇을 그 사이에 놓는다.
- 분할된 부분 문제들에서도 재귀적으로 수행
- 문제가 2개로 분할되고, 부분 문제의 크기가 1/2로 감소하는 알고리즘

### Pseudo code

- QuickSort(A, left, right)
- Input: Array A[left] ~ A[right]
- Output: Sorted Array A[left] ~ A[right]

```kotlin
if (left < right) {
   p := partition(A, left, right)
   QuickSort(A, left, p) // 피봇보다 작은 그룹
   QuickSort(A, p + 1 right) // 피봇보다 큰 그룹
}
```

- Partition(A, left, right)
- Input: Array A[left] ~ A[right]
- Output: Pivot Position, Sorted with Pivot Array A[left] ~ A[right]

```kotlin
Pivot = (left + right) / 2 // 피봇의 위치 결정
swap A[left] with A[Pivot] // 피봇을 왼쪽 끝으로 이동
i = left // 왼쪽 커서를 왼쪽으로
j = right + 1 // 오른쪽 커서를 오른쪽으로

loop forever
	do i = i + 1
	while A[i] < A[left] // 왼쪽 커서를 오른쪽으로 옮기며 피봇보다 큰 값 찾기
	do j = j - 1
	while A[j] > A[left] // 오른쪽 커서를 왼쪽으로 옮기며 피봇보다 작은 값 찾기
	
	if i >= j then // 커서가 만났다면
		swap A[j] with A[left] // 피봇 값을 만난 점으로 이동
		return j // 만난 점 return
	swap A[i] with A[j] // 만나지 않았으면 양 커서 값 교환
```

## Time Complexity

- 피봇 선택이 좌우
    - 피봇으로 가장 작은 숫자 또는 가장 큰 숫자가 선택되면 한 부분으로 치우치는 결과 야기
- 최악의 경우
    - O(n^2)
- 최선의 경우
    - O(n) * O(층수) = O(n) * O(log2n)
    - n / 2^k = 1일 때, k = log2n
    - O(nlog2n)
- 평균의 경우
    - 최선과 동일하게 O(nlog2n)

### Example

```kotlin
private class QuickSortExample {
    fun solution(arr: IntArray): IntArray {
        quickSort(arr, 0, arr.size - 1)
        return arr
    }

    private fun quickSort(
        arr: IntArray,
        left: Int,
        right: Int
    ) {
        if(left < right) {
            val p = partition(arr, left, right)
            quickSort(
                arr = arr,
                left = left,
                right = p - 1
            )
            quickSort(
                arr = arr,
                left = p + 1,
                right = right
            )
        }
    }

    private fun partition(
        arr: IntArray,
        left: Int,
        right: Int
    ): Int {
        val pivot = (left + right) / 2
        swap(arr, left, pivot)

        var i = left
        var j = right + 1

        while (true) {
            do {
                i += 1
            } while (i < arr.size && arr[i] < arr[left])
            do {
                j -= 1
            } while (j >= 0 && arr[j] > arr[left])

            if (i >= j) {
                swap(arr, left, j)
                return j
            } else {
                swap(arr, i, j)
            }
        }
    }

    private fun swap(
        arr: IntArray,
        i: Int,
        j: Int
    ) {
        val temp = arr[i]
        arr[i] = arr[j]
        arr[j] = temp
    }
}

private fun main() {
    val solution = QuickSortExample()
    println(solution.solution(intArrayOf(6, 3, 11, 9, 12, 2, 8, 15, 18, 10, 7, 14)).toList())

    val size = (Math.random() * 1000).toInt()
    val intArr = IntArray(size) { 0 }
    for (i in 0 until size) {
        intArr[i] = (Math.random() * 10000).toInt()
    }
    println(solution.solution(intArr).toList())
}
```