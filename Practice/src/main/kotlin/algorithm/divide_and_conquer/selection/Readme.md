# 선택 문제 (Selection Problem)

- n 개의 숫자들 중 k 번째로 작은 숫자를 찾는 문제

### 간단한 idea

- 최소 숫자를 k번 찾는다
    - O(kn)
- 숫자들을 정렬한 후 k번째 숫자를 찾는다
    - O(nlogn)

### Idea

- 이진 탐색 (Binary Search)
    - 정렬된 입력인 경우, 중간에 있는 숫자와 찾고자 하는 숫자를 비교함으로써, 입력을 반으로 나눈 두 부분 중 한 부분만을 검색
- 선택 문제
    - 입력이 정렬되지 않는 형태, 입력 숫자 중 피봇을 선택하여 분할
    - Small group은 피봇보다 작은 숫자 그룹, Large group은 피봇보다 큰 숫자 그룹
    - 각 그룹의 크기 (숫자 개수)를 알면 k번째 작은 숫자가 어느 그룹에 있는지 알 수 있음

### Pseudo code

- Selection(A, left, right, k)
- Input: A[left] ~ A[right]와 k, 단 1 ≤ k ≤ |A|, |A| = right - left + 1
- Output: A[left] ~ A[right]에서 k 번째 작은 원소

```kotlin
1. 피봇을 A[left] ~ A[right]에서 랜덤하게 선택
2. 피봇과 A[left] 변경
3. 피봇과 배열의 각 원소와 비교하여 피봇보다 작은 숫자는 A[left] ~ A[p - 1]로 옮기고,
 피봇보다 큰 숫자는 A[p + 1] ~ A[right]로 옮기고 피봇은 A[p]에 놓는다. (partition)
4. S = (p - 1) - left + 1 // S = Small Group의 크기
5. if (k <= S) Selection(A, left, p - 1, k) // Small Group에서 찾기
6. else if (k = S + 1) return A[p] // 피봇 = k번째 작은 숫자
7. else Selection(A, p + 1, right, k - S - 1) // Large group에서 찾기
```