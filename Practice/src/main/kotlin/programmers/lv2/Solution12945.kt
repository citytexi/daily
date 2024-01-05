package programmers.lv2
/*
문제 설명
피보나치 수는 F(0) = 0, F(1) = 1일 때, 1 이상의 n에 대하여 F(n) = F(n-1) + F(n-2) 가 적용되는 수 입니다.

예를들어

F(2) = F(0) + F(1) = 0 + 1 = 1
F(3) = F(1) + F(2) = 1 + 1 = 2
F(4) = F(2) + F(3) = 1 + 2 = 3
F(5) = F(3) + F(4) = 2 + 3 = 5
와 같이 이어집니다.

2 이상의 n이 입력되었을 때, n번째 피보나치 수를 1234567으로 나눈 나머지를 리턴하는 함수, solution을 완성해 주세요.

제한 사항
n은 2 이상 100,000 이하인 자연수입니다.

입출력 예
n	return
3	2
5	5

* */
private class Solution12945 {
    val list = MutableList(100_001) { index: Int ->
        when (index) {
            1 -> 1
            else -> 0
        }
    }
    fun solution(n: Int): Int = fibonacci(n)

    private tailrec fun fibonacci(n: Int): Int = if (n < 2 || list[n] > 0) {
        list[n]
    } else {
        ((fibonacci(n - 1)+ fibonacci(n - 2)) % 1234567).also {
            list[n] = it
        }
    }
}

private fun main() {
    val solution = Solution12945()
    println(solution.solution(3))
    println(solution.solution(5))
    println(solution.solution(9))
    println(solution.solution(81))
    println(solution.solution(100_000))
}