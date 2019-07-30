package com.kassemitani.newsapi.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kassemitani.newsapi.R;
import com.kassemitani.newsapi.viewmodel.ArticleViewModel;

public class DetailFragment extends Fragment {

    ArticleViewModel articleViewModel;
    private ImageView imgThumbnail;
    private TextView tvSource, tvPublishedAt, tvAuthor, tvDescription, tvTitle;
    private Activity context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mFragmentRootView = inflater.inflate(R.layout.fragment_detail, container, false);
        this.init(mFragmentRootView);

        tvSource.setText("Source: " + articleViewModel.getSource());
        tvAuthor.setText("Author: " + articleViewModel.getSource());
        tvPublishedAt.setText("Published at: " + articleViewModel.getPublishedAt());
        tvTitle.setText(articleViewModel.getTitle());
        tvDescription.setText(articleViewModel.getDescription());

        if (articleViewModel.getImage() == null) {
            imgThumbnail.setImageResource(R.drawable.noimage);
        } else {
            Glide.with(this)
                    .load(articleViewModel.getImage())
                    .into(imgThumbnail);
        }
        return mFragmentRootView;
    }

    private void init(View v) {
        this.imgThumbnail = v.findViewById(R.id.imgThumbnail);
        this.tvTitle = v.findViewById(R.id.tvTitle);
        this.tvAuthor = v.findViewById(R.id.tvAuthor);
        this.tvSource = v.findViewById(R.id.tvSource);
        this.tvPublishedAt = v.findViewById(R.id.tvPublishedAt);
        this.tvDescription = v.findViewById(R.id.tvDesc);
    }

    public static DetailFragment getInstance(ArticleViewModel articleViewModel) {
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.articleViewModel = articleViewModel;
        return detailFragment;
    }

}
