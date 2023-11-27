package chess

data class ULongBinary(
  val value: ULong,
) {
    override fun toString(): String {
        val bytes = BitUtils.uLongToUBytes(value)
        val byteStrings = bytes.map { byte -> "0b${byte.toString(2).padStart(8, '0')}" }
        val str = byteStrings.joinToString("\n")

        return "\n$str"
    }
}

