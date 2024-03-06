# 최근접 점의 쌍 찾기 (**Closest Pair of Points Problem**)

- 2차원 평면상의 n개의 점이 입력으로 주어질 때, 거리가 가장 가까운 한 쌍의 점을 찾는 문제

### 간단한 idea

- 모든 점에 대하여 각각의 두 점 사이의 거리를 계산하여 가장 가까운 점의 쌍을 찾는다
    - example
        - 1, 2, 3, 4, 5 총 5개의 점이 주어진 경우
            - 비교해야 할 쌍의 개수 = nC2 = n(n-1)/2
            - 시간 복잡도는 O(n^2)
- O(n^2)보다 효율적인 분할 정복 이용
- n개의 점을 1/2로 분할하여 각각의 부분 문제에서 최근접 점의 쌍을 찾고, 2개의 부분해 중에서 짧은 거리를 가진 점의 쌍을 찾는다
    - 2개의 부분해를 취합할 때 아래와 같이 양쪽을 비교해서 짧은 해라고 답으로 정할 수 없다.
    - 거리가 짧은 해 이내의 중간 영역 안에 있는 점들 중에 최근접 점의 쌍이 있는지도 확인해보아야 한다.


### Pseudo code

- ClosestPair(S)
- input:  x 좌표의 오름차순으로 정렬된 배열 S에 있는 i개의 점
    - (단, 각 점은 (x,y)로 표현된다.)
- output: S에 있는 점들 중 최근접 점의 쌍의 거리

```kotlin
1. if (i ≤ 3) return (2 또는 3개의 점들 사이의 최근접 쌍)
2. 정렬된 S를 같은 크기의 SL과 SR로 분할한다. |S|가 홀수이면, |SL| = |SR|+1이 되도록 분할한다.
3. CPL = ClosestPair(SL)         // CPL은 SL에서의 최근접 점의 쌍
4. CPR = ClosestPair(SR)        // CPR은 SR에서의 최근접 점의 쌍
5. d = min{dist(CPL), dist(CPR)}일 때, 
   중간 영역에 속하는 점들 중에서 최근접 점의 쌍을 찾아서 이를 CPC라고 하자.
   단, dist()는 두 점 사이의 거리이다.
6. return (CPL, CPC, CPR 중에서 거리가 가장 짧은 쌍)
```

### Time Complexity

- 입력 S에 n개의 점이 있다면
    - Preprocessing 과정으로 S의 점을 x 좌표로 정렬 → O(nlogn)
- S-L 과 S-R에 대하여 각각 ClosestPair 호출
    - 합병 정렬과 동일
- d = min{dist(CPL), dist(CPR)}
    - y 좌표로 정렬하는 과정에서 O(nlogn)
- ClosestPair의 분할 과정은 합병 정렬의 분할과 동일
    - 추가로 해를 취합하여 올라가는 과정에서 O(nlogn)
- 각 층의 수행시간 O(nlogn), 층수인 logn을 곱하면 O(nlog^2n)
- 미리 y 축을 정렬할 경우 O(nlogn)도 가능

### 응용

- 컴퓨터 그래픽스
- 컴퓨터 비전
- Geographic Information System
- etc