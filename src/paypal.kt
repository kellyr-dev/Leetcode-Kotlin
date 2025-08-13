
import kotlin.collections.HashSet

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
            return dfs(i+1, j, board, index+1, memo) ||  dfs(i, j+1, board, index+1, memo) || dfs(i-1, j, board, index+1, memo) || dfs(i, j-1, board, index+1, memo)
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
}


fun main(){

    val testClass = Paypal()
    val words = arrayOf("cat","bt","hat","tree")
    val chars = "atach"
    /*
    board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"

     */
    val board = arrayOf(charArrayOf('A', 'B', 'C', 'E'), charArrayOf('S', 'F', 'C', 'S'), charArrayOf(
        'A', 'D', 'E', 'E'
    ))
    val word = "ABCB"

    println(testClass.exist(board, word))

}