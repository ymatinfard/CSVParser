# CSV File Parser

This is a simple CSV file parser written in Kotlin, which allows you to read CSV data into a list of maps or custom data classes.

## Overview

This CSV file parser consists of the following components:

- `AcceptanceTest`: Contains JUnit tests to validate the functionality of the CSV parser.
- `TableReaderTest`: Contains test cases for various scenarios of reading CSV data.

## Usage

You can use this CSV parser to read CSV data from a string or file and convert it into a list of maps or custom data classes.

### Example

```kotlin
val csv = """
    column0,column1,column2
    0,cell01,cell02
    1,cell11,cell12
""".trimIndent()

// Reading CSV data into a list of custom data class MyData
val lines = csv.split('\n')
val expected = listOf(
    MyData(0, "cell01", "cell02"),
    MyData(1, "cell11", "cell12")
)

assertEquals(
    expected,
    readTableWithHeader(
        headerLine = lines.first(),
        lines = lines.drop(1),
        splitter = separateOnComma
    ).map { it.toMyData() }
)

```

## Customization

You can customize the behavior of the CSV parser by providing different header providers and splitters.

## Dependency

This CSV parser relies on JUnit for testing.
