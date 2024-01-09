package programmers.lv1

/*
가운데 글자 가져오기
문제 설명
단어 s의 가운데 글자를 반환하는 함수, solution을 만들어 보세요. 단어의 길이가 짝수라면 가운데 두글자를 반환하면 됩니다.

재한사항
s는 길이가 1 이상, 100이하인 스트링입니다.

입출력 예
s	return
"abcde"	"c"
"qwer"	"we"
* */

private class Solution12903 {
    fun solution(s: String): String = when (s.length % 2) {
        0 ->  s[s.length / 2 - 1].toString() + s[s.length / 2].toString()
        else -> s[s.length / 2].toString()
    }
}

private fun main() {
    val solution12903 = Solution12903()
    println(solution12903.solution("abcde"))
    println(solution12903.solution("qwer"))
    println(solution12903.solution("a"))
}