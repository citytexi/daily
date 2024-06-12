package baekjoon.gold.solution_1043

private class Solution1043(
    private val n: Int,
    private val parent: IntArray = IntArray(n + 1) { it }
) {

    fun find(x: Int): Int = when (parent[x]) {
        x -> x
        else -> {
            parent[x] = find(parent[x])
            parent[x]
        }
    }

    fun union(x: Int, y: Int) {
        val findX = find(x)
        val findY = find(y)

        if (findX > findY) {
            parent[findX] = findY
        } else {
            parent[findY] = findX
        }
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)
    val solution1043 = Solution1043(n)

    var inputs = br.readLine().split(" ")
    val solveNum = inputs[0].toInt()

    var index = 1
    for (i in 0 until solveNum) {
        val value = inputs[index].toInt()
        solution1043.union(0, value)
        index += 1
    }

    val party = mutableListOf<MutableList<Int>>()

    repeat(m) {
        inputs = br.readLine().split(" ")
        val containNum = inputs[0].toInt()
        val list = mutableListOf<Int>()

        index = 1
        var prev = inputs[index].toInt()
        for (i in 0 until containNum) {
            val value = inputs[index].toInt()
            list.add(value)
            solution1043.union(prev, value)
            prev = value
            index += 1
        }

        party.add(list)
    }

    var result = 0

    for (current in party) {
        var isSuccess = true

        for (i in current.indices) {
            val inDegree = solution1043.find(current[i])

            if (inDegree == 0) {
                isSuccess = false
                break
            }
        }

        if (isSuccess) {
            result += 1
        }
    }

    bw.append("${result}\n")
    bw.flush()

    br.close()
    bw.close()
}