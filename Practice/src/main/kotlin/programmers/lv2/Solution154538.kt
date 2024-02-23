package programmers.lv2

/*
문제 설명
자연수 x를 y로 변환하려고 합니다. 사용할 수 있는 연산은 다음과 같습니다.

x에 n을 더합니다
x에 2를 곱합니다.
x에 3을 곱합니다.
자연수 x, y, n이 매개변수로 주어질 때, x를 y로 변환하기 위해 필요한 최소 연산 횟수를 return하도록 solution 함수를 완성해주세요. 이때 x를 y로 만들 수 없다면 -1을 return 해주세요.

제한사항
1 ≤ x ≤ y ≤ 1,000,000
1 ≤ n < y

입출력 예
x	y	n	result
10	40	5	2
10	40	30	1
2	5	4	-1

입출력 예 설명
입출력 예 #1
x에 2를 2번 곱하면 40이 되고 이때가 최소 횟수입니다.

입출력 예 #2
x에 n인 30을 1번 더하면 40이 되고 이때가 최소 횟수입니다.

입출력 예 #3
x를 y로 변환할 수 없기 때문에 -1을 return합니다.
* */

private class Solution154538 {
    fun solution(x: Int, y: Int, n: Int): Int {
        val dp = IntArray(y + 1)
        var currentX = x

        if (x == y) {
            return 0
        }

        while (currentX < y) {
            if (currentX != x && dp[currentX] == 0) {
                currentX += 1
                continue
            }
            val time = dp[currentX] + 1

            dp.getOrNull(currentX * 2)?.let {
                when (it) {
                    0 -> dp[currentX * 2] = time
                    else -> if (it > time) {
                        dp[currentX * 2] = time
                    }
                }
            }

            dp.getOrNull(currentX * 3)?.let {
                when (it) {
                    0 -> dp[currentX * 3] = time
                    else -> if (it > time) {
                        dp[currentX * 3] = time
                    }
                }
            }

            dp.getOrNull(currentX + n)?.let {
                when (it) {
                    0 -> dp[currentX + n] = time
                    else -> if (it > time) {
                        dp[currentX + n] = time
                    }
                }
            }

            currentX += 1
        }

        return when (dp[y]) {
            0 -> -1
            else -> dp[y]
        }
    }
}

private fun main() {
    val solution154538 = Solution154538()
    println(solution154538.solution(10, 10, 5))
    println(solution154538.solution(10, 40, 5))
    println(solution154538.solution(10, 40, 30))
    println(solution154538.solution(2, 5, 4))
    println(solution154538.solution(38, 40, 2))
}