package chess

class Board(
    val boardData: BoardData = BoardData(
        whitePawns   = 0b1111111100000000uL,
        whiteKnights = 0b01000010uL,
        whiteBishops = 0b00100100uL,
        whiteRooks   = 0b10000001uL,
        whiteQueens  = 0b00010000uL,
        whiteKings   = 0b00001000uL,
        blackPawns   = 0b0000000011111111000000000000000000000000000000000000000000000000uL,
        blackKnights = 0b0100001000000000000000000000000000000000000000000000000000000000uL,
        blackBishops = 0b0010010000000000000000000000000000000000000000000000000000000000uL,
        blackRooks   = 0b1000000100000000000000000000000000000000000000000000000000000000uL,
        blackQueens  = 0b0001000000000000000000000000000000000000000000000000000000000000uL,
        blackKings   = 0b0000100000000000000000000000000000000000000000000000000000000000uL,
        pieceStayed  = 0b1000100111111111000000000000000000000000000000001111111110001001uL,

        whiteTurn = true,
        enPassantSquare = 0uL,
    ),
) {
    fun boards(): Set<BoardData> {
        val boards = mutableSetOf<BoardData>()

        boards.addAll(Pawn.boards(boardData))
        boards.addAll(Knight.boards(boardData))
        boards.addAll(Bishop.boards(boardData))
        boards.addAll(Rook.boards(boardData))
        boards.addAll(Queen.boards(boardData))
        boards.addAll(King.boards(boardData))

        return boards
    }

    fun reverse(): BoardData {
        return boardData.reverse()
    }
}
