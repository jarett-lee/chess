package chess

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class BitUtilsTest {
    @Test fun boardDataUBytesToULongSuccess() {
        val actual = BitUtils.uBytesToULong(listOf<UByte>(
            0b10000000u,
            0b00100001u,
            0b01000000u,
            0b00001000u,
            0b00010000u,
            0b00000010u,
            0b10000100u,
            0b00000001u,
        ))
        val expected = 0b1000000000100001010000000000100000010000000000101000010000000001uL
        assertEquals(expected, actual)
    }

    @Test fun boardDataUBytesToULongListSize9() {
        assertFailsWith(
            exceptionClass = IllegalArgumentException::class,
            block = {
                BitUtils.uBytesToULong(listOf<UByte>(
                    0b10000000u,
                    0b00100001u,
                    0b01000000u,
                    0b00001000u,
                    0b00010000u,
                    0b00000010u,
                    0b10000100u,
                    0b00000001u,
                    0b00000000u,
                ))
            },
        )
    }

    @Test fun boardDataUBytesToULongListSize7() {
        assertFailsWith(
            exceptionClass = IllegalArgumentException::class,
            block = {
                BitUtils.uBytesToULong(listOf<UByte>(
                    0b10000000u,
                    0b00100001u,
                    0b01000000u,
                    0b00001000u,
                    0b00010000u,
                    0b00000010u,
                    0b10000100u,
                ))
            },
        )
    }

    @Test fun boardDataULongToUByteSuccess() {
        val actual = BitUtils.uLongToUBytes(0b1000000000100001010000000000100000010000000000101000010000000001uL)
        val expected = listOf<UByte>(
            0b10000000u,
            0b00100001u,
            0b01000000u,
            0b00001000u,
            0b00010000u,
            0b00000010u,
            0b10000100u,
            0b00000001u,
        )
        assertEquals(expected, actual)
    }

    @Test fun boardDataOneBitsToSetEmptySet() {
        val actual = BitUtils.oneBitsToSet(0uL)
        val expected = setOf<ULong>()
        assertEquals(expected, actual)
    }

    @Test fun boardDataOneBitsToSetOneSet() {
        val actual = BitUtils.oneBitsToSet(1uL)
        val expected = setOf<ULong>(1uL)
        assertEquals(expected, actual)
    }

    @Test fun boardDataOneBitsToSetTwoSet() {
        val actual = BitUtils.oneBitsToSet(3uL)
        val expected = setOf<ULong>(1uL, 2uL)
        assertEquals(expected, actual)
    }

    @Test fun boardDataOneBitsToSetThreeSet() {
        val actual = BitUtils.oneBitsToSet(0b1000000000000000000000000010000000000000000000000000000000000100uL)
        val expected = setOf<ULong>(
            0b1000000000000000000000000000000000000000000000000000000000000000uL,
            0b0000000000000000000000000010000000000000000000000000000000000000uL,
            0b0000000000000000000000000000000000000000000000000000000000000100uL,
        )
        assertEquals(expected, actual)
    }

    @Test fun boardDataBitShiftUp() {
        val actual = BitUtils.bitShiftUp(BitUtils.uBytesToULong(listOf<UByte>(
            0b01000001u,
            0b00000000u,
            0b00010000u,
            0b10000000u,
            0b00000100u,
            0b00000000u,
            0b00000001u,
            0b10000000u,
        )))
        val expected = BitUtils.uBytesToULong(listOf<UByte>(
            0b00000000u,
            0b00010000u,
            0b10000000u,
            0b00000100u,
            0b00000000u,
            0b00000001u,
            0b10000000u,
            0b00000000u,
        ))
        assertEquals(expected, actual)
    }

    @Test fun boardDataBitShiftDown() {
        val actual = BitUtils.bitShiftDown(BitUtils.uBytesToULong(listOf<UByte>(
            0b01000001u,
            0b00000000u,
            0b00010000u,
            0b10000000u,
            0b00000100u,
            0b00000000u,
            0b00000001u,
            0b10000000u,
        )))
        val expected = BitUtils.uBytesToULong(listOf<UByte>(
            0b00000000u,
            0b01000001u,
            0b00000000u,
            0b00010000u,
            0b10000000u,
            0b00000100u,
            0b00000000u,
            0b00000001u,
        ))
        assertEquals(expected, actual)
    }

    @Test fun boardDataBitShiftUp2() {
        val actual = BitUtils.bitShiftUp(BitUtils.uBytesToULong(listOf<UByte>(
            0b01000001u,
            0b00000000u,
            0b00010000u,
            0b10000000u,
            0b00000100u,
            0b00000000u,
            0b00000001u,
            0b10000000u,
        )), 2)
        val expected = BitUtils.uBytesToULong(listOf<UByte>(
            0b00010000u,
            0b10000000u,
            0b00000100u,
            0b00000000u,
            0b00000001u,
            0b10000000u,
            0b00000000u,
            0b00000000u,
        ))
        assertEquals(expected, actual)
    }

    @Test fun boardDataBitShiftDown2() {
        val actual = BitUtils.bitShiftDown(BitUtils.uBytesToULong(listOf<UByte>(
            0b01000001u,
            0b00000000u,
            0b00010000u,
            0b10000000u,
            0b00000100u,
            0b00000000u,
            0b00000001u,
            0b10000000u,
        )), 2)
        val expected = BitUtils.uBytesToULong(listOf<UByte>(
            0b00000000u,
            0b00000000u,
            0b01000001u,
            0b00000000u,
            0b00010000u,
            0b10000000u,
            0b00000100u,
            0b00000000u,
        ))
        assertEquals(expected, actual)
    }

    @Test fun boardDataBitShiftLeft() {
        val actual = BitUtils.bitShiftLeft(BitUtils.uBytesToULong(listOf<UByte>(
            0b01000001u,
            0b00000000u,
            0b00010000u,
            0b10000000u,
            0b00000100u,
            0b00000000u,
            0b00000001u,
            0b10000000u,
        )))
        val expected = BitUtils.uBytesToULong(listOf<UByte>(
            0b10000010u,
            0b00000000u,
            0b00100000u,
            0b00000000u,
            0b00001000u,
            0b00000000u,
            0b00000010u,
            0b00000000u,
        ))
        assertEquals(expected, actual)
    }

    @Test fun boardDataBitShiftRight() {
        val actual = BitUtils.bitShiftRight(BitUtils.uBytesToULong(listOf<UByte>(
            0b01000001u,
            0b00000000u,
            0b00010000u,
            0b10000000u,
            0b00000100u,
            0b00000000u,
            0b00000001u,
            0b10000000u,
        )))
        val expected = BitUtils.uBytesToULong(listOf<UByte>(
            0b00100000u,
            0b00000000u,
            0b00001000u,
            0b01000000u,
            0b00000010u,
            0b00000000u,
            0b00000000u,
            0b01000000u,
        ))
        assertEquals(expected, actual)
    }
}

