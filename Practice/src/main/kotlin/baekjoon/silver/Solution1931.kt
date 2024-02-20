package baekjoon.silver

import java.util.PriorityQueue

/*
문제
한 개의 회의실이 있는데 이를 사용하고자 하는 N개의 회의에 대하여 회의실 사용표를 만들려고 한다. 각 회의 I에 대해 시작시간과 끝나는 시간이 주어져 있고, 각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 회의의 최대 개수를 찾아보자. 단, 회의는 한번 시작하면 중간에 중단될 수 없으며 한 회의가 끝나는 것과 동시에 다음 회의가 시작될 수 있다. 회의의 시작시간과 끝나는 시간이 같을 수도 있다. 이 경우에는 시작하자마자 끝나는 것으로 생각하면 된다.

입력
첫째 줄에 회의의 수 N(1 ≤ N ≤ 100,000)이 주어진다. 둘째 줄부터 N+1 줄까지 각 회의의 정보가 주어지는데 이것은 공백을 사이에 두고 회의의 시작시간과 끝나는 시간이 주어진다. 시작 시간과 끝나는 시간은 231-1보다 작거나 같은 자연수 또는 0이다.

출력
첫째 줄에 최대 사용할 수 있는 회의의 최대 개수를 출력한다.
* */

private class Solution1931 {
    fun solution(queue: PriorityQueue<Pair<Int, Int>>): Int {
        var answer = 1
        var current = queue.poll()

        while (queue.isNotEmpty()) {
            val next = queue.poll()
            if (current.second <= next.first) {
                answer += 1
                current = next
            }
        }

        return answer
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val priorityQueue = PriorityQueue<Pair<Int, Int>>(
        compareBy(
            { it.second },
            { it.first }
        )
    )

    repeat(br.readLine().toInt()) {
        val (start, end) = br.readLine().split(" ").map { it.toInt() }
        priorityQueue.add(start to end)
    }

    val solution1931 = Solution1931()
    bw.append("${solution1931.solution(priorityQueue)}\n")
    bw.flush()

    br.close()
    bw.close()
}