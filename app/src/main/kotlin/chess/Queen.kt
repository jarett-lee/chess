package chess

object Queen {
    fun boards(board: BoardData): Set<BoardData> {
        val boards = mutableSetOf<BoardData>()

        val moves = Queen.moves(board, board.whiteQueens)
        boards.addAll(moves.map { value -> board.queenCaptureBoard(value.originalSquare, value.move) })

        return boards
    }

    fun moves(board: BoardData): Set<Move> {
        return moves(board, board.whiteQueens)
    }

    fun moves(board: BoardData, pieces: ULong): Set<Move> {
        val moves = mutableSetOf<Move>()

        moves.addAll(Rook.moves(board, pieces))
        moves.addAll(Bishop.moves(board, pieces))

        return moves
    }
}

