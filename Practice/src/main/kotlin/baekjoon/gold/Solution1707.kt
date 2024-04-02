package baekjoon.gold

/*
문제
그래프의 정점의 집합을 둘로 분할하여, 각 집합에 속한 정점끼리는 서로 인접하지 않도록 분할할 수 있을 때, 그러한 그래프를 특별히 이분 그래프 (Bipartite Graph) 라 부른다.

그래프가 입력으로 주어졌을 때, 이 그래프가 이분 그래프인지 아닌지 판별하는 프로그램을 작성하시오.

입력
입력은 여러 개의 테스트 케이스로 구성되어 있는데, 첫째 줄에 테스트 케이스의 개수 K가 주어진다. 각 테스트 케이스의 첫째 줄에는 그래프의 정점의 개수 V와 간선의 개수 E가 빈 칸을 사이에 두고 순서대로 주어진다. 각 정점에는 1부터 V까지 차례로 번호가 붙어 있다. 이어서 둘째 줄부터 E개의 줄에 걸쳐 간선에 대한 정보가 주어지는데, 각 줄에 인접한 두 정점의 번호 u, v (u ≠ v)가 빈 칸을 사이에 두고 주어진다.

출력
K개의 줄에 걸쳐 입력으로 주어진 그래프가 이분 그래프이면 YES, 아니면 NO를 순서대로 출력한다.
* */

private class Solution1707 {
    private lateinit var visited: BooleanArray
    private lateinit var colorArray: IntArray

    fun solution(graph: List<Node>): String {
        visited = BooleanArray(graph.size)
        colorArray = IntArray(graph.size)

//        println()
//        println(graph)

        for (node in graph) {
            dfs(node.number, graph)

            if (checkSameColorSubNode(node)) {
//                println(colorArray.toList())
//                println()
                return "NO"
            }
        }

//        println(colorArray.toList())
//        println()

        return "YES"
    }

    private fun dfs(
        node: Int,
        graph: List<Node>
    ) {
        graph[node].sub.forEach { subNode ->
            if (!visited[subNode]) {
                visited[subNode] = true
                colorArray[subNode] = (colorArray[node] + 1) % 2
                dfs(subNode, graph)
            }
        }
    }

    private fun checkSameColorSubNode(node: Node): Boolean {
        val myColor = colorArray[node.number]

        for (subNode in node.sub) {
            val subColor = colorArray[subNode]

            if (myColor == subColor) {
                return true
            }
        }

        return false
    }

    data class Node(
        val number: Int,
        val sub: MutableList<Int> = mutableListOf()
    )
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val solution1707 = Solution1707()

    val k = br.readLine().toInt()

    repeat(k) {
        val (v, e) = br.readLine().split(" ").map { it.toInt() }
        val graph = List(v) { Solution1707.Node(it) }
        repeat(e) {
            val (start, end) = br.readLine().split(" ").map { it.toInt() - 1 }
            graph[start].sub.add(end)
            graph[end].sub.add(start)
        }
        bw.append("${solution1707.solution(graph)}\n")
    }
    bw.flush()


    br.close()
    bw.close()
}

