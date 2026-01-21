import java.util.Comparator
import java.util.PriorityQueue
import javax.print.DocFlavor.STRING
import kotlin.math.max
import kotlin.math.min

class Capital {

    // 896. Monotonic Array
    fun isMonotonic(nums: IntArray): Boolean{
        //[1,2,2,3]
        //     ^ ^
        if (nums.size <= 1){
            return true
        }
        return isIncreasing(nums) || isDecreasing(nums)
    }

    fun isIncreasing(nums: IntArray) : Boolean{
        var i = 0
        var j = 1

        while (j < nums.size){
            if (nums[i] <= nums[j]){
                j+= 1
                i+= 1

            } else {
                return false
            }
        }
        return true
    }

    fun isDecreasing(nums: IntArray): Boolean{
        var i = 0
        var j = 1

        while (j < nums.size){
            if (nums[i] >= nums[j]){
                j+= 1
                i+= 1

            } else {
                return false
            }
        }
        return true
    }

    // 2544. Alternating Digit Sum
    fun alternateDigitSum(n: Int): Int {

        var auxnum = n
        var stack = ArrayDeque<Int>()


        var num = auxnum.toString()
        println("Num: ${num}")

        var i = 0
        var suma = 0

        while (i < num.length){
            suma += num[i].digitToInt()
            i += 2
        }
        var j = 1
        var resta = 0
        while (j < num.length){
            resta += num[j].digitToInt()
            j += 2
        }

        return suma - resta
    }

    // 2672. Number of Adjacent Elements With the Same Color
    fun colorTheArray(n: Int, queries: Array<IntArray>): IntArray {

        val colors = IntArray(n) {0}
        val result = IntArray(queries.size) {0}
        var countPair = 0

        for (i in 0 .. queries.size-1){

            var color = queries[i][1]
            var index = queries[i][0]
            var oldColor = colors[index]

            if (oldColor != 0){

                // check leftSide
                if (index > 0 && colors[index - 1] == oldColor){
                    countPair--
                }
                // check rightSide
                if (index < colors.size-1 && colors[index+1] == oldColor){
                    countPair--
                }
            }

            colors[index] = color

            if (color != 0){
                if (index > 0 && color == colors[index-1]){
                    countPair++
                }
                if (index < colors.size-1 && color == colors[index+1]){
                    countPair++
                }
            }


            result[i] = countPair

        }

        return result
    }

    // 1052. Grumpy Bookstore Owner
    fun maxSatisfied(customers: IntArray, grumpy: IntArray, minutes: Int): Int {

        if (minutes >= customers.size){
            return customers.sum()
        }

        var i = 0
        var j = minutes-1
        var maxCount = 0
        var windowCount = 0

        for (h in 0 .. j){
            windowCount += customers[h]
        }

        maxCount = windowCount

        for (index in j+1 .. grumpy.size-1){
            if (grumpy[index] == 0){
                maxCount += customers[index]
            }
        }

        while (j < customers.size){
            var localCount = 0
            if (j+1 < customers.size){
                localCount = windowCount - customers[i] + customers[j+1]
                windowCount = localCount

                for (ith in 0 .. i){
                    if (grumpy[ith] == 0){
                        localCount += customers[ith]
                    }
                }

                for (jth in j+2 .. grumpy.size-1){
                    if (grumpy[jth] == 0){
                        localCount += customers[jth]
                    }
                }
            }
            maxCount = Math.max(maxCount, localCount)

            j++
            i++
        }

        return maxCount

    }

    // 11. Container With Most Water
    fun maxArea(height: IntArray): Int {

        var j = height.size-1
        var i = 0
        var mArea = 0
        var localArea = 0

        while (i < j){
            localArea = (j - i) * (min(height[j], height[i]))
            mArea = max(localArea, mArea)

            if (height[j] <= height[i]){
                j--
            } else {
                i++
            }

        }

        return mArea
    }

    // 1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
    fun longestSubarray(nums: IntArray, limit: Int): Int {

        // [1, 5, 6, 7, 8, 10, 6, 5, 6] p=4

        var i = 0
        var j = 0
        /*
            Pair<A, B>
            // A: is the Max or Min value
            // B: is the index

         */
        var comparedByValue : Comparator<Pair<Int,Int>> = compareBy { it.first }
        var pqMax : PriorityQueue<Pair<Int,Int>> = PriorityQueue(comparedByValue.reversed())
        var pqMin : PriorityQueue<Pair<Int,Int>> = PriorityQueue(comparedByValue)
        var longest = 0

        while (j < nums.size){

            pqMax.add(Pair(nums[j],j))
            pqMin.add(Pair(nums[j],j))

            if (Math.abs(pqMax.peek().first - pqMin.peek().first) <= limit ){
                longest = max(longest, j-i+1)
            } else {
                var valueInPq = i

                // update my Priorities Queues
                if (valueInPq == pqMax.peek().second) pqMax.poll()
                if (valueInPq == pqMin.peek().second) pqMin.poll()

                i++
            }
            j++

        }

        return longest
    }

