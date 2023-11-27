package chess

data class Move(
  val originalSquare: ULong,
  val move: ULong,
) {
    override fun toString(): String {
        val attributes = listOf(
            Pair("originalSquare", originalSquare),
            Pair("move", move),
        )
        val attributeStrings = attributes.map { "${it.first}=${BitUtils.uLongToHexString(it.second)}" }
        val attributeString = attributeStrings.joinToString()

        return "Move($attributeString)"
    }
}
