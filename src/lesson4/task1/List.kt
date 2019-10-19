@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson3.task1.isPrime
import kotlin.math.abs
import kotlin.math.sqrt
import kotlin.math.pow

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = abs(sqrt(v.map { it * it }.sum()))

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    return if (list.isEmpty()) 0.0
    else list.sum() / list.size.toDouble()
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if (list.isNotEmpty()) {
        val m = list.sum() / list.size.toDouble()
        for (i in 0 until list.size) {
            list[i] -= m
        }
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    return if (a.isEmpty() || b.isEmpty()) 0
    else {
        var sum = 0
        for (i in 0 until a.size) {
            sum += a[i] * b[i]
        }
        sum
    }
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    return if (p.isEmpty()) 0
    else {
        var sum = 0
        for (i in 0 until p.size) {
            sum += p[i] * x.toDouble().pow(i).toInt()
        }
        sum
    }
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    if (list.isNotEmpty()) {
        var sum = list[0]
        for (i in 1 until list.size) {
            sum += list[i]
            list[i] = sum
        }
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val result = mutableListOf<Int>()
    var insteadN = n
    for (i in 2..insteadN) {
        while (insteadN % i == 0) {
            result.add(i)
            insteadN /= i
        }
    }
    return result
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    return if (isPrime(n)) listOf(n).joinToString()
    else factorize(n).joinToString(separator = "*")
}

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var insteadN = n
    val result = mutableListOf<Int>()
    do {
        result.add(0, insteadN % base)
        insteadN /= base
    } while (insteadN != 0)
    return result
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    val result = mutableListOf<Char>()
    val convert = convert(n, base)
    for (i in 0 until convert.size) {
        if (convert[i] > 9) result.add('a' + convert[i] - 10)
        else result.add('0' + convert[i])
    }
    return result.joinToString(separator = "")
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var sum = 0
    for (i in 0 until digits.size) {
        sum += digits[i] * base.toDouble().pow(digits.size - 1 - i).toInt()
    }
    return sum
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    val result = mutableListOf<Int>()
    for (i in 0 until str.length) {
        if (str[i].toInt() in 48..57) {
            result.add(str[i].toInt() - 48)
        } else {
            result.add(str[i].toInt() - 87)
        }

    }
    return decimal(result, base)
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    val romNum = mapOf(
        1 to "I", 4 to "IV", 5 to "V", 9 to "IX", 10 to "X", 40 to "XL", 50 to "L",
        90 to "XC", 100 to "C", 400 to "CD", 500 to "D", 900 to "CM", 1000 to "M"
    )
    var insteadN = n
    val result = mutableListOf<String>()
    while (insteadN - 1000 >= 0) {
        result.add(romNum[1000].toString())
        insteadN -= 1000
    }
    var i = 100
    var key = insteadN / i
    while (i > 0) {
        if (key == 1 || key == 4 || key == 5 || key == 9) result.add(romNum[key * i].toString())
        else {
            if (key > 5) {
                result.add(romNum[5 * i].toString())
                key -= 5
            }
            for (x in 0 until key) {
                result.add(romNum[1 * i].toString())
            }
        }
        i /= 10
        if (i != 0) key = (insteadN - insteadN / (i * 10) * i * 10) / i
    }
    return result.joinToString(separator = "")
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val numbers = mapOf(
        1 to "один", 2 to "два", 3 to "три", 4 to "четыре", 5 to "пять",
        6 to "шесть", 7 to "семь", 8 to "восемь", 9 to "девять",
        10 to "десять", 11 to "одиннадцать", 12 to "двенадцать", 13 to "тринадцать", 14 to "четырнадцать",
        15 to "пятнадцать", 16 to "шестнадцать", 17 to "семнадцать", 18 to "восемнадцать", 19 to "девятнадцать",
        20 to "двадцать", 30 to "тридцать", 40 to "сорок", 50 to "пятьдесят",
        60 to "шестьдесят", 70 to "семьдесят", 80 to "восемьдесят", 90 to "девяносто",
        100 to "сто", 200 to "двести", 300 to "триста", 400 to "четыреста", 500 to "пятьсот",
        600 to "шестьсот", 700 to "семьсот", 800 to "восемьсот", 900 to "девятьсот"
    )
    var three = n / 1000
    val result = mutableListOf<String>()
    for (i in 0..1) {
        result.add(numbers[three / 100 * 100].toString())
        if (three % 100 in 3..19) result.add(numbers[three % 100].toString())
        else if (three % 10 in 1..2 && three % 100 / 10 != 1 && i == 0 && n / 1000 != 0) {
            result.add(numbers[three % 100 / 10 * 10].toString())
            when {
                three % 10 == 1 -> result.add("одна")
                else -> result.add("две")
            }
        } else {
            result.add(numbers[three % 100 / 10 * 10].toString()); result.add(numbers[three % 10].toString())
        }
        if (i == 0 && n / 1000 != 0) {
            when {
                n / 1000 % 100 / 10 != 1 && n / 1000 % 10 == 1 -> result.add("тысяча")
                n / 1000 % 100 / 10 != 1 && n / 1000 % 10 in 2..4 -> result.add("тысячи")
                else -> result.add("тысяч")
            }
        }
        three = n % 1000
    }
    while ("null" in result) {
        result.removeAt(result.indexOf("null"))
    }
    return result.joinToString(separator = " ")
}