    // 2021. Brightest Position on Street (my solution)
    /*
    fun brightestPosition(lights: Array<IntArray>): Int {

        var newLights = ArrayList<Pair<Int, Int>>()
        var minRange = Int.MAX_VALUE
        var maxRange = Int.MIN_VALUE

        var maxValue = Int.MIN_VALUE

        for (intervals in lights){

            var startRange = (intervals[0]-intervals[1])
            var endRange = (intervals[0]+intervals[1])

            newLights.add(Pair(startRange, endRange))

            minRange = min(minRange, startRange)
            maxRange = max(maxRange, endRange)
        }
        newLights.sortBy { it.first }
        //   println("NewLights: ${newLights}" )

        var offset = 0
        if (minRange < 0){
            offset = -minRange
        }
        if (maxRange <= 0){
            maxRange = 0
        }

        var negativeRange = IntArray(offset+1)
        var positiveRange = IntArray(maxRange + 1)

        for (pair in newLights){

            var startR = pair.first
            var endR = pair.second

            if (startR < 0 && endR < 0){
                var auxStart = startR * -1
                var auxEnd = endR * -1

                for (j in auxEnd .. auxStart){
                    negativeRange[j] += 1

                    maxValue = max(maxValue, negativeRange[j])
                }
            }

            if (startR < 0 && endR >= 0){
                var auxStart = startR * -1

                for (i in 1 .. auxStart){
                    negativeRange[i] += 1
                    maxValue = max(maxValue, negativeRange[i])
                }

                for (j in 0 .. endR){
                    positiveRange[j] += 1
                    maxValue = max(maxValue, positiveRange[j])
                }
            }

            if (startR >=0){
                for (j in startR .. endR){
                    positiveRange[j] += 1
                    maxValue = max(maxValue, positiveRange[j])
                }
            }
        }

        //negativeRange.forEach { print("${it}->") }
        //println()
        //positiveRange.forEach { print("${it}->") }
        //println()

        var maxResult = 0

        for (j in minRange*-1 downTo 0){
            if (negativeRange[j] == maxValue){
                maxResult = j*-1
                return maxResult
            }
        }

        for (i in 0 until maxRange+1){
            if (positiveRange[i] == maxValue){
                maxResult = i
                return maxResult
            }
        }
        return maxResult


    }
    */

    // 1743. Restore the Array From Adjacent Pairs
    fun restoreArray(adjacentPairs: Array<IntArray>): IntArray {

        var dict = HashMap<Int, MutableList<Int>>()
        for (list in adjacentPairs){

            if (dict.containsKey(list[0])){
                dict[list[0]]!!.add(list[1])
            }else{
                dict.put(list[0], mutableListOf(list[1]))
            }

            if (dict.containsKey(list[1])){
                dict[list[1]]!!.add(list[0])
            }else{
                dict.put(list[1], mutableListOf(list[0]))
            }
        }

        var nodes = ArrayList<Int>()
        for ( (key, value) in dict){
            if (value.size == 1){
                nodes.add(key)
            }
        }
        var nodesStart = nodes.random()


        var result = ArrayList<Int>()
        var queue = ArrayDeque<Int>()
        var visited = HashSet<Int>()
        queue.add(nodesStart)

        while (queue.isNotEmpty()){

            var current = queue.removeFirst()
            result.add(current)
            visited.add(current)

            for (node in dict[current]!!){

                if (!(visited.contains(node))){
                    queue.add(node)
                }
            }
        }

        return result.toIntArray()
    }

    // 3043. Find the Length of the Longest Common Prefix
    fun longestCommonPrefix(arr1: IntArray, arr2: IntArray): Int {

        var hash1 = HashSet<String>()
        var hash2 = HashSet<String>()


        for (num in arr1){
            var aux = num.toString()
            var stringAux = ""

            for (i in 0 until aux.length){

                stringAux += aux[i]
                if (!(hash1.contains(stringAux))){
                    hash1.add(stringAux)
                }
            }
        }

        for (num in arr2){
            var aux = num.toString()
            var stringAux = ""

            for (i in 0 until aux.length){

                stringAux += aux[i]
                if (!(hash2.contains(stringAux))){
                    hash2.add(stringAux)
                }
            }
        }

        println("hash1: ${hash1}")
        println("hash2: ${hash2}")

        var maxLength1 = Int.MIN_VALUE
        for (key in hash1){
            if (hash2.contains(key)){
                maxLength1 = max(maxLength1, key.length)
            }
        }

        var maxLength2 = Int.MIN_VALUE
        for (key in hash2){
            if (hash1.contains(key)){
                maxLength2 = max(maxLength2, key.length)
            }
        }

        return max(maxLength2, maxLength1)
    }

