package chess

import kotlin.test.Test
import kotlin.test.assertEquals

class RookTest {
    @Test fun twoBlockedRook() {
        val whiteKnights = BitUtils.uBytesToULong(
            0b00000010u,
            0b00000001u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b10000000u,
            0b01000000u,
        )
        val whiteRooks = BitUtils.uBytesToULong(
            0b00000001u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b10000000u,
        )

        val actual = Rook.moves(BoardData(
            whiteRooks = whiteRooks,
            whiteKnights = whiteKnights,
        ))
        val expected = setOf<Move>()
        assertEquals(expected, actual)
    }

    @Test fun blackSurroundRook() {
        val blackKnights = BitUtils.uBytesToULong(
            0b00000000u,
            0b00000000u,
            0b00010000u,
            0b00101000u,
            0b00010000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
        )

        val actual = Rook.boards(BoardData(
            whiteRooks = BitUtils.uBytesToULong(
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
                whiteRooks = BitUtils.uBytesToULong(
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
                    0b00010000u,
                    0b00001000u,
                    0b00010000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteRooks = BitUtils.uBytesToULong(
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
                    0b00000000u,
                    0b00101000u,
                    0b00010000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteRooks = BitUtils.uBytesToULong(
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
                    0b00010000u,
                    0b00100000u,
                    0b00010000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteRooks = BitUtils.uBytesToULong(
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
                    0b00010000u,
                    0b00101000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                whiteTurn = false,
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun oneRook() {
        val actual = Rook.boards(BoardData(
            whiteRooks = BitUtils.uBytesToULong(
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
                whiteRooks = BitUtils.uBytesToULong(
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
                whiteRooks = BitUtils.uBytesToULong(
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
                whiteRooks = BitUtils.uBytesToULong(
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
                whiteRooks = BitUtils.uBytesToULong(
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
                whiteRooks = BitUtils.uBytesToULong(
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
                whiteRooks = BitUtils.uBytesToULong(
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
                whiteRooks = BitUtils.uBytesToULong(
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
                whiteRooks = BitUtils.uBytesToULong(
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
                whiteRooks = BitUtils.uBytesToULong(
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
                whiteRooks = BitUtils.uBytesToULong(
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
                whiteRooks = BitUtils.uBytesToULong(
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
                whiteRooks = BitUtils.uBytesToULong(
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
                whiteRooks = BitUtils.uBytesToULong(
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
                whiteRooks = BitUtils.uBytesToULong(
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

    @Test fun movesOneRook() {
        val whiteRook = BitUtils.uBytesToULong(
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00010000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
        )

        val actual = Rook.moves(BoardData(
            whiteRooks = whiteRook,
        ))
        val expected = setOf(
            Move(
                originalSquare = whiteRook,
                move = BitUtils.uBytesToULong(
                    0b00010000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),
            ),
            Move(
                originalSquare = whiteRook,
                move = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00010000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),
            ),
            Move(
                originalSquare = whiteRook,
                move = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00010000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),
            ),
            Move(
                originalSquare = whiteRook,
                move = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00010000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),
            ),
            Move(
                originalSquare = whiteRook,
                move = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00010000u,
                    0b00000000u,
                    0b00000000u,
                ),
            ),
            Move(
                originalSquare = whiteRook,
                move = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00010000u,
                    0b00000000u,
                ),
            ),
            Move(
                originalSquare = whiteRook,
                move = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00010000u,
                ),
            ),
            Move(
                originalSquare = whiteRook,
                move = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b10000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),
            ),
            Move(
                originalSquare = whiteRook,
                move = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b01000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),
            ),
            Move(
                originalSquare = whiteRook,
                move = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00100000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),
            ),
            Move(
                originalSquare = whiteRook,
                move = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00001000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),
            ),
            Move(
                originalSquare = whiteRook,
                move = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000100u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),
            ),
            Move(
                originalSquare = whiteRook,
                move = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000010u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),
            ),
            Move(
                originalSquare = whiteRook,
                move = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000001u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun rookMovesOneQueen() {
        val whiteQueens = BitUtils.uBytesToULong(
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00010000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
        )

        val actual = BitUtils.uLongBinaryOf(Rook.moves(
            BoardData(
                whiteQueens = whiteQueens
            ),
            whiteQueens,
        ))
        val expected = BitUtils.uLongBinaryOf(BitUtils.uBytesToULong(
            0b00010000u,
            0b00010000u,
            0b00010000u,
            0b00010000u,
            0b11101111u,
            0b00010000u,
            0b00010000u,
            0b00010000u,
        ))
        assertEquals(expected, actual)
    }
}