
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

    // 
}


fun main(){

    val testClass = Paypal()
    val words = arrayOf("cat","bt","hat","tree")
    val chars = "atach"
    println(testClass.countCharacters(words, chars))

}