    // 539. Minimum Time Difference
    fun findMinDifference(timePoints: List<String>): Int {

        var exists = HashSet<String>()
        var newTimePoint = ArrayList<Int>()
        println("date_times_original: ${timePoints}")

        for (i in 0 until timePoints.size){

            var timeP = timePoints[i]
            if (exists.contains(timeP)){
                return 0
            }
            exists.add(timeP)

            var arrayTime = timeP.split(":")
            var hr = arrayTime[0].toInt() * 60
            var minu = arrayTime[1].toInt()
            newTimePoint.add(hr + minu)

        }

        newTimePoint.sortBy { it }
        println("date_times_new: ${newTimePoint}")

        var closeValue = newTimePoint[newTimePoint.size-1]
        var result = Int.MAX_VALUE
        var i = 0; var j = 1

        while (j < newTimePoint.size){

            var toLeft = newTimePoint[j] - newTimePoint[i]
            var toRight = 1440 - closeValue + newTimePoint[i]

            println("i:${i} - j:${j} => toLeft:${toLeft}, toRight:${toRight}")

            var minToCalculate = min(toLeft, toRight)
            result = min(minToCalculate, result)
            j++
            i++

        }

        return result


    }

    // 71. Simplify Path
    fun simplifyPath(path: String): String {

        var listOfpath  = path.trim().split("/")
        println("List Path: ${listOfpath}")

        var stack = ArrayDeque<String>()

        for (part in listOfpath){

            println("stack: ${stack}")
            when(part) {
                ".." -> if (stack.isNotEmpty()) stack.removeLast()
                else -> stack.addLast(part)
            }
        }

        println("resultArray: ${stack}")
        if (stack.isEmpty()){
            return "/"
        }

        return "/" + stack.joinToString("/")
    }

    // 200. Number of Islands
    fun numIslands(grid: Array<CharArray>): Int {

        var visited = HashSet<String>()
        fun helper(i: Int, j:Int){
            if (i < 0 || j < 0 || i > grid.size || j > grid[0].size) return

            var key = "$i,$j"
            if (grid[i][j] == '0' || visited.contains(key)){
                return
            }
            visited.add(key)

            // left
            helper(i, j+1)
            // right
            helper(i, j-1)
            // up
            helper(i-1, j)
            // down
            helper(i+1, j)
        }

        var count = 0
        for (i in 0 until grid.size){
            for (j in 0 until grid[0].size){
                var key = "$i,$j"
                if (grid[i][j] == '1' && !visited.contains(key)){
                    helper(i,j)
                    count++
                }
            }
        }


        return count
    }

    // 3071. Minimum Operations to Write the Letter Y on a Grid
    fun minimumOperationsToWriteY(grid: Array<IntArray>): Int {

        var visited = HashSet<String>()
        var result = Int.MAX_VALUE
        for (y in 0 .. 2){

            if (y == 0){

                var yCount = moveYto(grid, y, visited)
                var other1Count = moveOthersto(grid,1, visited)
                var other2Count = moveOthersto(grid, 2, visited)
                var other = min(other1Count, other2Count)
                result = min(result, other + yCount)

            } else if (y == 1){
                var yCount = moveYto(grid, y, visited)
                var other1Count = moveOthersto(grid,0, visited)
                var other2Count = moveOthersto(grid, 2, visited)
                var other = min(other1Count, other2Count)
                result = min(result, other + yCount)

            } else {
                var yCount = moveYto(grid, y, visited)
                var other1Count = moveOthersto(grid,1, visited)
                var other2Count = moveOthersto(grid, 0, visited)
                var other = min(other1Count, other2Count)
                result = min(result, other + yCount)
            }

        }
        return result
    }

    fun moveYto (grid: Array<IntArray>, y: Int, visited: HashSet<String>): Int{

        println("For y:${y} this is the hashSet: ${visited}")

        var centerX = grid.size/2
        var centerY = grid[0].size/2
        var i = 0
        var j = 0
        var count = 0

        println("i:${i}, j:${j}")

        while (i < centerX && j < centerY){
            var key = "$i,$j"
            visited.add(key)

            if (grid[i][j] != y) count++
            i++
            j++
        }
        println("i:${i}, j:${j}")
        while (i < grid.size){
            var key = "$i,$j"
            visited.add(key)

            if (grid[i][j] != y) count++
            i++
        }
        println("i:${i}, j:${j}")
        j++
        i = centerX-1

        while (i >=0 && j < grid[0].size){
            var key = "$i,$j"
            visited.add(key)

            if (grid[i][j] != y) count++
            i--
            j++
        }
        println("i:${i}, j:${j}")
        return count
    }

