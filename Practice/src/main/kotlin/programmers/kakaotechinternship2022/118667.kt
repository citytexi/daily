package programmers.kakaotechinternship2022

/**
 * 두 개의 길이가 같은 큐가 주어지면, 다음 작업을 반복하여 두 큐의 요소 합을 동일하게 만드는 함수입니다:
 * * 한 큐에서 요소를 팝합니다.
 * * 팝된 요소를 다른 큐에 삽입합니다.
 *
 * 이 함수는 목표를 달성하기 위해 필요한 최소 작업 수를 반환합니다. 각 팝 및 삽입 작업은 하나의 작업으로 계산됩니다.
 *
 * 이 문제에서 큐는 배열로 표현됩니다. 배열에서 첫 번째 요소는 큐에 삽입된 첫 번째 요소입니다.
 * 예를 들어, 큐 [1, 2, 3, 4]가 주어지면, 요소를 팝하면 배열의 첫 번째 요소 (1)가 추출되며, 요소를 삽입하면 요소가 배열의 끝에 추가됩니다.
 *
 * 다음은 두 큐의 예입니다:
 * * queue1 = [3, 2, 7, 2]
 * * queue2 = [4, 6, 5, 1]
 *
 * 두 큐의 모든 요소의 합은 30입니다. 따라서 각 큐의 합은 15여야 합니다.
 * 목표를 달성하는 두 가지 방법이 있습니다.
 * * queue2에서 4, 6 및 5를 팝하고 queue1에 삽입합니다. 그런 다음, queue1에서 3, 2, 7 및 2를 팝하고 queue2에 삽입합니다. 두 큐는 각각 [4, 6, 5] 및 [1, 3, 2, 7, 2]가 되며, 그 합은 15입니다. 이 방법은 7개의 작업이 필요합니다.
 * * queue1에서 3을 팝하고 queue2에 삽입합니다. 그런 다음, queue2에서 4를 팝하고 queue1에 삽입합니다. 두 큐의 합이 동일하게 될 때까지이 작업을 반복합니다. 두 큐는 각각 [2, 7, 2, 4] 및 [6, 5, 1, 3]가 되며, 그 합은 15입니다. 이 방법은 최소한의 2개 작업이 필요합니다.
 * 두 큐의 합을 동일하게 만드는 것이 불가능한 경우 함수는 -1을 반환해야 합니다.
 * @param queue1 큐를 나타내는 정수 배열
 * @param queue2 큐를 나타내는 정수 배열
 * @return 두 큐의 합을 동일하게 만드는 데 필요한 최소 작업 수 또는 불가능한 경우 -1
*/

private class Solution118667 {

    fun solution(
        queue1: IntArray,
        queue2: IntArray
    ): Long {
        val deque1 = ArrayDeque<Long>().apply {
            addAll(queue1.map { it.toLong() })
        }
        val deque2 = ArrayDeque<Long>().apply {
            addAll(queue2.map { it.toLong() })
        }

        var answer: Long = 0
        var deque1Sum: Long = deque1.sum()
        var deque2Sum: Long = deque2.sum()

        while (answer <= queue1.size * 4) {
            if (deque1Sum == deque2Sum) {
                return answer
            }

            ++answer

            when (deque1Sum < deque2Sum) {
                true -> deque2.first().let {
                    deque1.add(it)
                    deque2.removeFirst()
                    deque1Sum += it
                    deque2Sum -= it
                }
                false -> deque1.first().let {
                    deque2.add(it)
                    deque1.removeFirst()
                    deque2Sum += it
                    deque1Sum -= it
                }
            }
        }

        return -1
    }

}

private fun main() {
    val solution118667 = Solution118667()

    println(
        solution118667.solution(
            queue1 = intArrayOf(3, 2, 7, 2),
            queue2 = intArrayOf(4, 6, 5, 1),
        )
    )
    println(
        solution118667.solution(
            queue1 = intArrayOf(1, 2, 1, 2),
            queue2 = intArrayOf(1, 10, 1, 2),
        )
    )
    println(
        solution118667.solution(
            queue1 = intArrayOf(1, 1),
            queue2 = intArrayOf(1, 5),
        )
    )
}