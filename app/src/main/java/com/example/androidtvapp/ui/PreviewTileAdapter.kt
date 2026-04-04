package com.example.androidtvapp.ui
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidtvapp.R
import com.example.androidtvapp.models.PreviewProgram
class PreviewTileAdapter(private val programs: List<PreviewProgram>, private val onItemClick: (PreviewProgram) -> Unit) : RecyclerView.Adapter<PreviewTileAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val posterImage: ImageView = itemView.findViewById(R.id.preview_poster)
        private val titleText: TextView = itemView.findViewById(R.id.preview_title)
        fun bind(program: PreviewProgram) {
            titleText.text = program.title
            program.posterArtUri?.let { uri -> Glide.with(itemView.context).load(uri).centerCrop().into(posterImage) }
            itemView.setOnClickListener { onItemClick(program) }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.preview_tile, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(programs[position])
    }
    override fun getItemCount(): Int = programs.size
}