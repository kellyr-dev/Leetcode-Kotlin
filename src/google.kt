import java.math.BigInteger
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.math.ceil
import kotlin.math.log
import kotlin.math.max


class Google {

    // 4. Median of Two Sorted Arrays
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {

        val minHeap = PriorityQueue<Int>()
        val maxHeap = PriorityQueue<Int>(compareByDescending { it })

        var i = 0
        var j = 0

        while (i < nums1.size && j < nums2.size) {

            var num = 0
            if (nums1[i] <= nums2[j]) {
                num = nums1[i]
                i += 1
            } else {
                num = nums2[j]
                j += 1
            }

            minHeap.add(num)
            if (minHeap.size > maxHeap.size + 1) {
                maxHeap.add(minHeap.poll())
            }

        }

        while (i < nums1.size) {
            minHeap.add(nums1[i])
            if (minHeap.size > maxHeap.size + 1) {
                maxHeap.add(minHeap.poll())
            }
            i += 1
        }

        while (j < nums2.size) {
            minHeap.add(nums2[j])
            if (minHeap.size > maxHeap.size + 1) {
                maxHeap.add(minHeap.poll())
            }
            j += 1
        }
        println("minHeap: ${minHeap} -> ${minHeap.poll()}")
        println("maxHeap: ${maxHeap} -> ${maxHeap.poll()}")

        if (minHeap.size == maxHeap.size) {
            return (minHeap.poll() + maxHeap.poll()) / 2.0
        } else {
            return minHeap.poll().toDouble()
        }

    }

    // 2. Add Two Numbers
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {


        val listResult = ArrayList<Int>()
        var carry = 0

        var l11 = l1
        var l22 = l2

        while (l11 != null && l22 != null) {

            val aux = l11.value + l22.value + carry
            if (aux >= 10) { // 12/10 = 1.2
                carry = 1
                listResult.add(aux % 10)
            } else {
                carry = 0
                listResult.add(aux)
            }

            l11 = l11.next
            l22 = l22.next

        }

        while (l11 != null) {

            val aux = l11.value + carry
            if (aux >= 10) {
                carry = 1
                listResult.add(aux % 10)
            } else {
                carry = 0
                listResult.add(aux)
            }
            l11 = l11.next
        }

        while (l22 != null) {

            val aux = l22.value + carry
            if (aux >= 10) {
                carry = 1
                listResult.add(aux % 10)
            } else {
                carry = 0
                listResult.add(aux)
            }
            l22 = l22.next
        }

        if (carry == 1) {
            listResult.add(1)
        }

        var head = ListNode(listResult.removeFirst())
        var iterator = head

        while (listResult.isNotEmpty()) {
            iterator.next = ListNode(listResult.removeFirst())
            iterator = iterator.next!!
        }

        return head

    }

    // 15. 3Sum
    fun threeSum(nums: IntArray): List<List<Int>> {
        // return all triplets that are equal to 0
        return emptyList()
    }

    // 3. Longest Substring Without Repeating Characters
    fun lengthOfLongestSubstring(s: String): Int {

        if (s.length <= 1) {
            return s.length
        }

        // p w w k e w
        //           ^
        //       ^

        var left = 0
        var right = 1
        val map = HashMap<Char, Int>()
        var maxLen = Int.MIN_VALUE

        while (right < s.length) {

            println("map: ${map}")
            if (map.containsKey(s[right])) {
                map[s[right]] = map[s[right]]!! + 1
            } else {
                map[s[right]] = 1
            }

            while (map.get(s[right])!! > 1) {
                map[s[left]] = map[s[left]]!! - 1
                left += 1
            }

            maxLen = maxOf(maxLen, right - left + 1)
            right += 1

        }
        return maxLen

    }

    // 42. Trapping Rain Water

    // 200. Number of Islands
    fun numIslands(grid: Array<CharArray>): Int {
        var count = 0
        val map = HashSet<Pair<Int, Int>>()

        fun dfs(i: Int, j: Int) {

            if (i < 0 || i >= grid.size || j < 0 || j >= grid[0].size || grid[i][j] == '0') {
                return
            }
            grid[i][j] = '0' // mark for the next iteration
            dfs(i + 1, j)
            dfs(i - 1, j)
            dfs(i, j + 1)
            dfs(i, j - 1)
        }

        for (i in 0 until grid.size) {
            for (j in 0 until grid[0].size) {
                if (grid[i][j] == '1') {
                    count += 1
                    dfs(i, j)
                }
            }
        }
        return count
    }

    // 3453. Separate Squares I


