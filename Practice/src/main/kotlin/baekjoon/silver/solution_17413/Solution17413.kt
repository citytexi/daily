package baekjoon.silver.solution_17413

private class Solution17413 {

    fun solution(str: String): String {
        val wordDeque = ArrayDeque<Char>()
        val tagDeque = ArrayDeque<Char>()

        val sb = StringBuilder()

        var isTag = false

        for (c in str) {
            if (isTag) {
                tagDeque.add(c)

                if (c == '>') {
                    isTag = false

                    while (tagDeque.isNotEmpty()) {
                        sb.append(tagDeque.removeFirst())
                    }
                }
            } else {
                when (c) {
                    '<' -> {
                        isTag = true
                        tagDeque.add(c)
                        while (wordDeque.isNotEmpty()) {
                            sb.append(wordDeque.removeLast())
                        }
                    }
                    ' ' -> {
                        while (wordDeque.isNotEmpty()) {
                            sb.append(wordDeque.removeLast())
                        }
                        sb.append(c)
                    }
                    else -> wordDeque.add(c)
                }
            }
        }

        while (wordDeque.isNotEmpty()) {
            sb.append(wordDeque.removeLast())
        }

        return sb.toString()
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val str = br.readLine()
    bw.append(Solution17413().solution(str))
    bw.flush()

    br.close()
    bw.close()
}