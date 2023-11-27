package chess

object Bishop {
    fun boards(board: BoardData): Set<BoardData> {
        val boards = mutableSetOf<BoardData>()

        val moves = Bishop.moves(board, board.whiteBishops)
        boards.addAll(moves.map { value -> board.bishopCaptureBoard(value.originalSquare, value.move) })

        return boards
    }

    fun moves(board: BoardData): Set<Move> {
        return moves(board, board.whiteBishops)
    }

    fun moves(board: BoardData, pieces: ULong): Set<Move> {
        return BitUtils.createMoveSet(board, pieces, setOf<(ULong) -> ULong>(
            { num -> BitUtils.bitShiftLeft(BitUtils.bitShiftUp(num)) },
            { num -> BitUtils.bitShiftLeft(BitUtils.bitShiftDown(num)) },
            { num -> BitUtils.bitShiftRight(BitUtils.bitShiftUp(num)) },
            { num -> BitUtils.bitShiftRight(BitUtils.bitShiftDown(num)) },
        ))
    }
}

