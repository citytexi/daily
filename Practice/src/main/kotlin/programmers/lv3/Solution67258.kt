package programmers.lv3

/*
문제 설명
[본 문제는 정확성과 효율성 테스트 각각 점수가 있는 문제입니다.]

개발자 출신으로 세계 최고의 갑부가 된 어피치는 스트레스를 받을 때면 이를 풀기 위해 오프라인 매장에 쇼핑을 하러 가곤 합니다.
어피치는 쇼핑을 할 때면 매장 진열대의 특정 범위의 물건들을 모두 싹쓸이 구매하는 습관이 있습니다.
어느 날 스트레스를 풀기 위해 보석 매장에 쇼핑을 하러 간 어피치는 이전처럼 진열대의 특정 범위의 보석을 모두 구매하되 특별히 아래 목적을 달성하고 싶었습니다.
진열된 모든 종류의 보석을 적어도 1개 이상 포함하는 가장 짧은 구간을 찾아서 구매

예를 들어 아래 진열대는 4종류의 보석(RUBY, DIA, EMERALD, SAPPHIRE) 8개가 진열된 예시입니다.

진열대 번호	1	2	3	4	5	6	7	8
보석 이름	DIA	RUBY	RUBY	DIA	DIA	EMERALD	SAPPHIRE	DIA
진열대의 3번부터 7번까지 5개의 보석을 구매하면 모든 종류의 보석을 적어도 하나 이상씩 포함하게 됩니다.

진열대의 3, 4, 6, 7번의 보석만 구매하는 것은 중간에 특정 구간(5번)이 빠지게 되므로 어피치의 쇼핑 습관에 맞지 않습니다.

진열대 번호 순서대로 보석들의 이름이 저장된 배열 gems가 매개변수로 주어집니다. 이때 모든 보석을 하나 이상 포함하는 가장 짧은 구간을 찾아서 return 하도록 solution 함수를 완성해주세요.
가장 짧은 구간의 시작 진열대 번호와 끝 진열대 번호를 차례대로 배열에 담아서 return 하도록 하며, 만약 가장 짧은 구간이 여러 개라면 시작 진열대 번호가 가장 작은 구간을 return 합니다.

[제한사항]
gems 배열의 크기는 1 이상 100,000 이하입니다.
gems 배열의 각 원소는 진열대에 나열된 보석을 나타냅니다.
gems 배열에는 1번 진열대부터 진열대 번호 순서대로 보석이름이 차례대로 저장되어 있습니다.
gems 배열의 각 원소는 길이가 1 이상 10 이하인 알파벳 대문자로만 구성된 문자열입니다.

입출력 예
gems	result
["DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"]	[3, 7]
["AA", "AB", "AC", "AA", "AC"]	[1, 3]
["XYZ", "XYZ", "XYZ"]	[1, 1]
["ZZZ", "YYY", "NNNN", "YYY", "BBB"]	[1, 5]

입출력 예에 대한 설명
입출력 예 #1
문제 예시와 같습니다.

입출력 예 #2
3종류의 보석(AA, AB, AC)을 모두 포함하는 가장 짧은 구간은 [1, 3], [2, 4]가 있습니다.
시작 진열대 번호가 더 작은 [1, 3]을 return 해주어야 합니다.

입출력 예 #3
1종류의 보석(XYZ)을 포함하는 가장 짧은 구간은 [1, 1], [2, 2], [3, 3]이 있습니다.
시작 진열대 번호가 가장 작은 [1, 1]을 return 해주어야 합니다.

입출력 예 #4
4종류의 보석(ZZZ, YYY, NNNN, BBB)을 모두 포함하는 구간은 [1, 5]가 유일합니다.
그러므로 [1, 5]를 return 해주어야 합니다.

※ 공지 - 2020년 7월 21일 테스트케이스가 추가되었습니다.
* */

