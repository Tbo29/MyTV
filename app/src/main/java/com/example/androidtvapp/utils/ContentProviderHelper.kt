package com.example.androidtvapp.utils

import android.content.ContentResolver
import android.media.tv.TvContract
import android.net.Uri
import com.example.androidtvapp.models.Channel
import com.example.androidtvapp.models.PreviewProgram
import com.example.androidtvapp.models.WatchNextProgram

object ContentProviderHelper {
    fun getChannels(contentResolver: ContentResolver): List<Channel> {
        val channels = mutableListOf<Channel>()
        val cursor = contentResolver.query(
            TvContract.Channels.CONTENT_URI,
            arrayOf(
                TvContract.Channels._ID,
                TvContract.Channels.COLUMN_DISPLAY_NAME,
                LOGO_URI,
                TvContract.Channels.COLUMN_DESCRIPTION
            ),
            null,
            null,
            null
        )
        cursor?.use {
            while (it.moveToNext()) {
                channels.add(
                    Channel(
                        id = it.getLong(0),
                        displayName = it.getString(1),
                        logoUri = it.getString(2)?.let { uri -> Uri.parse(uri) },
                        description = it.getString(3)
                    )
                )
            }
        }
        return channels
    }

    fun getPreviewPrograms(contentResolver: ContentResolver, channelId: Long = -1): List<PreviewProgram> {
        val programs = mutableListOf<PreviewProgram>()
        val uri = if (channelId > 0) {
            TvContract.PreviewPrograms.CONTENT_URI.buildUpon()
                .appendQueryParameter("channel_id", channelId.toString()).build()
        } else {
            TvContract.PreviewPrograms.CONTENT_URI
        }
        val cursor = contentResolver.query(
            uri,
            arrayOf(
                TvContract.PreviewPrograms._ID,
                TvContract.PreviewPrograms.COLUMN_CHANNEL_ID,
                TvContract.PreviewPrograms.COLUMN_TITLE,
                DESCRIPTION,
                TvContract.PreviewPrograms.COLUMN_POSTER_ART_URI,
                TvContract.PreviewPrograms.COLUMN_THUMBNAIL_URI,
                TvContract.PreviewPrograms.COLUMN_DURATION_MILLIS,
                TvContract.PreviewPrograms.COLUMN_INTENT_URI
            ),
            null,
            null,
            null
        )
        cursor?.use {
            while (it.moveToNext()) {
                programs.add(
                    PreviewProgram(
                        id = it.getLong(0),
                        channelId = it.getLong(1),
                        title = it.getString(2),
                        description = it.getString(3),
                        posterArtUri = it.getString(4)?.let { uri -> Uri.parse(uri) },
                        thumbnailUri = it.getString(5)?.let { uri -> Uri.parse(uri) },
                        duration = it.getLong(6),
                        intentUri = it.getString(7)?.let { uri -> Uri.parse(uri) }
                    )
                )
            }
        }
        return programs
    }

    fun getWatchNextPrograms(contentResolver: ContentResolver): List<WatchNextProgram> {
        val programs = mutableListOf<WatchNextProgram>()
        val cursor = contentResolver.query(
            TvContract.WatchNextPrograms.CONTENT_URI,
            arrayOf(
                TvContract.WatchNextPrograms._ID,
                TvContract.WatchNextPrograms.COLUMN_TITLE,
                DESCRIPTION,
                TvContract.WatchNextPrograms.COLUMN_POSTER_ART_URI,
                PROGRESS_PERCENT,
                TvContract.WatchNextPrograms.COLUMN_DURATION_MILLIS,
                TvContract.WatchNextPrograms.COLUMN_INTENT_URI
            ),
            null,
            null,
            null
        )
        cursor?.use {
            while (it.moveToNext()) {
                programs.add(
                    WatchNextProgram(
                        id = it.getLong(0),
                        title = it.getString(1),
                        description = it.getString(2),
                        posterArtUri = it.getString(3)?.let { uri -> Uri.parse(uri) },
                        progress = it.getInt(4),
                        duration = it.getLong(5),
                        intentUri = it.getString(6)?.let { uri -> Uri.parse(uri) }
                    )
                )
            }
        }
        return programs
    }
}
