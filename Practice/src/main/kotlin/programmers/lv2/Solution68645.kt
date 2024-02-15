package programmers.lv2

/*
문제 설명
정수 n이 매개변수로 주어집니다.
다음 그림과 같이 밑변의 길이와 높이가 n인 삼각형에서 맨 위 꼭짓점부터 반시계 방향으로 달팽이 채우기를 진행한 후,
첫 행부터 마지막 행까지 모두 순서대로 합친 새로운 배열을 return 하도록 solution 함수를 완성해주세요.

제한사항
n은 1 이상 1,000 이하입니다.

입출력 예
n	result
1   [1] 1
2   [1,2,3] 3
3   [1,2,6,3,4,5] 6
4	[1,2,9,3,10,8,4,5,6,7] 10
5	[1,2,12,3,13,11,4,14,15,10,5,6,7,8,9] 15
6	[1,2,15,3,16,14,4,17,21,13,5,18,19,20,12,6,7,8,9,10,11] 21

입출력 예 설명
입출력 예 #1

문제 예시와 같습니다.
입출력 예 #2

문제 예시와 같습니다.
입출력 예 #3

문제 예시와 같습니다.
* */

private class Solution68645 {
    private lateinit var sizeDp: IntArray
    fun solution(n: Int): IntArray {
        sizeDp = IntArray(n + 1)
        sizeDp[1] = 1

        val size = sizeCheck(n)

        val arr = List(n) { row -> MutableList(row + 1) { 0 } }
        val orientation = arrayOf(
            1 to 0,
            0 to 1,
            -1 to -1
        )

        var number = 1
        var moveCount = 0
        var moveMaxCount = n
        var orientationIndex = 0
        var row = 0
        var col = 0
        while (number <= size) {
            arr[row][col] = number++
            moveCount += 1

            if (moveCount == moveMaxCount) {
                moveMaxCount -= 1
                moveCount = 0
                orientationIndex += 1
            }

            val (addRow, addCol) = orientation[orientationIndex % 3]
            row += addRow
            col += addCol
        }

        return arr.flatten().toIntArray()
    }

    private fun sizeCheck(n: Int): Int = when (sizeDp[n]) {
        0 -> {
            sizeDp[n - 1] = sizeCheck(n - 1)
            sizeDp[n] = sizeDp[n - 1] + n
            sizeDp[n]
        }
        else -> sizeDp[n]
    }

}

private fun main() {
    val solution68645 = Solution68645()
    println(solution68645.solution(1).toList())
    println(solution68645.solution(2).toList())
    println(solution68645.solution(3).toList())
    println(solution68645.solution(4).toList())
    println(solution68645.solution(5).toList())
    println(solution68645.solution(6).toList())
}