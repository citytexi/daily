package algorithm.sorting.radix_sort

import kotlin.math.pow

private class RadixSortExample {
    fun radixSort(
        n: Int,
        r: Int,
        k: Int,
        a: IntArray
    ): IntArray {
        var output: IntArray? = null
        for (i in 0 until  k) {
            output = stableSort(
                a = output ?: a,
                n = n,
                r = r,
                e = i
            )
        }

        return output ?: a
    }

    private fun stableSort(
        a: IntArray,
        n: Int,
        r: Int,
        e: Int
    ): IntArray {
        val output = IntArray(n)
        val count = IntArray(r)

        for (i in 0 until n) {
            var num = a[i]
            if (e != 0) {
                num /= r.toDouble().pow(e).toInt()
            }
            num %= r
            count[num] += 1
        }

        for (i in 1 until count.size) {
            count[i] += count[i - 1]
        }

        for (i in n - 1 downTo 0) {
            var num = a[i]
            if (e != 0) {
                num /= r.toDouble().pow(e).toInt()
            }
            num %= r

            output[count[num] - 1] = a[i]
            count[num] -= 1
        }

        return output
    }
}

private fun main() {
    val bw = System.out.bufferedWriter()
    bw.append("${RadixSortExample().radixSort(
        n = 5,
        r = 10,
        k = 3,
        intArrayOf(89, 70, 35, 131, 910)
    ).toList()}\n")
    bw.append("${RadixSortExample().radixSort(
        n = 8,
        r = 10,
        k = 2,
        intArrayOf(90, 10, 35, 13, 10, 35, 31, 8)
    ).toList()}\n")
    bw.flush()
    bw.close()
}