    // 22. Generate Parentheses
    fun generateParenthesis(n: Int): List<String> {

        val opn = 0
        val close = 0
        val result = ArrayList<String>()

        fun helper(opn: Int, close: Int, string: String) {

            // println("string: ${string}")
            if (opn > n || close > n || opn < close) {
                return
            }

            if (opn == n && close == n) {
                result.add(string)
                return
            }

            helper(opn + 1, close, string + "(")
            helper(opn, close + 1, string + ")")

        }

        helper(opn, close, "")
        return result
    }

    // 56. Merge Intervals
    fun merge(intervals: Array<IntArray>): Array<IntArray> {

        if (intervals.size == 1) {
            return arrayOf(intervals[0])
        }

        intervals.sortBy { it[0] }
        val result = ArrayList<IntArray>()
        result.add(intArrayOf(intervals[0][0], intervals[0][1]))

        var i = 1

        while (i < intervals.size) {

            if (intervals[i][0] <= result.last()[1]) {

                val current = result.removeLast()
                val aux = intArrayOf(Math.min(intervals[i][0], current[0]), Math.max(intervals[i][1], current[1]))
                result.add(aux)
            } else {
                result.add(intArrayOf(intervals[i][0], intervals[i][1]))
            }
            i += 1

        }

        return result.toTypedArray()
    }

    // 198. House Robber
    fun rob(nums: IntArray): Int {

        val dp = HashMap<Int, Int>()
        fun helper(i: Int, nums: IntArray): Int {

            if (i >= nums.size) {
                return 0
            }

            if (dp.containsKey(i)) return dp[i]!!

            var result = Math.max(nums[i] + helper(i + 2, nums), helper(i + 1, nums))
            dp[i] = result
            return result
        }

        return helper(0, nums)

    }

    // 11. Container With Most Water
    fun maxArea(height: IntArray): Int {

        if (height.size == 1) {
            return 0
        }

        var left = 0
        var right = height.size - 1
        var maxArea = 0

        while (left < right) {
            var currentArea = (right - left) * (Math.min(height[left], height[right]))
            maxArea = Math.max(maxArea, currentArea)

            if (height[left] < height[right]) {
                left += 1
            } else {
                right -= 1
            }
        }

        return maxArea

    }

    // 560. Subarray Sum Equals K

    // 7. Reverse Integer
    fun reverse(x: Int): Int {

        // 123 % 10 = 3 (value)
        // 123 / 10 = 12 (next Iteration)
        // 12.3

        var isNeg = false
        if (x < 0) {
            isNeg = true
        }

        if (x == 0) {
            return 0
        }

        if (x == Int.MIN_VALUE || x == Int.MAX_VALUE) {
            return 0
        }

        var aux = 1
        if (isNeg) {
            aux = -x
        } else {
            aux = x
        }

        var queue = StringBuilder()
        while (aux != 0) {

            queue.append(aux % 10)
            aux = (aux / 10).toInt()

        }

        var result = queue.toString().toDouble()
        if (isNeg) {
            result *= -1
        }

        if (result > Int.MAX_VALUE || result < Int.MIN_VALUE) {
            return 0
        }

        return result.toInt()

    }

    // 17. Letter Combinations of a Phone Number
    fun letterCombinations(digits: String): List<String> {

        val codes = hashMapOf<Char, String>(
            '2' to "abc",
            '3' to "def",
            '4' to "ghi",
            '5' to "jkl",
            '6' to "mno",
            '7' to "pqrs",
            '8' to "tuv",
            '9' to "wxyz"
        )

        if (digits.length == 0) {
            return emptyList()
        }

        var result = ArrayList<String>()

        for (c in digits) {
            //   println("char: $c")

            if (!(codes.containsKey(c))) {
                continue
            }
            var aux = codes[c]!!
            // abc by [ff, fx, fy, fz]
            // a*[ff, fx, fy, fz] = [aff, afx, afy, afz]
            // b*[ff, fx, fy, fz] = [bff, bfx, bfy, bfz]
            // c*[ff, fx, fy, fz] = [cff, cfx, cfy, cfz]

            // abc by []
            // a*[] = [a]
            // b*[] = [b]
            // c*[] = [c]

            if (result.isNotEmpty()) {

                var auxArr = ArrayList<String>()
                while (result.isNotEmpty()) {

                    var value = result.removeLast()
                    for (w in aux) {
                        var newString = value + w
                        auxArr.add(newString)
                    }

                }
                result = auxArr

            } else {
                for (w in aux) {
                    result.add(w.toString())
                }
            }
        }

        return result

    }

    // 253. Meeting Rooms II

