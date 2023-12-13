package chess

import kotlin.test.Test
import kotlin.test.assertEquals

class KingTest {
    @Test fun kingBoardsBlocked() {
        val whiteKnights = BitUtils.uBytesToULong(
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b11000000u,
            0b01000000u,
        )
        val whiteKings = BitUtils.uBytesToULong(
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b10000000u,
        )

        val actual = King.boards(BoardData(
            whiteKings = whiteKings,
            whiteKnights = whiteKnights,
        ))
        val expected = setOf<BoardData>()
        assertEquals(expected, actual)
    }

    @Test fun kingBoardsCastles() {
        val whitePawns = BitUtils.uBytesToULong(
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b11111111u,
            0b00000000u,
        )
        val whiteRooks = BitUtils.uBytesToULong(
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b10000001u,
        )

        val actual = King.boards(BoardData(
            whiteKings = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00001000u,
            ),
            whiteRooks = whiteRooks,
            whitePawns = whitePawns,
            pieceStayed = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10001001u,
            ),
        ))
        val expected = setOf(
            BoardData(
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
                whiteRooks = whiteRooks,
                whitePawns = whitePawns,
                pieceStayed = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b10000001u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteKings = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000100u,
                ),
                whiteRooks = whiteRooks,
                whitePawns = whitePawns,
                pieceStayed = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b10000001u,
                ),

                whiteTurn = false,
            ),
            BoardData(
                whiteKings = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000010u,
                ),
                whiteRooks = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b10000100u,
                ),
                whitePawns = whitePawns,
                pieceStayed = BitUtils.uBytesToULong(
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
                whiteKings = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00100000u,
                ),
                whiteRooks = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00010001u,
                ),
                whitePawns = whitePawns,
                pieceStayed = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000001u,
                ),

                whiteTurn = false,
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun kingBoardsBlackSurroundsKing() {
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

        val actual = King.boards(BoardData(
            whiteKings = BitUtils.uBytesToULong(
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
                whiteKings = BitUtils.uBytesToULong(
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
                whiteKings = BitUtils.uBytesToULong(
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
                whiteKings = BitUtils.uBytesToULong(
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
                whiteKings = BitUtils.uBytesToULong(
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
                whiteKings = BitUtils.uBytesToULong(
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
                whiteKings = BitUtils.uBytesToULong(
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
                whiteKings = BitUtils.uBytesToULong(
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
                whiteKings = BitUtils.uBytesToULong(
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
                whiteKings = BitUtils.uBytesToULong(
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

    @Test fun kingMovesKingInMiddle() {
        val actual = BitUtils.uLongBinaryOf(King.moves(BoardData(
            whiteKings = BitUtils.uBytesToULong(
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
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00111000u,
            0b00101000u,
            0b00111000u,
            0b00000000u,
            0b00000000u,
        ))
        assertEquals(expected, actual)
    }

    @Test fun kingMovesCastle() {
        val actual = BitUtils.uLongBinaryOf(King.moves(BoardData(
            whiteKings = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00001000u,
            ),
            whiteRooks = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000001u,
            ),
            pieceStayed = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10001001u,
            ),
        )))
        val expected = BitUtils.uLongBinaryOf(BitUtils.uBytesToULong(
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00011100u,
            0b00110110u,
        ))
        assertEquals(expected, actual)
    }

    @Test fun kingMovesCastleBlockedByWhite() {
        val actual = BitUtils.uLongBinaryOf(King.moves(BoardData(
            whiteKings = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00001000u,
            ),
            whiteRooks = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000001u,
            ),
            whiteKnights = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b01000010u,
            ),
            pieceStayed = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10001001u,
            ),
        )))
        val expected = BitUtils.uLongBinaryOf(BitUtils.uBytesToULong(
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00011100u,
            0b00010100u,
        ))
        assertEquals(expected, actual)
    }

    @Test fun kingMovesCastleBlockedByBlack() {
        val actual = BitUtils.uLongBinaryOf(King.moves(BoardData(
            whiteKings = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00001000u,
            ),
            whiteRooks = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000001u,
            ),
            blackKnights = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b01000010u,
            ),
            pieceStayed = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10001001u,
            ),
        )))
        val expected = BitUtils.uLongBinaryOf(BitUtils.uBytesToULong(
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00011100u,
            0b00010100u,
        ))
        assertEquals(expected, actual)
    }

    @Test fun kingMovesCastleKingDidNotStay() {
        val actual = BitUtils.uLongBinaryOf(King.moves(BoardData(
            whiteKings = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00001000u,
            ),
            whiteRooks = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000001u,
            ),
            pieceStayed = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000001u,
            ),
        )))
        val expected = BitUtils.uLongBinaryOf(BitUtils.uBytesToULong(
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00011100u,
            0b00010100u,
        ))
        assertEquals(expected, actual)
    }

    @Test fun kingMovesCastleRooksDidNotStay() {
        val actual = BitUtils.uLongBinaryOf(King.moves(BoardData(
            whiteKings = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00001000u,
            ),
            whiteRooks = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000001u,
            ),
            pieceStayed = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00001000u,
            ),
        )))
        val expected = BitUtils.uLongBinaryOf(BitUtils.uBytesToULong(
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00011100u,
            0b00010100u,
        ))
        assertEquals(expected, actual)
    }

    @Test fun kingCastleMovesBlackCastle() {
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
            blackRooks = BitUtils.uBytesToULong(
                0b10000001u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            ),
            pieceStayed = BitUtils.uBytesToULong(
                0b10001001u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            ),
        )
        val rboard = board.reverse()
        val actual = BitUtils.uLongBinaryOf(King.castleMoves(rboard, rboard.whiteKings))
        val expected = BitUtils.uLongBinaryOf(BitUtils.uBytesToULong(
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b01000100u,
        ))
        assertEquals(expected, actual)
    }

    @Test fun kingMovesBlackCastle() {
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
            blackRooks = BitUtils.uBytesToULong(
                0b10000001u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            ),
            pieceStayed = BitUtils.uBytesToULong(
                0b10001001u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            ),
        )
        val actual = BitUtils.uLongBinaryOf(King.moves(board.reverse()))
        val expected = BitUtils.uLongBinaryOf(BitUtils.uBytesToULong(
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00111000u,
            0b01101100u,
        ))
        assertEquals(expected, actual)
    }
}
