import java.lang.Math.max


class Amazon {

    // 3. Longest Substring Without Repeating Characters
    fun lengthOfLongestSubstring(s: String): Int {

        val exists = HashSet<Char>()

        var l = 0
        var r = 0
        var maxLength = 0
        // a a b a a b ! b b
        // ^
        //   ^

        while (r < s.length){

            if (exists.contains(s[r])){
                // reducing windows
                while (l < r){
                    if (s[l] != s[r]){
                        println("Set Removing: ${exists}")
                        exists.remove(s[l])
                        l++
                    }else{
                        l++
                        break
                    }
                }
            } else {
                exists.add(s[r])
                maxLength = max(maxLength, exists.size)
                println("Set: ${exists}")
            }

            r++
        }

        return maxLength
    }


}

fun main(args : Array<String>){


    val AmznTEst = Amazon()
    var s = "aabaab!bb"

    println(AmznTEst.lengthOfLongestSubstring(s))

}