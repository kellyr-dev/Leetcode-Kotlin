import java.math.BigInteger
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
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

        fun helper(opn : Int, close : Int, string : String){

            // println("string: ${string}")
            if (opn > n || close > n || opn < close){
                return
            }

            if (opn == n && close == n){
                result.add(string)
                return
            }

            helper(opn+1, close, string+"(")
            helper(opn, close+1, string+")")

        }

        helper(opn, close, "")
        return result
    }

    // 56. Merge Intervals
    fun merge(intervals: Array<IntArray>): Array<IntArray> {

        if (intervals.size == 1){
            return arrayOf(intervals[0])
        }

        intervals.sortBy{it[0]}
        val result = ArrayList<IntArray>()
        result.add(intArrayOf(intervals[0][0], intervals[0][1]) )

        var i = 1

        while (i < intervals.size){

            if (intervals[i][0] <= result.last()[1]){

                val current = result.removeLast()
                val aux = intArrayOf( Math.min(intervals[i][0], current[0]), Math.max(intervals[i][1], current[1]) )
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
        fun helper(i : Int, nums: IntArray) : Int{

            if (i >= nums.size){
                return 0
            }

            if (dp.containsKey(i)) return dp[i]!!

            var result = Math.max( nums[i] + helper(i+2, nums), helper(i+1, nums) )
            dp[i] = result
            return result
        }

        return helper(0, nums)

    }

    // 11. Container With Most Water
    fun maxArea(height: IntArray): Int {

        if (height.size == 1){
            return 0
        }

        var left = 0
        var right = height.size-1
        var maxArea = 0

        while (left < right ){
            var currentArea = (right - left) * (Math.min(height[left], height[right]) )
            maxArea = Math.max( maxArea, currentArea)

            if ( height[left] < height[right]){
                left += 1
            } else {
                right -=1
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
        if (x < 0){
            isNeg = true
        }

        if (x == 0){
            return 0
        }

        if (x == Int.MIN_VALUE || x == Int.MAX_VALUE){
            return 0
        }

        var aux = 1
        if (isNeg){
            aux = -x
        } else {
            aux = x
        }

        var queue = StringBuilder()
        while (aux != 0){

            queue.append(aux % 10)
            aux = (aux/10).toInt()

        }

        var result = queue.toString().toDouble()
        if (isNeg){
            result *= -1
        }

        if (result > Int.MAX_VALUE || result < Int.MIN_VALUE){
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

        if (digits.length == 0){
            return emptyList()
        }

        var result = ArrayList<String>()

        for (c in digits){
            //   println("char: $c")

            if ( !(codes.containsKey(c)) ) {
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

            if ( result.isNotEmpty() ){

                var auxArr = ArrayList<String>()
                while (result.isNotEmpty()){

                    var value = result.removeLast()
                    for (w in aux){
                        var newString = value + w
                        auxArr.add(newString)
                    }

                }
                result = auxArr

            } else {
                for (w in aux){
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

        for (i in 0 until nums.size){

            if ( !(existed.contains(nums[i])) ){
                existed.add(nums[i])
            }
        }

        for (j in 0 until nums.size){

            var localMaxOf = 0
            if ( existed.contains(nums[j]+1) ){
                continue
            } else {
                var aux = nums[j]
                while (existed.contains(aux) ) {
                    localMaxOf += 1
                    aux = aux - 1
                }

                lenghOf = Math.max(lenghOf, localMaxOf)
            }
        }
        return lenghOf
    }
}


fun main(){

    val testClass = Google()
    val num1 = intArrayOf(-1,0,1,2,-1,-4)
    val string = "bbbbb"

    val replacements = arrayListOf(arrayListOf("A","bce"), arrayListOf("B","ace"), arrayListOf("C","abc%B%"))
    val text = "%A%_%B%"
    val n = -2147483648
    val intervals = arrayOf(intArrayOf(9,16), intArrayOf(6,16), intArrayOf(1,9), intArrayOf(3,15))

}