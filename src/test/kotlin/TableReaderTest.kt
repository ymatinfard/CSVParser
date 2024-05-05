import junit.framework.Assert
import org.junit.Test

class TableReaderTest {

    @Test
    fun `empty list return empty table`() {
        val table = emptyList<Map<String, String>>()
        val result = readTable(lines = emptyList())
        Assert.assertEquals(table, result)
    }

    @Test
    fun `table with one line without header`() {
        Assert.assertEquals(
            listOf(
                mapOf(
                    "0" to "item0",
                    "1" to "item1"
                )
            ),
            readTable(lines = listOf("item0,item1"))
        )
    }

    @Test
    fun `table with one line with header`() {
        Assert.assertEquals(
            listOf(
                mapOf(
                    "column0" to "item0",
                    "column1" to "item1"
                )
            ),
            readTableWithHeader(headerLine = "column0,column1", lines = listOf("item0,item1"), separateOnComma)
        )
    }

    @Test
    fun `table with two lines with header`() {
        Assert.assertEquals(
            listOf(
                mapOf(
                    "column0" to "item00",
                    "column1" to "item01"
                ),
                mapOf(
                    "column0" to "item10",
                    "column1" to "item11"
                )
            ),
            readTableWithHeader(
                headerLine = "column0,column1",
                lines = listOf("item00,item01", "item10,item11"),
                splitter = separateOnComma
            )
        )
    }

    @Test
    fun `table with two lines`() {
        Assert.assertEquals(
            listOf(
                mapOf("0" to "item00", "1" to "item01"),
                mapOf("0" to "item10", "1" to "item11")
            ),
            readTable(
                lines = listOf(
                    "item00,item01",
                    "item10,item11"
                )
            )
        )
    }

    @Test
    fun `specify custom column name`() {
        val headers = listOf("column0", "column1").toHeaderProvider()
        Assert.assertEquals(
            listOf(
                mapOf("column0" to "item00", "column1" to "item01"),
                mapOf("column0" to "item10", "column1" to "item11")
            ),
            readTable(
                lines = listOf(
                    "item00,item01",
                    "item10,item11"
                ),
                headerProvider = headers
            )
        )
    }
}