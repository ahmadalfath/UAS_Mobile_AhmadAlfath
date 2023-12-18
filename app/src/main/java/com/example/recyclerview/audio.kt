package com.example.recyclerview

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class audio (
    val gambar:Int,
    val judul:String,
    val data_deskripsi:String,
    val audioId:Int

):Parcelable