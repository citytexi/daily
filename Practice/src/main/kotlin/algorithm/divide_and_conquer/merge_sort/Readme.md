- 문제가 2개로 분할되고, 부분 문제의 크기가 1/2로 감소하는 알고리즘

## Merge

- 2개의 각각 정렬된 숫자들을 1개의 정렬된 숫자로 합치는 것

### Example

- Array A: 6, 14, 18, 20, 29
- Array B: 1, 2, 15, 25, 30, 45
- Array C: 1, 2, 6, 14, 15, 18, 20, 25, 29, 30, 45

### Pseudo code

- MergeSort(A, start, end)
- Input: A[start] ~ A[end]
- Output: Sorted A[start] ~ A[end]

```kotlin
if (start < end) { // 배열의 원소의 수가 2개 이상이면
	k = (start + end) / 2 // k는 반으로 나누기 위한 중간 원소의 인덱스
	
	MergeSort(A, start, k) // 앞부분 재귀 호출
	MergeSort(A, k + 1, end) // 뒷부분 재귀 호출
	
	A[start] ~ A[k] 와 A[k + 1] ~ A[end] 합병
}
```

## Time Complexity

- 분할
    - O(1)
- 합병
    - 2개의 정렬된 배열 A와 B의 크기가 a, b 라면, 최대 비교 횟수는 (a + b - 1)
    - O(a + b)
- 합병별 비교횟수
    - 모든 숫자를 체크하여 합병
    - O(n)
- 합병별 층수
    - n을 계속 반으로 나누다가 나눌 수 없는 크기인 1이 될 때 분할 종료
    - O(log2n)
- 최종 시간 복잡도
    - O(nlogn)

## Space Complexity

- 입력을 위한 메모리 공간 필요
- O(n)