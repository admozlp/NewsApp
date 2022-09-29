package com.ademozalp.newsappjava.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ademozalp.newsappjava.activity.WebActivity;
import com.ademozalp.newsappjava.databinding.NewsRowBinding;
import com.ademozalp.newsappjava.model.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    Context context;
    List<Article> articleList;
    public NewsAdapter(List<Article> articleList, Context context){
        this.articleList = articleList;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewsRowBinding newsRowBinding = NewsRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(newsRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(articleList.get(position).getUrlToImage()).into(holder.newsRowBinding.articleAdapterTvImage);
        holder.newsRowBinding.articleAdapterTvTitle.setText(articleList.get(position).getTitle());
        holder.newsRowBinding.articleAdapterTvDescription.setText(articleList.get(position).getDescription());
        holder.newsRowBinding.articleAdapterTvDate.setText(articleList.get(position).getPublishedAt().replace("T","").replace("Z", ""));

        holder.newsRowBinding.articleAdapterLlParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = articleList.get(position).getUrl();
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("url", url);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        NewsRowBinding newsRowBinding;
        public ViewHolder(NewsRowBinding newsRowBinding) {
            super(newsRowBinding.getRoot());
            this.newsRowBinding = newsRowBinding;
        }
    }
}
