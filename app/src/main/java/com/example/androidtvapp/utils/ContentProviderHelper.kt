package com.example.androidtvapp.utils

import android.content.ContentResolver
import com.example.androidtvapp.models.Channel
import com.example.androidtvapp.models.PreviewProgram
import com.example.androidtvapp.models.WatchNextProgram

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
