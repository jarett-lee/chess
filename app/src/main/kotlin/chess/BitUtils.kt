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

    fun bitShiftRight(num: ULong): ULong {
        // 0b11111110 0xFE
        return (num and 0xFEFEFEFEFEFEFEFEuL) shr 1
    }

    fun uLongToHexString(num: ULong, prefix: String = "0x"): String {
        return "$prefix${num.toString(16).padStart(16, '0')}"
    }
}
