import java.math.BigInteger
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.math.ceil
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

    // 3481. Apply Substitutions

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

    
}


fun main(){

    val testClass = Google()
    val string = "bbbbb"
    val replacements = arrayListOf(arrayListOf("A","bce"), arrayListOf("B","ace"), arrayListOf("C","abc%B%"))
    val text = "%A%_%B%"
    val n = -2147483648
    val intervals = arrayOf(intArrayOf(9,16), intArrayOf(6,16), intArrayOf(1,9), intArrayOf(3,15))
    val start = "_L__R__R_"
    val end = "L______RR"
    val num = intArrayOf(4,3,2,7,8,2,3,1)
    val threshold =5
    val nums = intArrayOf(805306368,805306368,805306368)
    val k = 1
    var r = 3
    var c = 3
    var positions = arrayOf(intArrayOf(0,0), intArrayOf(0,1), intArrayOf(1,2), intArrayOf(2,1))
    var s = "PAYPALISHIRING"
    var numRows = 4
    var h = 1000000000
    println(testClass.minEatingSpeed(nums, h))

}