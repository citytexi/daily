package softeer.lv1

private class Solution7698 {
    fun solution(submit: Submit): String {
        val plusSize = submit.num / 5
        val splitSize = submit.num % 5
        val mutableList = mutableListOf<String>()

        for (i in 0 until plusSize) {
            mutableList.add(PLUS_STRING)
        }

        if (splitSize != 0) {
            var splitString = ""
            for (i in 0 until splitSize) {
                splitString += SPLIT_STRING
            }
            mutableList.add(splitString)
        }

        return mutableList.joinToString(" ")
    }

    companion object {
        private const val PLUS_STRING = "++++"
        private const val SPLIT_STRING = "|"
    }
}

private data class Submit(
    val num: Int = 0
)

private fun main() = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt()
    val solution = Solution7698()
    for (i in 0 until t) {
        println(solution.solution(Submit(readLine().toInt())))
    }
}