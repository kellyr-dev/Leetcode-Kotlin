import java.lang.Exception
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.math.max
import kotlin.math.min
import kotlin.properties.Delegates



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

    // 163. Missing Ranges
    fun findMissingRanges(nums: IntArray, lower: Int, upper: Int): List<List<Int>> {

        if (nums.size == 0){
            return arrayListOf(arrayListOf(lower, upper))
        }

        var left = 0
        var right = 1
        var result = ArrayList<List<Int>>()

        if (lower < nums[0]){
            result.add(arrayListOf(lower, nums[0]-1))
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

        if (left >= 0 && left < nums.size && nums[left]+1 <= upper){
            result.add(arrayListOf(nums[left]+1, upper))
        }
        return result
    }

    // 1047. Remove All Adjacent Duplicates In String
    fun removeDuplicates(s: String): String {

        val result = StringBuilder()

        for (char in s){ // loop by Char at i in String "s"
            if (result.isNotEmpty() && result.last() == char){
                result.deleteAt(result.length-1)
            } else {
                result.append(char)
            }
        }

        return result.toString()
    }

    // 824. Goat Latin
    fun toGoatLatin(sentence: String): String {

        val vowels = hashSetOf('a', 'e', 'i', 'o', 'u')
        val sentenceArray = sentence.split(" ")
        val result = ArrayList<String>()
        var factor = "ma"

        for ( word in sentenceArray ){

            factor += "a"

            if ( vowels.contains(word[0]) ){
                var updatedWord = word + factor
                result.add(updatedWord)

            } else {

                var noFirst = ""
                for (i in 1 .. word.length-1){
                    noFirst += word[i]
                }

                var updatedWord = noFirst + word[0] + factor
                result.add(updatedWord)
            }
        }

        var resultString = StringBuilder()

        for (i in 0 until result.size){
            resultString.append(result[i])
            if (i != result.size-1){
                resultString.append(" ")
            }

        }
        return resultString.toString()

    }

    // 523. Continuous Subarray Sum
    fun checkSubarraySum(nums: IntArray, k: Int): Boolean {

        if (nums.size == 1) return false

        val prefix = IntArray(nums.size+1)
        var occurs = HashMap<Int, Int>()
        var suma = 0

        for (i in 1 until prefix.size){
            prefix[i] = prefix[i-1] + nums[i-1]
        }

        for (j in 0 until prefix.size){

            var aux = prefix[j] % k

            if (occurs.containsKey(aux)){

                var index = occurs[aux]!!

                if ((j - index) >= 2){
                    return true
                }
            } else {
                occurs[aux] = j
            }

        }
        return false

    }

    // 525. Contiguous Array
    fun findMaxLength(nums: IntArray): Int {

        if (nums.size < 2){
            return 0
        }

        if (nums.size == 2){
            if ( nums.sum() == 1){
                return 2
            } else {
                return 0
            }
        }

        val prefix = IntArray(nums.size)
        val table = HashMap<Int, Int>()
        var maxLength = 0
        var suma = 0

        for (i in 0 until nums.size){

            if (nums[i] == 1){
                suma += 1
            } else {
                suma -= 1
            }

            prefix[i] = suma

            if (suma == 0) {
                maxLength = Math.max(maxLength, i+1)
            }

            if ( table.containsKey(suma) ){

                val mapIndex = table[suma]!!
                maxLength = Math.max(maxLength, i-mapIndex)
            } else {

                table[suma] = i
            }
        }
        return maxLength
    }

    // 325. Maximum Size Subarray Sum Equals k
    fun maxSubArrayLen(nums: IntArray, k: Int): Int {

        var prefix = IntArray(nums.size)
        var map = HashMap<Int, Int>()
        var maxLen = 0
        var suma = 0

        for (i in 0 until nums.size){

            println("map: ${map}")
            prefix.forEach { print("${it}->") }
            println()

            suma += nums[i]

            if (suma == k) {
                maxLen = Math.max(maxLen, i+1)
            }

            prefix[i] = suma
            var factor = prefix[i] - k

            if ( map.containsKey(factor) ){
                maxLen = Math.max(maxLen, i-map[factor]!!)
            } else {
                map[prefix[i]] = i
            }

        }

        return maxLen
    }

    // 560. Subarray Sum Equals K
    fun subarraySum(nums: IntArray, k: Int): Int {

        var prefix = IntArray(nums.size)
        var map = HashMap<Int, Int>()
        var suma = 0
        var count = 0
        map[0] = 1

        for (i in 0 until nums.size){

            suma += nums[i]

            if (map.containsKey(suma - k)){
                count+= map[suma - k]!!
            }

            if ((map.containsKey(suma))){
                map[suma] = map[suma]!! + 1
            } else {
                map[suma] = 1
            }

        }
        return count

    }

    // 713. Subarray Product Less Than K
    fun numSubarrayProductLessThanK(nums: IntArray, k: Int): Int {

        var count = 0
        var product = 1
        var r = 0
        var l = 0

        for ( r in 0 until  nums.size){
            product *= nums[r]

            while (l <= r && product >= k){
                product /= nums[l]
                l +=1
            }
            count += (r - l + 1)
        }

        return count
    }

    // 724. Find Pivot Index
    fun pivotIndex(nums: IntArray): Int {

        var prefix = IntArray(nums.size)
        prefix[0] = nums[0]

        for (i in 1 until nums.size){
            prefix[i] = nums[i] + prefix[i-1]
        }

        var result = -1
        var n = prefix[nums.size-1]
        var suma = 0

        for (i in 0 until prefix.size){

            if (i == 0){
                if (n - prefix[i] == 0){
                    return i
                }
            } else {
                if (n - prefix[i] == prefix[i-1]){
                    return i
                }
            }
        }

        return result

    }

    // 791. Custom Sort String
    fun customSortString(order: String, s: String): String {

        var stringsad = "asdadasd"

        if (s.length == 0){
            return ""
        }

        if (order.length == 0){
            return s
        }

        val comparator : Comparator<Structure> = compareBy<Structure> { it.priority}.thenBy { it.index }.thenBy { it.char }
        var minHeap : PriorityQueue<Structure> = PriorityQueue(comparator)
        var hashTable = HashMap<Char, Int>()

        for (i in 0 until order.length){
            if (!(hashTable.containsKey(order[i]))){
                hashTable[order[i]] = i
            }
        }

        for (j in 0 until s.length){

            if (hashTable.containsKey(s[j])){

                var keyStructure = Structure(hashTable[s[j]]!!, j, s[j])
                minHeap.add(keyStructure)

            } else {
                var auxStructure = Structure(Int.MAX_VALUE, Int.MAX_VALUE, s[j])
            }
        }

        var result = StringBuilder()

        while (minHeap.isNotEmpty()){
            result.append(minHeap.poll().char)
        }
        return result.toString()

    }

    // 249. Group Shifted Strings (pass 25/26)
    fun groupStrings(strings: Array<String>): List<List<String>> {

        var res = ArrayList<List<String>>()
        var strs = HashMap<String, Int>()

        for (words in strings){ //asuming unique values a set will be ok // asuming different values a Map will be the solution
            if (strs.containsKey(words)){
                strs[words] = strs[words]!! +1
            } else {
                strs[words] = 1
            }
        }
        println("map: ${strs}")

        for (word in strings){

            if (strs.isEmpty()) {
                break
            }

            var aux = ArrayList<String>()
            if (strs.contains(word)){
                strs.remove(word)
                aux.add(word)
            }

            var strBld = word
            // shift right (26 call function)
            // for each shift check if the string is in the map

            println("for word: ${word}")
            for (i in 0 until 25){

                var newString = ""
                for (j in 0 until strBld.length){

                    var newChar = strBld[j].toInt()+1
                    if (newChar > 122){
                        newString += "a"
                    } else {
                        newString += newChar.toChar()
                    }
                    // check each letter in the position j at strBld
                    // get the ascii code and add +1 to get the right Shift for each letter
                    //
                }
                println("combination ${i} -> ${newString}")

                if (strs.isNotEmpty()){

                    if (strs.contains(newString)){
                        aux.add(newString)
                        strs.remove(newString)
                    }

                } else {
                    break
                }

                strBld = newString

            }


            // shift left (26 call function)
            // for each shift check if the string is in the map

            if (aux.isNotEmpty()){
                res.add(aux)
            }
        }

        return res

    }

    // 670. Maximum Swap
    fun maximumSwap(num: Int): Int {

        val aux = num.toString()
        val stringArray = ArrayList<Int>()

        for (i in 0 until aux.length){
            stringArray.add(aux[i].digitToInt())
        }

        var max_value = -1
        var max_valueIndex = -1
        var index_left = -1
        var index_right = -1

        for ( i in stringArray.size-1 downTo 0){

            if (stringArray[i] == max_value){
                continue
            }

            if (stringArray[i] > max_value){
                max_value = stringArray[i]
                max_valueIndex = i
            }

            if (stringArray[i] < max_value){
                index_left = i
                index_right = max_valueIndex
            }
        }

        if (index_left != -1){

            val valueAux = stringArray[index_left]
            stringArray[index_left] = stringArray[index_right]
            stringArray[index_right] = valueAux

        }

        var result = ""

        for (i in 0 until stringArray.size){
            result += stringArray[i].digitToChar()
        }

        return result.toInt()

    }

    // 1762. Buildings With an Ocean View
    fun findBuildings(heights: IntArray): IntArray {

        val tam = heights.size-1
        var maxHeight = heights[tam]
        val result = ArrayList<Int>()
        result.add(tam)
        for ( i in tam-1 downTo 0){

            if (heights[i] > maxHeight){
                result.add(i)
                maxHeight = heights[i]
            }

        }

        var res = IntArray(result.size)
        var j = 0
        for (i in result.size downTo  0){
            res[j] = result[i]
        }

        return res
    }

    // 636. Exclusive Time of Functions
    fun exclusiveTime(n: Int, logs: List<String>): IntArray {

        val stack = ArrayDeque<Int>()
        var prevTime = 0
        var result = IntArray(n)

        for (log in logs) {

            var comp = log.split(":")
            var index = comp[0].toInt()
            var mode = comp[1]
            var timeStamp = comp[2].toInt()

            if (mode == "start") {

                // time to push
                if (stack.size >= 1){
                    var lastIndex = stack.last()
                    result[lastIndex] +=  timeStamp - prevTime
                }
                stack.add(index)
                prevTime = timeStamp

            } else {
                // time to pop
                if (stack.size >= 1){
                    var lastIndex = stack.removeLast()
                    result[lastIndex] += (timeStamp - prevTime + 1)
                    prevTime = timeStamp + 1
                }

            }
        }

        return result
    }

    // 339. Nested List Weight Sum
    fun depthSum(nestedList: List<NestedInteger>): Int {

        var result = 0
        fun returnSumDepth(nestedList: List<NestedInteger>, depthIn: Int) {

            for (value in nestedList){
                if (value.isInteger()){
                    result += value.getInteger()!! * depthIn
                } else {
                    returnSumDepth(value.getList()!!, depthIn+1)
                }
            }
        }

        returnSumDepth(nestedList, 1)
        return result

    }

    // 50. Pow(x, n)
    fun myPow(x: Double, n: Int): Double {

        var result = 1.0
        var base = x
        var exp = n

        if (exp == 0){
            return 1.0
        }

        if (exp == Int.MIN_VALUE){

            if (base == 1.0 || base == -1.0){
                return 1.0
            } else {
                return 0.0
            }
        }

        if (exp < 0){
            base = 1/x
            exp *= -1
        }

        var product = base

        while (exp > 0){

            if (exp % 2 != 0){
                result = result * product
            }

            product *= product
            exp = exp/2
        }

        return result

    }

    // 498. Diagonal Traverse
    fun findDiagonalOrder(mat: Array<IntArray>): IntArray {

        // i+= 0 && j+= 1 (derecha)
        // i+= 1 && j+= 0 (down)

        // i-= 1 && j+= 1 (diagonal arriba) until i >=0 && j <= maxCol
        // i+= 1 && j-= 1 (diagonal abajo) until i <= maxRow && j >= 0

        var i = 0
        var j = 0
        val maxRow = mat.size
        val maxCol = mat[0].size
        val maxTam = maxRow * maxCol
        var index = 0

        //val res = IntArray(maxRow * maxCol)
        val res = IntArray(maxTam)
        var dirUp = true

        while (index < maxTam){
            if (dirUp) {

                while (i >= 0 && j < maxCol){
                    res[index] = mat[i][j]
                    j += 1
                    i -= 1
                    index += 1
                }
                dirUp = false
                // two cases if we get both boundaries
                if (j == maxCol) { // means we reach both boundaries because direction is UP
                    i += 2
                    j -= 1
                } else { // because direction is UP the other case is reach only row boundary
                    i += 1
                }

            } else {

                // two cases if we get both boundaries
                // if we get only col boundary

                while ( i < maxRow && j >= 0){
                    res[index] = mat[i][j]
                    j -= 1
                    i += 1
                    index += 1
                }

                dirUp = true

                if ( i == maxRow){
                    j += 2
                    i -= 1
                } else {
                    j+=1
                }
            }
        }
        return res
    }

    // 129. Sum Root to Leaf Numbers
    fun sumNumbers(root: TreeNode?): Int {

        if (root == null){
            return 0
        }
        var globalSum = 0

        fun dfs(root: TreeNode?, localSum: Int) {

            if (root == null){
                return
            }
            var local = localSum * 10 + root.value

            if (root.left == null && root.right == null){
                globalSum += local
                return
            }

            dfs(root.left, local)
            dfs(root.right, local)

        }

        dfs(root, 0)
        return globalSum

    }

    // 46. Permutations
    fun permute(nums: IntArray): List<List<Int>> {

        val result = ArrayList<ArrayList<Int>>()
        val local_res = ArrayList<Int>()
        val local_set = HashSet<Int>()

        fun backtracking(nums : IntArray, local_res : ArrayList<Int>, local_set : HashSet<Int>){

            println("local_res: ${local_res}")
            if (local_res.size == nums.size){
                result.add(ArrayList(local_res))
                println("so why is not here adding")
                return
            }

            for ( i in 0 until nums.size){

                if (local_set.contains(nums[i])){
                    continue
                }
                local_res.add(nums[i])
                local_set.add(nums[i])

                backtracking(nums, local_res, local_set)
                local_res.removeLast()
                local_set.remove(nums[i])

            }

        }

        backtracking(nums, local_res, local_set)
        println("result: ${result}")
        return result

    }

    // 1209. Remove All Adjacent Duplicates in String II
    fun removeDuplicatesa(s: String, k: Int): String {

        val stack = mutableListOf<Pair<Char, Int>>()

        for (char in s) {
            if (stack.isNotEmpty() && stack.last().first == char) {
                val count = stack.last().second + 1
                stack[stack.size - 1] = Pair(char, count)
                if (count == k) {
                    stack.removeAt(stack.size - 1)
                }
            } else {

                stack.add(Pair(char, 1))
            }

        }

        val result = StringBuilder()
        for ((char, count) in stack) {
            for (i in 0 until count) {
                result.append(char)
            }
        }
        return result.toString()
    }

    // 921. Minimum Add to Make Parentheses Valid
    fun minAddToMakeValid(s: String): Int {

        var opened = 0
        var closed = 0

        for (char in s){
            if (char == '('){
                opened += 1
            } else {
                if (opened > 0 ){
                    opened -= 1
                } else {
                    closed += 1
                }
            }
        }
        return opened + closed
    }

    // 1249. Minimum Remove to Make Valid Parentheses
    fun minRemoveToMakeValid(s: String): String {

        var stack = ArrayDeque<Int>()
        var str = StringBuilder(s)

        for (i in 0 until s.length){

            if (str[i] == '('){
                stack.add(i)
            } else {
                if (str[i] == ')'){
                    if (stack.size > 0){
                        stack.removeLast()
                    } else {
                        str[i] = '*'
                    }
                }
            }
        }

        while (stack.size > 0 ){
            str[stack.removeLast()] = '*'
        }

        var result = StringBuilder()
        for (i in 0 until str.length){

            if (str[i] != '*'){
                result.append(str[i])
            }

        }

        return result.toString()
    }

    // 76. Minimum Window Substring
    fun minWindow(s: String, t: String): String {

        if (t.length > s.length){
            return ""
        }

        if (t.length == s.length){

            if (t.toList().sorted() == s.toList().sorted()){
                return s
            }else{
                return ""
            }
        }

        if (t.length == 1){
            if (t in s){
                return t
            }
            return ""
        }

        var map = HashMap<Char, Int>()
        for (char in t){
            map[char] = map.getOrDefault(char, 0) + 1
        }

        println("map: ${map}")

        var left  = 0
        var right = 0
        var minLen = Int.MAX_VALUE
        var count =0
        var indexLeft = 0

        var windowMap = HashMap<Char, Int>()

        while (right < s.length){

            var currentChar = s[right]
            windowMap[currentChar] = windowMap.getOrDefault(currentChar, 0) + 1

            if (map.containsKey(currentChar) && map[currentChar]!! >= windowMap[currentChar]!!) {
                count += 1
            }

            while (count == t.length){
                if (right - left + 1 < minLen){
                    minLen = (right - left + 1)
                    indexLeft = left
                }

                var leftChar = s[left]
                windowMap[leftChar] = windowMap[leftChar]!! - 1

                if ( map.containsKey(leftChar) && map[leftChar]!! > windowMap[leftChar]!! ){
                    count -= 1
                }
                left += 1
            }
            right += 1

        }

        if (minLen == Int.MAX_VALUE) return ""

        return s.slice(indexLeft .. indexLeft + minLen-1)

    }

    // 1216. Valid Palindrome III
    fun isValidPalindrome(s: String, k: Int): Boolean {

        if (s.length <= 1){
            return true
        }

        if (k > s.length){
            return false
        }

        var memo = HashMap<Triple<Int, Int, Int>, Boolean>()

        fun dfs(s: String, left: Int, right: Int, k:Int): Boolean{
            if (left >= right){
                if (k >= 0){
                    return true
                } else {
                    return false
                }
            }

            if (right < 0 || left >= s.length || k < 0){
                return false
            }

            var key = Triple(left, right, k)

            if (memo.containsKey(key)){
                return memo[key]!!
            }

            if (s[left] == s[right]){
                var result = dfs(s, left +1, right-1, k)
                memo[key] = result
                return result
            } else {
                var result = dfs(s, left+1, right, k-1) || dfs(s, left, right-1, k-1)
                memo[key] = result
                return result
            }

        }
        return dfs(s,0,s.length-1, k)
    }

    // 300. Longest Increasing Subsequence
    fun lengthOfLIS(nums: IntArray): Int {

        var result = ArrayList<Int>()
        var set = HashSet<Int>()
        set.add(nums[0])
        result.add(nums[0])

        for (i in 1 until nums.size) {

            if (nums[i] > result.last()) {
                result.add(nums[i])
                set.add(nums[i])
                println("result: ${result}")
            } else {

                // apply binary Search to find a right place to place the current num
                println("set: ${set}")
                var l = 0
                var r = result.size - 1
                var mid = (l + r) // 2
                var target = nums[i]

                if (!(set.contains(nums[i]))) {
                    while (l < r) {
                        mid = (l + r)/2
                        if (result[mid] > target) {
                            r = mid
                        } else {
                            l = mid + 1
                        }
                    }

                    set.remove(result[r])
                    set.add(nums[i])
                    result[r] = nums[i]

                }
                println("result: ${result}")
            }

        }
        return result.size
    }

    // 354. Russian Doll Envelopes
    fun maxEnvelopes(envelopes: Array<IntArray>): Int {


        envelopes.sortWith(compareBy<IntArray> { it[0] }.thenByDescending { it[1] })

        var height = ArrayList<Int>()
        height.add(envelopes[0][1])
        var setHeight = HashSet<Int>()
        setHeight.add(envelopes[0][1])

        // [[5,4],[6,4],[6,7],[2,3]]
        // [2,3], [5,4], [6,4], [6,7]
        // h = [3]

        // [3,4,7]

        for (i in 1 until envelopes.size){
            var currenHeight = envelopes[i][1]
            if (currenHeight > height.last()){
                height.add(currenHeight)
                setHeight.add(currenHeight)
            } else {
                // need to find a way

                var l = 0
                var r = height.size - 1
                var mid = -1

                if (!(setHeight.contains(currenHeight))){
                    while (l < r){
                        mid = (l + r)/ 2

                        if (height[mid] > currenHeight){
                            r = mid
                        } else {
                            l = mid + 1
                        }
                    }

                    setHeight.remove(height[l])
                    setHeight.add(currenHeight)
                    height[l] = currenHeight
                }

            }
        }

        return height.size

    }

    // 480. Sliding Window Median
    fun medianSlidingWindow(nums: IntArray, k: Int): DoubleArray {

        var minHeap = PriorityQueue<Int>()
        var maxHeap = PriorityQueue<Int>(Comparator.reverseOrder())
        var result = ArrayList<Double>()
        var isEven = false
        var index = 0

        if (k % 2 == 0){
            isEven = true
        }

        for (i in 0 until nums.size){
            if (i >= k) {
                var dropValue = nums[index]

                if (minHeap.contains(dropValue)){
                    minHeap.remove(dropValue)
                } else {
                    if (maxHeap.contains(dropValue)){
                        maxHeap.remove(dropValue)
                    }
                }
                index += 1
            }

            minHeap.add(nums[i])
            maxHeap.add(minHeap.poll())

            if (maxHeap.size > minHeap.size){
                minHeap.add(maxHeap.poll())
            }

            if (i >= k-1){
                if (isEven){
                    var auxResult = (minHeap.first().toDouble() + maxHeap.first().toDouble())/ 2.0
                    result.add(auxResult)
                } else {
                    var auxResult = minHeap.first().toDouble()
                    result.add(auxResult)
                }
            }
        }
        return result.toDoubleArray()
    }

    // 329. Longest Increasing Path in a Matrix
    fun longestIncreasingPath(matrix: Array<IntArray>): Int {

        var maxLen = 1
        var map = HashMap<Triple<Int, Int, Int>, Int>()
        fun dfs(i: Int, j: Int, map : HashMap<Triple<Int, Int, Int>, Int>, prev: Int): Int{

            if (i < 0 || i >= matrix.size || j < 0 || j >= matrix[0].size){
                return 0
            }

            var key = Triple(i,j, prev)
            if (map.containsKey(key)) {
                return map[key]!!
            }

            if ( matrix[i][j] > prev){

                var result = 1 + maxOf(dfs(i,j+1, map, matrix[i][j]), dfs(i,j-1, map, matrix[i][j]), dfs(i+1, j, map, matrix[i][j]), dfs(i-1, j, map, matrix[i][j]) )
                map[key] = result
                return result
            } else {
                return 0
            }
        }

        for ( i in 0 until matrix.size){
            for (j in 0 until matrix[0].size){
                var prev = -1
                var localResult = dfs(i, j, map, prev)
                maxLen = Math.max(maxLen, localResult)
                //localResult = 0
            }
        }
        return maxLen
    }

    // 42. Trapping Rain Water
    fun trap(height: IntArray): Int {

        var left_max = 0
        var right_max = 0
        var leftArray = IntArray(height.size)
        var rightArray = IntArray(height.size)

        var trapResult = IntArray(height.size)

        for (i in 0 until height.size){
            leftArray[i]= (left_max)
            left_max = max(height[i], left_max)
        }

        for (j in height.size-1 downTo 0){
            rightArray[j] = (right_max)
            right_max = max(height[j], right_max)
        }

        for (i in 0 until height.size){
            var vol = min(leftArray[i], rightArray[i]) - height[i]
            if (vol > 0){
                trapResult[i] = vol
            }
        }

        print("Left: ")
        leftArray.forEach { print("${it} ") }
        println()
        print("Righ: ")
        rightArray.forEach { print("${it} ") }
        println()
        print("heig: ")
        height.forEach { print("${it} ") }
        println()
        print("Trap: ")
        trapResult.forEach { print("${it} ") }
        println()
        println("MaxLeft: ${left_max}")
        println("MaxRight: ${right_max}")



        return trapResult.sum()

    }

    // 827. Making A Large Island
    fun largestIsland(grid: Array<IntArray>): Int {

        fun dfs(i : Int, j : Int, color: Int): Int {

            if (i < 0 || j < 0 || i >= grid.size || j >= grid[0].size ){
                return 0
            }

            if (grid[i][j] == 1){
                grid[i][j] = color
                return 1 + dfs(i+1, j, color) + dfs(i-1, j, color) + dfs(i, j+1, color) + dfs(i, j-1, color)
            } else {
                return  0
            }

        }

        var maxLen = 1
        var color = 1
        var colored = HashMap<Int, Int>() // color, value
        var hasZeros = false
        for (i in 0 until grid.size){
            for (j in 0 until grid[0].size){

                var value = 0
                if (grid[i][j] == 1){
                    color += 1
                    value = dfs(i, j, color)
                    colored[color] = value
                }

                if (grid[i][j] == 0){
                    hasZeros = true
                }
            }
        }

        if (!hasZeros){
            return grid.size * grid[0].size
        }

        for (list in grid){

            print("[")
            list.forEach { print("${it},") }
            print("]")
        }
        println()
        println("colored: ${colored}")
        println()

        fun dfsL(i:Int, j:Int, colored: HashMap<Int, Int>, visited: HashSet<Int>): Int {

            if (i < 0 || j < 0 || i >= grid.size || j >= grid[0].size ){
                return 0
            }

            if (visited.contains(grid[i][j])){
                return 0
            }

            visited.add(grid[i][j])

            if (colored.containsKey(grid[i][j])){
                println("for: (${i},${j}) value is: ${colored[grid[i][j]]!!}")
                return colored[grid[i][j]]!!
            } else {
                return 0
            }
        }


        for (i in 0 until grid.size){
            for (j in 0 until grid[0].size){

                var local_max : Int
                var visited = HashSet<Int>()
                if (grid[i][j] == 0){
                    local_max = 1 + dfsL(i+1,j, colored, visited) + dfsL(i-1,j, colored, visited) + dfsL(i,j+1, colored, visited) + dfsL(i,j-1, colored, visited)
                    maxLen = Math.max(local_max, maxLen)
                }
            }
        }

        return maxLen
    }

    // valid number
    fun isNumber(s: String): Boolean {

        // Recap:
        // if the string number has exponential
            // if yes? I will split in two part
            // exponential part must be integer
            // base part must be an integer or decimal
            // if these two can be convert by built-in function are good string accordindly
        // else
            // check if a decimal or integer
            // if it can be convert to decimal or integer

        // return true


        var hasExponential = false
        var isE = false
        var ise = false

        if (s.contains("e") || s.contains("E")){ // O(n)
            hasExponential = true
        }

        if (hasExponential){

            var basePart = ""
            var expoPart = ""
            if (s.contains("e")) {
                var arraList = s.split("e")

                if (arraList.size != 2){
                    return false
                }
                basePart += arraList[0]
                expoPart += arraList[1]


            } else {
                var arrayList = s.split("E")

                if (arrayList.size != 2){
                    return false
                }
                basePart += arrayList[0]
                expoPart += arrayList[1]
            }

            // can I convert expoPart to Int?
            // can I convert basePart to Decimal?

            try {
                basePart.toBigDecimal() // O(N)
            }catch (e : Exception){
                println("${e.message}")
                return false
            }


            try {
                expoPart.toBigInteger() // O(N)
            } catch (e : Exception){
                println(" ${e.message}")
                return false
            }


        } else {

            if (s.contains(".")) {
                try {
                    var result = s.toBigDecimal()
                    return true
                } catch (e: Exception){
                    println("result can not be convert to a Double: ${e.message}")
                    return false
                }
            } else {

                try {
                    var result = s.toBigInteger()
                    return true
                } catch (e: Exception){
                    println("result can not be convert to a Int: ${e.message}")
                    return false
                }

            }
        }

        return true
    }



}

