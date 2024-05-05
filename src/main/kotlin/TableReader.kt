
fun readTable(lines: String): Map<String, String> {
    TODO()
}

fun Map<String, String>.toMyData(): MyData {
    return MyData(
        first = this["column0"]!!.toInt(),
        second = this["column1"]!!,
        third = this["column2"]!!
    )
}