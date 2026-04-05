package com.example.androidtvapp.providers

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import android.provider.BaseColumns
import android.media.tv.TvContract

class TVContentProvider : ContentProvider() {
    companion object {
        private const val AUTHORITY = "com.example.androidtvapp.tv"
        private val URI_MATCHER = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTHORITY, "channels", 1)
            addURI(AUTHORITY, "preview_programs", 2)
            addURI(AUTHORITY, "watch_next_programs", 3)
        }
    }

    override fun onCreate(): Boolean = true

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        return when (URI_MATCHER.match(uri)) {
            1 -> queryChannels(projection)
            2 -> queryPreviewPrograms(projection)
            3 -> queryWatchNextPrograms(projection)
            else -> null
        }
    }

    private fun queryChannels(projection: Array<String>?): Cursor {
        val cursor = MatrixCursor(
            projection ?: arrayOf(
                BaseColumns._ID,
                TvContract.Channels.COLUMN_DISPLAY_NAME,
                TvContract.Channels.COLUMN_LOGO_URI,
                TvContract.Channels.COLUMN_DESCRIPTION
            )
        )
        cursor.addRow(arrayOf(1L, "Channel 1", "https://example.com/logo1.png", "Sample Channel 1"))
        cursor.addRow(arrayOf(2L, "Channel 2", "https://example.com/logo2.png", "Sample Channel 2"))
        return cursor
    }

    private fun queryPreviewPrograms(projection: Array<String>?): Cursor {
        val cursor = MatrixCursor(
            projection ?: arrayOf(
                BaseColumns._ID,
                TvContract.PreviewPrograms.COLUMN_CHANNEL_ID,
                TvContract.PreviewPrograms.COLUMN_TITLE,
                TvContract.PreviewPrograms.COLUMN_DESCRIPTION,
                TvContract.PreviewPrograms.COLUMN_POSTER_ART_URI,
                TvContract.PreviewPrograms.COLUMN_THUMBNAIL_URI,
                TvContract.PreviewPrograms.COLUMN_DURATION_MILLIS,
                TvContract.PreviewPrograms.COLUMN_INTENT_URI
            )
        )
        cursor.addRow(
            arrayOf(
                1L,
                1L,
                "Program 1",
                "Description 1",
                "https://example.com/poster1.jpg",
                "https://example.com/thumb1.jpg",
                3600000L,
                "intent://example.com/program1"
            )
        )
        cursor.addRow(
            arrayOf(
                2L,
                1L,
                "Program 2",
                "Description 2",
                "https://example.com/poster2.jpg",
                "https://example.com/thumb2.jpg",
                3600000L,
                "intent://example.com/program2"
            )
        )
        return cursor
    }

    private fun queryWatchNextPrograms(projection: Array<String>?): Cursor {
        val cursor = MatrixCursor(
            projection ?: arrayOf(
                BaseColumns._ID,
                TvContract.WatchNextPrograms.COLUMN_TITLE,
                TvContract.WatchNextPrograms.COLUMN_DESCRIPTION,
                TvContract.WatchNextPrograms.COLUMN_POSTER_ART_URI,
                TvContract.WatchNextPrograms.COLUMN_PROGRESS_PERCENT,
                TvContract.WatchNextPrograms.COLUMN_DURATION_MILLIS,
                TvContract.WatchNextPrograms.COLUMN_INTENT_URI
            )
        )
        cursor.addRow(
            arrayOf(
                1L,
                "Continue Watching 1",
                "Resume from 30 minutes",
                "https://example.com/watch_next1.jpg",
                30,
                3600000L,
                "intent://example.com/continue1"
            )
        )
        return cursor
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int = 0

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int = 0

    override fun getType(uri: Uri): String? = null
}