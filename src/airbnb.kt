import java.util.PriorityQueue
import kotlin.math.ceil

class TreeNode(var value : Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution {

    // 1554. Strings Differ by One Character
    fun differByOne(dict: Array<String>): Boolean {

        for (i in 0.. dict.size-1){

            var word = dict[i]
            for (j in 0.. dict.size-1){

                var auxWord = dict[j]
                var cont = 0
                for (k in 0.. word.length-1){

                    if (word[k] != auxWord[k]){
                        cont+= 1
                    }

                    if (cont > 1){
                        break
                    }
                }

                if ((cont == 1) && (word.length) > 1){
                    return true
                }
            }

        }

        return false
    }

    // 219. Contains Duplicate II
    fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
        var findSet = HashSet<Int>()

        for (i in 0 .. nums.size-1){

            if (findSet.contains(nums[i])) return true

            findSet.add(nums[i])

            if (findSet.size > k){
                findSet.remove(nums[i-k])
            }

        }
        return false
    }

    // 415. Add Strings
    fun addStrings(num1: String, num2: String): String {

        var i = num1.length - 1
        var j = num2.length - 1
        var carry = 0
        var result = ""

        while ((i >= 0) && (j >= 0)) {

            println("value1: ${num1.get(i).digitToInt()}")
            println("value2: ${num2.get(j).digitToInt()}")
            var value = num1.get(i).digitToInt() + num2.get(j).digitToInt() + carry


            if (value >= 10) {
                value = value % 10
                carry = 1
                // 14 % 10 = 1.4
            }else{
                carry = 0
            }
            result = value.toString() + result
            println("result: ${result}")
            i -= 1
            j -= 1
        }

        while (i >= 0) {
            var value = num1.get(i).digitToInt() + carry
            if (value >= 10) {
                value = value % 10
                carry = 1
            }else{
                carry = 0
            }
            i -= 1
            result = value.toString() + result

        }

        while (j >= 0) {

            var value = num2.get(j).digitToInt() + carry
            if (value >= 10) {
                value = value % 10
                carry = 1
            }else{
                carry =0
            }
            j -= 1
            result = value.toString() + result

        }


        if (carry >= 1) {

            result = carry.toString() + result

        }

        return result
    }

    // 136. Single Number
    fun singleNumber(nums: IntArray): Int {

        var tableMap = HashSet<Int>()

        for (i in 0 .. nums.size-1){
            if (tableMap.contains(nums[i])){
                tableMap.remove(nums[i])
            }else{
                tableMap.add(nums[i])
            }
        }
        println(tableMap)

        var result = tableMap.toList()

        return result[0]

    }

    // 160. Intersection of Two Linked Lists
    fun getIntersectionNode(headA:ListNode?, headB:ListNode?):ListNode? {

        var findInter = HashSet<ListNode>()

        var auxA = headA
        while (auxA != null){
            findInter.add(auxA)
            auxA = auxA.next
        }

        var auxB = headB
        while(auxB != null){

            if (findInter.contains(auxB)){
                return auxB
            }
            auxB = auxB.next
        }

        return null

    }

    // 1091. Shortest Path in Binary Matrix
    fun shortestPathBinaryMatrix(grid: Array<IntArray>): Int {

        val n = grid.size-1
        if (grid[0][0] == 1 || grid[n][n] == 1) return -1

        var visited = HashSet<Pair<Int, Int>>() // to check if there is a visited
        var directions = ArrayList<Pair<Int, Int>>()

        directions.add(Pair(-1,-1))
        directions.add(Pair(-1,0))
        directions.add(Pair(-1,1))
        directions.add(Pair(0,1))
        directions.add(Pair(0,-1))
        directions.add(Pair(1,-1))
        directions.add(Pair(1,0))
        directions.add(Pair(1,1))

        var queue = ArrayDeque<Triple<Int, Int, Int>>()
        queue.add(Triple(0, 0, 1))

        while (queue.isNotEmpty()){

            var current = queue.removeFirst()
            var auxRow = current.first
            var auxCol = current.second
            var distance = current.third

            for (direction in directions){

                val newRow = auxRow + direction.first
                val newCol = auxCol + direction.second

                if ((newRow == n) && (newCol == n)) return distance

                if (newRow >= 0 && newRow <= n && newCol >= 0 && newCol <= n && grid[newRow][newCol] == 0 && !visited.contains(Pair(newRow,newCol)) ){
                    queue.add(Triple(newRow, newCol, distance+1))
                    visited.add(Pair(newRow,newCol))
                }

            }

        }
        return -1

    }

    // 1257. Smallest Common Region
    fun findSmallestRegion(regions: List<List<String>>, region1: String, region2: String): String {

        val parentsChild = HashMap<String, String>()
        val onlyParents = HashSet<String>()

        // Here only get parents
        for (region in regions){
            onlyParents.add(region[0])
            for (i in 1 .. region.size-1){
                parentsChild[region[i]] = region[0]
            }
        }
        var parentsOfRegion1 = ArrayDeque<String>()
        var parents0fRegion2 = ArrayDeque<String>()

        if (onlyParents.contains(region1)) parentsOfRegion1.addFirst(region1)
        if (onlyParents.contains(region2)) parents0fRegion2.addFirst(region2)

        println(parentsChild)

        // get Path of region1
        var auxRegion1 = region1
        while (!auxRegion1.equals("")){
            var aux = parentsChild[auxRegion1]
            if (aux != null){
                parentsOfRegion1.addFirst(aux)
                auxRegion1 = aux
            } else {
                auxRegion1 = ""
            }

        }

        // get Path of region2
        //println(parentsOfRegion1)
        var auxRegion2 = region2
        while (!auxRegion2.equals("")){

            var aux2 = parentsChild[auxRegion2]
            if (aux2 != null){
                parents0fRegion2.addFirst(aux2)
                auxRegion2 = aux2
            } else {
                auxRegion2 = ""
            }

        }

        println(parentsOfRegion1)
        println(parents0fRegion2)

        var i = parentsOfRegion1.size-1
        var j = parents0fRegion2.size-1

        while (i >= 0 && j >= 0){

            if (parentsOfRegion1[i].equals(parents0fRegion2[j])){
                return parentsOfRegion1[i]
            }

            if (i == j){
                i -= 1
                j -= 1
                parentsOfRegion1.removeLast()
                parents0fRegion2.removeLast()

            } else if (i > j){
                i -= 1
                parentsOfRegion1.removeLast()
            } else {
                j -= 1
                parents0fRegion2.removeLast()
            }

            println("region1: ${parentsOfRegion1}")
            println("region2: ${parents0fRegion2}")

        }

        return ""
    }

    // 713. Subarray Product Less Than K
    fun numSubarrayProductLessThanK(nums: IntArray, k: Int): Int {

        var i = 0
        var result = ArrayList<List<Int>>()

        while (i < nums.size){
            var j = i
            var kk = i
            var product = 1

            while ((j < nums.size) && (product < k)){
                product *= nums[j]
                j += 1
            }

            var aux = ArrayList<Int>()
            var productReverse = 1
            while (kk < j){

                productReverse *= nums[kk]
                if (productReverse < k){
                    aux.add(nums[kk])
                    var local = ArrayList<Int>(aux)
                    println("auxReverse: ${aux}")
                    result.add(local)
                }
                kk += 1
            }
            i+=1

            println("result: ${result}")
        }

        return result.size
    }

    // 3076. Shortest Uncommon Substring in an Array
    fun shortestSubstrings(arr: Array<String>): Array<String> {

        var result = ArrayList<String>()
        var data = ArrayList<HashSet<String>>()

        for (i in 0 .. arr.size-1) {

            var currentString = arr[i]
            var currentSet = HashSet<String>()

            var aux = ""
            for (j in 0..currentString.length - 1) {

                aux += currentString[j]
                if (!currentSet.contains(aux)) currentSet.add(aux)

                var anotherAux = ""
                for (k in j downTo 0){
                    anotherAux = currentString[k] + anotherAux
                    if (!currentSet.contains(anotherAux)) currentSet.add(anotherAux)
                }
            }

            data.add(currentSet)

        }

        for (j in 0 .. data.size-1) {
            var auxSet = data.removeFirst()
            var completeSet = HashSet<String>()

            val pairComparator: Comparator<Pair<Int, String>> = compareBy<Pair<Int, String>> { it.first }
                .thenBy { it.second }

            var currentQueue = PriorityQueue(pairComparator)

            for (set in data) {
                completeSet.addAll(set)
            }

            for (word in auxSet) {
                if (completeSet.contains(word)) {
                    println("${word} exist in ${completeSet}")
                    continue
                }
                var pair = Pair(word.length, word)
                currentQueue.add(pair)

            }

            data.add(auxSet)
            if (currentQueue.size > 0) result.add(currentQueue.poll().second) else result.add("")

        }

        return result.toTypedArray()
    }

    // 76. Minimum Window Substring
    fun minWindow(s: String, t: String): String {

        if (t.length > s.length){
            return ""
        }

        val map = HashMap<Char, Int>()

        for (i in 0 .. t.length-1){

            if (map.containsKey(t.get(i))){
                map[t.get(i)] = map[t.get(i)]!! + 1
            } else {
                map[t.get(i)] = 1
            }
        }

        var left = 0
        var right = 0
        var minWindows = Int.MAX_VALUE
        var totalCounts = 0
        var indexL = 0
        var indexR = 0

        while (right < s.length){

         //   println("left   -> ${left}")
         //   println("right  -> ${right}")
         //   println("HashMap right: ${map}")

            if (map.containsKey(s.get(right))){
                totalCounts++
            }

            while (totalCounts == t.length && left < right){
          //      println("HashMap left: ${map}")
                if (right - left < minWindows){
                    minWindows = (right - left)
                    indexL = left
                    indexR = right
                }
          //      println("minWindows: ${minWindows}")

                if (map.containsKey(s.get(left))){
                    totalCounts--
                }
                left += 1
            }

            right += 1
        }
       // println("tam: ${minWindows}")
        var result = ""
        while (indexL <= indexR && (left != 0 && right !=0)){

            if (indexL < s.length){
                result += s[indexL]
            }
            indexL += 1
        }

        return result

    }

}

