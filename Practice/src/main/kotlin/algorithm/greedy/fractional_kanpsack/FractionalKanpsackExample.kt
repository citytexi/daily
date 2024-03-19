package algorithm.greedy.fractional_kanpsack

import java.util.PriorityQueue

private class FractionalKanpsackExample {
    fun solution(
        n: Int,
        weightAndPriceList: List<Object>,
        c: Int
    ): Pair<List<Object>, Int> {
        val l = mutableListOf<Object>()
        var w = 0
        var v = 0

        val priorityQueue = PriorityQueue<Object> { a, b -> -a.weightPerPrice.compareTo(b.weightPerPrice) }
        priorityQueue.addAll(weightAndPriceList)

        var x = priorityQueue.poll()

        while ((w + x.weight <= c)) {
            l.add(x)
            w += x.weight
            v += x.price
            x = priorityQueue.poll()
        }

        if ((c - w) > 0) {
            val newObject = Object(
                weight = c - w,
                price = ((c - w) * x.weightPerPrice).toInt()
            )
            l.add(newObject)
            v += newObject.price
        }

        return l to v
    }

    data class Object(
        val weight: Int,
        val price: Int,
        val weightPerPrice: Double = price.toDouble() / weight.toDouble()
    )
}

private fun main() {
    val example = FractionalKanpsackExample()

    println(
        example.solution(
            n = 4,
            weightAndPriceList = listOf(
                FractionalKanpsackExample.Object(
                    weight = 50,
                    price = 5
                ),
                FractionalKanpsackExample.Object(
                    weight = 10,
                    price = 60
                ),
                FractionalKanpsackExample.Object(
                    weight = 25,
                    price = 10
                ),
                FractionalKanpsackExample.Object(
                    weight = 15,
                    price = 75
                ),
            ),
            c = 40
        )
    )
}