@file:Suppress("PackageDirectoryMismatch", "MemberVisibilityCanBePrivate")

package kotlin_for_cp.learn

/* ------------------ START SUBMISSION ------------------ */
import java.io.*
import java.util.*
import kotlin.collections.ArrayList


private fun solve() {
    println(tabGridTraveller(4,3))
    println(gridTraveller(4,3))
}

fun main() {
    setUpIO()
    var tc = 1
    // TODO: no tc input for single test
    tc = i.int()
    flush { for (i in 0 until tc) solve() }
}

fun tabGridTraveller(row: Int, col: Int) : Int {
    val grid = MutableList(row + 1) { MutableList(col + 1) { 0 } }
    grid[1][1] = 1

    for (i in 1..row) {
        for (j in 1..col) {
            if (j + 1 <= col) grid[i][j+1] += grid[i][j]
            if (i +1 <= row) grid[i+1][j] += grid[i][j]
        }
    }

    return grid[row][col]
}

fun tabFib(n : Int) : Long{
    val fib = MutableList(n+1){0L}
    fib[0] = 0
    fib[1] = 1
    for (i in 2..n){
        fib[i] = fib[i-1] + fib[i-2]
    }
    return fib[n]
}

fun allConstruct(
    target: String, wordBank: Array<String>, memo: MutableMap<String, MutableList<MutableList<String>>>
    = mutableMapOf()
): MutableList<MutableList<String>> {
    if (target == "") return mutableListOf(mutableListOf())

    val total = mutableListOf<MutableList<String>>()

    for (word in wordBank) {
        if (target.startsWith(word)) {
            val suffix = target.substring(word.length)
            val suffixes = allConstruct(suffix, wordBank)
            val result = suffixes.map {
                mutableListOf(word) + it
            }
            result.map {
                total.add(it as MutableList<String>)
            }
        }
    }

    memo[target] = total
    return total
}

fun countConstruct(target: String, wordBank: Array<String>, memo: MutableMap<String, Int?> = mutableMapOf()): Int? {
    if (target in memo) return memo[target]
    if (target == "") return 1

    var totalRes: Int = 0

    for (word in wordBank) {
        if (target.indexOf(word) == 0) {
            val suffix = target.slice(word.length until target.length)
            totalRes += countConstruct(suffix, wordBank, memo) ?: 0
        }
    }

    memo[target] = totalRes
    return totalRes
}

fun canConstruct(
    target: String,
    wordBank: Array<String>,
    memo: MutableMap<String, Boolean> = mutableMapOf()
): Boolean? {
    if (target in memo) return memo[target]
    if (target == "") return true

    for (word in wordBank) {
        if (target.indexOf(word, ignoreCase = true) == 0) {
            val suffix = target.slice(word.length until target.length)
            if (canConstruct(suffix, wordBank, memo) != null && canConstruct(suffix, wordBank, memo) == true) {
                memo[target] = true
                return true
            }
        }
    }

    memo[target] = false
    return false
}

fun bestSum(targetSum: Int, numbers: IntArray, memo: MutableMap<Int, Array<Int>?> = mutableMapOf()): Array<Int>? {
    if (targetSum in memo) return memo[targetSum]
    if (numbers.isEmpty()) return null
    if (targetSum == 0) return emptyArray()
    if (targetSum < 0) return null

    var shortestArr: Array<Int>? = null

    for (num in numbers) {
        val res = targetSum - num
        var result = bestSum(res, numbers, memo)
        result?.let {
            result = it + arrayOf(num)
        }
        if (shortestArr.isNullOrEmpty() || !result.isNullOrEmpty() && result!!.size < shortestArr.size) {
            shortestArr = result
        }
    }

    memo[targetSum] = shortestArr
    return shortestArr
}

fun howSum(targetSum: Int, numbers: IntArray, memo: MutableMap<Int, Array<Int>?> = mutableMapOf()): Array<Int>? {
    if (targetSum in memo) return memo[targetSum]
    if (numbers.isEmpty()) return null
    if (targetSum == 0) return emptyArray()
    if (targetSum < 0) return null

    for (num in numbers) {
        val res = targetSum - num
        val result = howSum(res, numbers, memo)
        result?.let {
            memo[res] = it + arrayOf(num)
            return memo[res]
        }
    }

    memo[targetSum] = null
    return memo[targetSum]
}

