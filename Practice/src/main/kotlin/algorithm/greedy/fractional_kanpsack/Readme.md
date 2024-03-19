# 배낭 (Knapsack) 문제

- n개의 각각 무게와 가치를 지닌 물건을 최대의 가치를 갖도록 배낭에 넣는 문제

## 부분 배낭 (Fractional Knapsack) 문제

- 물건을 나눠서 담는 것을 허용
- 그리디 사용 가능

## 0-1 배낭 문제

- 물건을 통째로 배낭에 넣어야 함
- 동적 계획 알고리즘, 백트래킹 기법, 분기 한정 기법으로 해결

## 부분 배낭 문제 그리디 알고리즘

### Pseudo code

- FractionalKnapsack(n, weightAndPriceList, C)
- Input
    - 물건 n개
    - 각 물건의 무게와 가치
    - 배낭의 용량 C
- Output
    - 배낭에 담은 물건 리스트 L
    - 배낭에 담은 물건 가치의 합 v

```kotlin
1. 각 물건에 대해 단위 무게 당 가치를 계산
2. 물건들을 단위 무게 당 가치를 기준으로 내림차순으로 정렬하고, 정렬된 물건 리스트를 S라고 하자
3. L=∅, w=0, v=0 
// L은 배낭에 담을 물건 리스트, w는 배낭에 담긴 물건들의 무게의 합, v는 배낭에 담긴 물건들의 가치의 합
4. S에서 단위 무게 당 가치가 가장 큰 물건 x를 가져옴
5. while ( (w + x의 무게) ≤ C ) { 
6.      x를 L에 추가
7.      w = w + x의 무게
8.      v = v + x의 가치
9.      x를 S에서 제거
10.	S에서 단위 무게 당 가치가 가장 큰 물건 x를 가져옴
11.}
12. if ((C-w) > 0) { // 배낭에 물건을 부분적으로 담을 여유가 있으면 
13. 	물건 x를 (C-w)만큼만 L에 추가 
14. 	v = v +(C-w)만큼의 x의 가치
15.}
16. return L, v
```

### Example

```kotlin
import java.util.PriorityQueue

private class FractionalKanpsackExample {
    fun solution(
        n: Int,
        weightAndPriceList: List<Object>,
        c: Int
    ): Pair<List<Object>, Int> {
        val l = mutableListOf<Object>()
        var w = 0
        var v = 0

        val priorityQueue = PriorityQueue<Object> { a, b -> -a.weightPerPrice.compareTo(b.weightPerPrice) }
        priorityQueue.addAll(weightAndPriceList)

        var x = priorityQueue.poll()

        while ((w + x.weight <= c)) {
            l.add(x)
            w += x.weight
            v += x.price
            x = priorityQueue.poll()
        }

        if ((c - w) > 0) {
            val newObject = Object(
                weight = c - w,
                price = ((c - w) * x.weightPerPrice).toInt()
            )
            l.add(newObject)
            v += newObject.price
        }

        return l to v
    }

    data class Object(
        val weight: Int,
        val price: Int,
        val weightPerPrice: Double = price.toDouble() / weight.toDouble()
    )
}

private fun main() {
    val example = FractionalKanpsackExample()

    println(
        example.solution(
            n = 4,
            weightAndPriceList = listOf(
                FractionalKanpsackExample.Object(
                    weight = 50,
                    price = 5
                ),
                FractionalKanpsackExample.Object(
                    weight = 10,
                    price = 60
                ),
                FractionalKanpsackExample.Object(
                    weight = 25,
                    price = 10
                ),
                FractionalKanpsackExample.Object(
                    weight = 15,
                    price = 75
                ),
            ),
            c = 40
        )
    )
}
```

### Time Complexity

- 단위 무게 당 가치 계산 - O(n)
- 단위 무게당 가치 내림차순 정렬 - O(nlogn)
- O(n) + O(nlogn) = O(nlogn)