    // 128. Longest Consecutive Sequence
    fun longestConsecutive(nums: IntArray): Int {

        var lenghOf = 0
        val existed = HashSet<Int>()

        for (i in 0 until nums.size) {

            if (!(existed.contains(nums[i]))) {
                existed.add(nums[i])
            }
        }

        for (j in 0 until nums.size) {

            var localMaxOf = 0
            if (existed.contains(nums[j] + 1)) {
                continue
            } else {
                var aux = nums[j]
                while (existed.contains(aux)) {
                    localMaxOf += 1
                    aux = aux - 1
                }

                lenghOf = Math.max(lenghOf, localMaxOf)
            }
        }
        return lenghOf
    }

    // 84. Largest Rectangle in Histogram

    // 2337. Move Pieces to Obtain a String
    fun canChange(start: String, target: String): Boolean {

        if (start.length != target.length) {
            return false
        }

        var startR = 0
        var startL = 0

        var targetR = 0
        var targetL = 0

        for (i in 0 until start.length) {

            if (start[i] == 'R') {
                startR += 1
            }

            if (start[i] == 'L') {
                startL += 1
            }
        }

        for (j in 0 until target.length) {

            if (target[j] == 'R') {
                targetR += 1
            }

            if (target[j] == 'L') {
                targetL += 1
            }
        }

        if (targetR != startR || startL != targetL) {
            return false
        }

        var i = 0
        var j = 0

        val stackStart = ArrayDeque<Pair<Char, Int>>()
        val stackTarget = ArrayDeque<Pair<Char, Int>>()

        while (i < start.length) {

            if (start[i] == 'L' || start[i] == 'R') {
                stackStart.add(Pair(start[i], i))
            }

            i += 1

        }

        while (j < target.length) {

            if (target[j] == 'L' || target[j] == 'R') {
                stackTarget.add(Pair(target[j], j))
            }

            j += 1
        }

        // if (L in start is greater than L in target) ok
        // if (L in start is less than L in target) not

        // if (R in start is less than R in target) ok
        // if (R in start is greater than R in target) not

        while (stackStart.isNotEmpty() && stackTarget.isNotEmpty()) {

            var aux_start = stackStart.removeFirst()
            var aux_target = stackTarget.removeFirst()
            if (aux_start.first != aux_target.first) {
                return false
            } else {
                if (aux_start.first == 'L') {
                    if (aux_start.second < aux_target.second) {
                        return false
                    }

                } else {
                    if (aux_start.second > aux_target.second) {
                        return false
                    }

                }
            }
        }

        return true

    }

    // 1944. Number of Visible People in a Queue

    // 6. Zigzag Conversion

    // 3532. Path Existence Queries in a Graph I
    fun pathExistenceQueries(n: Int, nums: IntArray, maxDiff: Int, queries: Array<IntArray>): BooleanArray {

        val result = BooleanArray(queries.size)
        val map = HashMap<Int, ArrayList<Int>>()

        var i = 0
        for (query in queries){

            if (Math.abs(nums[query[0]] - nums[query[1]]) <= maxDiff){
                result[i] = true

            } else{

                var minIndex = query.min()
                var maxIndex = query.max()
                var flagResult = true
                var prev = minIndex
                while (minIndex <= maxIndex){
                    if (Math.abs(nums[prev] - nums[minIndex]) > maxDiff){
                        flagResult = false
                        break
                    }
                    prev = minIndex
                    minIndex +=1
                }

                if (flagResult){
                    result[i] = true
                } else {
                    result[i] = false
                }
            }
            i += 1
        }

        println("printing: ")
        result.forEach { print("${it}->") }
        return result

    }

    fun decodeString(s: String): String {

        val stack = ArrayDeque<Char>()

        for (c in s) {

            if (c != ']') {
                stack.add(c)
            } else {

                var aux = ""
                while (stack.last() != '[') {
                    aux = stack.removeLast() + aux
                }

                stack.removeLast()
                var auxK = ""
                while (stack.isNotEmpty() && stack.last().isDigit()) {
                    auxK = stack.removeLast() + auxK
                }

                var k = auxK.toInt()
                var result = ""
                var i = 0
                while (i < k) {
                    result += aux
                    i += 1
                }

                for (ch in result) {
                    stack.add(ch)
                }

            }

        }

        var res = ""
        while (stack.isNotEmpty()) {
            res += stack.removeFirst()
        }
        return res

    }

