package chess

object King {
    fun boards(board: BoardData): Set<BoardData> {
        val boards = mutableSetOf<BoardData>()
        val king = board.whiteKings

        val moves = King.normalMoves(board, king)
        boards.addAll(moves.map { value -> board.kingCaptureBoard(value.originalSquare, value.move) })

        if (canRightCastle(board, king)) {
            val newBoard = board
                .kingMoveBoard(king, BitUtils.bitShiftRight2(king))
                .rookMoveBoard(rightRookStart(), BitUtils.bitShiftRight(king))
            boards.add(newBoard.copy(whiteTurn = !board.whiteTurn))
        }

        if (canLeftCastle(board, king)) {
            val newBoard = board
                .kingMoveBoard(king, BitUtils.bitShiftLeft2(king))
                .rookMoveBoard(leftRookStart(), BitUtils.bitShiftLeft(king))
            boards.add(newBoard.copy(whiteTurn = !board.whiteTurn))
        }

        return boards
    }

    fun rightRookStart(): ULong {
        return 0b00000001uL
    }

    fun leftRookStart(): ULong {
        return 0b10000000uL
    }

    fun canRightCastle(board: BoardData, king: ULong): Boolean {
        val kingStayed = (king and board.pieceStayed) != 0uL
        val rightRookStayed = (rightRookStart() and board.pieceStayed and board.whiteRooks) != 0uL
        if (!(kingStayed and rightRookStayed)) {
            return false
        }

        // TODO handle infinite loop in a better way
        var i = 0
        var check = BitUtils.bitShiftRight(king)
        while((check != rightRookStart()) and (i < 8)) {
            val clear = (board.pieces() and check) == 0uL
            if (!clear) {
                return false
            }
            check = check shr 1
        }

        return true
    }

    fun canLeftCastle(board: BoardData, king: ULong): Boolean {
        val kingStayed = (king and board.pieceStayed) != 0uL
        val leftRookStayed = (leftRookStart() and board.pieceStayed and board.whiteRooks) != 0uL
        if (!(kingStayed and leftRookStayed)) {
            return false
        }

        // TODO handle infinite loop in a better way
        var i = 0
        var check = BitUtils.bitShiftLeft(king)
        while((check != leftRookStart()) and (i < 8)) {
            val clear = (board.pieces() and check) == 0uL
            if (!clear) {
                return false
            }
            check = check shl 1
            i++
        }

        return true
    }

    fun moves(board: BoardData): Set<Move> {
        return moves(board, board.whiteKings)
    }

    fun moves(board: BoardData, pieces: ULong): Set<Move> {
        val moves = mutableSetOf<Move>()

        moves.addAll(normalMoves(board, pieces))
        moves.addAll(castleMoves(board, pieces))

        return moves
    }

    fun castleMoves(board: BoardData, pieces: ULong): Set<Move> {
        val moves = mutableSetOf<Move>()

        // Assume only one king
        val king = pieces

        if (canRightCastle(board, king)) {
            moves.add(Move(
                originalSquare = king,
                move = BitUtils.bitShiftRight2(king)),
            )
        }

        if (canLeftCastle(board, king)) {
            moves.add(Move(
                originalSquare = king,
                move = BitUtils.bitShiftLeft2(king)),
            )
        }

        return moves
    }

    fun normalMoves(board: BoardData, pieces: ULong): Set<Move> {
        val moves = mutableSetOf<Move>()
        val unblockedSquares = board.whitePieces().inv()
        // Assume only one king
        val king = pieces

        val directions = setOf<(ULong) -> ULong>(
            { piece -> BitUtils.bitShiftUp(piece) },
            { piece -> BitUtils.bitShiftLeft(piece) },
            { piece -> BitUtils.bitShiftRight(piece) },
            { piece -> BitUtils.bitShiftDown(piece) },
            { piece -> BitUtils.bitShiftUp(BitUtils.bitShiftLeft(piece)) },
            { piece -> BitUtils.bitShiftUp(BitUtils.bitShiftRight(piece)) },
            { piece -> BitUtils.bitShiftDown(BitUtils.bitShiftLeft(piece)) },
            { piece -> BitUtils.bitShiftDown(BitUtils.bitShiftRight(piece)) },
        )

        for (direction in directions) {
            val move = direction(pieces) 

            if ((move and unblockedSquares) != 0uL) {
                moves.add(Move(
                    originalSquare = king,
                    move = move,
                ))
            }
        }

        return moves
    }
}


