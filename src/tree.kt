
class TreeNode(var value : Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}


class TreeLeetcode {

    internal fun printingTre(root : TreeNode?){

        if (root == null){
            return
        }
        print("${root.value}->")
        printingTre(root.left)
        printingTre(root.right)
    }

    // 104. Maximum Depth of Binary Tree
    fun maxDepth(root: TreeNode?): Int {

        if (root == null){
            return 0
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right))
    }

}

fun main(args : Array<String>){

    val testClass = TreeLeetcode()

    var root = TreeNode(3)
    root.left = TreeNode(9)
    root.right = TreeNode(20)
    root.left!!.left = null
    root.left!!.right = null
    root.right!!.left = TreeNode(15)
    root.right!!.right = TreeNode(7)

    testClass.printingTre(root)
    println()
    println(testClass.maxDepth(root))

}