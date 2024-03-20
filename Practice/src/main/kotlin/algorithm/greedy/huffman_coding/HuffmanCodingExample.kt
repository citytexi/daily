package algorithm.greedy.huffman_coding

import java.util.PriorityQueue

private class HuffmanCodingExample {
    private lateinit var encodeCode: HashMap<String, String>
    fun solution(list: List<Node>): HashMap<String, String> {
        encodeCode = hashMapOf()

        list.forEach { node ->
            encodeCode[node.data.first] = ""
        }

        getStringByHuffmanTree(huffmanCoding(list))

        return encodeCode
    }

    private fun getStringByHuffmanTree(
        node: Node?,
        str: String = ""
    ) {
        if (node == null) {
            return
        }
        getStringByHuffmanTree(node.left, str + "0")
        getStringByHuffmanTree(node.right, str + "1")
        if (node.data.first.isNotEmpty()) {
            encodeCode[node.data.first] = str
        }
    }

    private fun huffmanCoding(list: List<Node>): Node {
        val priorityQueue = PriorityQueue<Node> { a, b -> a.data.second.compareTo(b.data.second) }
        priorityQueue.addAll(list)

        while (priorityQueue.size >= 2) {
            val a = priorityQueue.poll()
            val b = priorityQueue.poll()

            val node = Node(
                data = "" to a.data.second + b.data.second,
                left = a,
                right = b
            )
            priorityQueue.add(node)
        }

        return priorityQueue.poll()
    }

    fun decodeSolution(
        str: String,
        decodeMap: HashMap<String, String>
    ): String {
        val deque = ArrayDeque<Char>()
        deque.addAll(str.toList())

        var result = ""
        var currentStr = ""

        while (deque.isNotEmpty()) {
            currentStr += deque.removeFirst()

            for ((key, value) in decodeMap.entries) {
                if (value == currentStr) {
                    result += key
                    currentStr = ""
                    break
                }
            }
        }

        return result
    }

    data class Node(
        val data: Pair<String, Int>,
        val left: Node? = null,
        val right: Node? = null
    )
}

private fun main() {
    val example = HuffmanCodingExample()

    val a = HuffmanCodingExample.Node("A" to 450)
    val t = HuffmanCodingExample.Node("T" to 90)
    val g = HuffmanCodingExample.Node("G" to 120)
    val c = HuffmanCodingExample.Node("C" to 270)

    val list = listOf(a, t, g, c)

    val encodeCode = example.solution(list)

    println(
        example.decodeSolution(
            str = "10110010001110101010100",
            decodeMap = encodeCode
        )
    )
}