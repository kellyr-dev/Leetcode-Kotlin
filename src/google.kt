import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.max


class Google {

    // 4. Median of Two Sorted Arrays
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {

        val minHeap = PriorityQueue<Int>()
        val maxHeap = PriorityQueue<Int>( compareByDescending { it })

        var i = 0
        var j = 0

        while (i < nums1.size && j < nums2.size){

            var num = 0
            if (nums1[i] <= nums2[j]){
                num = nums1[i]
                i += 1
            } else {
                num = nums2[j]
                j+=1
            }

            minHeap.add(num)
            if (minHeap.size > maxHeap.size + 1){
                maxHeap.add(minHeap.poll())
            }

        }

        while (i < nums1.size){
            minHeap.add(nums1[i])
            if (minHeap.size > maxHeap.size + 1){
                maxHeap.add(minHeap.poll())
            }
            i+=1
        }

        while (j < nums2.size){
            minHeap.add(nums2[j])
            if (minHeap.size > maxHeap.size + 1){
                maxHeap.add(minHeap.poll())
            }
            j+=1
        }
        println("minHeap: ${minHeap} -> ${minHeap.poll()}")
        println("maxHeap: ${maxHeap} -> ${maxHeap.poll()}")

        if (minHeap.size == maxHeap.size){
            return (minHeap.poll() + maxHeap.poll())/2.0
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

        while (l11 != null && l22 != null){

            val aux = l11.value + l22.value + carry
            if (aux >= 10){ // 12/10 = 1.2
                carry = 1
                listResult.add(aux % 10)
            } else {
                carry = 0
                listResult.add(aux)
            }

            l11 = l11.next
            l22 = l22.next

        }

        while (l11 != null){

            val aux = l11.value + carry
            if (aux >= 10){
                carry = 1
                listResult.add(aux % 10)
            } else {
                carry = 0
                listResult.add(aux)
            }
            l11 = l11.next
        }

        while (l22 != null){

            val aux = l22.value + carry
            if (aux >= 10){
                carry = 1
                listResult.add(aux % 10)
            } else {
                carry = 0
                listResult.add(aux)
            }
            l22 = l22.next
        }

        if (carry == 1){
            listResult.add(1)
        }

        var head = ListNode(listResult.removeFirst())
        var iterator = head

        while (listResult.isNotEmpty()){
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

        if (s.length <= 1){
            return s.length
        }

        // p w w k e w
        //           ^
        //       ^

        var left = 0
        var right = 1
        val map = HashMap<Char, Int>()
        var maxLen = Int.MIN_VALUE

        while (right < s.length){

            println("map: ${map}")
            if (map.containsKey(s[right])){
                map[s[right]] = map[s[right]]!! + 1
            } else {
                map[s[right]] = 1
            }

            while (map.get(s[right])!! > 1){
                map[s[left]] = map[s[left]]!! - 1
                left += 1
            }

            maxLen = maxOf(maxLen, right - left + 1)
            right +=1

        }
        return maxLen

    }

    // 200. Number of Islands
    fun numIslands(grid: Array<CharArray>): Int {
        var count = 0
        val map = HashSet<Pair<Int, Int>>()

        fun dfs(i: Int, j: Int){

            if (i < 0 || i >= grid.size || j < 0 || j >= grid[0].size || grid[i][j] == '0'){
                return
            }
            grid[i][j] = '0' // mark for the next iteration
            dfs(i+1, j)
            dfs(i-1, j)
            dfs(i, j+1)
            dfs(i, j-1)
        }

        for (i in 0 until grid.size){
            for (j in 0 until grid[0].size){
                if (grid[i][j] == '1'){
                    count += 1
                    dfs(i,j)
                }
            }
        }
        return count
    }


}


fun main(){

    val testClass = Google()
    val num1 = intArrayOf(-1,0,1,2,-1,-4)
    val string = "bbbbb"

    println(testClass.lengthOfLongestSubstring(string))

}