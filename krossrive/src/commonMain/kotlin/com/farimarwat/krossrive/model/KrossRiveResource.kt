package com.farimarwat.krossrive.model

sealed class KrossRiveResource {
    data class Bytes(val data: ByteArray) : KrossRiveResource() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || this::class != other::class) return false

            other as Bytes

            if (!data.contentEquals(other.data)) return false

            return true
        }

        override fun hashCode(): Int {
            return data.contentHashCode()
        }
    }

    data class Url(val url: String) : KrossRiveResource()
}