
import java.util.Collections
import java.util.PriorityQueue
import kotlin.collections.HashSet
import kotlin.math.abs

class Paypal(){

    // 1160. Find Words That Can Be Formed by Characters
    fun countCharacters(words: Array<String>, chars: String): Int {
        val map = HashMap<Char, Int>()

        for (j in 0 until chars.length){

            if (map.containsKey(chars[j])){
                map[chars[j]] = map[chars[j]]!! + 1
            } else {
                map[chars[j]] = 1
            }
        }

        var ans = 0

        fun checkIfcan(word: String, map: HashMap<Char, Int>): Boolean{
            val auxMap = HashMap(map)

            for (i in 0 until word.length){
                if (auxMap.containsKey(word[i])){
                    if (auxMap[word[i]]!! >= 1){
                        auxMap[word[i]] = auxMap[word[i]]!! - 1
                    }else {
                        return false
                    }
                } else {
                    return false
                }
            }

            return true
        }

        for (word in words){
            if (checkIfcan(word, map)){
                ans += word.length
            }
        }
        return ans
    }

    // 79. Word Search
    fun exist(board: Array<CharArray>, word: String): Boolean {

        fun dfs(i: Int, j:Int, board: Array<CharArray>, index: Int, memo: HashSet<Pair<Int,Int>>) : Boolean {

            if (index >= word.length){
                return true
            }

            if (i >= board.size || i < 0 || j >= board[0].size || j < 0 || board[i][j] != word[index] || memo.contains(Pair(i,j))){
                return false
            }

            memo.add(Pair(i,j))
            var result = dfs(i+1, j, board, index+1, memo) ||  dfs(i, j+1, board, index+1, memo) || dfs(i-1, j, board, index+1, memo) || dfs(i, j-1, board, index+1, memo)
            memo.remove(Pair(i,j))
            return result
                // down by 1
                // right by 1
                // up by 1
                // left by 1
        }

        for (i in 0 until board.size){
            for (j in 0 until board[0].size){

                val memo = HashSet<Pair<Int, Int>>()
                if (board[i][j] == word[0]){

                    if (dfs(i,j, board, 0, memo)){
                        return true
                    }
                    // call DFS
                    // if DFS return true then return true
                }
            }
        }
        return false

        // if not true return false


    }

    // 6. Zigzag Conversion
    fun convert(s: String, numRows: Int): String {

        val list = ArrayList<ArrayList<Char>>()
        for (k in 0 until numRows){
            list.add(arrayListOf())
        }

        var i = 0
        while (i < s.length){

            //go down
            var j = 0
            while (j < numRows && i < s.length) {
                list[j].add(s[i])
                i+= 1
                j+= 1
            }

            //go up
            var up = numRows - 1 - 1
            while (up > 0 && i < s.length){

                list[up].add(s[i])
                up -= 1
                i += 1
            }

        }

        var res = ""
        for (smallist in list){

            for (kk in 0 until smallist.size){
                res += smallist[kk]
            }
        }
        println("list: ${list}")

        return res

    }

    // 49. Group Anagrams
    fun groupAnagrams(strs: Array<String>): List<List<String>> {


        val map = HashMap<String, ArrayList<String>>()

        for (word in strs){
            val wordSorted = word.toList().sorted()

            var auxWord = ""
            for (c in wordSorted){
                auxWord += c
            }

            if (map.containsKey(auxWord)){
                map[auxWord]!!.add(word)
            } else {
                map[auxWord] = arrayListOf(word)
            }
        }

      //  println("map: ${map}")

        val res = ArrayList<List<String>>()
        for ( (key, value) in map){
            res.add(value)
        }

        return res
    }

}


fun main(){

    val testClass = Paypal()
    val words = arrayOf("cat","bt","hat","tree")
    val chars = "atach"
    val board = arrayOf(charArrayOf('A', 'B', 'C', 'E'), charArrayOf('S', 'F', 'E', 'S'), charArrayOf('A', 'D', 'E', 'E'))
    val word = "PAYPALISHIRING"
    val numRows = 3
    val string = "PAYPALISHIRING"
    val strs = "dcab"
    val pairs = arrayListOf(listOf(0,3), listOf(1,2), listOf(0,2))
    val events = arrayOf(intArrayOf(1,4),intArrayOf(4,4), intArrayOf(2,2), intArrayOf(3,4), intArrayOf(1,1))
    val nums = intArrayOf(1,-2,2,1,0)
    val target = 1

}