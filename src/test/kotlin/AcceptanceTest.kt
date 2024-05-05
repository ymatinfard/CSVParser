import junit.framework.Assert.assertEquals
import org.junit.Test

class AcceptanceTest {

    @Test
    fun `read csv`() {

        val csv = """
            column0,column1,column2
            0,cell01,cell02
            1,cell11,cell12
        """.trimIndent()
        val lines = csv.split('\n')

        val expected = listOf(
            MyData(0, "cell01", "cell02"),
            MyData(1, "cell11", "cell12"),
        )

        assertEquals(
            expected,
            readTableWithHeader(
                headerLine = lines.first(),
                lines = lines.drop(1),
                splitter = separateOnComma
            ).map { it.toMyData() })
    }
}
