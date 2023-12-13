package chess

import kotlin.test.Test
import kotlin.test.assertEquals

class BoardDataTest {
    @Test fun reverse() {
        val board = BoardData(
            blackKings = BitUtils.uBytesToULong(
                0b00001000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            ),
            whiteTurn = true,
        )
        val actual = board.reverse()
        val expected = BoardData(
            whiteKings = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00010000u,
            ),
            whiteTurn = false,
        )
        assertEquals(expected, actual)
    }
}
