fun readTable(
    lines: List<String>,
    headerProvider: (Int) -> String = Int::toString,
    splitter: (String) -> List<String> = separateOnComma,
): List<Map<String, String>> = lines.map { line ->
    parseLine(line, headerProvider, splitter)
}

private fun parseLine(
    line: String,
    headerProvider: (Int) -> String,
    splitter: (String) -> List<String>
): Map<String, String> {
    val values = splitter(line)
    val headers = values.indices.map { headerProvider(it) }
    return headers.zip(values).toMap()
}

private fun splitter(delimiter: String) = { line: String -> line.splitFields(delimiter) }

private fun String.splitFields(delimiter: String) =
    if (isEmpty()) emptyList() else split(delimiter)

fun readTableWithHeader(headerLine: String, lines: List<String>, splitter: (String) -> List<String>) =
    readTable(lines, headerProviderFor(headerLine, splitter))

fun headerProviderFor(
    header: String,
    splitter: (String) -> List<String>
): (Int) -> String {
    val fields = splitter(header)
    return { index ->
        fields[index]
    }
}

val separateOnComma = splitter(",")
val separateOnTab = splitter("\t")

fun List<String>.toHeaderProvider(): (Int) -> String = { this[it] } //alternative: lambda this::get

fun Map<String, String>.toMyData(): MyData {
    return MyData(
        first = this["column0"]!!.toInt(),
        second = this["column1"]!!,
        third = this["column2"]!!
    )
}
