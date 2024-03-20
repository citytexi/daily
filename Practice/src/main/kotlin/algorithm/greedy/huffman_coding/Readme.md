# 파일 압축 (file compression)

- 파일의 각 문자가 8 bit 아스키 (ASCII) 코드로 저장되면, 그 파일의 bit 수는 8 x (파일의 문자 수)
- 주어진 파일의 크기를 줄이는 방법

# 허프만 압축(Huffman coding)

- 파일에 빈번히 나타나는 문자에는 짧은 이진 코드를 할당
- 드물게 나타나는 문자에는 긴 이진 코드를 할당
- 접두부 특성 (prefix property)이 존재
- 입력 파일에 대해 각 문자의 출현 빈도수에 기반을 둔 이진트리를 만들어서, 각 문자에 이진 코드를 할당
    - 해당 이진 코드를 허프만 코드라고 지칭

## 접두부 특성 (prefix property)

- 각 문자에 할당된 이진 코드는 어떤 다른 문자에 할당된 이진 코드의 접두부 (prefix)가 되지 않는다는 것
- 문자 ‘a’에 할당된 코드가 ‘101’이면 모든 다른 문자의 코드는 ‘101’로 시작되지 않으며 또한 ‘1’이나 ‘10’도 아님
- 장점: 코드와 코드 사이를 구분할 특별한 코드가 필요 없음

## Pseudo code

- HuffmanCoding
- Input: 입력 파일의 n개의 문자에 대한 각각의 빈도수
- Output: 허프만 트리

```kotlin
1. 각 문자 당 노드를 만들고, 그 문자의 빈도수를 노드에 저장
2. n개의 노드의 빈도수에 대해 우선순위 큐 Q를 만듬
3. while (Q에 있는 노드 수 ≥ 2) {
4.      빈도수가 가장 작은 2개의 노드 (A와 B)를 Q에서 제거
5.      새 노드 N을 만들고, A와 B를 N의 자식 노드로 만듬
6.      N의 빈도수 = A의 빈도수 + B의 빈도수
7.      노드 N을 Q에 삽입
8. }
9. return Q        // 허프만 트리의 루트를 리턴
```

## Example

```kotlin
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
```

## Time Complexity

- n개의 노드를 만들고 각 빈도 저장 O(n)
- n개의 노드로 우선순위 큐 만들기
    - heap을 사용하면 O(n)
- 루프
    - 삭제 및 새 노드를 큐에 삽입하는 과정 O(logn)
    - while 루프 (n - 1)
    - 따라서 O(nlogn)
- O(n) + O(n) + O(nlogn) = O(nlogn)