data class Structure(var priority : Int, var index: Int, var char: Char )

fun main(){


    val testClass = Meta()
    var lower = -1
    var upper = 0
    var sentence = "I speak Goat Latin"
    var logs = arrayListOf("0:start:0","0:start:2","0:end:5","1:start:6","1:end:6","0:end:7")
    var nums = arrayOf(intArrayOf(4,5), intArrayOf(4,6), intArrayOf(6,7), intArrayOf(2,3), intArrayOf(1,1)) //[[4,5],[4,6],[6,7],[2,3],[1,1]]
    var input = "AAB"
    var order = "cba"
    var s = "ADOBECODEBANC"
    var t = "ABC"
    var height = intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1)
    var k = 2
    //var matrix = arrayOf(intArrayOf(1,1,0,1), intArrayOf(0,0,1,1), intArrayOf(1,0,0,0), intArrayOf(1,0,0,1)) // [[1,1],[1,0]]
    var matrix = arrayOf(intArrayOf(1,1), intArrayOf(1,1))
    var number = "1E9"
    println(testClass.isNumber(number))

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

// 528. Random Pick with Weight
class RandomPickWeight(w: IntArray) {

    var prefix = IntArray(w.size)
    var totalSum = 0

    init {
        var suma = 0

        for (i in 0 until w.size){
            suma += w[i]
            prefix[i] = suma
        }
        totalSum = suma
    }

    fun pickIndex(): Int {

        var target = totalSum * Math.random() // [1,3,7] || target = 4
        var i = 0

        for (i in 0 until prefix.size){
            if (target < prefix[i])
                return i
        }
        return i - 1
    }

}


class NestedInteger(val single: Int?, val nestedList: List<NestedInteger>){

    fun isInteger(): Boolean {

        if (this.single != null && this.nestedList.isEmpty()){
            return true
        } else {
            return false
        }
    }

    fun getInteger() : Int? {
        if (this.nestedList.isEmpty() && this.single != null){
            return this.single
        }
        return null
    }

    fun getList() : List<NestedInteger>? {

        if (this.single == null && this.nestedList.isNotEmpty()){
            return this.nestedList
        }
        return null
    }

}