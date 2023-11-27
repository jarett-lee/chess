package chess

data class BoardData(
    val whitePawns: ULong = 0uL,
    val whiteKnights: ULong = 0uL,
    val whiteBishops: ULong = 0uL,
    val whiteRooks: ULong = 0uL,
    val whiteQueens: ULong = 0uL,
    val whiteKings: ULong = 0uL,
    val blackPawns: ULong = 0uL,
    val blackKnights: ULong = 0uL,
    val blackBishops: ULong = 0uL,
    val blackRooks: ULong = 0uL,
    val blackQueens: ULong = 0uL,
    val blackKings: ULong = 0uL,

    val whiteTurn: Boolean = true,
    val pieceStayed: ULong = 0uL,
    val enPassantSquare: ULong = 0uL,
) {
    fun whitePieces(): ULong {
        return whitePawns or whiteKnights or whiteBishops or whiteRooks or whiteKings
    }

    fun blackPieces(): ULong {
        return blackPawns or blackKnights or blackBishops or blackRooks or blackKings
    }

    fun pieces(): ULong {
        return whitePieces() or blackPieces()
    }

    fun emptySquares(): ULong {
        return pieces().inv()
    }

    fun pawnMoves(): Set<BoardData> {
        val boards = mutableSetOf<BoardData>()

        val whitePawnsMoveOne = BitUtils.bitShiftUp(whitePawns) and emptySquares()
        val whitePawnsMoveOneMoves = BitUtils.oneBitsToSet(whitePawnsMoveOne)
        for (move in whitePawnsMoveOneMoves) {
            val originalSquare = BitUtils.bitShiftDown(move)
            val newBoards = pawnMoveBoard(originalSquare, move)
            boards.addAll(newBoards)
        }

        val whitePawnsStayed = whitePawns and pieceStayed
        val whitePawnsStayedMoveOne = BitUtils.bitShiftUp(whitePawnsStayed) and emptySquares()
        val whitePawnsStayedMoveTwo = BitUtils.bitShiftUp(whitePawnsStayedMoveOne) and emptySquares()
        val whitePawnsStayedMoveTwoMoves = BitUtils.oneBitsToSet(whitePawnsStayedMoveTwo)
        for (move in whitePawnsStayedMoveTwoMoves) {
            val originalSquare = BitUtils.bitShiftDown(move, 2)
            val newBoards = pawnMoveBoard(originalSquare, move, checkEnPassant = true)
            boards.addAll(newBoards)
        }

        return boards
    }

    // BoardData(whitePawns=0, whiteKnights=0, whiteBishops=0, whiteRooks=0, whiteQueens=0, whiteKings=0, blackPawns=0, blackKnights=0, blackBishops=0, blackRooks=0, blackQueens=0, blackKings=0, whiteTurn=false, pieceStayed=0, enPassantSquare=0)
    override fun toString(): String {
        val attributes = listOf(
            Pair("whitePawns", whitePawns),
            Pair("whiteKnights", whiteKnights),
            Pair("whiteBishops", whiteBishops),
            Pair("whiteRooks", whiteRooks),
            Pair("whiteQueens", whiteQueens),
            Pair("whiteKings", whiteKings),
            Pair("blackPawns", blackPawns),
            Pair("blackKnights", blackKnights),
            Pair("blackBishops", blackBishops),
            Pair("blackRooks", blackRooks),
            Pair("blackQueens", blackQueens),
            Pair("blackKings", blackKings),
            Pair("pieceStayed", pieceStayed),
            Pair("enPassantSquare", enPassantSquare),
        )
        val nonZeroAttributes = attributes.filter { it.second != 0uL }
        val nonZeroAttributeStrings = nonZeroAttributes.map { "${it.first}=${BitUtils.uLongToHexString(it.second)}" }
        val attributeString = nonZeroAttributeStrings.joinToString()

        return "BoardData($attributeString, whiteTurn=$whiteTurn)"
    }

    fun pawnCaptures(): Set<BoardData> {
        val boards = mutableSetOf<BoardData>()

        val whitePawnsLeftCapture = BitUtils.bitShiftLeft(BitUtils.bitShiftUp(whitePawns)) and (blackPieces() or enPassantSquare)
        val whitePawnsLeftCaptureMoves = BitUtils.oneBitsToSet(whitePawnsLeftCapture)
        for (move in whitePawnsLeftCaptureMoves) {
            val originalSquare = BitUtils.bitShiftDown(BitUtils.bitShiftRight(move))
            val board = pawnCaptureBoard(originalSquare, move)
            boards.addAll(board)
        }

        val whitePawnsRightCapture = BitUtils.bitShiftRight(BitUtils.bitShiftUp(whitePawns)) and (blackPieces() or enPassantSquare)
        val whitePawnsRightCaptureMoves = BitUtils.oneBitsToSet(whitePawnsRightCapture)
        for (move in whitePawnsRightCaptureMoves) {
            val originalSquare = BitUtils.bitShiftDown(BitUtils.bitShiftLeft(move))
            val board = pawnCaptureBoard(originalSquare, move)
            boards.addAll(board)
        }

        return boards
    }

    fun pawnMoveBoard(originalSquare: ULong, move: ULong, checkEnPassant: Boolean = false): Set<BoardData> {
        val boards = mutableSetOf<BoardData>()

        val newWhiteTurn = !whiteTurn
        val newPieceStayed = pieceStayed and originalSquare.inv() and move.inv()

        var newEnPassantSquare = 0uL
        val blackPawnCheck = blackPawns and (BitUtils.bitShiftLeft(move) or BitUtils.bitShiftRight(move))
        if (checkEnPassant && blackPawnCheck.countOneBits() > 0) {
            newEnPassantSquare = BitUtils.bitShiftDown(move)
        }

        val promotion = (move and 0xFF00000000000000uL).countOneBits() > 0
        if (promotion) {
            val newWhitePawns = (whitePawns and originalSquare.inv())

            val newBoard = this.copy(
                whitePawns = newWhitePawns,

                whiteTurn = newWhiteTurn,
                pieceStayed = newPieceStayed,
                enPassantSquare = newEnPassantSquare,
            )

            boards.add(newBoard.copy(whiteKnights = whiteKnights or move))
            boards.add(newBoard.copy(whiteBishops = whiteBishops or move))
            boards.add(newBoard.copy(whiteRooks = whiteRooks or move))
            boards.add(newBoard.copy(whiteQueens = whiteQueens or move))
        } else {
            val newWhitePawns = (whitePawns and originalSquare.inv()) or move

            val newBoard = this.copy(
                whitePawns = newWhitePawns,

                whiteTurn = newWhiteTurn,
                pieceStayed = newPieceStayed,
                enPassantSquare = newEnPassantSquare,
            )
            boards.add(newBoard)
        }

        return boards
    }

    fun captureBlack(square: ULong): BoardData {
        val newBlackPawns = blackPawns and square.inv()
        val newBlackKnights = blackKnights and square.inv()
        val newBlackBishops = blackBishops and square.inv()
        val newBlackRooks = blackRooks and square.inv()
        val newBlackQueens = blackQueens and square.inv()

        val board = this.copy(
            blackPawns = newBlackPawns,
            blackKnights = newBlackKnights,
            blackBishops = newBlackBishops,
            blackRooks = newBlackRooks,
            blackQueens = newBlackQueens,
        )

        return board
    }

    fun pawnCaptureBoard(originalSquare: ULong, move: ULong): Set<BoardData> {
        var pawnLocation = move
        if ((move and enPassantSquare).countOneBits() > 0) {
            pawnLocation = BitUtils.bitShiftDown(enPassantSquare)
        }

        val newBoard = captureBlack(pawnLocation)
        return newBoard.pawnMoveBoard(originalSquare, move)
    }

    fun knightCaptureBoard(originalSquare: ULong, move: ULong): BoardData {
        val newBoard = captureBlack(move)
        return newBoard.knightMoveBoard(originalSquare, move)
    }

    fun knightMoveBoard(originalSquare: ULong, move: ULong): BoardData {
        val newWhiteKnights = (whiteKnights and originalSquare.inv()) or move

        val newBoard = regularMoveBoard(originalSquare, move).copy(
            whiteKnights = newWhiteKnights,
        )

        return newBoard
    }

    fun rookCaptureBoard(originalSquare: ULong, move: ULong): BoardData {
        val newBoard = captureBlack(move)
        return newBoard.rookMoveBoard(originalSquare, move)
    }

    fun rookMoveBoard(originalSquare: ULong, move: ULong): BoardData {
        val newWhiteRooks = (whiteRooks and originalSquare.inv()) or move

        val newBoard = regularMoveBoard(originalSquare, move).copy(
            whiteRooks = newWhiteRooks,
        )

        return newBoard
    }

    fun queenCaptureBoard(originalSquare: ULong, move: ULong): BoardData {
        val newBoard = captureBlack(move)
        return newBoard.queenMoveBoard(originalSquare, move)
    }

    fun queenMoveBoard(originalSquare: ULong, move: ULong): BoardData {
        val newWhiteQueens = (whiteQueens and originalSquare.inv()) or move

        val newBoard = regularMoveBoard(originalSquare, move).copy(
            whiteQueens = newWhiteQueens,
        )

        return newBoard
    }

    fun bishopCaptureBoard(originalSquare: ULong, move: ULong): BoardData {
        val newBoard = captureBlack(move)
        return newBoard.bishopMoveBoard(originalSquare, move)
    }

    fun bishopMoveBoard(originalSquare: ULong, move: ULong): BoardData {
        val newWhiteBishops = (whiteBishops and originalSquare.inv()) or move

        val newBoard = regularMoveBoard(originalSquare, move).copy(
            whiteBishops = newWhiteBishops,
        )

        return newBoard
    }

    fun regularMoveBoard(originalSquare: ULong, move: ULong): BoardData {
        val newWhiteTurn = !whiteTurn
        val newPieceStayed = pieceStayed and originalSquare.inv() and move.inv()
        val newEnPassantSquare = 0uL

        val newBoard = this.copy(
            whiteTurn = newWhiteTurn,
            pieceStayed = newPieceStayed,
            enPassantSquare = newEnPassantSquare,
        )

        return newBoard
    }
}
