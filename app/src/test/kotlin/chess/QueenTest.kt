package chess

import kotlin.test.Test
import kotlin.test.assertEquals

class QueenTest {
    @Test fun queenBoardsBlocked() {
        val whiteKnights = BitUtils.uBytesToULong(
            0b00000010u,
            0b00000011u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b11000000u,
            0b01000000u,
        )
        val whiteQueens = BitUtils.uBytesToULong(
            0b00000001u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b10000000u,
        )

        val actual = Queen.boards(BoardData(
            whiteQueens = whiteQueens,
            whiteKnights = whiteKnights,
        ))
        val expected = setOf<BoardData>()
        assertEquals(expected, actual)
    }

    @Test fun queenBoardsBlackSurroundsQueen() {
        val blackKnights = BitUtils.uBytesToULong(
            0b00000000u,
            0b00000000u,
            0b00111000u,
            0b00101000u,
            0b00111000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
        )

        val actual = Queen.boards(BoardData(
            whiteQueens = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00010000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            ),
            blackKnights = blackKnights,
        ))
        val expected = setOf(
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00100000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),
                blackKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00111000u,
                    0b00001000u,
                    0b00111000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00010000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),
                blackKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00101000u,
                    0b00101000u,
                    0b00111000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00001000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),
                blackKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00111000u,
                    0b00100000u,
                    0b00111000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00010000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),
                blackKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00111000u,
                    0b00101000u,
                    0b00101000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00100000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),
                blackKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00011000u,
                    0b00101000u,
                    0b00111000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00001000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),
                blackKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00110000u,
                    0b00101000u,
                    0b00111000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00001000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),
                blackKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00111000u,
                    0b00101000u,
                    0b00110000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00100000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),
                blackKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00111000u,
                    0b00101000u,
                    0b00011000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00010000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),
                blackKnights = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00111000u,
                    0b00101000u,
                    0b00101000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun queenBoardsOneQueen() {
        val actual = Queen.boards(BoardData(
            whiteQueens = BitUtils.uBytesToULong(
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
                whiteQueens = BitUtils.uBytesToULong(
                    0b00000001u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000010u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000100u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00001000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
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
                whiteQueens = BitUtils.uBytesToULong(
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
                whiteQueens = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
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
                whiteQueens = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000010u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
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
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
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
                whiteQueens = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00100000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b01000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b10000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
                    0b00010000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00010000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00010000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00010000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00010000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
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
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
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
            ),
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b10000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b01000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00100000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00001000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000100u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000010u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteQueens = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000001u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun queenMovesOneQueen() {
        val actual = BitUtils.uLongBinaryOf(Queen.moves(BoardData(
            whiteQueens = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00010000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            ),
        )))
        val expected = BitUtils.uLongBinaryOf(BitUtils.uBytesToULong(
            0b00010001u,
            0b10010010u,
            0b01010100u,
            0b00111000u,
            0b11101111u,
            0b00111000u,
            0b01010100u,
            0b10010010u,
        ))
        assertEquals(expected, actual)
    }
}