    // 1283. Find the Smallest Divisor Given a Threshold
    fun smallestDivisor(nums: IntArray, threshold: Int): Int {

        var result = 1
        val maxValue = nums.max()
        var left = 0
        var right = maxValue
        while (left <= right) {

            var sumaAux = 0.0
            val mid = (left + right) / 2

            for (num in nums) {
                val div = ceil(((num * 1.0) / mid))
                sumaAux += div
            }

            if (sumaAux <= threshold) {
                result = mid
                right = mid - 1
            } else {
                left = mid + 1
            }
        }

        return result

    }

    // 1944. Number of Visible People in a Queue (TLE)
    fun canSeePersonsCount(heights: IntArray): IntArray {

        //var result = IntArray(heights.size)
        var result = ArrayList<Int>()
        for (i in 0 until heights.size - 1) {


            var count = 1
            if (heights[i] < heights[i + 1]) {
                result.add(count)
                continue
            }
            var flag = false
            var max_local = heights[i + 1]
            for (j in i + 2 until heights.size) {

                if (heights[j] > max_local && flag) {
                    count += 1
                    max_local = heights[j]
                }

                if (heights[j] > heights[i]) {
                    flag = true
                }

            }

            //result[i] = count
            result.add(count)
            println("[i]:${i} -> ${result}")


        }

        return result.toIntArray()
    }

    // 162. Find Peak Element
    fun findPeakElement(nums: IntArray): Int {

        var left = 0
        var right = nums.size - 1

        while (left < right) {

            val mid = (left + right) / 2
            if (nums[mid] > nums[mid + 1]) {
                right = mid
            } else {
                left = mid + 1
            }

        }

        return left
    }

    // 739. Daily Temperatures
    fun dailyTemperatures(temperatures: IntArray): IntArray {

        var result = IntArray(temperatures.size)

        val stack = ArrayDeque<Pair<Int, Int>>()

        for (i in 0 until temperatures.size) {

            println("stack: ${stack}")

            while (stack.isNotEmpty() && temperatures[i] > stack.last().first) {
                result[stack.last().second] = i - stack.removeLast().second
            }
            stack.add(Pair(temperatures[i], i))

        }
        println("result =>")
        result.forEach { print("${it}->") }
        return result
    }

    // 496. Next Greater Element I
    fun nextGreaterElement(nums1: IntArray, nums2: IntArray): IntArray {

        val map = HashMap<Int, Int>()
        val stack = ArrayDeque<Int>()

        stack.add(nums2[0])
        for (i in 1 until nums2.size) {

            while (stack.isNotEmpty() && nums2[i] > stack.last()) {
                map[stack.removeLast()] = nums2[i]
            }
            stack.add(nums2[i])

        }
        while (stack.isNotEmpty()) {
            map[stack.removeLast()] = -1
        }


        println("map: ${map}")

        val result = IntArray(nums1.size)

        for (i in 0 until nums1.size) {
            result[i] = map[nums1[i]]!!
        }

        return result
    }

    // 402. Remove K Digits
    fun removeKdigits(num: String, k: Int): String {

        if (k >= num.length) {
            return "0"
        }

        var aux = k
        val stack = ArrayDeque<Int>()

        stack.add(num[0].digitToInt())
        for (i in 1 until num.length) {

            while (stack.isNotEmpty() && aux > 0 && num[i].digitToInt() < stack.last()) {
                stack.removeLast()
                aux -= 1
            }
            stack.add(num[i].digitToInt())
        }

        if (aux > 0) {
            while (stack.isNotEmpty() && aux > 0) {
                stack.removeLast()
                aux -= 1
            }
        }

        val result = StringBuilder()
        var leadingZero = true
        while (stack.isNotEmpty()) {

            if (leadingZero && stack.first() == 0) {
                stack.removeFirst()
                continue
            }
            leadingZero = false
            result.append(stack.removeFirst())
        }

        if (result.isEmpty()) return "0"
        return result.toString()
    }

    // 169. Majority Element
    fun majorityElement(nums: IntArray): Int {

        var count = 0
        var value = 0

        for (num in nums) {

            if (count == 0) {
                value = num
            }
            if (num == value) {
                count += 1
            } else {
                count -= 1
            }

        }

        return value
    }

    // 305. Number of Islands II (TLE)
    fun numIslands2(m: Int, n: Int, positions: Array<IntArray>): List<Int> {

        // for each positions change the matrix in that position to 1
        // after change the matrix call the function count island
        // put the result in the List
        // when finish positions return the List

        var matrix = Array(m) {IntArray(n)}

        var result = ArrayList<Int>()

        for (pos in positions){
            matrix[pos[0]][pos[1]] = 1
            result.add( countMatrix(matrix))
        }

        return result

    }