private class Solution67258 {
    // 효율성 틀림
    /*
    fun solution(gems: Array<String>): IntArray {
        val types = HashSet<String>()
        types.addAll(gems)

        return when (types.size) {
            1 -> intArrayOf(1, 1)
            else -> {
                val answers = mutableListOf<Triple<Int, Int, Int>>()

                for (index in gems.indices) {
                    val currentType = types.toMutableSet()

                    if (gems.size - index >= types.size) {
                        var j = index
                        while (j < gems.size && currentType.isNotEmpty()) {
                            currentType.remove(gems[j])

                            if (currentType.isEmpty()) {
                                answers.add(Triple(index + 1, j + 1, (j + 1) - (index + 1)))
                            }
                            j++
                        }
                    }
                }

                val answer = answers.minByOrNull { it.third }!!

                intArrayOf(answer.first, answer.second)
            }
        }
    }
    */

//    fun solution(gems: Array<String>): IntArray {
//        val types = HashSet<String>()
//        val typeCount = HashMap<String, Int>()
//        gems.forEach { gem ->
//            types.add(gem)
//            typeCount[gem] = typeCount.getOrDefault(gem, 0) + 1
//        }
//
//        return when (types.size) {
//            1 -> intArrayOf(1, 1)
//            else -> {
//                val deque = ArrayDeque<Pair<Int, String>>()
//                val currentType = HashSet<String>()
//                val currentCount = HashMap<String, Int>()
//                var size = Int.MAX_VALUE
//                val results = intArrayOf(0, 0)
//
//                for (i in gems.indices) {
//                    val gem = gems[i]
//                    deque.add(i + 1 to gem)
//                    currentType.add(gem)
//                    currentCount[gem] = currentCount.getOrDefault(gem, 0) + 1
//                    if (currentType == types) {
//                        var item: Pair<Int, String>? = null
//                        while (deque.size >= types.size && currentType == types) {
//                            item = deque.removeFirst()
//                            currentCount[item.second] = currentCount.getOrDefault(item.second, 0) - 1
//
//                            if (currentCount[item.second] == 0) {
//                                currentType.remove(item.second)
//                            }
//                        }
//                        item?.let { result ->
//                            if (size > (i + 1) - result.first) {
//                                size = (i + 1) - result.first
//                                results[0] = result.first
//                                results[1] = i + 1
//                            }
//                        }
//                    }
//                }
//
//                results
//            }
//        }
//    }

    fun solution(gems: Array<String>): IntArray {
        var answer = intArrayOf()
        val map: HashMap<String,Int> = hashMapOf()
        gems.forEach {
            map[it] = 0
        }
        val max = map.size
        map.clear()

        var left = 0
        var right = 0
        var min = gems.size

        while (right < gems.size) {
            map[gems[right]] = map.getOrDefault(gems[right], 0) + 1
            right += 1

            while (map.size == max) {
                map[gems[left]] = map.getOrDefault(gems[left], 0) - 1
                if (map[gems[left]] == 0) {
                    map.remove(gems[left])
                }
                left += 1
                if (right - left < min) {
                    min = right - left
                    answer = intArrayOf(left,right)
                }
            }
        }

        return answer
    }
}

private fun main() {
    val solution67258 = Solution67258()
    println(solution67258.solution(arrayOf("DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA")).toList())
    println(solution67258.solution(arrayOf("AA", "AB", "AC", "AA", "AC")).toList())
    println(solution67258.solution(arrayOf("XYZ", "XYZ", "XYZ")).toList())
    println(solution67258.solution(arrayOf("ZZZ", "YYY", "NNNN", "YYY", "BBB")).toList())

    val gemSize = (1 .. 100000).random()
    val gemList = mutableListOf<String>()
    for (i in 0 until gemSize / 3) {
        var str = ""
        val alphabetNum = (1 .. 10).random()
        repeat(alphabetNum) {
            str += ('A' .. 'Z').random()
        }
        gemList.add(str)
    }

    val gens = mutableListOf<String>()
    repeat(3) {
        gens.addAll(gemList)
    }
    gens.shuffle()
    println(solution67258.solution(gens.toTypedArray()).toList())

    val test = Array(100_000) {
        when (it) {
            in 55555 .. 99999 -> "AA"
            else -> "A"
        }
    }
    println(solution67258.solution(test).toList())
}