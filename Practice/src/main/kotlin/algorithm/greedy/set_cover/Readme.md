# 집합 커버 (Set Cover) 문제

- n 개의 원소를 가진 집합 U
- U의 부분집합들을 원소로 하는 집합 F
- F의 원소들 중 선택하여 합집합이 U랑 같아지는 원소의 최소 개수

## 신도시 학교 배치 문제

- 아래 두 조건을 만족하는 학교 위치 선정
    - 학교는 마을에 위치해야 함
    - 등교 거리는 걸어서 15분 이내이어야 함
- 학교의 위치 최소

### 예시 문제 도식화

```kotlin
1. U = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}
2. F = {S1, S2, S3, S4, S5, S6, S7, S8, S9, S10}
3. S1 = {1, 2, 3, 8}
4. S2 = {1, 2, 3, 4, 8}
5. S3 = {1, 2, 3, 4}
6. S4 = {2, 3, 4, 5, 7, 8}
7. S5 = {4, 5, 6, 7}
8. S6 = {5, 6, 7, 9, 10}
9. S7 = {4, 5, 6, 7}
10. S8 = {1, 2, 4, 8}
11. S9 = {6, 9}
12. S10 = {6, 10}

답: S2 ∪ S6 = {1, 2, 3, 4, 8} ∪ {5, 6, 7, 9, 10} 
	       = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10} = U
```

### 최적해 구하기

- 단순한 방법
    - F에 있는 집합들의 모든 조합을 1개씩 합집합하여 U가 되는지 확인
    - U가 되는 조합의 집합 수가 최소인 것을 찾는 것
    - 해당 방법은 n개의 원소가 있을 경우 (2^n - 1)개를 다 검사 → 조합 공식 사용
    - n이 커지면 최적해를 찾는 것은 실질적으로 불가능
- 극복 방법
    - 최적해에 근접한 근사해 (approximation solution) 찾기

### Pseudo code

- SetCover(U, F)
- Input
    - n 개의 원소를 가진 집합 U
    - 부분집합을 원소로하는 집합 F
- Output: 집합 C

```kotlin
1. C = ∅
2. while (U ≠ ∅) do {
3.      U의 원소들을 가장 많이 포함하고 있는 집합 Si를 F에서 선택한다.
4.      U = U - Si
5.      Si를 F에서 제거하고, Si를 C에 추가한다.
6. }
6. return C
```

### Time Complexity

- while 루프 실행 최대 n 번
- F에서 Si 선택 시 최대 O(n^2)
- U에서 원소 제거 O(n)
- 따라서 O(n) * (O(n^2) + O(n)) = O(n^3)

### Example

```kotlin
private class SetCoverExample {
    fun solution(
        u: List<Int>,
        f: MutableList<MutableList<Int>>
    ): List<List<Int>> = setCover(u, f)

    private fun setCover(
        u: List<Int>,
        f: MutableList<MutableList<Int>>,
    ): List<List<Int>> {
        val c = mutableListOf<MutableList<Int>>()
        var currentU = u

        while (currentU.isNotEmpty()) {
            f.maxByOrNull { fractionalF -> currentU.filter { it in fractionalF }.size }
                ?.let { s ->
                    currentU = currentU.filter { it !in s }
                    f.remove(s)
                    c.add(s)
                }
        }

        return c
    }
}

private fun main() {
    val example = SetCoverExample()
    val u = MutableList(10) { it + 1 }

    val s1 = mutableListOf(1, 2, 3, 8)
    val s2 = mutableListOf(1, 2, 3, 4, 8)
    val s3 = mutableListOf(1, 2, 3, 4)
    val s4 = mutableListOf(2, 3, 4, 5, 7, 8)
    val s5 = mutableListOf(4, 5, 6, 7)
    val s6 = mutableListOf(5, 6, 7, 9, 10)
    val s7 = mutableListOf(4, 5, 6, 7)
    val s8 = mutableListOf(1, 2, 4, 8)
    val s9 = mutableListOf(6, 9)
    val s10 = mutableListOf(6, 10)

    val f = mutableListOf(
        s1,
        s2,
        s3,
        s4,
        s5,
        s6,
        s7,
        s8,
        s9,
        s10
    )

    println(example.solution(u, f))
}
```