    fun countMatrix(matrix: Array<IntArray>) : Int{

        println("Matrix Entering:")
        for (arr in matrix){
            for (i in 0 until arr.size){
                print("${arr[i]}->")
            }
            println()
        }

        fun helper(i: Int, j: Int, matrix: Array<IntArray>, map: HashSet<String>){
            if (i < 0 || i >= matrix.size || j < 0 || j >= matrix[0].size || matrix[i][j] == 0){
                return
            }

            var key = "($i,$j)"
            if ( map.contains(key) ){
                return
            }
            map.add(key)
            helper(i+1, j, matrix, map)
            helper(i-1, j, matrix, map)
            helper(i, j+1, matrix, map)
            helper(i, j-1, matrix, map)

        }

        var count = 0
        var map = HashSet<String>()
        for (i in 0 until matrix.size){
            for (j in 0 until matrix[0].size){

                var pair = "($i,$j)"
                if (matrix[i][j] == 1 && !(map.contains(pair)) ){
                    count += 1
                    helper(i, j, matrix, map)
                }
            }
        }
        println("count: ${count}")
        return count

    }

    // 442. Find All Duplicates in an Array
    fun findDuplicates(nums: IntArray): List<Int> {

        val result = ArrayList<Int>()
        for (num in nums){

            nums.forEach { print("${it},") }

            if (nums[Math.abs(num)-1] < 0){
                result.add(Math.abs(num))
            } else {
                nums[Math.abs(num)-1] *= -1
            }
        }
        return result
    }

    // 875. Koko Eating Bananas
    fun minEatingSpeed(piles: IntArray, h: Int): Int {

        // then
        /* Problem: Find a k that is between min and max of number in piles
            where the sum of all elements divided by that k is less or equal to h
        */

        if (piles.size == h){
            return piles.max()
        }

        if (piles.size == 0){
            return 0
        }

        if (piles.size == 1){
            return ceil(piles[0]/h.toDouble()).toInt()
        }

        var minValue = piles.min()
        var maxValue = piles.max()

        if (minValue == maxValue){
            if (h < minValue){
                return minValue
            }
        }

        var left = 0
        var right = maxValue
        var k = -1

        while (left < right){

            val mid = (left + right) / 2

            var suma = 0.0
            piles.forEach { suma += ceil(it/mid.toDouble())  }
            if (suma <= h){
                right = mid
                k = right
            } else {
                left = mid + 1
            }
        }
        return k

    }

    // 1101. The Earliest Moment When Everyone Become Friends
    fun earliestAcq(logs: Array<IntArray>, n: Int): Int {

        // go build the map adjacency list
        // for each timestamp of creating adjacency list (make DFS or BFS)
        // if a each step your visited is equal to n
        // return the current timestamp


        // before run algo above
        // remember to sort ascending in order to have the earliest time

        logs.sortBy { it[0] }

        val adj = HashMap<Int, ArrayList<Int>>()

        for (log in logs){

            if (adj.containsKey(log[1])){
                adj[log[1]]?.add(log[2])
            } else {
                adj[log[1]] = arrayListOf(log[2])
            }

            if (adj.containsKey(log[2])){
                adj[log[2]]?.add(log[1])
            } else {
                adj[log[2]] = arrayListOf(log[1])
            }

            println("Graph until now: ${adj}")

            if (bfs(adj, n)){
                return log[0]
            }

        }
        return -1

    }

    fun bfs(map: HashMap<Int, ArrayList<Int>>, x : Int) : Boolean{

        var queue = ArrayDeque<Int>()
        var visited = HashSet<Int>()

        var first = map.keys.first()
        println("First: ${first}")
        queue.add(first)

        while (queue.isNotEmpty()){

            var current = queue.removeFirst()
            if (!visited.contains(current)){
                visited.add(current)
                if (visited.size == x){
                    return true
                }

                for (neighbor in map[current]!!){
                    queue.add(neighbor)
                }

            }

        }

       return false

    }

    // 31. Next Permutation
    fun nextPermutation(nums: IntArray): Unit {

        // 1) find the longest match or break point
        // 2) from the breakpoint get the minimun value in the rest values in array but greater than the breakpoint
        // 3) make a swap from the breakpoint to the smallest number in array greater than breakpoint
        // 3) putting all number in sorted order from breakpoint

        // input: 1,3,2
        // output: 2,1,3

        var breakPoint = -1
        for (i in 0 until nums.size - 1){

            if (nums[i] < nums[i+1]){
                breakPoint = i
            }
        }

        println("BreakPoint is: ${breakPoint}")

        if (breakPoint == -1){
            return nums.sort()
        }

        var minNumber = Int.MAX_VALUE
        var minIndex = -1
        for (i in breakPoint+1 until nums.size){
            if (nums[i] > nums[breakPoint]){
                if (nums[i] <= minNumber){
                    minNumber = nums[i]
                    minIndex = i
                }
            }
        }

        println("Index: ${minIndex}")

        if (minIndex != -1){

            var swap = nums[breakPoint]
            nums[breakPoint] = nums[minIndex]
            nums[minIndex] = swap
        }

        nums.sort(breakPoint+1, nums.size)


    }

