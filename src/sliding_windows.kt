

class SlidingWindows {

    // 438. Find All Anagrams in a String
    fun findAnagrams(s: String, p: String): List<Int> {

        var pMap = HashMap<Char, Int>()
        var result = ArrayList<Int>()

        if (p.length > s.length) {
            return emptyList()
        }
        if (p.length == s.length) {
            var auxP = p.toList().sorted().joinToString("")
            var auxS = s.toList().sorted().joinToString("")
            if (auxP == auxS) {
                //result.add(0)
                return arrayListOf(0)
            } else {
                return emptyList()
            }
        }

        for (i in 0..p.length - 1) {
            if (pMap.containsKey(p.get(i))) {
                pMap[p.get(i)] = pMap[p.get(i)]!! + 1
            } else {
                pMap[p.get(i)] = 1
            }
        }

        var right = 0
        var tam = p.length
        while (right < s.length) {

            var start = right
            var end = start + tam
            var aux = HashMap<Char, Int>(pMap)
            println("aux: ${aux}")
            while (start < end && start < s.length) {
                if (aux.containsKey(s.get(start))) {
                    if (aux[s.get(start)]!! >= 1) {
                        aux[s.get(start)] = aux[s.get(start)]!! - 1
                    } else {
                        aux.remove(s.get(start))
                    }
                }
                start++
            }
            println("aux after: ${aux}")
            if (aux.values.sum() == 0) {
                result.add(right)
            }
            right++

        }

        return result
    }

    // 2260. Minimum Consecutive Cards to Pick Up
    fun minimumCardPickup(cards: IntArray): Int {

        var map = HashSet<Int>()
        var right = 0
        var left = 0
        var minMatch = Int.MAX_VALUE

        while (right < cards.size) {

            if (!map.contains(cards.get(right))) {
                map.add(cards.get(right))
            } else {
                minMatch = Math.min(minMatch, (right - left + 1))
                var valueInMap = cards[right]
                var noFound = true

                while (left < right && noFound) {
                    if (cards[left] == valueInMap) {
                        noFound = false
                        minMatch = Math.min(minMatch, (right - left + 1))
                        println("founded: ${cards[left]}")
                    } else {
                        map.remove(cards[left])
                    }
                    left++
                }
            }
            right++
        }

        if (minMatch == Int.MAX_VALUE) {
            minMatch = -1
        }
        return minMatch
    }

    // 167. Two Sum II - Input Array Is Sorted
    fun twoSum(numbers: IntArray, target: Int): IntArray {

        var right = numbers.size - 1
        var left = 0
        var suma = 0

        while (left <= right) {

            suma = numbers[left] + numbers[right]
            if (suma == target) {
                return intArrayOf(left + 1, right + 1)
            } else if (suma > target) {
                right--
            } else {
                left++
            }
        }

        return intArrayOf(0)
    }

    // 11. Container With Most Water
    fun maxArea(height: IntArray): Int {

        var maxAmount = Int.MIN_VALUE
        var localAmount = 0

        var right = height.size - 1
        var left = 0

        while (right > left) {

            localAmount = (right - left) * Math.min(height[right], height[left])
            maxAmount = Math.max(maxAmount, localAmount)

            if (height[right] > height[left]) {
                left++
            } else {
                right--
            }
        }

        return maxAmount
    }

    // 238. Product of Array Except Self
    fun productExceptSelf(nums: IntArray): IntArray {

        var prodLeft = IntArray(nums.size)
        var prodRight = IntArray(nums.size)

        var prevLeft = 1
        prodLeft[0] = 1
        prodRight[0] = 1
        for (i in 1..nums.size - 1) {

            prodLeft[i] = prevLeft * nums[i - 1]
            prevLeft = prodLeft[i]
        }

        prodLeft.forEach { println(it) }

        var prevRight = 1
        prodRight[nums.size - 1] = 1
        for (j in nums.size - 2 downTo 0) {
            prodRight[j] = prevRight * nums[j + 1]
            prevRight = prodRight[j]
        }

        prodRight.forEach { println(it) }

        for (i in 0..prodRight.size - 1) {
            var aux = prodRight[i]
            prodRight[i] = aux * prodLeft[i]
        }

        return prodRight
    }

    

}

fun main(args : Array<String>){

    val testClass = SlidingWindows()
    val cards = intArrayOf(77,10,11,51,69,83,33,94,0,42,86,41,65,40,72,8,53,31,43,22,9,94,45,80,40,0,84,34,76,28,7,79,80,93,20,82,36,74,82,89,74,77,27,54,44,93,98,44,39,74,36,9,22,57,70,98,19,68,33,68,49,86,20,50,43)
    val numbers = intArrayOf(2,7,11,15)
    val target = 9
    val height = intArrayOf(4,3,1,2,1,9)
    val nums = intArrayOf(1,2,3,4)
    println(testClass.productExceptSelf(nums))

}