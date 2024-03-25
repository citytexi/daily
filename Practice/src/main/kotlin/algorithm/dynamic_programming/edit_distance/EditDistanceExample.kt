package algorithm.dynamic_programming.edit_distance

import kotlin.math.min

private class EditDistanceExample {
    fun solution(s: String, t: String): Int {
        val e = Array(s.length + 1) { IntArray(t.length + 1) { 0 } }

        for (i in 1 .. s.length) {
            e[i][0] = i
        }

        for (j in 1 .. t.length) {
            e[0][j] = j
        }

        for (i in e.indices) {
            println(e[i].toList())
        }

        println()


        for (i in 1 .. s.length) {
            for (j in 1 .. t.length) {
                e[i][j] = min(e[i][j - 1] + 1, min(e[i - 1][j] + 1, e[i - 1][j - 1] + if (s[i - 1] == t[j - 1]) 0 else 1 ))
            }
        }

        for (i in e.indices) {
            println(e[i].toList())
        }

        return e[s.length][t.length]
    }
}

private fun main() {
    val example = EditDistanceExample()

    println(example.solution(s = "strong", t = "stone"))
}