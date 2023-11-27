package chess

object Pawn {
    fun boards(board: BoardData): Set<BoardData> {
        val boards = mutableSetOf<BoardData>()

        boards.addAll(forwardBoards(board))
        boards.addAll(captureBoards(board))

        return boards
    }

    // fun moves(board: BoardData): Set<Move> {
    // }

    fun forwardBoards(board: BoardData): Set<BoardData> {
        val boards = mutableSetOf<BoardData>()
        val emptySquares = board.emptySquares()

        val whitePawnsMoveOne = BitUtils.bitShiftUp(board.whitePawns) and emptySquares
        val whitePawnsMoveOneMoves = BitUtils.oneBitsToSet(whitePawnsMoveOne)
        for (move in whitePawnsMoveOneMoves) {
            val originalSquare = BitUtils.bitShiftDown(move)
            val newBoards = board.pawnMoveBoard(originalSquare, move)
            boards.addAll(newBoards)
        }

        val whitePawnsStayed = board.whitePawns and board.pieceStayed
        val whitePawnsStayedMoveOne = BitUtils.bitShiftUp(whitePawnsStayed) and emptySquares
        val whitePawnsStayedMoveTwo = BitUtils.bitShiftUp(whitePawnsStayedMoveOne) and emptySquares
        val whitePawnsStayedMoveTwoMoves = BitUtils.oneBitsToSet(whitePawnsStayedMoveTwo)
        for (move in whitePawnsStayedMoveTwoMoves) {
            val originalSquare = BitUtils.bitShiftDown(move, 2)
            val newBoards = board.pawnMoveBoard(originalSquare, move, checkEnPassant = true)
            boards.addAll(newBoards)
        }

        return boards
    }

    fun captureBoards(board: BoardData): Set<BoardData> {
        val boards = mutableSetOf<BoardData>()

        val whitePawnsLeftCapture = BitUtils.bitShiftLeft(BitUtils.bitShiftUp(board.whitePawns)) and (board.blackPieces() or board.enPassantSquare)
        val whitePawnsLeftCaptureMoves = BitUtils.oneBitsToSet(whitePawnsLeftCapture)
        for (move in whitePawnsLeftCaptureMoves) {
            val originalSquare = BitUtils.bitShiftDown(BitUtils.bitShiftRight(move))
            val newBoard = board.pawnCaptureBoard(originalSquare, move)
            boards.addAll(newBoard)
        }

        val whitePawnsRightCapture = BitUtils.bitShiftRight(BitUtils.bitShiftUp(board.whitePawns)) and (board.blackPieces() or board.enPassantSquare)
        val whitePawnsRightCaptureMoves = BitUtils.oneBitsToSet(whitePawnsRightCapture)
        for (move in whitePawnsRightCaptureMoves) {
            val originalSquare = BitUtils.bitShiftDown(BitUtils.bitShiftLeft(move))
            val newBoard = board.pawnCaptureBoard(originalSquare, move)
            boards.addAll(newBoard)
        }

        return boards
    }
}