    fun moveOthersto (grid: Array<IntArray>, x: Int, visited : HashSet<String>): Int{

        println("For x:${x} this is the hashSet: ${visited}")

        var count = 0
        for (i in 0 until grid.size){
            for (j in 0 until grid[0].size){
                var key = "$i,$j"

                if (!(visited.contains(key))){
                    if (grid[i][j] != x) count++
                }
            }
        }

        return count
    }

}

fun main(args : Array<String>){


    val capitalTest = Capital()
    val balance = longArrayOf(0)
    val testClass = Bank(balance)
    val intervals = arrayOf(intArrayOf(1,2), intArrayOf(2,3), intArrayOf(3,4), intArrayOf(1,3))
    val queries = arrayOf(intArrayOf(0,2), intArrayOf(1,2), intArrayOf(3,1), intArrayOf(1,1), intArrayOf(2,1))
    val customers = intArrayOf(1,0,1,2,1,1,7,5)
    val grumpy = intArrayOf(0,1,0,1,0,1,0,1)
    val minutes = 3
    val height = intArrayOf(1,1)
    val n = 4
    val nums = intArrayOf(24,12,71,33,5,87,10,11,3,58,2,97,97,36,32,35,15,80,24,45,38,9,22,21,33,68,22,85,35,83,92,38,59,90,42,64,61,15,4,40,50,44,54,25,34,14,33,94,66,27,78,56,3,29,3,51,19,5,93,21,58,91,65,87,55,70,29,81,89,67,58,29,68,84,4,51,87,74,42,85,81,55,8,95,39)
    val limit = 87
    val lights = arrayOf(intArrayOf(2,1), intArrayOf(3,4), intArrayOf(3,2))
    val arr1 = intArrayOf(1,10,100)
    val arr2 = intArrayOf(1000)
    val timepoints = arrayListOf("00:00","23:59","00:10")
    val path = "/.../a/../b/c/../d/./"
    //val grid = arrayOf(intArrayOf(0,1,0,1,0), intArrayOf(2,1,0,1,2), intArrayOf(2,2,2,0,1), intArrayOf(2,2,2,2,2), intArrayOf(2,1,2,2,2))
    val grid = arrayOf(intArrayOf(1,2,2), intArrayOf(1,1,0), intArrayOf(0,1,0))
    // [[0,1,0,1,0],[2,1,0,1,2],[2,2,2,0,1],[2,2,2,2,2],[2,1,2,2,2]]
    // [1,2,2],[1,1,0],[0,1,0]
    println(capitalTest.minimumOperationsToWriteY(grid))

}

// 2043. Simple Bank System
class Bank(balance: LongArray) {

    var mapBalances = HashMap<Int, Long>()
    val default : Long = -1
    init {
        for (i in 0 .. balance.size-1){

            if (!(mapBalances.containsKey(i+1))){
                mapBalances[i+1] = balance[i]
            }
        }
    }

    fun printingBalanceByAccount(){

        for ((key, value) in mapBalances){
            println("Key: ${key} -> ${value}")
        }
    }

    fun transfer(account1: Int, account2: Int, money: Long): Boolean {

        if ((mapBalances.containsKey(account1) && mapBalances.containsKey(account2))){

            if (account1 != account2){
                var accountTotal1 = mapBalances.getValue(account1)
                var accountTotal2 = mapBalances.getValue(account2)

                if (accountTotal1 >= money){

                    accountTotal2 += money
                    accountTotal1 -= money
                    mapBalances.replace(account1, accountTotal1)
                    mapBalances.replace(account2, accountTotal2)
                    return true

                }else{
                    return false
                }

            } else {

                var accountTotal1 = mapBalances.getValue(account1)
                if (accountTotal1 >= money){
                    return true
                } else {
                    return false
                }

            }


        } else {
            return false
        }

    }

    fun deposit(account: Int, money: Long): Boolean {

        val existAccount = mapBalances.getOrDefault(account, -1)
        if (existAccount != default){
            var newValue = mapBalances.getValue(account)
            newValue += money
            mapBalances.replace(account, newValue)
            return true
        }
        return false

    }

    fun withdraw(account: Int, money: Long): Boolean {

        val totalMoney = mapBalances.getOrDefault(account, -1)
        if (totalMoney != default){

            if (totalMoney >= money){

                var newValue = totalMoney - money
                mapBalances.replace(account, newValue)
                return true

            }else{
                return false
            }

        }else {
            return false
        }

    }

}
