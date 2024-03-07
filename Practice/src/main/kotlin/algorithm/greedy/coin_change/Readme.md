# 동전 거스름돈 (Coin Change)
- 동전 거스름돈의 최소 동전 수를 찾는 문제
    - 단, 동전 액면 500, 100, 50, 10, 1

### Idea

- 남은 액수를 초과하지 않는 조건하에 가장 큰 액면의 동전을 택함

### Pseudo code

- CoinChange(W)
- Input: 거스름돈 액수 W
- Output: 거스름돈 액수에 대한 최소 동전 수

```kotlin
1. change = W 
   n500 = n100 = n50 = n10 = n1 = 0 // n500, n100, n50, n10, n1은 각각의 동전 카운트
2. while ( change ≥ 500 ) change = change-500, n500++ // 500원짜리 동전 수를 1 증가
3. while ( change ≥ 100 ) change = change-100, n100++ // 100원짜리 동전 수를 1 증가
4. while ( change ≥ 50 ) change = change-50, n50++ // 50원짜리 동전 수를 1 증가
5. while ( change ≥ 10 ) change = change-10, n10++ // 10원짜리 동전 수를 1 증가
6. while ( change ≥ 1 ) change = change-1, n1++ // 1원짜리 동전 수를 1 증가
7. return (n500 + n100 + n50 + n10 + n1) // 총 동전 수를 리턴한다.
```

### 특성

- 가장 큰 액면을 처리하는 동안 나머지 액면에 대해서는 전혀 고려하지 않음
- 160원, 140원 등의 예외적인 액면이 발생할 경우 최소의 답을 줄 수 없음

### 한계

- CoinChange 알고리즘은 항상 최적의 답을 주지 못함

### Example

```kotlin
private class CoinChangeExample {

    /**
     * @param w 거스름돈 액수
     * @return 거스름돈 액수에 대한 최소 동전 수
     * **/
    fun coinChange(w: Int): Int {
        var change = w
        var (n500, n100, n50, n10, n1) = intArrayOf(0, 0, 0, 0, 0)

        while (change >= 500) {
            change -= 500
            n500++
        }

        while (change >= 100) {
            change -= 100
            n100++
        }

        while (change >= 50) {
            change -= 50
            n50++
        }

        while (change >= 10) {
            change -= 10
            n10++
        }

        while (change >= 1) {
            change -= 1
            n1++
        }

        return n500 + n100 + n50 + n10 + n1
    }
}

private fun main() {
    val coinChangeExample = CoinChangeExample()

    println(coinChangeExample.coinChange(760))
}
```