fun canSum(targetSum: Int, numbers: IntArray, memo: MutableMap<Int, Boolean> = mutableMapOf()): Boolean {
    if (targetSum in memo) return memo[targetSum]!!
    if (numbers.isEmpty()) return false
    if (targetSum == 0) return true
    if (targetSum < 0) return false

    for (num in numbers) {
        val result = targetSum - num
        if (canSum(result, numbers, memo)) {
            memo[result] = true
            return true
        }
    }

    memo[targetSum] = false
    return false
}

fun gridTraveller(col: Int, row: Int, memo: MutableMap<String, Long> = mutableMapOf()): Long {
    val key = "$col,$row"
    if (key in memo) memo[key]?.let { return it }

    if (col == 0 || row == 0) return 0
    if (col == 1 && row == 1) return 1

    memo[key] = gridTraveller(col - 1, row, memo) + gridTraveller(col, row - 1, memo)
    return memo[key]!!
}

fun fib(n: Int, memo: MutableMap<Int, Long> = mutableMapOf()): Long {
    if (n in memo) memo[n]?.let { return it }
    if (n <= 0) return 0
    if (n == 1) return 1
    memo[n] = fib(n - 1, memo) + fib(n - 2, memo)
    return memo[n]!!
}


private fun mergesort(arr: IntArray) {
    mergesort(arr, 0, arr.size - 1)
}

private fun mergesort(arr: IntArray, left: Int, right: Int) {
    if (left < right) {
        val mid = (left + right) / 2
        mergesort(arr, left, mid)
        mergesort(arr, mid + 1, right)
        merge(arr, left, mid, right)
    }
}

private fun merge(arr: IntArray, left: Int, mid: Int, right: Int) {
    val n1 = mid - left + 1
    val n2 = right - mid

    val leftArr = arr.copyOfRange(left, mid + 1)
    val rightArr = arr.copyOfRange(mid + 1, right + 1)

    var i = 0
    var j = 0
    var k = left

    while (i < n1 && j < n2) {
        if (leftArr[i] <= rightArr[j]) {
            arr[k++] = leftArr[i++]
        } else {
            arr[k++] = rightArr[j++]
        }
    }

    while (i < n1) {
        arr[k++] = leftArr[i++]
    }

    while (j < n2) {
        arr[k++] = rightArr[j++]
    }
}

private fun mergesort(arr: MutableList<Int>) {
    mergesort(arr, 0, arr.size - 1)
}

private fun mergesort(arr: MutableList<Int>, left: Int, right: Int) {
    if (left < right) {
        val mid = (left + right) / 2
        mergesort(arr, left, mid)
        mergesort(arr, mid + 1, right)
        merge(arr, left, mid, right)
    }
}

private fun merge(arr: MutableList<Int>, left: Int, mid: Int, right: Int) {
    val n1 = mid - left + 1
    val n2 = right - mid

    val leftArr = arr.subList(left, mid + 1)
    val rightArr = arr.subList(mid + 1, right + 1)

    var i = 0
    var j = 0
    var k = left

    while (i < n1 && j < n2) {
        if (leftArr[i] <= rightArr[j]) {
            arr[k++] = leftArr[i++]
        } else {
            arr[k++] = rightArr[j++]
        }
    }

    while (i < n1) {
        arr[k++] = leftArr[i++]
    }

    while (j < n2) {
        arr[k++] = rightArr[j++]
    }
}

fun setUpIO() {
    i = FastIn()
    o = PrintWriter(BufferedOutputStream(OUTPUT), false)
}

var INPUT: InputStream = System.`in`
var OUTPUT: PrintStream = System.out
lateinit var i: FastIn
lateinit var o: PrintWriter
inline fun flush(block: PrintWriter.() -> Unit) = o.apply(block).flush()
class FastIn {
    val br = INPUT.bufferedReader()
    var st: StringTokenizer? = null

    operator fun next(): String {
        while (st == null || !st!!.hasMoreElements()) st = StringTokenizer(br.readLine() ?: return "", " ")
        return st!!.nextToken()
    }

    fun int() = next().toInt()
    fun long() = next().toLong()
    fun double() = next().toDouble()
    fun line() = br.readLine()!!
    fun str(n: Int) = MutableList(n) { next() }
    fun lines(n: Int) = MutableList(n) { line() }
    fun ints(n: Int) = MutableList(n) { int() }
    fun intArr(n: Int) = IntArray(n) { int() }
    fun doubles(n: Int) = MutableList(n) { double() }
    fun doubleArr(n: Int) = DoubleArray(n) { double() }
    fun longs(n: Int) = MutableList(n) { long() }
    fun longArr(n: Int) = LongArray(n) { long() }
}
/* ------------------ END SUBMISSION ------------------ */