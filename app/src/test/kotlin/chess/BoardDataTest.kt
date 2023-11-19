package chess

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class BoardDataTest {
    @Test fun boardDataUByteToULongSuccess() {
        val actual = BoardData.UByteToULong(listOf<UByte>(
            0b10000000u,
            0b00100001u,
            0b01000000u,
            0b00001000u,
            0b00010000u,
            0b00000010u,
            0b10000100u,
            0b00000001u,
        ))
        val expected = 0b1000000000100001010000000000100000010000000000101000010000000001uL
        assertEquals(expected, actual)
    }

    @Test fun boardDataUByteToULongListSize9() {
        assertFailsWith(
            exceptionClass = IllegalArgumentException::class,
            block = {
                BoardData.UByteToULong(listOf<UByte>(
                    0b10000000u,
                    0b00100001u,
                    0b01000000u,
                    0b00001000u,
                    0b00010000u,
                    0b00000010u,
                    0b10000100u,
                    0b00000001u,
                    0b00000000u,
                ))
            },
        )
    }

    @Test fun boardDataUByteToULongListSize7() {
        assertFailsWith(
            exceptionClass = IllegalArgumentException::class,
            block = {
                BoardData.UByteToULong(listOf<UByte>(
                    0b10000000u,
                    0b00100001u,
                    0b01000000u,
                    0b00001000u,
                    0b00010000u,
                    0b00000010u,
                    0b10000100u,
                ))
            },
        )
    }

    @Test fun boardDataULongToUByteSuccess() {
        val actual = BoardData.ULongToUByte(0b1000000000100001010000000000100000010000000000101000010000000001uL)
        val expected = listOf<UByte>(
            0b10000000u,
            0b00100001u,
            0b01000000u,
            0b00001000u,
            0b00010000u,
            0b00000010u,
            0b10000100u,
            0b00000001u,
        )
        assertEquals(expected, actual)
    }

    @Test fun boardDataOneBitSetEmptySet() {
        val actual = BoardData.OneBitSet(0uL)
        val expected = setOf<ULong>()
        assertEquals(expected, actual)
    }

    @Test fun boardDataOneBitSetOneSet() {
        val actual = BoardData.OneBitSet(1uL)
        val expected = setOf<ULong>(1uL)
        assertEquals(expected, actual)
    }

    @Test fun boardDataOneBitSetTwoSet() {
        val actual = BoardData.OneBitSet(3uL)
        val expected = setOf<ULong>(1uL, 2uL)
        assertEquals(expected, actual)
    }

    @Test fun boardDataOneBitSetThreeSet() {
        val actual = BoardData.OneBitSet(0b1000000000000000000000000010000000000000000000000000000000000100uL)
        val expected = setOf<ULong>(
            0b1000000000000000000000000000000000000000000000000000000000000000uL,
            0b0000000000000000000000000010000000000000000000000000000000000000uL,
            0b0000000000000000000000000000000000000000000000000000000000000100uL,
        )
        assertEquals(expected, actual)
    }

    @Test fun boardDataBitShiftUp() {
        val actual = BoardData.BitShiftUp(BoardData.UByteToULong(listOf<UByte>(
            0b01000001u,
            0b00000000u,
            0b00010000u,
            0b10000000u,
            0b00000100u,
            0b00000000u,
            0b00000001u,
            0b10000000u,
        )))
        val expected = BoardData.UByteToULong(listOf<UByte>(
            0b00000000u,
            0b00010000u,
            0b10000000u,
            0b00000100u,
            0b00000000u,
            0b00000001u,
            0b10000000u,
            0b00000000u,
        ))
        assertEquals(expected, actual)
    }

    @Test fun boardDataBitShiftDown() {
        val actual = BoardData.BitShiftDown(BoardData.UByteToULong(listOf<UByte>(
            0b01000001u,
            0b00000000u,
            0b00010000u,
            0b10000000u,
            0b00000100u,
            0b00000000u,
            0b00000001u,
            0b10000000u,
        )))
        val expected = BoardData.UByteToULong(listOf<UByte>(
            0b00000000u,
            0b01000001u,
            0b00000000u,
            0b00010000u,
            0b10000000u,
            0b00000100u,
            0b00000000u,
            0b00000001u,
        ))
        assertEquals(expected, actual)
    }

    @Test fun boardDataBitShiftUp2() {
        val actual = BoardData.BitShiftUp(BoardData.UByteToULong(listOf<UByte>(
            0b01000001u,
            0b00000000u,
            0b00010000u,
            0b10000000u,
            0b00000100u,
            0b00000000u,
            0b00000001u,
            0b10000000u,
        )), 2)
        val expected = BoardData.UByteToULong(listOf<UByte>(
            0b00010000u,
            0b10000000u,
            0b00000100u,
            0b00000000u,
            0b00000001u,
            0b10000000u,
            0b00000000u,
            0b00000000u,
        ))
        assertEquals(expected, actual)
    }

    @Test fun boardDataBitShiftDown2() {
        val actual = BoardData.BitShiftDown(BoardData.UByteToULong(listOf<UByte>(
            0b01000001u,
            0b00000000u,
            0b00010000u,
            0b10000000u,
            0b00000100u,
            0b00000000u,
            0b00000001u,
            0b10000000u,
        )), 2)
        val expected = BoardData.UByteToULong(listOf<UByte>(
            0b00000000u,
            0b00000000u,
            0b01000001u,
            0b00000000u,
            0b00010000u,
            0b10000000u,
            0b00000100u,
            0b00000000u,
        ))
        assertEquals(expected, actual)
    }

    @Test fun boardDataBitShiftLeft() {
        val actual = BoardData.BitShiftLeft(BoardData.UByteToULong(listOf<UByte>(
            0b01000001u,
            0b00000000u,
            0b00010000u,
            0b10000000u,
            0b00000100u,
            0b00000000u,
            0b00000001u,
            0b10000000u,
        )))
        val expected = BoardData.UByteToULong(listOf<UByte>(
            0b10000010u,
            0b00000000u,
            0b00100000u,
            0b00000000u,
            0b00001000u,
            0b00000000u,
            0b00000010u,
            0b00000000u,
        ))
        assertEquals(expected, actual)
    }

    @Test fun boardDataBitShiftRight() {
        val actual = BoardData.BitShiftRight(BoardData.UByteToULong(listOf<UByte>(
            0b01000001u,
            0b00000000u,
            0b00010000u,
            0b10000000u,
            0b00000100u,
            0b00000000u,
            0b00000001u,
            0b10000000u,
        )))
        val expected = BoardData.UByteToULong(listOf<UByte>(
            0b00100000u,
            0b00000000u,
            0b00001000u,
            0b01000000u,
            0b00000010u,
            0b00000000u,
            0b00000000u,
            0b01000000u,
        ))
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnMovesOne() {
        val boardData = BoardData(
            whitePawns = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
            )),

            pieceStayed = 0uL,
        )

        val actual = boardData.pawnMoves()
        val expected = setOf(
            BoardData(
                whitePawns = BoardData.UByteToULong(listOf<UByte>(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b10000000u,
                    0b00000000u,
                    0b00000000u,
                )),

                whiteTurn = false,
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnMovesTwo() {
        val boardData = BoardData(
            whitePawns = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
            )),

            pieceStayed = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
            )),
        )

        val actual = boardData.pawnMoves()
        val expected = setOf(
            BoardData(
                whitePawns = BoardData.UByteToULong(listOf<UByte>(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b10000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                )),

                whiteTurn = false,
                pieceStayed = 0uL,
            ),
            BoardData(
                whitePawns = BoardData.UByteToULong(listOf<UByte>(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b10000000u,
                    0b00000000u,
                    0b00000000u,
                )),

                whiteTurn = false,
                pieceStayed = 0uL,
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnMovesOneBlocked() {
        val boardData = BoardData(
            whitePawns = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
            )),
            blackPawns = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
                0b00000000u,
            )),

            pieceStayed = 0uL,
        )

        val actual = boardData.pawnMoves()
        val expected = setOf<BoardData>()
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnMovesTwoBlocked() {
        val blackPawns = BoardData.UByteToULong(listOf<UByte>(
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b10000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
        ))

        val boardData = BoardData(
            whitePawns = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
            )),
            blackPawns = blackPawns,

            pieceStayed = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
            )),
        )

        val actual = boardData.pawnMoves()
        val expected = setOf(
            BoardData(
                whitePawns = BoardData.UByteToULong(listOf<UByte>(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b10000000u,
                    0b00000000u,
                    0b00000000u,
                )),
                blackPawns = blackPawns,

                whiteTurn = false,
                pieceStayed = 0uL,
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnMovesTwoCompletelyBlocked() {
        val boardData = BoardData(
            whitePawns = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
            )),
            blackPawns = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
                0b00000000u,
            )),

            pieceStayed = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
            )),
        )

        val actual = boardData.pawnMoves()
        val expected = setOf<BoardData>()
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnMovesTwoEnPassantRight() {
        val blackPawns = BoardData.UByteToULong(listOf<UByte>(
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00010000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
        ))

        val boardData = BoardData(
            whitePawns = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00100000u,
                0b00000000u,
            )),
            blackPawns = blackPawns,

            pieceStayed = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00100000u,
                0b00000000u,
            )),
        )

        val actual = boardData.pawnMoves()
        val expected = setOf(
            BoardData(
                whitePawns = BoardData.UByteToULong(listOf<UByte>(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00100000u,
                    0b00000000u,
                    0b00000000u,
                )),
                blackPawns = blackPawns,

                whiteTurn = false,
                pieceStayed = 0uL,
            ),
            BoardData(
                whitePawns = BoardData.UByteToULong(listOf<UByte>(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00100000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                )),
                blackPawns = blackPawns,

                whiteTurn = false,
                pieceStayed = 0uL,
                enPassantSquare = BoardData.UByteToULong(listOf<UByte>(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00100000u,
                    0b00000000u,
                    0b00000000u,
                ))
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnMovesTwoEnPassantLeft() {
        val blackPawns = BoardData.UByteToULong(listOf<UByte>(
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
            0b01000000u,
            0b00000000u,
            0b00000000u,
            0b00000000u,
        ))

        val boardData = BoardData(
            whitePawns = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00100000u,
                0b00000000u,
            )),
            blackPawns = blackPawns,

            pieceStayed = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00100000u,
                0b00000000u,
            )),
        )

        val actual = boardData.pawnMoves()
        val expected = setOf(
            BoardData(
                whitePawns = BoardData.UByteToULong(listOf<UByte>(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00100000u,
                    0b00000000u,
                    0b00000000u,
                )),
                blackPawns = blackPawns,

                whiteTurn = false,
                pieceStayed = 0uL,
            ),
            BoardData(
                whitePawns = BoardData.UByteToULong(listOf<UByte>(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00100000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                )),
                blackPawns = blackPawns,

                whiteTurn = false,
                pieceStayed = 0uL,
                enPassantSquare = BoardData.UByteToULong(listOf<UByte>(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00100000u,
                    0b00000000u,
                    0b00000000u,
                ))
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnMovesTwoNoEnPassantRightEdge() {
        val blackPawns = BoardData.UByteToULong(listOf<UByte>(
            0b11111111u,
            0b11111111u,
            0b11111111u,
            0b11111111u,
            0b11111100u,
            0b11111110u,
            0b11111110u,
            0b00000000u,
        ))

        val boardData = BoardData(
            whitePawns = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000001u,
                0b00000000u,
            )),
            blackPawns = blackPawns,

            pieceStayed = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000001u,
                0b00000000u,
            )),
        )

        val actual = boardData.pawnMoves()
        val expected = setOf(
            BoardData(
                whitePawns = BoardData.UByteToULong(listOf<UByte>(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000001u,
                    0b00000000u,
                    0b00000000u,
                )),
                blackPawns = blackPawns,

                whiteTurn = false,
                pieceStayed = 0uL,
            ),
            BoardData(
                whitePawns = BoardData.UByteToULong(listOf<UByte>(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000001u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                )),
                blackPawns = blackPawns,

                whiteTurn = false,
                pieceStayed = 0uL,
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnMovesTwoEnPassantLeftEdge() {
        val blackPawns = BoardData.UByteToULong(listOf<UByte>(
            0b11111111u,
            0b11111111u,
            0b11111111u,
            0b11111111u,
            0b00111111u,
            0b01111111u,
            0b01111111u,
            0b00000000u,
        ))

        val boardData = BoardData(
            whitePawns = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
            )),
            blackPawns = blackPawns,

            pieceStayed = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
            )),
        )

        val actual = boardData.pawnMoves()
        val expected = setOf(
            BoardData(
                whitePawns = BoardData.UByteToULong(listOf<UByte>(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b10000000u,
                    0b00000000u,
                    0b00000000u,
                )),
                blackPawns = blackPawns,

                whiteTurn = false,
                pieceStayed = 0uL,
            ),
            BoardData(
                whitePawns = BoardData.UByteToULong(listOf<UByte>(
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b10000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                )),
                blackPawns = blackPawns,

                whiteTurn = false,
                pieceStayed = 0uL,
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnMovesPromotion() {
        val boardData = BoardData(
            whitePawns = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b10000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            )),
        )

        val actual = boardData.pawnMoves()
        val expected = setOf(
            BoardData(
                whiteKnights = BoardData.UByteToULong(listOf<UByte>(
                    0b10000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                )),

                whiteTurn = false,
            ),
            BoardData(
                whiteBishops = BoardData.UByteToULong(listOf<UByte>(
                    0b10000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                )),

                whiteTurn = false,
            ),
            BoardData(
                whiteRooks = BoardData.UByteToULong(listOf<UByte>(
                    0b10000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                )),

                whiteTurn = false,
            ),
            BoardData(
                whiteQueens = BoardData.UByteToULong(listOf<UByte>(
                    0b10000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                )),

                whiteTurn = false,
            ),
        )
        assertEquals(expected, actual)
    }

    // This is impossible in a regular game, so I don't handle promotions like this
    @Test fun boardDataPawnMovesTwoMovePromotion() {
        val boardData = BoardData(
            whitePawns = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            )),

            pieceStayed = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            )),
        )

        val actual = boardData.pawnMoves()
        val expected = setOf(
            BoardData(
                whitePawns = BoardData.UByteToULong(listOf<UByte>(
                    0b10000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                )),

                pieceStayed = 0uL,
                whiteTurn = false,
            ),
            BoardData(
                whitePawns = BoardData.UByteToULong(listOf<UByte>(
                    0b00000000u,
                    0b10000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                )),

                pieceStayed = 0uL,
                whiteTurn = false,
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnCaptureLeft() {
        val boardData = BoardData(
            whitePawns = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00100000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            )),
            blackPawns = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b01000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            )),

            pieceStayed = 0uL,
        )

        val actual = boardData.pawnCaptures()
        val expected = setOf(
            BoardData(
                whitePawns = BoardData.UByteToULong(listOf<UByte>(
                    0b00000000u,
                    0b00000000u,
                    0b01000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                )),
                blackPawns = 0uL,

                whiteTurn = false,
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnCaptureLeftEnPassant() {
        val boardData = BoardData(
            whitePawns = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00010000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            )),
            blackPawns = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00100000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            )),

            pieceStayed = 0uL,
            enPassantSquare = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b00100000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            )),
        )

        val actual = boardData.pawnCaptures()
        val expected = setOf(
            BoardData(
                whitePawns = BoardData.UByteToULong(listOf<UByte>(
                    0b00000000u,
                    0b00000000u,
                    0b00100000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                )),
                blackPawns = 0uL,
                enPassantSquare = 0uL,

                whiteTurn = false,
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnCaptureRightEnPassant() {
        val boardData = BoardData(
            whitePawns = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b01000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            )),
            blackPawns = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00100000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            )),

            pieceStayed = 0uL,
            enPassantSquare = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b00100000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            )),
        )

        val actual = boardData.pawnCaptures()
        val expected = setOf(
            BoardData(
                whitePawns = BoardData.UByteToULong(listOf<UByte>(
                    0b00000000u,
                    0b00000000u,
                    0b00100000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                )),
                blackPawns = 0uL,
                enPassantSquare = 0uL,

                whiteTurn = false,
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnCaptureRight() {
        val boardData = BoardData(
            whitePawns = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00100000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            )),
            blackPawns = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b00010000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            )),

            pieceStayed = 0uL,
        )

        val actual = boardData.pawnCaptures()
        val expected = setOf(
            BoardData(
                whitePawns = BoardData.UByteToULong(listOf<UByte>(
                    0b00000000u,
                    0b00000000u,
                    0b00010000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                    0b00000000u,
                )),
                blackPawns = 0uL,

                whiteTurn = false,
            ),
        )
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnCaptureLeftAvoidWrap() {
        val boardData = BoardData(
            whitePawns = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            )),
            blackPawns = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000001u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            )),

            pieceStayed = 0uL,
        )

        val actual = boardData.pawnCaptures()
        val expected = setOf<BoardData>()
        assertEquals(expected, actual)
    }

    @Test fun boardDataPawnCaptureRightAvoidWrap() {
        val boardData = BoardData(
            whitePawns = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000001u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            )),
            blackPawns = BoardData.UByteToULong(listOf<UByte>(
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b10000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
                0b00000000u,
            )),

            pieceStayed = 0uL,
        )

        val actual = boardData.pawnCaptures()
        val expected = setOf<BoardData>()
        assertEquals(expected, actual)
    }
}