//private fun main() {
//    val bw = System.out.bufferedWriter()
//
//    val solution1707 = Solution1707()
//
////    /*
////        1
////
////        1 1
////
////        1 1
////
////        NO
////     */
////    run {
////        val (v, e) = 1 to 1
////        val graph = List(v) { Solution1707.Node(it) }
////
////        listOf(
////            1 to 1,
////        ).forEach {
////            val (start, end) = it.let { it.first - 1 to it.second - 1 }
////            graph[start].sub.add(end)
////            graph[end].sub.add(start)
////        }
////
////        bw.append("case1: ${solution1707.solution(graph)}\n")
////    }
//
//    /*
//        1
//
//        3 1
//
//        1 2
//
//        YES
//    * */
//    run {
//        val (v, e) = 3 to 1
//        val graph = List(v) { Solution1707.Node(it) }
//
//        listOf(
//            1 to 2,
//        ).forEach {
//            val (start, end) = it.let { it.first - 1 to it.second - 1 }
//            graph[start].sub.add(end)
//            graph[end].sub.add(start)
//        }
//
//        bw.append("case2: ${solution1707.solution(graph)}\n")
//    }
//
//    /*
//        1
//
//        5 2
//
//        1 2
//
//        2 3
//
//        YES
//    * */
//    run {
//        val (v, e) = 5 to 2
//        val graph = List(v) { Solution1707.Node(it) }
//
//        listOf(
//            1 to 2,
//            2 to 3,
//        ).forEach {
//            val (start, end) = it.let { it.first - 1 to it.second - 1 }
//            graph[start].sub.add(end)
//            graph[end].sub.add(start)
//        }
//
//        bw.append("case3: ${solution1707.solution(graph)}\n")
//    }
//
//    /*
//        1
//
//        4 2
//
//        1 2
//
//        3 4
//
//        YES
//    * */
//    run {
//        val (v, e) = 4 to 2
//        val graph = List(v) { Solution1707.Node(it) }
//
//        listOf(
//            1 to 2,
//            3 to 4,
//        ).forEach {
//            val (start, end) = it.let { it.first - 1 to it.second - 1 }
//            graph[start].sub.add(end)
//            graph[end].sub.add(start)
//        }
//
//        bw.append("case4: ${solution1707.solution(graph)}\n")
//    }
//
//    /*
//        1
//
//        4 3
//
//        1 4
//
//        2 3
//
//        3 4
//
//        YES
//    * */
//    run {
//        val (v, e) = 4 to 3
//        val graph = List(v) { Solution1707.Node(it) }
//
//        listOf(
//            1 to 4,
//            2 to 3,
//            3 to 4,
//        ).forEach {
//            val (start, end) = it.let { it.first - 1 to it.second - 1 }
//            graph[start].sub.add(end)
//            graph[end].sub.add(start)
//        }
//
//        bw.append("case5: ${solution1707.solution(graph)}\n")
//    }
//
//    /*
//        1
//
//        5 4
//
//        1 2
//
//        3 4
//
//        3 5
//
//        4 5
//
//        NO
//    * */
//    run {
//        val (v, e) = 5 to 4
//        val graph = List(v) { Solution1707.Node(it) }
//
//        listOf(
//            1 to 2,
//            3 to 4,
//            3 to 5,
//            4 to 5,
//        ).forEach {
//            val (start, end) = it.let { it.first - 1 to it.second - 1 }
//            graph[start].sub.add(end)
//            graph[end].sub.add(start)
//        }
//
//        bw.append("case6: ${solution1707.solution(graph)}\n")
//    }
//
//    /*
//        1
//
//        6 4
//
//        1 2
//
//        2 4
//
//        4 5
//
//        5 6
//
//        YES
//    * */
//
//    run {
//        val (v, e) = 6 to 4
//        val graph = List(v) { Solution1707.Node(it) }
//
//        listOf(
//            1 to 2,
//            2 to 4,
//            4 to 5,
//            5 to 6,
//        ).forEach {
//            val (start, end) = it.let { it.first - 1 to it.second - 1 }
//            graph[start].sub.add(end)
//            graph[end].sub.add(start)
//        }
//
//        bw.append("case7: ${solution1707.solution(graph)}\n")
//    }
//
//    /*
//        1
//
//        6 6
//
//        1 3
//
//        3 4
//
//        4 2
//
//        2 5
//
//        5 6
//
//        6 1
//
//        YES
//    * */
//
//    run {
//        val (v, e) = 6 to 6
//        val graph = List(v) { Solution1707.Node(it) }
//
//        listOf(
//            1 to 3,
//            3 to 4,
//            4 to 2,
//            2 to 5,
//            5 to 6,
//            6 to 1
//        ).forEach {
//            val (start, end) = it.let { it.first - 1 to it.second - 1 }
//            graph[start].sub.add(end)
//            graph[end].sub.add(start)
//        }
//
//        bw.append("case8: ${solution1707.solution(graph)}\n")
//    }
//
//    /*
//        2
//
//        4 4
//
//        1 2
//
//        2 3
//
//        3 4
//
//        4 1
//
//        4 4
//
//        1 2
//
//        2 3
//
//        3 4
//
//        4 2
//
//        YES
//
//        NO
//    * */
//
//    run {
//        val (v, e) = 4 to 4
//        val graph = List(v) { Solution1707.Node(it) }
//
//        listOf(
//            1 to 2,
//            2 to 3,
//            3 to 4,
//            4 to 1,
//        ).forEach {
//            val (start, end) = it.let { it.first - 1 to it.second - 1 }
//            graph[start].sub.add(end)
//            graph[end].sub.add(start)
//        }
//        bw.append("case9: ${solution1707.solution(graph)}\n")
//
//        val graph2 = List(4) { Solution1707.Node(it) }
//
//        listOf(
//            1 to 2,
//            2 to 3,
//            3 to 4,
//            4 to 2,
//        ).forEach {
//            val (start, end) = it.let { it.first - 1 to it.second - 1 }
//            graph2[start].sub.add(end)
//            graph2[end].sub.add(start)
//        }
//
//        bw.append("case9: ${solution1707.solution(graph2)}\n")
//    }
//
//    /*
//        2
//
//        3 3
//
//        1 2
//
//        2 3
//
//        3 1
//
//        3 3
//
//        1 2
//
//        2 3
//
//        3 1
//
//        NO
//
//        NO
//    * */
//
//    run {
//        val (v, e) = 3 to 3
//        val graph = List(v) { Solution1707.Node(it) }
//
//        listOf(
//            1 to 2,
//            2 to 3,
//            3 to 1,
//        ).forEach {
//            val (start, end) = it.let { it.first - 1 to it.second - 1 }
//            graph[start].sub.add(end)
//            graph[end].sub.add(start)
//        }
//        bw.append("case10: ${solution1707.solution(graph)}\n")
//
//        val graph2 = List(3) { Solution1707.Node(it) }
//
//        listOf(
//            1 to 2,
//            2 to 3,
//            3 to 1,
//        ).forEach {
//            val (start, end) = it.let { it.first - 1 to it.second - 1 }
//            graph2[start].sub.add(end)
//            graph2[end].sub.add(start)
//        }
//
//        bw.append("case10: ${solution1707.solution(graph2)}\n")
//    }
//
//    bw.flush()
//
//    bw.close()
//}
