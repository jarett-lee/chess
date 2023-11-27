package chess

import kotlin.test.Test
import kotlin.test.assertEquals

class BoardDataTest {
    @Test fun boardDataPawnMovesOne() {
        val boardData = BoardData(
            whitePawns = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
            ),

            pieceStayed = 0uL,
        )

        val actual = boardData.pawnMoves()
        val expected = setOf(
            BoardData(
                whitePawns = BitUtils.uBytesToULong(
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
        )
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnMovesTwo() {
        val boardData = BoardData(
            whitePawns = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
            ),

            pieceStayed = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
            ),
        )

        val actual = boardData.pawnMoves()
        val expected = setOf(
            BoardData(
                whitePawns = BitUtils.uBytesToULong(
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
                pieceStayed = 0uL,
            ),
            BoardData(
                whitePawns = BitUtils.uBytesToULong(
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
                pieceStayed = 0uL,
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnMovesOneBlocked() {
        val boardData = BoardData(
            whitePawns = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
            ),
            blackPawns = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
                0b00000000u,
            ),

            pieceStayed = 0uL,
        )

        val actual = boardData.pawnMoves()
        val expected = setOf<BoardData>()
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnMovesTwoBlocked() {
        val blackPawns = BitUtils.uBytesToULong(
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b10000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
        )

        val boardData = BoardData(
            whitePawns = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
            ),
            blackPawns = blackPawns,

            pieceStayed = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
            ),
        )

        val actual = boardData.pawnMoves()
        val expected = setOf(
            BoardData(
                whitePawns = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b10000000u,
                    0b00000000u,
                    0b00000000u,
                ),
                blackPawns = blackPawns,

                whiteTurn = false,
                pieceStayed = 0uL,
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnMovesTwoCompletelyBlocked() {
        val boardData = BoardData(
            whitePawns = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
            ),
            blackPawns = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
                0b00000000u,
            ),

            pieceStayed = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
            ),
        )

        val actual = boardData.pawnMoves()
        val expected = setOf<BoardData>()
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnMovesTwoEnPassantRight() {
        val blackPawns = BitUtils.uBytesToULong(
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00010000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
        )

        val boardData = BoardData(
            whitePawns = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00100000u,
                0b00000000u,
            ),
            blackPawns = blackPawns,

            pieceStayed = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00100000u,
                0b00000000u,
            ),
        )

        val actual = boardData.pawnMoves()
        val expected = setOf(
            BoardData(
                whitePawns = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00100000u,
                    0b00000000u,
                    0b00000000u,
                ),
                blackPawns = blackPawns,

                whiteTurn = false,
                pieceStayed = 0uL,
            ),
            BoardData(
                whitePawns = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00100000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),
                blackPawns = blackPawns,

                whiteTurn = false,
                pieceStayed = 0uL,
                enPassantSquare = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00100000u,
                    0b00000000u,
                    0b00000000u,
                ),
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnMovesTwoEnPassantLeft() {
        val blackPawns = BitUtils.uBytesToULong(
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b01000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
        )

        val boardData = BoardData(
            whitePawns = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00100000u,
                0b00000000u,
            ),
            blackPawns = blackPawns,

            pieceStayed = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00100000u,
                0b00000000u,
            ),
        )

        val actual = boardData.pawnMoves()
        val expected = setOf(
            BoardData(
                whitePawns = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00100000u,
                    0b00000000u,
                    0b00000000u,
                ),
                blackPawns = blackPawns,

                whiteTurn = false,
                pieceStayed = 0uL,
            ),
            BoardData(
                whitePawns = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00100000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),
                blackPawns = blackPawns,

                whiteTurn = false,
                pieceStayed = 0uL,
                enPassantSquare = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00100000u,
                    0b00000000u,
                    0b00000000u,
                )
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnMovesTwoNoEnPassantRightEdge() {
        val blackPawns = BitUtils.uBytesToULong(
            0b11111111u,
            0b11111111u,
            0b11111111u,
            0b11111111u,
            0b11111100u,
            0b11111110u,
            0b11111110u,
            0b00000000u,
        )

        val boardData = BoardData(
            whitePawns = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000001u,
                0b00000000u,
            ),
            blackPawns = blackPawns,

            pieceStayed = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000001u,
                0b00000000u,
            ),
        )

        val actual = boardData.pawnMoves()
        val expected = setOf(
            BoardData(
                whitePawns = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000001u,
                    0b00000000u,
                    0b00000000u,
                ),
                blackPawns = blackPawns,

                whiteTurn = false,
                pieceStayed = 0uL,
            ),
            BoardData(
                whitePawns = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000001u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),
                blackPawns = blackPawns,

                whiteTurn = false,
                pieceStayed = 0uL,
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnMovesTwoEnPassantLeftEdge() {
        val blackPawns = BitUtils.uBytesToULong(
            0b11111111u,
            0b11111111u,
            0b11111111u,
            0b11111111u,
            0b00111111u,
            0b01111111u,
            0b01111111u,
            0b00000000u,
        )

        val boardData = BoardData(
            whitePawns = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
            ),
            blackPawns = blackPawns,

            pieceStayed = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
            ),
        )

        val actual = boardData.pawnMoves()
        val expected = setOf(
            BoardData(
                whitePawns = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b10000000u,
                    0b00000000u,
                    0b00000000u,
                ),
                blackPawns = blackPawns,

                whiteTurn = false,
                pieceStayed = 0uL,
            ),
            BoardData(
                whitePawns = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b10000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),
                blackPawns = blackPawns,

                whiteTurn = false,
                pieceStayed = 0uL,
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnMovesPromotion() {
        val boardData = BoardData(
            whitePawns = BitUtils.uBytesToULong(
                0b00000000u,
                0b10000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            ),
        )

        val actual = boardData.pawnMoves()
        val expected = setOf(
            BoardData(
                whiteKnights = BitUtils.uBytesToULong(
                    0b10000000u,
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
                whiteBishops = BitUtils.uBytesToULong(
                    0b10000000u,
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
                    0b10000000u,
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
                    0b10000000u,
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
        )
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnMovesTwoMovePromotion() {
        val boardData = BoardData(
            whitePawns = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            ),

            pieceStayed = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            ),
        )

        val actual = boardData.pawnMoves()
        val expected = setOf(
            BoardData(
                whitePawns = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b10000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),

                pieceStayed = 0uL,
                whiteTurn = false,
            ),
            BoardData(
                whiteKnights = BitUtils.uBytesToULong(
                    0b10000000u,
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
                whiteBishops = BitUtils.uBytesToULong(
                    0b10000000u,
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
                    0b10000000u,
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
                    0b10000000u,
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
        )
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnCaptureLeft() {
        val boardData = BoardData(
            whitePawns = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00100000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            ),
            blackPawns = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b01000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            ),

            pieceStayed = 0uL,
        )

        val actual = boardData.pawnCaptures()
        val expected = setOf(
            BoardData(
                whitePawns = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b01000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),
                blackPawns = 0uL,

                whiteTurn = false,
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnCaptureLeftEnPassant() {
        val boardData = BoardData(
            whitePawns = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00010000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            ),
            blackPawns = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00100000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            ),

            pieceStayed = 0uL,
            enPassantSquare = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00100000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            ),
        )

        val actual = boardData.pawnCaptures()
        val expected = setOf(
            BoardData(
                whitePawns = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00100000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),
                blackPawns = 0uL,
                enPassantSquare = 0uL,

                whiteTurn = false,
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnCaptureRightEnPassant() {
        val boardData = BoardData(
            whitePawns = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b01000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            ),
            blackPawns = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00100000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            ),

            pieceStayed = 0uL,
            enPassantSquare = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00100000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            ),
        )

        val actual = boardData.pawnCaptures()
        val expected = setOf(
            BoardData(
                whitePawns = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00100000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),
                blackPawns = 0uL,
                enPassantSquare = 0uL,

                whiteTurn = false,
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnCaptureRight() {
        val boardData = BoardData(
            whitePawns = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00100000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            ),
            blackPawns = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00010000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            ),

            pieceStayed = 0uL,
        )

        val actual = boardData.pawnCaptures()
        val expected = setOf(
            BoardData(
                whitePawns = BitUtils.uBytesToULong(
                    0b00000000u,
                    0b00000000u,
                    0b00010000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                ),
                blackPawns = 0uL,

                whiteTurn = false,
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnCaptureLeftAvoidWrap() {
        val boardData = BoardData(
            whitePawns = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            ),
            blackPawns = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000001u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            ),

            pieceStayed = 0uL,
        )

        val actual = boardData.pawnCaptures()
        val expected = setOf<BoardData>()
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnCaptureRightAvoidWrap() {
        val boardData = BoardData(
            whitePawns = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000001u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            ),
            blackPawns = BitUtils.uBytesToULong(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            ),

            pieceStayed = 0uL,
        )

        val actual = boardData.pawnCaptures()
        val expected = setOf<BoardData>()
        assertEquals(expected, actual)
    }
}

