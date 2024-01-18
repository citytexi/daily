package programmers.lv2

/*
문제 설명
다음 규칙을 지키는 문자열을 올바른 괄호 문자열이라고 정의합니다.

(), [], {} 는 모두 올바른 괄호 문자열입니다.
만약 A가 올바른 괄호 문자열이라면, (A), [A], {A} 도 올바른 괄호 문자열입니다.
예를 들어, [] 가 올바른 괄호 문자열이므로, ([]) 도 올바른 괄호 문자열입니다.
만약 A, B가 올바른 괄호 문자열이라면, AB 도 올바른 괄호 문자열입니다.
예를 들어, {} 와 ([]) 가 올바른 괄호 문자열이므로, {}([]) 도 올바른 괄호 문자열입니다.
대괄호, 중괄호, 그리고 소괄호로 이루어진 문자열 s가 매개변수로 주어집니다.
이 s를 왼쪽으로 x (0 ≤ x < (s의 길이)) 칸만큼 회전시켰을 때 s가 올바른 괄호 문자열이 되게 하는 x의 개수를 return 하도록 solution 함수를 완성해주세요.

제한사항
s의 길이는 1 이상 1,000 이하입니다.

입출력 예
s	result
"[](){}"	3
"}]()[{"	2
"[)(]"	0
"}}}"	0

* */

private class Solution76502 {
    fun solution(s: String): Int {
        var answer = 0
        var str = s

        for (x in s.indices) {
            if (isCorrect(str)) {
                answer++
            }
            str = str.removeRange(0 .. 0) + str.first()
        }

        return answer
    }

    private fun isCorrect(input: String): Boolean {
        var str = input
        var iter = 0

        while (str.length > 1) {
            if (iter >= str.length - 1) {
                break
            }
            when (str[iter].toString() + str[iter + 1].toString()) {
                SUCCESS_STR_1, SUCCESS_STR_2, SUCCESS_STR_3 -> {
                    str = str.removeRange(iter .. iter + 1)
                    if (iter > 0) {
                        iter--
                    }
                }
                else -> iter++
            }
        }

        return when (str.length) {
            0 -> true
            else -> false
        }
    }

    companion object {
        private const val SUCCESS_STR_1 = "[]"
        private const val SUCCESS_STR_2 = "{}"
        private const val SUCCESS_STR_3 = "()"
    }
}

private fun main() {
    val solution76502 = Solution76502()
    println(solution76502.solution("[](){}"))
    println(solution76502.solution("}]()[{"))
    println(solution76502.solution("[)(]"))
    println(solution76502.solution("}}}"))
    println(solution76502.solution("}"))
}