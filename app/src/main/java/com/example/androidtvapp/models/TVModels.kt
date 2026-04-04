package com.example.androidtvapp.models
import android.net.Uri
data class Channel(
    val id: Long,
    val displayName: String,
    val logoUri: Uri?,
    val description: String
)
data class PreviewProgram(
    val id: Long,
    val channelId: Long,
    val title: String,
    val description: String,
    val posterArtUri: Uri?,
    val thumbnailUri: Uri?,
    val duration: Long,
    val intentUri: Uri?
)
data class WatchNextProgram(
    val id: Long,
    val title: String,
    val description: String,
    val posterArtUri: Uri?,
    val progress: Int,
    val duration: Long,
    val intentUri: Uri?
)