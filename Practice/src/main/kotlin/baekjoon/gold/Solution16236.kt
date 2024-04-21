package baekjoon.gold

private class Solution16236 {
    fun solution(
        n: Int,
        map: Array<IntArray>,
        foods: List<Food>
    ) {
        var currentSize = 1

    }

    data class Food(
        val size: Int,
        val position: Pair<Int, Int>
    )
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val foods = mutableListOf<Solution16236.Food>()
    val map = Array(n) { row ->
        val values = br.readLine().split(" ").map(String::toInt)
        IntArray(n) { col ->
            if (values[col] != 0 && values[col] != 9) {
                foods.add(Solution16236.Food(values[col], row to col))
            }
            values[col]
        }
    }

    Solution16236().solution(n, map, foods)

    bw.flush()

    br.close()
    bw.close()
}