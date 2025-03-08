package com.example.mywishlistapp.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "wish-title")
    val title : String,
    @ColumnInfo(name = "wish-desc")
    val description: String
)

object DummyWish{
    val wishList = listOf(
        Wish(1,"Google Watch 2","An Android Watch"),
        Wish(2,"Occulus Quest 2","A VR headset"),
        Wish(3,"Sci-Fi Book", "A scientific fiction"),
        Wish(4,"Bean Bag","A comfy substitute of Chair")
    )
}
