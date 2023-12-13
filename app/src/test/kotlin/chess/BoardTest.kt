package chess

import kotlin.test.Test
import kotlin.test.assertEquals

class BoardTest {
    @Test fun testToTextBoard() {
        val board = Board()
        val actual = board.boardData.toTextBoard()
        val expected = """rnbqkbnr
pppppppp
--------
--------
--------
--------
PPPPPPPP
RNBQKBNR"""
        assertEquals(expected, actual)
    }

    @Test fun board() {
        val board = Board()

        val turn1Data = board.boardData.copy()
        val turn1Boards = board.boards()

        val turn1DataBlackTurn = turn1Data.copy(whiteTurn = false)
        val pieceStayed = 0b1000100111111111000000000000000000000000000000001111111110001001uL
        val turn1BoardExpected = setOf(
            turn1DataBlackTurn.copy(
                whitePawns = 0b00000000100000000111111100000000uL,
                pieceStayed = 0b1000100111111111000000000000000000000000000000000111111110001001uL,
            ),
            turn1DataBlackTurn.copy(
                whitePawns = 0b10000000000000000111111100000000uL,
                pieceStayed = 0b1000100111111111000000000000000000000000000000000111111110001001uL,
            ),
            turn1DataBlackTurn.copy(
                whitePawns = 0b00000000010000001011111100000000uL,
                pieceStayed = 0b1000100111111111000000000000000000000000000000001011111110001001uL,
            ),
            turn1DataBlackTurn.copy(
                whitePawns = 0b01000000000000001011111100000000uL,
                pieceStayed = 0b1000100111111111000000000000000000000000000000001011111110001001uL,
            ),
            turn1DataBlackTurn.copy(
                whitePawns = 0b00000000001000001101111100000000uL,
                pieceStayed = 0b1000100111111111000000000000000000000000000000001101111110001001uL,
            ),
            turn1DataBlackTurn.copy(
                whitePawns = 0b00100000000000001101111100000000uL,
                pieceStayed = 0b1000100111111111000000000000000000000000000000001101111110001001uL,
            ),
            turn1DataBlackTurn.copy(
                whitePawns = 0b00000000000100001110111100000000uL,
                pieceStayed = 0b1000100111111111000000000000000000000000000000001110111110001001uL,
            ),
            turn1DataBlackTurn.copy(
                whitePawns = 0b00010000000000001110111100000000uL,
                pieceStayed = 0b1000100111111111000000000000000000000000000000001110111110001001uL,
            ),
            turn1DataBlackTurn.copy(
                whitePawns = 0b00000000000010001111011100000000uL,
                pieceStayed = 0b1000100111111111000000000000000000000000000000001111011110001001uL,
            ),
            turn1DataBlackTurn.copy(
                whitePawns = 0b00001000000000001111011100000000uL,
                pieceStayed = 0b1000100111111111000000000000000000000000000000001111011110001001uL,
            ),
            turn1DataBlackTurn.copy(
                whitePawns = 0b00000000000001001111101100000000uL,
                pieceStayed = 0b1000100111111111000000000000000000000000000000001111101110001001uL,
            ),
            turn1DataBlackTurn.copy(
                whitePawns = 0b00000100000000001111101100000000uL,
                pieceStayed = 0b1000100111111111000000000000000000000000000000001111101110001001uL,
            ),
            turn1DataBlackTurn.copy(
                whitePawns = 0b00000000000000101111110100000000uL,
                pieceStayed = 0b1000100111111111000000000000000000000000000000001111110110001001uL,
            ),
            turn1DataBlackTurn.copy(
                whitePawns = 0b00000010000000001111110100000000uL,
                pieceStayed = 0b1000100111111111000000000000000000000000000000001111110110001001uL,
            ),
            turn1DataBlackTurn.copy(
                whitePawns = 0b00000000000000011111111000000000uL,
                pieceStayed = 0b1000100111111111000000000000000000000000000000001111111010001001uL,
            ),
            turn1DataBlackTurn.copy(
                whitePawns = 0b00000001000000001111111000000000uL,
                pieceStayed = 0b1000100111111111000000000000000000000000000000001111111010001001uL,
            ),
            turn1DataBlackTurn.copy(
                whiteKnights = 0b00000000100000000000000000000010uL,
                pieceStayed = pieceStayed,
            ),
            turn1DataBlackTurn.copy(
                whiteKnights = 0b00000000001000000000000000000010uL,
                pieceStayed = pieceStayed,
            ),
            turn1DataBlackTurn.copy(
                whiteKnights = 0b00000000000000010000000001000000uL,
                pieceStayed = pieceStayed,
            ),
            turn1DataBlackTurn.copy(
                whiteKnights = 0b00000000000001000000000001000000uL,
                pieceStayed = pieceStayed,
            ),
        )

        assertEquals(turn1BoardExpected, turn1Boards)
        assertEquals(20, turn1Boards.size)

        val turn2Boards = mutableSetOf<BoardData>()
        for (turn1Board in turn1Boards) {
            turn2Boards.addAll(Board(boardData = turn1Board).boards())
        }

        // TODO remove debugging assert
        assertEquals(turn2Boards.take(1), turn2Boards.take(5))

        assertEquals(400, turn2Boards.size)
    }
}

