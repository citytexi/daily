package baekjoon.platinum.solution_1786

private class Solution1786 {
    fun solution(
        t: String,
        p: String
    ): String {
        val tArray = t.toCharArray()
        val pArray = p.toCharArray()

        val stringBuilder = StringBuilder()
        val result = mutableListOf<Int>()

        var currentNextValue = 0

        while (tArray.size - currentNextValue >= pArray.size) {
            var nextIndex = Int.MAX_VALUE

            var isSame = true

            for (j in pArray.indices) {
                val currentT = tArray[currentNextValue + j]
                val currentP = pArray[j]

                if (j != 0 && currentT == pArray[0]) {
                    nextIndex = minOf(nextIndex, currentNextValue + j)
                }

                when (currentT) {
                    currentP -> Unit
                    else -> {
                        isSame = false
                    }
                }
            }

            if (isSame) {
                result.add(currentNextValue + 1)
            }

            when (nextIndex) {
                Int.MAX_VALUE -> currentNextValue += pArray.size
                else -> currentNextValue = nextIndex
            }
        }

        stringBuilder.append("${result.size}\n")
        result.forEach { stringBuilder.append("$it ") }

        return stringBuilder.toString()
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val t = br.readLine()
    val p = br.readLine()

    bw.append("${Solution1786().solution(t, p)}\n")
    bw.flush()

    br.close()
    bw.close()
}