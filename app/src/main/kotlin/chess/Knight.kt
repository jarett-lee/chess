package chess

object Knight {
    fun boards(board: BoardData): Set<BoardData> {
        val boards = mutableSetOf<BoardData>()
        val unblockedSquares = board.whitePieces().inv()

        val whiteKnights = BitUtils.oneBitsToSet(board.whiteKnights)
        for (whiteKnight in whiteKnights) {
            val left2up1 = BitUtils.bitShiftUp(BitUtils.bitShiftLeft2(whiteKnight))
            val left1up2 = BitUtils.bitShiftUp(BitUtils.bitShiftLeft(whiteKnight), 2)
            val right1up2 = BitUtils.bitShiftUp(BitUtils.bitShiftRight(whiteKnight), 2)
            val right2up1 = BitUtils.bitShiftUp(BitUtils.bitShiftRight2(whiteKnight))
            val right2down1 = BitUtils.bitShiftDown(BitUtils.bitShiftRight2(whiteKnight))
            val right1down2 = BitUtils.bitShiftDown(BitUtils.bitShiftRight(whiteKnight), 2)
            val left1down2 = BitUtils.bitShiftDown(BitUtils.bitShiftLeft(whiteKnight), 2)
            val left2down1 = BitUtils.bitShiftDown(BitUtils.bitShiftLeft2(whiteKnight))

            if ((left2up1 != 0uL) and (left2up1 and unblockedSquares != 0uL)) {
                boards.add(board.knightCaptureBoard(whiteKnight, left2up1))
            }
            if ((left1up2 != 0uL) and (left1up2 and unblockedSquares != 0uL)) {
                boards.add(board.knightCaptureBoard(whiteKnight, left1up2))
            }
            if ((right1up2 != 0uL) and (right1up2 and unblockedSquares != 0uL)) {
                boards.add(board.knightCaptureBoard(whiteKnight, right1up2))
            }
            if ((right2up1 != 0uL) and (right2up1 and unblockedSquares != 0uL)) {
                boards.add(board.knightCaptureBoard(whiteKnight, right2up1))
            }
            if ((right2down1 != 0uL) and (right2down1 and unblockedSquares != 0uL)) {
                boards.add(board.knightCaptureBoard(whiteKnight, right2down1))
            }
            if ((right1down2 != 0uL) and (right1down2 and unblockedSquares != 0uL)) {
                boards.add(board.knightCaptureBoard(whiteKnight, right1down2))
            }
            if ((left1down2 != 0uL) and (left1down2 and unblockedSquares != 0uL)) {
                boards.add(board.knightCaptureBoard(whiteKnight, left1down2))
            }
            if ((left2down1 != 0uL) and (left2down1 and unblockedSquares != 0uL)) {
                boards.add(board.knightCaptureBoard(whiteKnight, left2down1))
            }
        }

        return boards
    }
}
