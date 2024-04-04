# 기수 정렬 (Radix Sort)

- 비교 정렬이 아닌 정렬 방식
- 숫자를 비교적으로 정렬
- 제한적인 범위 내에 있는 숫자에 대해 자리수 별로 정렬
- 어느 비교 정렬 알고리즘보다 빠름

## 기 (Radix)

- 특정 진수를 나타내는 숫자
- 예시
    - 10진수 → 0, 1, 2, 3, …, 9
    - 2진수 → 0, 1

## 정렬 알고리즘의 안정성(Stability)

- 입력에 중복된 숫자가 있을 때, 정렬된 후에도 중복된 숫자의 순서가 입력에서의 순서와 동일한 경우

## Pseudo code

- RadixSort
- Input: n개의 r진수의 k자리 숫자
- Output: 정렬된 숫자

```kotlin
1. for i = 1 to k
2. 각 숫자의 i자리 숫자에 대해 안정한 정렬을 수행한다
3. return 배열 A

stableSort(A, n, e)
  for i = 0 to n - 1 :
      count[A[i]의 e번째 자리수] += 1  // 각 기수 별로 해당 숫자의 개수를 셈
  for i = 1 to 9:
      count[i] += count[i-1]  // 각 기수 별로 마지막 숫자의 위치를 계산
  for i = n - 1 to 0:
      output[count[A[i]의 e번째 자리수] - 1] = A[i] // 각 숫자를 기수별 마지막
                                                                                                                                 // 위치에 저장한다.
      count[count[A[i]의 e번째 자리수] -= 1 // 해당 기수의 마지막 위치를 앞으로 옮김
return output

```

## Example code

```kotlin
import kotlin.math.pow

private class RadixSortExample {
    fun radixSort(
        n: Int,
        r: Int,
        k: Int,
        a: IntArray
    ): IntArray {
        var output: IntArray? = null
        for (i in 0 until  k) {
            output = stableSort(
                a = output ?: a,
                n = n,
                r = r,
                e = i
            )
        }

        return output ?: a
    }

    private fun stableSort(
        a: IntArray,
        n: Int,
        r: Int,
        e: Int
    ): IntArray {
        val output = IntArray(n)
        val count = IntArray(r)

        for (i in 0 until n) {
            var num = a[i]
            if (e != 0) {
                num /= r.toDouble().pow(e).toInt()
            }
            num %= r
            count[num] += 1
        }

        for (i in 1 until count.size) {
            count[i] += count[i - 1]
        }

        for (i in n - 1 downTo 0) {
            var num = a[i]
            if (e != 0) {
                num /= r.toDouble().pow(e).toInt()
            }
            num %= r

            output[count[num] - 1] = a[i]
            count[num] -= 1
        }

        return output
    }
}

private fun main() {
    val bw = System.out.bufferedWriter()
    bw.append("${RadixSortExample().radixSort(
        n = 5,
        r = 10,
        k = 3,
        intArrayOf(89, 70, 35, 131, 910)
    ).toList()}\n")
    bw.append("${RadixSortExample().radixSort(
        n = 8,
        r = 10,
        k = 2,
        intArrayOf(90, 10, 35, 13, 10, 35, 31, 8)
    ).toList()}\n")
    bw.flush()
    bw.close()
}
```

## Time Complexity

- for 반복문 k번 반복
- O(n + r)
    - 한번 루프가 수행될 때 n개의 숫자의 i자리 수를 읽음
    - r개로 분류하여 개수를 셈
- 따라서 O(k(n + r))
    - k나 r이 n보다 크지 않으면 O(n)