    // 19. Remove Nth Node From End of List
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {

        val stack = ArrayDeque<Int>()

        var aux = head
        while (aux != null){
            stack.add(0, aux.value)
            aux = aux.next
        }

        if (stack.isNotEmpty() && n >= 1){
            print("here entering")
            if (n == 1){
                stack.removeFirst()
            } else{
                stack.removeAt(n-1)
            }

        } else {
            return null
        }

        if (stack.isEmpty()){
            return null
        }

        var auxiliar = ListNode(stack.removeLast())
        val result = auxiliar

        while (stack.isNotEmpty()){
            auxiliar.next = ListNode(stack.removeLast())
            auxiliar = auxiliar.next!!
        }

        return result

    }

    // 3481. Apply Substitutions
    fun applySubstitutions(replacements: List<List<String>>, text: String): String {

        // create a map from replacements
        // if (value has %)
        // build the map with only letters
        // make the same for text


        // %A%_%B%_%C%
        //            ^
        //         ^
        // %A%_%B%_abc%B%
        //

        val map = HashMap<String, String>()

        for (list in replacements){
            map[list[0]] = list[1]
        }

        val queue = ArrayDeque<String>()
        for (s in text){
            queue.add(s.toString())
        }

        var result = StringBuilder()

        while (queue.isNotEmpty()){

            var aux = queue.removeFirst()
            if (aux == "%"){

                var key = ""
                while (queue.isNotEmpty() && queue.first() != "%"){
                    key += queue.removeFirst()
                }
                if (queue.isNotEmpty() && queue.first() == "%"){
                    queue.removeFirst()
                }

                println("key: ${key}")

                if (map.containsKey(key)){
                    queue.add(0, map[key]!!)
                }


            }else{
                result.append(aux)
            }

        }

        return result.toString()

    }

    // 2200. Find All K-Distant Indices in an Array
    fun findKDistantIndices(nums: IntArray, key: Int, k: Int): List<Int> {

        val exist = HashSet<Int>()
        for (i in 0 until nums.size){

            if (nums[i] == key){
                exist.add(i)
            }
        }

        if (exist.size == 0){
            return emptyList()
        }

        val checkIn = HashSet<Int>()
        val result = ArrayList<Int>()


        for (value in exist){

            for (i in 0 until nums.size){

                if (!(result.contains(i))) {

                    if (Math.abs(value - i) <= k){
                        result.add(i)
                        checkIn.add(i)
                    }
                }

            }
        }

        result.sort()
        return result
    }

    // 283. Move Zeroes
    fun moveZeroes(nums: IntArray): Unit {

        // nums [1, 3, 12, 0, 0]
        //                    ^
        //                    ^
        //      [1, 3, 0, 0, 12]
        //
        //                   ^


        for (i in 0 until nums.size){


            if (nums[i] == 0){

                println("nums[i]: ${nums[i]} && i:${i}")

                var j = i
                while (j < nums.size && nums[j] == 0){
                    j+= 1
                }
                println("j:${j}")
                if (j < nums.size){
                    nums[i] = nums[j]
                    nums[j] = 0
                }

            } else {
                continue
            }

        }
    }

    // 202. Happy Number
    fun isHappy(n: Int): Boolean {

        var check = HashSet<Int>()
        var num = n

        while (num >= 0 && (!check.contains(num)) ){

            println("current num: ${num}")

            if (check.contains(num)){
                return false
            }
            check.add(num)

            var aux = num
            var newNum = 0
            var i = 10
//19 / 2 = 1.9
            // 19

            while (aux > 0){

                var digit = (aux % 10)
                var pow2 = digit * digit
                newNum += pow2
                aux = (aux / 10)

            }
            num = newNum

            if (num == 1){
                return true
            }

        }

        if (num == 1){
            return true
        }

        return false

    }

