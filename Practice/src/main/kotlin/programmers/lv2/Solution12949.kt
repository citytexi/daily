package programmers.lv2

/*
문제 설명
2차원 행렬 arr1과 arr2를 입력받아, arr1에 arr2를 곱한 결과를 반환하는 함수, solution을 완성해주세요.

제한 조건
행렬 arr1, arr2의 행과 열의 길이는 2 이상 100 이하입니다.
행렬 arr1, arr2의 원소는 -10 이상 20 이하인 자연수입니다.
곱할 수 있는 배열만 주어집니다.

입출력 예
arr1	arr2	return
[[1, 4], [3, 2], [4, 1]]	[[3, 3], [3, 3]]	[[15, 15], [15, 15], [15, 15]]
[[2, 3, 2], [4, 2, 4], [3, 1, 4]]	[[5, 4, 3], [2, 4, 1], [3, 1, 1]]	[[22, 22, 11], [36, 28, 18], [29, 20, 14]]
* */

private class Solution12949 {
    fun solution(arr1: Array<IntArray>, arr2: Array<IntArray>): Array<IntArray> {
        val answer = Array(arr1.size) { IntArray(arr2[0].size) { 0 } }

        for (row in arr1.indices) {
            for (col in arr2[0].indices) {
                var result = 0
                for (i in arr1[0].indices) {
                    result += arr1[row][i] * arr2[i][col]
                }
                answer[row][col] = result
            }
        }

        return answer
    }
}

private fun main() {
    val solution12949 = Solution12949()
    println(
        solution12949.solution(
            arr1 = arrayOf(
                intArrayOf(1, 4),
                intArrayOf(3, 2),
                intArrayOf(4, 1)
            ),
            arr2 = arrayOf(
                intArrayOf(3, 3),
                intArrayOf(3, 3)
            )
        ).map { it.toList() }.toList()
    )
    println(
        solution12949.solution(
            arr1 = arrayOf(
                intArrayOf(2, 3, 2),
                intArrayOf(4, 2, 4),
                intArrayOf(3, 1, 4)
            ),
            arr2 = arrayOf(
                intArrayOf(5, 4, 3),
                intArrayOf(2, 4, 1),
                intArrayOf(3, 1, 1)
            )
        ).map { it.toList() }.toList()
    )

    println(
        solution12949.solution(
            arr1 = arrayOf(
                intArrayOf(2, 3, 2),
                intArrayOf(4, 2, 4),
                intArrayOf(3, 1, 4)
            ),
            arr2 = arrayOf(
                intArrayOf(5, 4),
                intArrayOf(2, 4),
                intArrayOf(3, 1)
            )
        ).map { it.toList() }.toList()
    )
}