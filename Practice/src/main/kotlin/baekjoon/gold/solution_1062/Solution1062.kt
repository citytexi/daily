package baekjoon.gold.solution_1062

private class Solution1062 {
    private var max = 0

    fun solution(
        n: Int,
        k: Int,
        words: Array<String>
    ): Int = when {
        k < 5 -> 0
        k >= 26 -> n
        else -> {
            val containWords = hashSetOf('a' - 'a', 'c' - 'a', 'i' - 'a', 'n' - 'a', 't' - 'a')

            dfs(0, 0, k, containWords, words)

            max
        }
    }

    fun dfs(
        count: Int,
        index: Int,
        k: Int,
        containWords: HashSet<Int>,
        words: Array<String>
    ) {
        if (count == k - 5) {
            val visited = BooleanArray(26)
            var result = 0

            loop@ for (word in words) {
                var currentCount = 0
                for (char in word) {
                    val value = (char - 'a')

                    if (!containWords.contains(value)) {
                        continue@loop
                    }

                    if (!visited[value]) {
                        visited[value] = true
                        currentCount += 1
                    }

                    if (currentCount > k) {
                        continue@loop
                    }
                }
                result += 1
            }

            max = maxOf(max,result)
            return
        }

        for (i in index until 26) {
            if (containWords.contains(i)) {
                continue
            }

            containWords.add(i)
            dfs(
                count = count + 1,
                index = i + 1,
                k = k,
                containWords = containWords,
                words = words,
            )
            containWords.remove(i)
        }
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, k) = br.readLine().split(" ").map(String::toInt)
    val words = Array(n) { br.readLine().removePrefix("anta").removeSuffix("tica") }

    bw.append("${ Solution1062().solution(n, k, words)}\n")
    bw.flush()

    br.close()
    bw.close()
}