package baekjoon.gold.solution_1202

import java.util.*

private class Solution1202 {

    fun solution(
        n: Int,
        k: Int,
        jewels: PriorityQueue<Jewel>,
        bags: LongArray
    ): Long {
        var result = 0L

        val prices = PriorityQueue<Long>(Collections.reverseOrder())

        for (i in bags.indices) {
            val bag = bags[i]

            while (jewels.isNotEmpty()) {
                if (jewels.peek().weight > bag) {
                    break
                }

                prices.add(jewels.poll().price)
            }

            if (prices.isNotEmpty()) {
                result += prices.poll()
            }
        }

        return result
    }

    data class Jewel(
        val weight: Long,
        val price: Long
    ) : Comparable<Jewel> {
        override fun compareTo(other: Jewel): Int = when (weight) {
            other.weight -> price.compareTo(other.price)
            else -> weight.compareTo(other.weight)
        }

    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    val jewels = PriorityQueue<Solution1202.Jewel>()


    repeat(n) {
        st = StringTokenizer(br.readLine())
        val weight = st.nextToken().toLong()
        val price = st.nextToken().toLong()

        jewels.offer(Solution1202.Jewel(weight, price))
    }

    val bags = LongArray(k) {
        st = StringTokenizer(br.readLine())
        st.nextToken().toLong()
    }.sortedArray()

    bw.write("${Solution1202().solution(n, k, jewels, bags)}\n")
    bw.flush()

    br.close()
    bw.close()
}

/*
9 5
4 5
4 9
4 10
8 55
14 20
9 15
8 55
8 5
11 54
10
5
4
15
20

183

4 4
1 100
2 200
13 300
10 500
10
10
10
14

* */
