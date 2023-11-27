package chess

object BitUtils {
    fun uBytesToULong(nums: List<UByte>): ULong {
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

    fun uBytesToULong(num1: UByte, num2: UByte, num3: UByte, num4: UByte, num5: UByte, num6: UByte, num7: UByte, num8: UByte): ULong {
        val out =
            (num1.toULong() shl 56) or
            (num2.toULong() shl 48) or
            (num3.toULong() shl 40) or
            (num4.toULong() shl 32) or
            (num5.toULong() shl 24) or
            (num6.toULong() shl 16) or
            (num7.toULong() shl 8) or
            (num8.toULong())
        return out
    }

    fun uLongToUBytes(num: ULong): List<UByte> {
        var window = num
        val out = mutableListOf<UByte>()
        for (i in 0 ..< 8) {
            out.add(window.toUByte())
            window = window shr 8
        }

        return out.asReversed()
    }

    fun oneBitsToSet(num: ULong): Set<ULong> {
        val moves: MutableSet<ULong> = mutableSetOf<ULong>()
        for (i in 0 ..< 64) {
            val mask = 1uL shl i
            if ((mask and num) > 0uL) {
                moves.add(mask)
            }
        }
        return moves
    }

    fun bitShiftUp(num: ULong, by: Int = 1): ULong {
        return num shl (8 * by)
    }

    fun bitShiftDown(num: ULong, by: Int = 1): ULong {
        return num shr (8 * by)
    }

    fun bitShiftLeft(num: ULong): ULong {
        // 0b01111111 = 0x7F
        return (num and 0x7F7F7F7F7F7F7F7FuL) shl 1
    }

    fun bitShiftLeft2(num: ULong): ULong {
        // 0b00111111 = 0x3F
        return (num and 0x3F3F3F3F3F3F3F3FuL) shl 2
    }

    fun bitShiftRight(num: ULong): ULong {
        // 0b11111110 0xFE
        return (num and 0xFEFEFEFEFEFEFEFEuL) shr 1
    }

    fun bitShiftRight2(num: ULong): ULong {
        // 0b11111100 0xFC
        return (num and 0xFCFCFCFCFCFCFCFCuL) shr 2
    }

    fun bitShiftRight3(num: ULong): ULong {
        // 0b11111000 0xF8
        return (num and 0xF8F8F8F8F8F8F8F8uL) shr 3
    }

    fun rotate180(num: ULong): ULong {
        var out = 0uL
        for (i in 0 ..< 64) {
            val mask = 1uL shl i
            if ((mask and num) > 0uL) {
                val reverseMask = 1uL shl (63 - i)
                out = out or reverseMask
            }

        }
        return out
    }

    fun uLongToHexString(num: ULong, prefix: String = "0x"): String {
        return "$prefix${num.toString(16).padStart(16, '0')}"
    }

    fun createMoveSet(board: BoardData, pieces: ULong, directions: Set<(num: ULong) -> ULong>): Set<Move> {
        val moves = mutableSetOf<Move>()

        val whitePieces = board.whitePieces()
        val blackPieces = board.blackPieces()

        val piecesSet = BitUtils.oneBitsToSet(pieces)
        for (piece in piecesSet) {
            for (direction in directions) {
                moves.addAll(BitUtils.repeatUntilBlocked(whitePieces, blackPieces, piece, direction))
            }
        }

        return moves
    }

    fun repeatUntilBlocked(whitePieces: ULong, blackPieces: ULong, piece: ULong, direction: (num: ULong) -> ULong): Set<Move> {
        val moves = mutableSetOf<Move>()

        var move = direction(piece)
        while ((move != 0uL) and ((move and whitePieces.inv()) != 0uL)) {
            moves.add(Move(
                originalSquare = piece,
                move = move,
            ))
            if ((move and blackPieces.inv()) == 0uL) {
                break
            }
            move = direction(move)
        }

        return moves
    }

    fun uLongBinaryOf(moves: Set<Move>): ULongBinary {
        var value = 0uL
        for (move in moves) {
            value = value or move.move
        }
        return ULongBinary(value)
    }

    fun uLongBinaryOf(move: Move): ULongBinary {
        return ULongBinary(move.move)
    }

    fun uLongBinaryOf(value: ULong): ULongBinary {
        return ULongBinary(value)
    }
}
