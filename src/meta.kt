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


    // 118. Pascal's Triangle
    fun generate(numRows: Int): List<List<Int>> {

        if (numRows == 0){
            return emptyList()
        }

        val result = ArrayList<List<Int>>()
        result.add( arrayListOf(1) ) // i = 0

        for ( i in 1 until numRows){

            var aux = ArrayList<Int>()
            var current = result.last()
            var acum = 0


            for ( j in 0 .. current.size){

                if ( (j == 0) || (j == current.size)){ //  j=0 (value=1) | j=1 (value=2) | j=2 (value=1)
                    aux.add(1)
                    acum = 1

                } else {
                    acum = current[j] + current[j-1]
                    aux.add(acum)
                }
            }

            result.add(aux)

        }

        return result
    }

    //
    fun findMissingRanges(nums: IntArray, lower: Int, upper: Int): List<List<Int>> {

        if (nums.isEmpty()){
            return arrayListOf(arrayListOf(lower, upper))
        }

        var left = 0
        var right = 0
        var result = ArrayList<List<Int>>()

        if (lower < nums[0]){
            println("I am not less")
            result.add(arrayListOf(left, nums[0]-1))
            left = 0
            right = 1
        }

        while (right < nums.size){

            if (Math.abs((nums[left]+1 - nums[right]-1)) >= 2){
                var aux = ArrayList<Int>()
                aux.add(nums[left]+1)
                aux.add(nums[right]-1)

                result.add(aux)
                left = right
                right+= 1
            } else {
                left = right
                right += 1
            }


        }

        if (left > 1 && left <= nums.size-1 && nums[left]+1 <= upper){
            result.add(arrayListOf(nums[left]+1, upper))
        }

        return result

    }


}

fun main(){

    val testClass = Meta()

    var nums = intArrayOf(-1)
    var lower = -1
    var upper = 0
    println(testClass.findMissingRanges(nums, lower, upper))

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