implementationResolvere.androidtvapp.utils

import android.content.ContentResolver
import android.media.tv.TvContract
import android.net.Uri

object ContentProviderHelper {
    fun getChannels(contentResolver: ContentResolver): List<Channel> {
        // Your implementation
        return emptyList()
    }
    
    fun getPreviewPrograms(contentResolver: ContentResolver): List<PreviewProgram> {
        // Your implementation
        return emptyList()
    }
    
    fun getWatchNextPrograms(contentResolver: ContentResolver): List<WatchNextProgram> {
        // Your implementation
        return emptyList()
    }
}
