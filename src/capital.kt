import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*

class Capital {

    // 896. Monotonic Array
    fun isMonotonic(nums: IntArray): Boolean {
        //[1,2,2,3]
        //     ^ ^
        if (nums.size <= 1) {
            return true
        }
        return isIncreasing(nums) || isDecreasing(nums)
    }

    fun isIncreasing(nums: IntArray): Boolean {
        var i = 0
        var j = 1

        while (j < nums.size) {
            if (nums[i] <= nums[j]) {
                j += 1
                i += 1

            } else {
                return false
            }
        }
        return true
    }

    fun isDecreasing(nums: IntArray): Boolean {
        var i = 0
        var j = 1

        while (j < nums.size) {
            if (nums[i] >= nums[j]) {
                j += 1
                i += 1

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

        while (i < num.length) {
            suma += num[i].digitToInt()
            i += 2
        }
        var j = 1
        var resta = 0
        while (j < num.length) {
            resta += num[j].digitToInt()
            j += 2
        }

        return suma - resta
    }

    // 2672. Number of Adjacent Elements With the Same Color
    fun colorTheArray(n: Int, queries: Array<IntArray>): IntArray {

        val colors = IntArray(n) { 0 }
        val result = IntArray(queries.size) { 0 }
        var countPair = 0

        for (i in 0..queries.size - 1) {

            var color = queries[i][1]
            var index = queries[i][0]
            var oldColor = colors[index]

            if (oldColor != 0) {

                // check leftSide
                if (index > 0 && colors[index - 1] == oldColor) {
                    countPair--
                }
                // check rightSide
                if (index < colors.size - 1 && colors[index + 1] == oldColor) {
                    countPair--
                }
            }

            colors[index] = color

            if (color != 0) {
                if (index > 0 && color == colors[index - 1]) {
                    countPair++
                }
                if (index < colors.size - 1 && color == colors[index + 1]) {
                    countPair++
                }
            }


            result[i] = countPair

        }

        return result
    }

    // 1052. Grumpy Bookstore Owner
    fun maxSatisfied(customers: IntArray, grumpy: IntArray, minutes: Int): Int {

        if (minutes >= customers.size) {
            return customers.sum()
        }

        var i = 0
        var j = minutes - 1
        var maxCount = 0
        var windowCount = 0

        for (h in 0..j) {
            windowCount += customers[h]
        }

        maxCount = windowCount

        for (index in j + 1..grumpy.size - 1) {
            if (grumpy[index] == 0) {
                maxCount += customers[index]
            }
        }

        while (j < customers.size) {
            var localCount = 0
            if (j + 1 < customers.size) {
                localCount = windowCount - customers[i] + customers[j + 1]
                windowCount = localCount

                for (ith in 0..i) {
                    if (grumpy[ith] == 0) {
                        localCount += customers[ith]
                    }
                }

                for (jth in j + 2..grumpy.size - 1) {
                    if (grumpy[jth] == 0) {
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

        var j = height.size - 1
        var i = 0
        var mArea = 0
        var localArea = 0

        while (i < j) {
            localArea = (j - i) * (min(height[j], height[i]))
            mArea = max(localArea, mArea)

            if (height[j] <= height[i]) {
                j--
            } else {
                i++
            }

        }

        return mArea
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
        for (list in adjacentPairs) {

            if (dict.containsKey(list[0])) {
                dict[list[0]]!!.add(list[1])
            } else {
                dict.put(list[0], mutableListOf(list[1]))
            }

            if (dict.containsKey(list[1])) {
                dict[list[1]]!!.add(list[0])
            } else {
                dict.put(list[1], mutableListOf(list[0]))
            }
        }

        var nodes = ArrayList<Int>()
        for ((key, value) in dict) {
            if (value.size == 1) {
                nodes.add(key)
            }
        }
        var nodesStart = nodes.random()


        var result = ArrayList<Int>()
        var queue = ArrayDeque<Int>()
        var visited = HashSet<Int>()
        queue.add(nodesStart)

        while (queue.isNotEmpty()) {

            var current = queue.removeFirst()
            result.add(current)
            visited.add(current)

            for (node in dict[current]!!) {

                if (!(visited.contains(node))) {
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


        for (num in arr1) {
            var aux = num.toString()
            var stringAux = ""

            for (i in 0 until aux.length) {

                stringAux += aux[i]
                if (!(hash1.contains(stringAux))) {
                    hash1.add(stringAux)
                }
            }
        }

        for (num in arr2) {
            var aux = num.toString()
            var stringAux = ""

            for (i in 0 until aux.length) {

                stringAux += aux[i]
                if (!(hash2.contains(stringAux))) {
                    hash2.add(stringAux)
                }
            }
        }

        println("hash1: ${hash1}")
        println("hash2: ${hash2}")

        var maxLength1 = Int.MIN_VALUE
        for (key in hash1) {
            if (hash2.contains(key)) {
                maxLength1 = max(maxLength1, key.length)
            }
        }

        var maxLength2 = Int.MIN_VALUE
        for (key in hash2) {
            if (hash1.contains(key)) {
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

        for (i in 0 until timePoints.size) {

            var timeP = timePoints[i]
            if (exists.contains(timeP)) {
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

        var closeValue = newTimePoint[newTimePoint.size - 1]
        var result = Int.MAX_VALUE
        var i = 0;
        var j = 1

        while (j < newTimePoint.size) {

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

        var listOfpath = path.trim().split("/")
        println("List Path: ${listOfpath}")

        var stack = ArrayDeque<String>()

        for (part in listOfpath) {

            println("stack: ${stack}")
            when (part) {
                ".." -> if (stack.isNotEmpty()) stack.removeLast()
                else -> stack.addLast(part)
            }
        }

        println("resultArray: ${stack}")
        if (stack.isEmpty()) {
            return "/"
        }

        return "/" + stack.joinToString("/")
    }

    // 200. Number of Islands
    fun numIslands(grid: Array<CharArray>): Int {

        var visited = HashSet<String>()
        fun helper(i: Int, j: Int) {
            if (i < 0 || j < 0 || i > grid.size || j > grid[0].size) return

            var key = "$i,$j"
            if (grid[i][j] == '0' || visited.contains(key)) {
                return
            }
            visited.add(key)

            // left
            helper(i, j + 1)
            // right
            helper(i, j - 1)
            // up
            helper(i - 1, j)
            // down
            helper(i + 1, j)
        }

        var count = 0
        for (i in 0 until grid.size) {
            for (j in 0 until grid[0].size) {
                var key = "$i,$j"
                if (grid[i][j] == '1' && !visited.contains(key)) {
                    helper(i, j)
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
        for (y in 0..2) {

            if (y == 0) {

                var yCount = moveYto(grid, y, visited)
                var other1Count = moveOthersto(grid, 1, visited)
                var other2Count = moveOthersto(grid, 2, visited)
                var other = min(other1Count, other2Count)
                result = min(result, other + yCount)

            } else if (y == 1) {
                var yCount = moveYto(grid, y, visited)
                var other1Count = moveOthersto(grid, 0, visited)
                var other2Count = moveOthersto(grid, 2, visited)
                var other = min(other1Count, other2Count)
                result = min(result, other + yCount)

            } else {
                var yCount = moveYto(grid, y, visited)
                var other1Count = moveOthersto(grid, 1, visited)
                var other2Count = moveOthersto(grid, 0, visited)
                var other = min(other1Count, other2Count)
                result = min(result, other + yCount)
            }

        }
        return result
    }

    fun moveYto(grid: Array<IntArray>, y: Int, visited: HashSet<String>): Int {

        println("For y:${y} this is the hashSet: ${visited}")

        var centerX = grid.size / 2
        var centerY = grid[0].size / 2
        var i = 0
        var j = 0
        var count = 0

        println("i:${i}, j:${j}")

        while (i < centerX && j < centerY) {
            var key = "$i,$j"
            visited.add(key)

            if (grid[i][j] != y) count++
            i++
            j++
        }
        println("i:${i}, j:${j}")
        while (i < grid.size) {
            var key = "$i,$j"
            visited.add(key)

            if (grid[i][j] != y) count++
            i++
        }
        println("i:${i}, j:${j}")
        j++
        i = centerX - 1

        while (i >= 0 && j < grid[0].size) {
            var key = "$i,$j"
            visited.add(key)

            if (grid[i][j] != y) count++
            i--
            j++
        }
        println("i:${i}, j:${j}")
        return count
    }

    fun moveOthersto(grid: Array<IntArray>, x: Int, visited: HashSet<String>): Int {

        println("For x:${x} this is the hashSet: ${visited}")

        var count = 0
        for (i in 0 until grid.size) {
            for (j in 0 until grid[0].size) {
                var key = "$i,$j"

                if (!(visited.contains(key))) {
                    if (grid[i][j] != x) count++
                }
            }
        }

        return count
    }

    // 723. Candy Crush
    fun candyCrush(board: Array<IntArray>): Array<IntArray> {

        var crushedSet = find(board)
        while (crushedSet.isNotEmpty()) {
            crush(board, crushedSet)
            dropBoard(board)
            crushedSet = find(board)
        }

        return board
    }

    private fun find(board: Array<IntArray>): Set<Pair<Int, Int>> {
        val crushedSet = mutableSetOf<Pair<Int, Int>>()

        // Check vertically adjacent candies
        for (r in 1 until board.size - 1) {
            for (c in 0 until board[0].size) {
                if (board[r][c] == 0) continue
                if (board[r][c] == board[r - 1][c] && board[r][c] == board[r + 1][c]) {
                    crushedSet.add(Pair(r, c))
                    crushedSet.add(Pair(r - 1, c))
                    crushedSet.add(Pair(r + 1, c))
                }
            }
        }

        // Check horizontally adjacent candies
        for (r in 0 until board.size) {
            for (c in 1 until board[0].size - 1) {
                if (board[r][c] == 0) continue
                if (board[r][c] == board[r][c - 1] && board[r][c] == board[r][c + 1]) {
                    crushedSet.add(Pair(r, c))
                    crushedSet.add(Pair(r, c - 1))
                    crushedSet.add(Pair(r, c + 1))
                }
            }
        }

        return crushedSet
    }

    private fun crush(board: Array<IntArray>, crushedSet: Set<Pair<Int, Int>>) {
        for ((r, c) in crushedSet) {
            board[r][c] = 0
        }
    }

    private fun drop(board: Array<IntArray>) {
        for (c in 0 until board[0].size) {
            var lowestZero = -1

            for (r in board.size - 1 downTo 0) {
                if (board[r][c] == 0) {
                    lowestZero = maxOf(lowestZero, r)
                } else if (lowestZero >= 0) {
                    val temp = board[r][c]
                    board[r][c] = board[lowestZero][c]
                    board[lowestZero][c] = temp
                    lowestZero--
                }
            }
        }
    }

    fun dropBoard(board: Array<IntArray>){

        var lowestZero = -1
        for (i in 0 until board[0].size){
            var j = board.size-1

            for (j in board.size-1 downTo 0){
                if (board[j][i] == 0){
                    lowestZero = max(lowestZero, j)
                } else if (lowestZero >= 0) {
                    //var temp = board[j][i]
                    board[lowestZero][i] = board[j][i]
                    board[j][i] = 0
                    lowestZero--
                }

            }
        }

        for (i in 0 until board.size){
            print("[")
            for (j in 0 until board[0].size){
                print("${board[i][j]}\t")
            }
            print("]")
            println()
        }

    }

    // 68. Text Justification
    fun fullJustify(words: Array<String>, maxWidth: Int): List<String> {
        data class StringComponent(var part: String, var spaces: Int, var col: Int, var row: Int, var totalSize: Int)

        var lineWords = ArrayList<StringComponent>()
        var listOflistWords = ArrayList<ArrayList<StringComponent>>()
        var tam = 0
        for (word in words){

            var wordSize = word.length
            var totalSize = wordSize + tam
            var col = lineWords.size
            var row = listOflistWords.size
            var aux = StringComponent(word, 0, col, row, totalSize)

            if (totalSize <= maxWidth) {
                if (totalSize < maxWidth){
                    aux.spaces++
                    lineWords.add(aux)
                } else {
                    lineWords.add(aux)
                }

            } else {

                lineWords[lineWords.size-1].totalSize -= 1
                lineWords[lineWords.size-1].spaces--
                listOflistWords.add(ArrayList(lineWords))

                lineWords.clear()
                aux.totalSize = wordSize
                tam = wordSize
                aux.col=0
                aux.row+=1
                lineWords.add(aux)
            }
        }

        if (lineWords.isNotEmpty()){
            listOflistWords.add(ArrayList(lineWords))
        }

        for (wordsIn in listOflistWords){
            println("${wordsIn}")
        }

        // at this point, let's justify all words
        var result = ArrayList<String>()

        for ( j in 0 until listOflistWords.size-1){

            var currentList = listOflistWords[j]
            var totalCols = currentList[currentList.size-1].totalSize
            var spaceNeeded = maxWidth - totalCols // n of blank spaces to fill
            var tamList = currentList.size - 1
            println("for: ${currentList} -> need: ${spaceNeeded}")

            if (tamList == 0){
                // all spaceNeed for the unique word
                currentList[0].spaces = spaceNeeded

            } else if (tamList == spaceNeeded){
                // one space from 0 to spaceNeed
                for (i in 0 until currentList.size-1){
                    currentList[i].spaces++
                }

            } else if (tamList > spaceNeeded ){
                // one space from 0 to spaceNeeded
                for (i in 0 ..  spaceNeeded){
                    currentList[i].spaces++
                }

            } else if (tamList < spaceNeeded){
                // need to make calculation
                var countSpaces = (spaceNeeded / tamList).toInt()
                for (i in 0 ..  tamList-1){
                    currentList[i].spaces += countSpaces
                }
                var stillSpaces = (spaceNeeded % tamList)
                for (l in 0 until  stillSpaces){
                    currentList[l].spaces++
                }
            }

            var sb = StringBuilder()
            for (string in currentList){

                var spaces = string.spaces
                sb.append(string.part)
                for (sp in 0 until spaces ){
                    sb.append(" ")
                }
            }
            result.add(sb.toString())
        }
        println("Result ==> ${result}")

        // lastWord ...

        var lastLine = listOflistWords.last()
        var lastWord = lastLine.removeLast()
        var lastString = StringBuilder()
        while (lastLine.isNotEmpty()){
            lastString.append(lastLine.removeFirst().part)
            lastString.append(" ")
        }

        var nSpaces = maxWidth - lastWord.totalSize
        lastString.append(lastWord.part)
        for (it in 0 .. nSpaces){
            lastString.append(" ")
        }
        result.add(lastString.toString())
        return result
    }

    // 1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
    fun longestSubarray(nums: IntArray, limit: Int): Int {

        var maxLength = 0
        var start = 0
        var minDeque = ArrayDeque<Int>()
        var maxDeque = ArrayDeque<Int>()

        for (end in nums.indices) {
            while (minDeque.isNotEmpty() && nums[end] < nums[minDeque.last()]) {
                minDeque.removeLast()
            }
            minDeque.addLast(end)

            while (maxDeque.isNotEmpty() && nums[end] > nums[maxDeque.last()]) {
                maxDeque.removeLast()
            }
            maxDeque.addLast(end)

            while (nums[maxDeque.first()] - nums[minDeque.first()] > limit) {
                start++
                if (minDeque.first() < start) {
                    minDeque.removeFirst()
                }
                if (maxDeque.first() < start) {
                    maxDeque.removeFirst()
                }
            }

            maxLength = maxOf(maxLength, end - start + 1)
        }

        return maxLength
    }

}

fun main(args : Array<String>){


    val capitalTest = Capital()
    val balance = longArrayOf(10, 100, 20, 50, 30)
    val n = 10
    val testClass = Allocator(n)
    val intervals = arrayOf(intArrayOf(1,2), intArrayOf(2,3), intArrayOf(3,4), intArrayOf(1,3))
    val queries = arrayOf(intArrayOf(0,2), intArrayOf(1,2), intArrayOf(3,1), intArrayOf(1,1), intArrayOf(2,1))
    val customers = intArrayOf(1,0,1,2,1,1,7,5)
    val grumpy = intArrayOf(0,1,0,1,0,1,0,1)
    val minutes = 3
    val height = intArrayOf(1,1)
    val nums = intArrayOf(24,12,71,33,5,87,10,11,3,58,2,97,97,36,32,35,15,80,24,45,38,9,22,21,33,68,22,85,35,83,92,38,59,90,42,64,61,15,4,40,50,44,54,25,34,14,33,94,66,27,78,56,3,29,3,51,19,5,93,21,58,91,65,87,55,70,29,81,89,67,58,29,68,84,4,51,87,74,42,85,81,55,8,95,39)
    val limit = 87
    val lights = arrayOf(intArrayOf(2,1), intArrayOf(3,4), intArrayOf(3,2))
    val arr1 = intArrayOf(1,10,100)
    val arr2 = intArrayOf(1000)
    val timepoints = arrayListOf("00:00","23:59","00:10")
    val path = "/.../a/../b/c/../d/./"
    var words = arrayOf("Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do")
    var maxWidth = 20
    val grid = arrayOf(
        intArrayOf(110,0,0,0,114),
        intArrayOf(210,0,0,113,214),
        intArrayOf(310,0,0,213,314),
        intArrayOf(410,0,112,313,3),
        intArrayOf(521,5,511,5,414),
        intArrayOf(0,3,1,3,0),
        intArrayOf(0,412,2,613,822),
        intArrayOf(0,512,2,713,844),
        intArrayOf(198,1,1,2,2),
        intArrayOf(431,4,4,4,114)
    )
    println(capitalTest.fullJustify(words, maxWidth))

}

// 2043. Simple Bank System
class Bank(balance: LongArray) {

   // var accounts = HashMap<Int, Long>()
    var accountsArray = LongArray(balance.size)

    init {
        for (i in 0 until balance.size){
            accountsArray[i] = balance[i]
        }
        // println("Balance: ${accounts}")
    }


    fun transfer(account1: Int, account2: Int, money: Long): Boolean {

        if ((account1-1) > accountsArray.size || account1-1 < 0 || (account2-1) > accountsArray.size || account2-1 <0 ) return false
        var accountTotal1 = accountsArray[account1-1]
        var accountTotal2 = accountsArray[account2-1]

        if (account1 != account2) {

            if (accountTotal1 < money) return false
            accountTotal2 += money
            accountTotal1 -= money
            accountsArray[account1-1] = accountTotal1
            accountsArray[account2-1] = accountTotal2
            return true

        } else {
            if (accountTotal1 >= money) return true
            else return false
        }
    }

    fun deposit(account: Int, money: Long): Boolean {

        if ((account-1) > accountsArray.size || account-1 < 0) return false

        accountsArray[account-1] += money
        return true

    }

    fun withdraw(account: Int, money: Long): Boolean {

        if ((account-1) > accountsArray.size || account-1 < 0) return false

        var availableBalance = accountsArray[account-1]
        if (money <= availableBalance){
            accountsArray[account-1] -= money
            return true
        } else {
            return false
        }

    }

}

// 2502. Design Memory Allocator
class Allocator(n: Int) {

    // var minIndex || optimization = 0
    // var maxSize  || optimization = n
    var memory = IntArray(n)

    fun allocate(size: Int, mID: Int): Int {
        var i = 0
        var j = 0

        while (j < memory.size){
            if (memory[j] == 0){
                if (j - i + 1 >= size){
                    for (l in i .. j){
                        memory[l] = mID
                    }
                    return i
                }
                j++
            } else {
                j++
                i=j
            }
        }
        return -1
    }

    fun freeMemory(mID: Int): Int {
        var freed = 0
        for (i in 0 until memory.size){
            if (memory[i] == mID){
                memory[i] = 0
                freed++
            }
        }
        return freed
    }

}