    // 70. Climbing Stairs
    fun climbStairs(n: Int): Int {
        val memo = HashMap<Int, Int>()

        fun helper(top : Int, i: Int): Int{

            if (memo.containsKey(i)){
                return memo[i]!!
            }

            if (i > top){
                return 0
            }

            if (i == top){
                return 1
            }

            val mem = helper(top, i + 1) + helper(top, i+2)
            memo[i] = mem
            return mem

        }

        var result = helper(n,0)
        return result
    }

    // 3330. Find the Original Typed String I
    fun possibleStringCount(word: String): Int {

        var count = 1

        if (word.length <= 1){
            return word.length
        }

        for ( i in 1 until word.length){

            if (word[i] == word[i-1]){
                count += 1
            }
        }

        return count
    }

    // 67. Add Binary
    fun addBinary(a: String, b: String): String {

        if (a.isEmpty() && b.isEmpty()){
            return "0"
        }

        if (a.isEmpty()){
            return b
        }

        if (b.isEmpty()){
            return a
        }

        var i = a.length-1
        var j = b.length-1
        var carry = '0'

        var result = ""
        while (i >= 0 && j >= 0 ){
            var res = "0"
            if (a[i] == '0' && b[j] == '0'){

                if (carry == '1'){
                    res = "1"
                    carry = '0'
                }else{
                    res = "0"
                }
            } else if (a[i] == '1' && b[j] == '1'){
                if (carry == '1'){
                    res = "1"
                    carry = '1'
                } else {
                    res = "0"
                    carry = '1'
                }
            } else {
                if (carry == '1'){
                    res = "0"
                    carry = '1'
                } else {
                    res = "1"
                    carry = '0'
                }
            }

            println("res: ${res}")
            println("i:${i} && j:${j}")
            result = res + result

            i -= 1
            j -= 1

        }

        while (i >= 0){

           if (carry == '1'){
               if (a[i] == '1'){
                   result = "0" + result
               } else {
                   carry = '0'
                   result = "1" + result
               }
           } else {
               if (a[i] == '1'){
                   result = "1" + result
               } else {
                   result = "0" + result
               }
           }
            i -= 1

        }

        while (j >= 0){

            if (carry == '1'){
                if (b[j] == '1'){
                    result = "0" + result
                } else {
                    carry = '0'
                    result = "1" + result
                }
            } else {
                if (b[j] == '1'){
                    result = "1" + result
                } else {
                    result = "0" + result
                }
            }
        j -= 1
        }

        if (carry == '1'){
            result = "1" + result
        }

        return result
    }

    // 148. Sort List
    fun sortList(head: ListNode?): ListNode? {

        var aux = head
        var auxList = ArrayList<Int>()

        while (aux != null){
            auxList.add(aux.value)
            aux = aux.next
        }

        auxList.sort()

        var auxResult = ListNode(-1)
        var result = auxResult
        for ( i in 0 until auxList.size){
            var newNode = ListNode(auxList[i])
            auxResult.next = newNode
            auxResult = auxResult.next!!
        }

        // [-1]
        return result.next

    }

    // 3597. Partition String
    fun partitionString(s: String): List<String> {

        if (s.isEmpty()){
            return emptyList()
        }

        if (s.length == 1){
            return arrayListOf(s)
        }

        val segments = HashMap<String, Int>()
        var index = 0
        var auxString = StringBuilder()

        for (i in 0 until s.length){

            auxString.append(s[i])
            if (!(segments.containsKey(auxString.toString()))) {
                // means I got an already String that is in segments
                // means this String does not exist in segments
                // I can add it and clear my String
                segments[auxString.toString()] = index
                auxString.clear()
                index += 1
            }

        }

        val result = ArrayList<String>()
        for (j in 0 until segments.size){
            result.add("")
        }

        for ( (value, key) in segments ){
            result[key] = value
        }

        return result
    }

    // 58. Length of Last Word
    fun lengthOfLastWord(s: String): Int {

        var newString = s.trimStart().trimEnd()
        val words = newString.split(" ")

        for (word in words){
            println("word: ${word}")
        }

        return words.last().length

    }


    // 852. Peak Index in a Mountain Array
    fun peakIndexInMountainArray(arr: IntArray): Int {
        var maxdiff = 0
        var res = -1

        for (i in 1 until arr.size){

            if (arr[i] < arr[i-1]){
                if (arr[i-1] > maxdiff){
                    maxdiff = arr[i-1]
                    res = i-1
                }
            }

        }

        return res

    }

