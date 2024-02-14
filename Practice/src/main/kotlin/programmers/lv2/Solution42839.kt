package programmers.lv2

import java.math.BigInteger
import kotlin.math.sqrt

/*
문제 설명
한자리 숫자가 적힌 종이 조각이 흩어져있습니다. 흩어진 종이 조각을 붙여 소수를 몇 개 만들 수 있는지 알아내려 합니다.

각 종이 조각에 적힌 숫자가 적힌 문자열 numbers가 주어졌을 때, 종이 조각으로 만들 수 있는 소수가 몇 개인지 return 하도록 solution 함수를 완성해주세요.

제한사항
numbers는 길이 1 이상 7 이하인 문자열입니다.
numbers는 0~9까지 숫자만으로 이루어져 있습니다.
"013"은 0, 1, 3 숫자가 적힌 종이 조각이 흩어져있다는 의미입니다.

입출력 예
numbers	return
"17"	3
"011"	2

입출력 예 설명
예제 #1
[1, 7]으로는 소수 [7, 17, 71]를 만들 수 있습니다.

예제 #2
[0, 1, 1]으로는 소수 [11, 101]를 만들 수 있습니다.

11과 011은 같은 숫자로 취급합니다.
* */

private class Solution42839 {
    fun solution(numbers: String): Int {
        var answer = 0
        val set = mutableSetOf<BigInteger>()

        for (i in 1 .. numbers.length) {
            permutation(numbers.toCharArray().toTypedArray(), i).forEach {
                set.add(it.joinToString("").toBigInteger())
            }
        }

        return set.filter { isPrime(it.toInt()) }.size
    }
    private fun isPrime(num: Int): Boolean {
        when (num) {
            0, 1 -> return false
            else -> {
                val sqrt = sqrt(num.toDouble()).toInt()

                for(i in 2..sqrt) {
                    if(num % i == 0) {
                        return false
                    }
                }

                return true
            }
        }
    }

    private fun <T> permutation(elements: Array<T>, r: Int): List<List<T>> {
        val visited = BooleanArray(elements.size)
        val results = mutableListOf<List<T>>()

        val result = elements.sliceArray(0 until r)

        fun recursionPermutation(depth: Int = 0) {
            if (depth == r) {
                results.add(result.toList())
                return
            }

            for (i in elements.indices) {
                if (visited[i]) continue

                visited[i] = true
                result[depth] = elements[i]
                recursionPermutation(depth + 1)
                visited[i] = false
            }
        }

        recursionPermutation()
        return results
    }

}

private fun main() {
    val solution42839 = Solution42839()

    println(solution42839.solution("17"))
    println(solution42839.solution("011"))
}