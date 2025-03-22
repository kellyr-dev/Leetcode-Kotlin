import java.util.PriorityQueue
import kotlin.math.min

class Meta {

    // 34. Find First and Last Position of Element in Sorted Array
    fun searchRange(nums: IntArray, target: Int): IntArray {

        var right = 0
        var left = -1

        while (right < nums.size){

            if (nums[right] == target){
                left = right
                while (right < nums.size && nums[right] == target){
                    right += 1
                }
                return intArrayOf(left, right-1)

            } else {
                right += 1
            }
        }
        return intArrayOf(-1, -1)
    }

    // 34. Find First and Last Position of Element in Sorted Array
    fun searchRangeBS(nums: IntArray, target: Int) : IntArray {

        var startIndex = binarySearch(nums, target, true)
        var endIndex = binarySearch(nums, target, false)

        if ((startIndex == -1) || (endIndex == -1)){
            return intArrayOf(-1,-1)
        }
        return intArrayOf(startIndex, endIndex)
    }

    private fun binarySearch(nums: IntArray, target: Int, isFirst: Boolean): Int {

        var start = 0
        var end = nums.size-1

        while (start <= end){
            var mid = (start + end) / 2

            if (nums[mid] == target){
                if (isFirst) {
                    if (mid == start || nums[mid- 1] != target){
                        return mid
                    }
                    end = mid - 1
                } else {

                    if (mid == end || nums[mid + 1] != target){
                        return mid
                    }
                    start = mid + 1

                }
            } else if (nums[mid] > target){
                end = mid - 1
            } else {
                start = mid + 1
            }
        }
        return -1
    }

    // 670. Maximum Swap (not tested)
    fun maximumSwap(num: Int): Int {

        val compareByNum : Comparator<Pair<Int, Int>> = compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second }
        val minHeap : PriorityQueue<Pair<Int, Int>> = PriorityQueue(compareByNum)
        val numToString = num.toString()
        val originalArray = IntArray(numToString.length)

        for (i in 0 .. numToString.length-1){

            var pair = Pair(-numToString[i].digitToInt(), i)
            minHeap.add(pair)
            originalArray[i] = numToString[i].digitToInt()


        }

        println("minHeap: ${minHeap}")

        var i = 0
        while (i < originalArray.size){

            var maxPair = minHeap.poll()
            var maxIndex = maxPair.second
            var maxValue = maxPair.first * -1

            println("maxIndex: ${maxIndex}")
            println("maxValue: ${maxValue}")

            if (maxIndex != i){
                var valueToSwap = originalArray[i]
                originalArray[i] = maxValue
                originalArray[maxIndex] = valueToSwap
                break
            }
            i += 1

        }

        var result = ""
        for (i in 0 until originalArray.size){
            result += originalArray[i].digitToChar()
        }

        return result.toInt()
    }

    // 408. Valid Word Abbreviation
    fun validWordAbbreviation(word: String, abbr: String): Boolean {
        if (abbr.length > word.length){
            return false
        }
        var i = 0
        var j = 0

        while (i < word.length && j < abbr.length){

            if (word[i] == abbr[j]){
                i+= 1
                j+= 1
                continue
            }

            if (abbr[j] == '0'){
                return false
            }

            var aux = ""
            while (j < abbr.length && abbr[j].isDigit()){
                aux += abbr[j]
                j+=1
            }

            if (aux.isNotEmpty()){
                var moveI = aux.toInt()
                i += moveI
            } else {
                return false
            }
        }
        return i == word.length && j == abbr.length
    }

    // 392. Is Subsequence
    fun isSubsequence(s: String, t: String): Boolean {

        if (s.length > t.length){
            return false
        }

        if (s.length == t.length){
            return s == t
        }

        var i= 0
        var j =0
        while (i < s.length && j < t.length){

            if (s[i] == t[j]){
                i += 1
                j += 1
            } else {
                j += 1
            }

        }

        return i == s.length

    }


    // 523. Continuous Subarray Sum
    
}

fun main(){

    val testClass = Meta()
    var nums = intArrayOf(5,7,7,8,8,10)

    var word = "apple"
    var abbr = "a2e"
    println(testClass.validWordAbbreviation(word, abbr))

}


// 346. Moving Average from Data Stream
class MovingAverage(size: Int) {

    var suma = 0
    var queue = ArrayDeque<Int>()
    var avg = 0.0
    var windows = size
    // [10,3,5]

    fun next(`val`: Int): Double {

        if (queue.size < windows){
            queue.add(`val`)
            suma+= `val`
            avg = suma / (queue.size).toDouble()
            return avg
        } else {
            // fill my entire windows which is "size"
            suma -= queue.removeFirst()
            suma += `val`
            queue.add(`val`)
            avg = suma / (queue.size).toDouble()
            return avg
        }
    }
}