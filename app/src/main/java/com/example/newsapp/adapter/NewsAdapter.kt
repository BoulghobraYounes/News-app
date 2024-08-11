package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.ItemNewsBinding
import com.example.newsapp.models.Article

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(val itemBinding: ItemNewsBinding): RecyclerView.ViewHolder(itemBinding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val currentItem = differ.currentList[position]
        holder.itemBinding.articleTitle.text = currentItem.title
        holder.itemBinding.articleSource.text = currentItem.source!!.name
        holder.itemBinding.articleDescription.text = currentItem.description
        holder.itemBinding.articleDateTime.text = currentItem.publishedAt
        if (currentItem.urlToImage == null) {
            Glide.with(holder.itemView).load("https://upload.wikimedia.org/wikipedia/commons/9/9b/Google_news_logo.png").into(holder.itemBinding.articleImage)
        }else {
            Glide.with(holder.itemView).load(currentItem.urlToImage).into(holder.itemBinding.articleImage)
        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(currentItem)
            }
        }
    }

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

}