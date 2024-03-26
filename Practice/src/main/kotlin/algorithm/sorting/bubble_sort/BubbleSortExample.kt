package algorithm.sorting.bubble_sort

private class BubbleSortExample {
    fun solution(a: IntArray) {
        val sortedA = bubbleSort(a)
        println(sortedA.toList())
    }

    private fun bubbleSort(a: IntArray): IntArray {
        for (pass in 1 until  a.size) {
            for (i in 1 .. a.size - pass) {
                if (a[i - 1] > a[i]) {
                    val temp = a[i - 1]
                    a[i - 1] = a[i]
                    a[i] = temp
                }
            }
            println("$pass end, a = ${a.toList()}")
        }

        return a
    }
}

private fun main() {
    val example = BubbleSortExample()
    val a = intArrayOf(40, 10, 50, 90, 20, 80, 30, 60)
    example.solution(a)
}