    // 417. Pacific Atlantic Water Flow (TLE)
    fun pacificAtlantic(heights: Array<IntArray>): List<List<Int>> {


        fun atlanticDown(i: Int, j: Int, heights: Array<IntArray>) : Boolean{

            var prev = heights[i][j]
            var row = i+1

            while (row < heights.size){
                if (prev >= heights[row][j]){
                    prev = heights[row][j]
                    row += 1
                } else {
                    return false
                }
            }

            return true
        }

        fun atlanticRight(i: Int, j: Int, heights: Array<IntArray>) : Boolean{

            var prev = heights[i][j]
            var col = j+1

            while (col < heights[0].size){
                if (prev >= heights[i][col]){
                    prev = heights[i][col]
                    col +=1
                } else {
                    return false
                }
            }

            return true
        }

        fun pacificUp(i: Int, j: Int, heights: Array<IntArray>) : Boolean{

            var prev = heights[i][j]
            var row = i-1

            while (row > -1){
                if (prev >= heights[row][j]){
                    prev = heights[row][j]
                    row -= 1
                } else {
                    return false
                }
            }

            return true
        }

        fun pacificLeft(i: Int, j: Int, heights: Array<IntArray>) : Boolean{

            var prev = heights[i][j]
            var col = j-1

            while (col > -1){
                if (prev >= heights[i][col]){
                    prev = heights[i][col]
                    col -= 1
                } else {
                    return false
                }
            }

            return true
        }


        val res = ArrayList<List<Int>>()

        for (i in 0 until heights.size){
            for (j in 0 until heights[0].size){

                if ((atlanticDown(i,j, heights) || atlanticRight(i,j, heights)) && (pacificUp(i,j, heights) || pacificLeft(i,j, heights))) {
                    res.add( listOf(i,j) )
                }

            }
        }

        return res
    }

}


fun main(){

    val testClass = Google()
    val string = "bbbbb"
    val intervals = arrayOf(intArrayOf(9,16), intArrayOf(6,16), intArrayOf(1,9), intArrayOf(3,15))
    val start = "_L__R__R_"
    val end = "L______RR"

    val threshold =5
    val k = 1
    var r = 3
    var c = 3
    var positions = arrayOf(intArrayOf(0,0), intArrayOf(0,1), intArrayOf(1,2), intArrayOf(2,1))
    var numRows = 4
    val maxDiff = 6
    val nums = intArrayOf(1,3,2)
    val queries = arrayOf(intArrayOf(1,0))
    val replacements = arrayListOf(arrayListOf("A","bce"), arrayListOf("B","ace"), arrayListOf("C","abc%B%"))
    val a = "1111"
    val b = "1111"
    val head = ListNode(4)
    head.next = ListNode(2)
    head!!.next!!.next = ListNode(1)
    head!!.next!!.next!!.next = ListNode(3)
    val s = "aabbgsadb"
    val t = "aaabbbba"
    val n = 10
    val num = intArrayOf(24,69,100,99,79,78,67,36,26,19)
    val heights = arrayOf(intArrayOf(1,2,2,3,5), intArrayOf(3,2,3,4,4), intArrayOf(2,4,5,3,1), intArrayOf(6,7,1,4,5), intArrayOf(5,1,1,2,4) ) // [[],[],[],[],[]]

    println(testClass.pacificAtlantic(heights))
//    val testing = RandomizedSet()
//    println(testing.remove(0))
//    println(testing.remove(0))
//    println(testing.insert(0))
//    println(testing.getRandom())
//    println(testing.remove(0))
//    println(testing.insert(0))
//    println(testClass.peakIndexInMountainArray(num))

}

// 359. Logger Rate Limiter
class Logger() {

    val timeMessage = HashMap<String, Int>()
    fun shouldPrintMessage(timestamp: Int, message: String): Boolean {

        if (timeMessage.containsKey(message)){

            var currentTime = timeMessage[message]!!

            if (timestamp >= currentTime + 10){
                timeMessage[message] = timestamp
                return true
            } else {
                return false
            }

        } else {
            timeMessage[message] = timestamp
            return true
        }

    }

}

// 380. Insert Delete GetRandom O(1)
class RandomizedSet() {

    val set = HashMap<Int, Int>()
    val indexes = ArrayList<Int>()

    fun insert(`val`: Int): Boolean {

        if (set.containsKey(`val`)){
            return false
        } else {
            set[`val`] = indexes.size
            indexes.add(`val`)
            return true
        }

    }

    fun remove(`val`: Int): Boolean {

        //

        if (set.containsKey(`val`)){

            var index = set[`val`]!!
            var elemeInlast = indexes.last()
            indexes[index] = elemeInlast
            set[elemeInlast] = index
            set.remove(`val`)
            indexes.removeLast()

            return true
        } else {
            return false
        }

    }

    fun getRandom(): Int {

        return indexes.random()
    }

}