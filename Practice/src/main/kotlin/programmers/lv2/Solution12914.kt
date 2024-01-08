package programmers.lv2

/*
문제 설명
효진이는 멀리 뛰기를 연습하고 있습니다.
효진이는 한번에 1칸, 또는 2칸을 뛸 수 있습니다.
칸이 총 4개 있을 때, 효진이는
(1칸, 1칸, 1칸, 1칸)
(1칸, 2칸, 1칸)
(1칸, 1칸, 2칸)
(2칸, 1칸, 1칸)
(2칸, 2칸)
의 5가지 방법으로 맨 끝 칸에 도달할 수 있습니다.
멀리뛰기에 사용될 칸의 수 n이 주어질 때, 효진이가 끝에 도달하는 방법이 몇 가지인지 알아내,
여기에 1234567를 나눈 나머지를 리턴하는 함수, solution을 완성하세요.

예를 들어 4가 입력된다면, 5를 return하면 됩니다.

제한 사항
n은 1 이상, 2000 이하인 정수입니다.

입출력 예
n	result
4	5
3	3
2   2
1   1

(2칸, 1칸)
(1칸, 2칸)
(1칸, 1칸, 1칸)

(2칸)
(1칸, 1칸)

(1칸)

* */

private class Solution12914 {
    val list = MutableList(2001) { index: Int ->
        when (index) {
            2 -> 2L
            1 -> 1L
            else -> 0L
        }
    }

    fun solution(n: Int): Long = when (list[n]) {
        0L -> {
            for (i in 3 .. n) {
                list[i] = (list[i - 1] + list[i - 2]) % 1234567L
            }
            list[n]
        }
        else -> list[n]
    }

}

private fun main() {
    val solution12914 = Solution12914()
    println(solution12914.solution(2000))
    println(solution12914.solution(100))
    println(solution12914.solution(8))
    println(solution12914.solution(7))
    println(solution12914.solution(6))
    println(solution12914.solution(5))
    println(solution12914.solution(4))
    println(solution12914.solution(3))
    println(solution12914.solution(2))
    println(solution12914.solution(1))
}