fun main(args : Array<String>){

    val testClass = Solution()
    val regions = arrayListOf(
        arrayListOf("United States", "California", "Texas"),
        arrayListOf("California", "Los Angeles", "San Francisco"),
        arrayListOf("South America", "Brazil", "Argentina"),
        arrayListOf("North America", "United States", "Canada"),
        arrayListOf("Earth", "North America", "South America")
        )

    val region1 = "Los Angeles"
    val region2 = "Brazil"
    val entry = arrayOf("wyamwxxka","psvigdfpolwejwueoh","ecgilythpelf","jfmxhodpr","zcnzaxytzyagp","tvzmobdtowfjkorbn","ceehiyvowilrvdc","cugfoaatoue","yo","y","gavmsmelgljfagem","enhrtjiblhuajxjfnnfr","dcnoo","qhmji","umgycqypfhphterhhz","ilavch","qye","svxfmcfmjpd","qqpmhrkazgmq","dsqxpsltlmpufz","xyzhwzshhpyrghqoj","ycqbbqqex","mfmytfmeffwae","tmriihekvotwfezsmxh","gplhakypjfrjvbfkkwko","btjcpacaluef","sdxqiorsobcayvkvher","isgrgdrxlkhzwkeyqwo","bvoqphfxyetsjm","p","drvq","zivuaujjd","cvyjqisoxuqfpvg","xqdrncfplbqgaqg","jrpovinaliwavqk","lroeefsfisvcpyj","ocxxsjmufmici","dxlgodaatmnfpwfqdqjg","yb","taxjzbzngkqy","wwjejxyhjgohgnov","npetn","ifybg","c","auqslzx","lxu","sdxtkhrrmwr","glirwi","hqlpdagz","qslurgmdevkfrmu","zkrb","rvs","llotpivntrq","juwnbrrndwpsgalj","cx","orikxdsigcnolwtz","yle","vdkgmmilkttmti","gdsd","dtlhdesmvuomhsgti","tlydxtsxa","euamgnvbjtgnkd","bcmkv","jkntrswe","dbqpybgwhfwkprqtsyyr")
    val s = "a"
    val t = "b"
    print(testClass.minWindow(s, t))

    // "ADOBECODEBANC",
    // t = "ABC"

}

    // 251. Flatten 2D Vector
class Vector2D(vec: Array<IntArray>) {

    var internalArray = ArrayDeque<Int>()
    init {

        for (array in vec){
            for (i in 0 .. array.size-1){
                internalArray.add(array[i])
            }
        }

    }

    fun printingArray(){

        for (i in 0.. internalArray.size-1){
            println(internalArray[i])
        }
    }

    fun next(): Int {

        if (internalArray.size > 0){
            return internalArray.removeFirst()
        }
        return -1
    }

    fun hasNext(): Boolean {

        if (internalArray.size > 0){
            return true
        } else {
            return false
        }
    }

}
