package com.example.androidtvapp.utils

import android.content.ContentResolver
import android.media.tv.TvContract
import android.net.Uri

// Data classes for the helper
data class Channel(val id: Long, val name: String)
data class PreviewProgram(val id: Long, val title: String)
data class WatchNextProgram(val id: Long, val title: String)

object ContentProviderHelper {
    fun getChannels(contentResolver: ContentResolver): List<Channel> {
        return emptyList()
    }
    
    fun getPreviewPrograms(contentResolver: ContentResolver): List<PreviewProgram> {
        return emptyList()
    }
    
    fun getWatchNextPrograms(contentResolver: ContentResolver): List<WatchNextProgram> {
        return emptyList()
    }
}
