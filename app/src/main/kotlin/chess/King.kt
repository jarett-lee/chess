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
                .rookMoveBoard(rightRookStart(), BitUtils.bitShiftLeft2(rightRookStart()))
            boards.add(newBoard.copy(whiteTurn = !board.whiteTurn))
        }

        if (canLeftCastle(board, king)) {
            val newBoard = board
                .kingMoveBoard(king, BitUtils.bitShiftLeft2(king))
                .rookMoveBoard(leftRookStart(), BitUtils.bitShiftRight3(leftRookStart()))
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

    fun canRightCastle(board: BoardData, pieces: ULong): Boolean {
        val kingStayed = (pieces and board.pieceStayed) != 0uL
        val rightRookStayed = (rightRookStart() and board.pieceStayed and board.whiteRooks) != 0uL
        val rightCastleClear = (board.pieces() and 0b00000110uL) == 0uL

        return kingStayed and rightRookStayed and rightCastleClear
    }

    fun canLeftCastle(board: BoardData, pieces: ULong): Boolean {
        val kingStayed = (pieces and board.pieceStayed) != 0uL
        val leftRookStayed = (leftRookStart() and board.pieceStayed and board.whiteRooks) != 0uL
        val leftCastleClear = (board.pieces() and 0b01110000uL) == 0uL

        return kingStayed and leftRookStayed and leftCastleClear
    }

    fun moves(board: BoardData): Set<Move> {
        return moves(board, board.whiteKings)
    }

    fun moves(board: BoardData, pieces: ULong): Set<Move> {
        val moves = mutableSetOf<Move>()

        moves.addAll(normalMoves(board, pieces))

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


