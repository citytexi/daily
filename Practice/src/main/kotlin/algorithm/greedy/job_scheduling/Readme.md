# 작업 스케줄링 (Job Scheduling) 문제

- 작업의 수행 시간이 중복되지 않도록 모든 작업을 가장 적은 수의 기계에 배정하는 문제
- 시작시간, 종료시간, 작업 길이에 대한 그리디 알고리즘
    - 빠른 시작시간 작업 우선 (Earliest start time first) 배정
    - 빠른 종료시간 작업 우선 (Earliest finish time first) 배정
    - 짧은 작업 우선 (Shortest job first) 배정
    - 긴 작업 우선 (Longest job first) 배정
    - 첫 번째 알고리즘을 제외하고 나머지 3가지는 항상 최적해를 찾지 못함

## Pseudo code

- JobScheduling(t)
- Input: n개의 작업 t1, t2, ⋯, tn
- Output: 각 기계에 배정된 작업 순서

```kotlin
1. 시작시간의 오름차순으로 정렬한 작업 리스트: L 
2. while ( L ≠ ∅ ) {
3.      L에서 가장 이른 시작시간 작업 ti를 가져옴
4.      if (ti를 수행할 기계가 있으면) 
5.              ti를 수행할 수 있는 기계에 배정
6.      else 
7.             새로운 기계에 ti를 배정
8.      ti를 L에서 제거
9. }
10. return 각 기계에 배정된 작업 순서
```

## Example

```kotlin
private class JobSchedulingExample {
    fun solution(t: List<List<Int>>): List<List<List<Int>>> = jobScheduling(t)

    private fun jobScheduling(t: List<List<Int>>): List<List<List<Int>>> {
        val l = t.sortedBy { it[0] }.toMutableList()
        val result = mutableListOf<MutableList<List<Int>>>()

        while (l.isNotEmpty()) {
            val firstT = l.removeFirst()

            var addSuccess = false
            for (i in result.indices) {
                val last = result[i].last()
                if (last[1] <= firstT[0]) {
                    result[i].add(firstT)
                    addSuccess = true
                    break
                }
            }

            if (!addSuccess) {
                result.add(mutableListOf(firstT))
            }
        }

        return result
    }
}

private fun main() {
    val example = JobSchedulingExample()

    val t1 = listOf(7, 8)
    val t2 = listOf(3, 7)
    val t3 = listOf(1, 5)
    val t4 = listOf(5, 9)
    val t5 = listOf(0, 2)
    val t6 = listOf(6, 8)
    val t7 = listOf(1, 6)

    val t = listOf(t1, t2, t3, t4, t5, t6, t7)

    println(example.solution(t))
}
```

## Time Complexity

- n개의 작업 정렬 O(nlogn)
- 수행 가능한 기계 찾기 O(m) → m은 사용된 기계 수
- while 루프 O(n)
- 최종: O(nlogn) + O(mn)