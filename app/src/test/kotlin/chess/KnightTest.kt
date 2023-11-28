package chess

import kotlin.test.Test
import kotlin.test.assertEquals

class KnightTest {
    @Test fun oneCornerKnight() {
        val actual = Knight.boards(BoardData(
            whiteKnights = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000000u,
            ),
        ))
        val expected = setOf(
            BoardData(
                whiteKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00100000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b01000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun twoCornerKnight() {
        val actual = Knight.boards(BoardData(
            whiteKnights = BitUtils.uBytesToULong(
                0b00000001u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000000u,
            ),
        ))
        val expected = setOf(
            BoardData(
                whiteKnights = BitUtils.uBytesToULong(
                    0b00000001u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00100000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteKnights = BitUtils.uBytesToULong(
                    0b00000001u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b01000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000100u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b10000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000010u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b10000000u,
                ),

                whiteTurn = false,
            ),
        )
        assertEquals(expected, actual)
    }


    @Test fun oneCloseEdgeKnight() {
        val actual = Knight.boards(BoardData(
            whiteKnights = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b01000000u,
            ),
        ))
        val expected = setOf(
            BoardData(
                whiteKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b10000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00100000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00010000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun oneMiddleEdgeKnight() {
        val actual = Knight.boards(BoardData(
            whiteKnights = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00010000u,
            ),
        ))
        val expected = setOf(
            BoardData(
                whiteKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b01000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00100000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00001000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000100u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun oneMiddleKnight() {
        val actual = Knight.boards(BoardData(
            whiteKnights = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00010000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            ),
        ))
        val expected = setOf(
            BoardData(
                whiteKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b01000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00100000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00001000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000100u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000100u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00001000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00100000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b01000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun oneEdgeKnightCapture() {
        val blackKnights = BitUtils.uBytesToULong(
            0b00000000u,
            0b00000000u,
            0b00000010u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b01000000u,
            0b00000000u,
        )

        val actual = Knight.boards(BoardData(
            whiteKnights = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00010000u,
            ),
            blackKnights = blackKnights
        ))
        val expected = setOf(
            BoardData(
                whiteKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b01000000u,
                    0b00000000u,
                ),
                blackKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000010u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00100000u,
                    0b00000000u,
                    0b00000000u,
                ),
                blackKnights = blackKnights,

                whiteTurn = false,
            ),
            BoardData(
                whiteKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00001000u,
                    0b00000000u,
                    0b00000000u,
                ),
                blackKnights = blackKnights,

                whiteTurn = false,
            ),
            BoardData(
                whiteKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000100u,
                    0b00000000u,
                ),
                blackKnights = blackKnights,

                whiteTurn = false,
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun knightBlocked() {
        val actual = Knight.boards(BoardData(
            whiteKnights = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00010000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            ),
            whitePawns = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00101000u,
                0b01000100u,
                0b00000000u,
                0b01000100u,
                0b00101000u,
                0b00000000u,
            ),
        ))
        val expected = setOf<BoardData>()
        assertEquals(expected, actual)
    }
}