package chess

object Rook {
    fun boards(board: BoardData): Set<BoardData> {
        val boards = mutableSetOf<BoardData>()

        val moves = Rook.moves(board, board.whiteRooks)
        boards.addAll(moves.map { value -> board.rookCaptureBoard(value.originalSquare, value.move) })

        return boards
    }

    fun moves(board: BoardData): Set<Move> {
        return moves(board, board.whiteRooks)
    }

    fun moves(board: BoardData, pieces: ULong): Set<Move> {
        return BitUtils.createMoveSet(board, pieces, setOf<(ULong) -> ULong>(
            BitUtils::bitShiftLeft,
            { num -> BitUtils.bitShiftUp(num) },
            BitUtils::bitShiftRight,
            { num -> BitUtils.bitShiftDown(num) },
        ))
    }
}
