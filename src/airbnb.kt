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
}

fun main(args : Array<String>){

    val testClass = Solution()
    var nums = arrayOf(intArrayOf(0,0,0), intArrayOf(1,1,0), intArrayOf(1,1,0))
    println(testClass.shortestPathBinaryMatrix(nums))

}