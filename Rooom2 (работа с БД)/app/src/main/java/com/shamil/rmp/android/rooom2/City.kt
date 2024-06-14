package com.shamil.rmp.android.rooom2

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class City(@PrimaryKey val id: UUID = UUID.randomUUID(), val name: String, val temp: Float){
    constructor(name: String, temp: Float): this(UUID.randomUUID(), name, temp)
}
