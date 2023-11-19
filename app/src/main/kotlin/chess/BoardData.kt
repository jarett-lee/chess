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
    companion object {
        fun UByteToULong(nums: List<UByte>): ULong {
            if (nums.size != 8) {
                throw IllegalArgumentException("array of size 8 required")
            }

            var out = 0uL
            for (num in nums) {
                out = out shl 8
                out = out or num.toULong()
            }
            return out
        }

        fun ULongToUByte(num: ULong): List<UByte> {
            var window = num
            val out = mutableListOf<UByte>()
            for (i in 0..<8) {
                out.add(window.toUByte())
                window = window shr 8
            }

            return out.asReversed()
        }

        fun OneBitSet(num: ULong): Set<ULong> {
            val moves: MutableSet<ULong> = mutableSetOf<ULong>()
            for (i in 0..<64) {
                val mask = 1uL shl i
                if ((mask and num) > 0uL) {
                    moves.add(mask)
                }
            }
            return moves
        }

        fun BitShiftUp(num: ULong, by: Int = 1): ULong {
            return num shl (8 * by)
        }

        fun BitShiftDown(num: ULong, by: Int = 1): ULong {
            return num shr (8 * by)
        }

        fun BitShiftLeft(num: ULong): ULong {
            // 0b01111111 = 0x7F
            return (num and 0x7F7F7F7F7F7F7F7FuL) shl 1
        }

        fun BitShiftRight(num: ULong): ULong {
            // 0b11111110 0xFE
            return (num and 0xFEFEFEFEFEFEFEFEuL) shr 1
        }

        fun ULongToHexString(num: ULong, prefix: String = "0x"): String {
            return "$prefix${num.toString(16).padStart(16, '0')}"
        }
    }

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

        val whitePawnsMoveOne = BitShiftUp(whitePawns) and emptySquares()
        val whitePawnsMoveOneMoves = OneBitSet(whitePawnsMoveOne)
        for (move in whitePawnsMoveOneMoves) {
            val originalSquare = BitShiftDown(move)

            val newWhiteTurn = !whiteTurn
            val newPieceStayed = pieceStayed and originalSquare.inv() and move.inv()
            val newEnPassantSquare = 0uL

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
        }

        val whitePawnsStayed = whitePawns and pieceStayed
        val whitePawnsStayedMoveOne = BitShiftUp(whitePawnsStayed) and emptySquares()
        val whitePawnsStayedMoveTwo = BitShiftUp(whitePawnsStayedMoveOne) and emptySquares()
        val whitePawnsStayedMoveTwoMoves = OneBitSet(whitePawnsStayedMoveTwo)
        for (move in whitePawnsStayedMoveTwoMoves) {
            val originalSquare = BitShiftDown(move, 2)
            val newWhitePawns = (whitePawns and originalSquare.inv()) or move

            val newWhiteTurn = !whiteTurn
            val newPieceStayed = pieceStayed and originalSquare.inv() and move.inv()

            var newEnPassantSquare = 0uL
            val blackPawnCheck = blackPawns and (BitShiftLeft(move) or BitShiftRight(move))
            if (blackPawnCheck.countOneBits() > 0) {
                newEnPassantSquare = BitShiftDown(move)
            }

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
        val nonZeroAttributeStrings = nonZeroAttributes.map { "${it.first}=${ULongToHexString(it.second)}" }
        val attributeString = nonZeroAttributeStrings.joinToString()

        return "BoardData($attributeString, whiteTurn=$whiteTurn)"
    }

    fun pawnCaptures(): Set<BoardData> {
        val boards = mutableSetOf<BoardData>()

        val whitePawnsLeftCapture = BitShiftLeft(BitShiftUp(whitePawns)) and (blackPieces() or enPassantSquare)
        val whitePawnsLeftCaptureMoves = OneBitSet(whitePawnsLeftCapture)
        for (move in whitePawnsLeftCaptureMoves) {
            val originalSquare = BitShiftDown(BitShiftRight(move))

            val newBlackPawns = blackPawns and move.inv()
            val newBlackKnights = blackKnights and move.inv()
            val newBlackBishops = blackBishops and move.inv()
            val newBlackRooks = blackRooks and move.inv()
            val newBlackQueens = blackQueens and move.inv()

            val newWhiteTurn = !whiteTurn
            val newPieceStayed = pieceStayed and originalSquare.inv() and move.inv()
            val newEnPassantSquare = 0uL

            val promotion = (move and 0xFF00000000000000uL).countOneBits() > 0
            if (promotion) {
                val newWhitePawns = (whitePawns and originalSquare.inv())

                val newBoard = this.copy(
                    whitePawns = newWhitePawns,
                    blackPawns = newBlackPawns,
                    blackKnights = newBlackKnights,
                    blackBishops = newBlackBishops,
                    blackRooks = newBlackRooks,
                    blackQueens = newBlackQueens,

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
                    blackPawns = newBlackPawns,
                    blackKnights = newBlackKnights,
                    blackBishops = newBlackBishops,
                    blackRooks = newBlackRooks,
                    blackQueens = newBlackQueens,

                    whiteTurn = newWhiteTurn,
                    pieceStayed = newPieceStayed,
                    enPassantSquare = newEnPassantSquare,
                )
                boards.add(newBoard)
            }
        }

        val whitePawnsRightCapture = BitShiftRight(BitShiftUp(whitePawns)) and (blackPieces() or enPassantSquare)
        val whitePawnsRightCaptureMoves = OneBitSet(whitePawnsRightCapture)
        for (move in whitePawnsRightCaptureMoves) {
            val originalSquare = BitShiftDown(BitShiftLeft(move))

            val newBlackPawns = blackPawns and move.inv()
            val newBlackKnights = blackKnights and move.inv()
            val newBlackBishops = blackBishops and move.inv()
            val newBlackRooks = blackRooks and move.inv()
            val newBlackQueens = blackQueens and move.inv()

            val newWhiteTurn = !whiteTurn
            val newPieceStayed = pieceStayed and originalSquare.inv() and move.inv()
            val newEnPassantSquare = 0uL

            val promotion = (move and 0xFF00000000000000uL).countOneBits() > 0
            if (promotion) {
                val newWhitePawns = (whitePawns and originalSquare.inv())

                val newBoard = this.copy(
                    whitePawns = newWhitePawns,
                    blackPawns = newBlackPawns,
                    blackKnights = newBlackKnights,
                    blackBishops = newBlackBishops,
                    blackRooks = newBlackRooks,
                    blackQueens = newBlackQueens,

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
                    blackPawns = newBlackPawns,
                    blackKnights = newBlackKnights,
                    blackBishops = newBlackBishops,
                    blackRooks = newBlackRooks,
                    blackQueens = newBlackQueens,

                    whiteTurn = newWhiteTurn,
                    pieceStayed = newPieceStayed,
                    enPassantSquare = newEnPassantSquare,
                )
                boards.add(newBoard)
            }
        }

        return boards
    }
}
