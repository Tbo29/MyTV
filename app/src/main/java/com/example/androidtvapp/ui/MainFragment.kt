package com.example.androidtvapp.ui
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtvapp.R
import com.example.androidtvapp.utils.ContentProviderHelper
class MainFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val previewRecyclerView = view.findViewById<RecyclerView>(R.id.preview_tiles_recycler)
        previewRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val previewPrograms = ContentProviderHelper.getPreviewPrograms(requireContext().contentResolver)
        val adapter = PreviewTileAdapter(previewPrograms) { program ->
            program.intentUri?.let { val intent = Intent(Intent.ACTION_VIEW, it); startActivity(intent) }
        }
        previewRecyclerView.adapter = adapter
    }
}
