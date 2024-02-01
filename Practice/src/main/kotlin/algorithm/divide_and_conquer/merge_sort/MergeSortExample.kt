package algorithm.divide_and_conquer.merge_sort

private class MergeSortExample {
    private lateinit var sortedArr: IntArray

    fun solution(arrA: IntArray, arrB: IntArray): IntArray {
        val arrC = arrA + arrB
        sortedArr = IntArray(arrC.size) { 0 }
        mergeSort(arrC, 0, arrC.size - 1)
        return sortedArr
    }

    private fun mergeSort(
        arr: IntArray,
        start: Int,
        end: Int
    ) {
        if (start < end) {
            val mid = (start + end) / 2
            mergeSort(
                arr = arr,
                start = start,
                end = mid
            )
            mergeSort(
                arr = arr,
                start = mid + 1,
                end = end
            )
            merge(
                arr = arr,
                start = start,
                mid = mid,
                end = end
            )
        }
    }

    private fun merge(
        arr: IntArray,
        start: Int,
        mid: Int,
        end: Int
    ) {
        var i = start
        var j = mid + 1
        var k = start

        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                sortedArr[k] = arr[i]
                i++
            } else {
                sortedArr[k] = arr[j]
                j++
            }
            println("i = $i, j = $j, k = $k, arr = ${arr.toList()}, sortedArr = ${sortedArr.toList()}")
            k++
        }

        if (i > mid) {
            for (t in j..end) {
                sortedArr[k] = arr[t]
                k++
            }
        } else {
            for (t in i..mid) {
                sortedArr[k] = arr[t]
                k++
            }
        }

        for (t in start..end) {
            arr[t] = sortedArr[t]
        }
    }
}

private fun main() {
    val solution = MergeSortExample()
    println(
        solution.solution(
            arrA = intArrayOf(6, 14, 18, 20, 29),
            arrB = intArrayOf(1, 2, 15, 25, 30, 45)
        ).toList()
    )
}