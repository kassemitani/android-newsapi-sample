package com.kassemitani.newsapi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.kassemitani.newsapi.MainListener;
import com.kassemitani.newsapi.R;
import com.kassemitani.newsapi.model.Article;
import com.kassemitani.newsapi.viewmodel.ArticleViewModel;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Article> articlesList;
    private Context context;
    private MainListener mainListener;

    public Adapter(List<Article> articlesList, Context context, MainListener mainListener) {
        this.articlesList = articlesList;
        this.context = context;
        this.mainListener = mainListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(this.context).
                inflate(R.layout.article_item_layout,
                        viewGroup,false);
        return new ViewHolder(view);
    }

    public void addMoreArticle(List<Article> articles)
    {
        int previousPos=this.articlesList.size();
        this.articlesList.addAll(articles);
        notifyItemRangeInserted(previousPos-1,previousPos);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Article article = this.articlesList.get(i);
        viewHolder.tvTitle.setText(article.getTitle());
        viewHolder.tvDesc.setText(article.getDescription().length()<=70 ? article.getDescription() : article.getDescription().substring(0, 70) + "....");

        if (article.getUrlToImage() == null) {
            viewHolder.imgThumbnail.setImageResource(R.drawable.noimage);
        }
        else{
            Glide.with(this.context)
                    .load(article.getUrlToImage())
                    .override(100,100)
                    .into(viewHolder.imgThumbnail);
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArticleViewModel articleViewModel = new ArticleViewModel(article);
                mainListener.onArticlePressed(articleViewModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.articlesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgThumbnail;
        private TextView tvTitle, tvDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgThumbnail = itemView.findViewById(R.id.imgThumbnail);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDesc = itemView.findViewById(R.id.tvDesc);
        }
    }
}
