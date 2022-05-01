@file:Suppress("PackageDirectoryMismatch", "MemberVisibilityCanBePrivate")
package kotlin_for_cp.code_forces.cf_1651a_playoff

/* ------------------ START SUBMISSION ------------------ */
import java.io.*
import java.util.*
import kotlin.math.pow

private fun solve(){
    val n = 2.0.pow(i.int()).toInt()

    if (n == 2){
        println("1")
        return
    }

    println(n-1)
}


fun main() {
    setUpIO()
    var tc = 1
    // TODO: no tc input for single test
    tc = i.int()
    flush { for (i in 0 until tc) solve() }
}

private fun mergesort(arr : IntArray){
    mergesort(arr, 0, arr.size - 1)
}

private fun mergesort(arr: IntArray, left : Int, right : Int){
    if (left < right){
        val mid = (left + right) / 2
        mergesort(arr, left, mid)
        mergesort(arr, mid + 1, right)
        merge(arr, left, mid, right)
    }
}

private fun merge(arr : IntArray, left : Int, mid : Int, right : Int){
    val n1 = mid - left + 1
    val n2 = right - mid

    val leftArr = arr.copyOfRange(left, mid + 1)
    val rightArr = arr.copyOfRange(mid + 1, right + 1)

    var i = 0
    var j = 0
    var k = left

    while (i < n1 && j < n2){
        if(leftArr[i] <= rightArr[j]){
            arr[k++] = leftArr[i++]
        }else{
            arr[k++] = rightArr[j++]
        }
    }

    while(i < n1){
        arr[k++] = leftArr[i++]
    }

    while(j < n2){
        arr[k++] = rightArr[j++]
    }
}

private fun mergesort(arr : MutableList<Int>){
    mergesort(arr, 0, arr.size - 1)
}

private fun mergesort(arr: MutableList<Int>, left : Int, right : Int){
    if (left < right){
        val mid = (left + right) / 2
        mergesort(arr, left, mid)
        mergesort(arr, mid + 1, right)
        merge(arr, left, mid, right)
    }
}

private fun merge(arr : MutableList<Int>, left : Int, mid : Int, right : Int){
    val n1 = mid - left + 1
    val n2 = right - mid

    val leftArr = arr.subList(left, mid + 1)
    val rightArr = arr.subList(mid + 1, right + 1)

    var i = 0
    var j = 0
    var k = left

    while (i < n1 && j < n2){
        if(leftArr[i] <= rightArr[j]){
            arr[k++] = leftArr[i++]
        }else{
            arr[k++] = rightArr[j++]
        }
    }

    while(i < n1){
        arr[k++] = leftArr[i++]
    }

    while(j < n2){
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