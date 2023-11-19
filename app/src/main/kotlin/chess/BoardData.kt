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

        // shl 8 is equivalant to moving 1 forward
        val whitePawnsMoveOne = (whitePawns shl 8) and emptySquares()
        val whitePawnsMoveOneMoves = OneBitSet(whitePawnsMoveOne)
        for (move in whitePawnsMoveOneMoves) {
            val originalSquare = move shr 8
            val newWhitePawns = (whitePawns and originalSquare.inv()) or move

            val newWhiteTurn = !whiteTurn
            val newPieceStayed = pieceStayed and originalSquare.inv() and move.inv()
            val newEnPassantSquare = 0uL
            val newBoard = this.copy(
                whitePawns = newWhitePawns,

                whiteTurn = newWhiteTurn,
                pieceStayed = newPieceStayed,
                enPassantSquare = newEnPassantSquare,
            )
            boards.add(newBoard)
        }

        val whitePawnsStayed = whitePawns and pieceStayed
        val whitePawnsStayedMoveOne = (whitePawnsStayed shl 8) and emptySquares()
        val whitePawnsStayedMoveTwo = (whitePawnsStayedMoveOne shl 8) and emptySquares()
        val whitePawnsStayedMoveTwoMoves = OneBitSet(whitePawnsStayedMoveTwo)
        for (move in whitePawnsStayedMoveTwoMoves) {
            val originalSquare = move shr 16
            val newWhitePawns = (whitePawns and originalSquare.inv()) or move

            val newWhiteTurn = !whiteTurn
            val newPieceStayed = pieceStayed and originalSquare.inv() and move.inv()
            val newEnPassantSquare = 0uL
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
}
