package com.kassemitani.newsapi.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kassemitani.newsapi.MainActivity;
import com.kassemitani.newsapi.R;
import com.kassemitani.newsapi.adapter.Adapter;
import com.kassemitani.newsapi.data.Constants;
import com.kassemitani.newsapi.data.ServiceGenerator;
import com.kassemitani.newsapi.data.service.ArticleService;
import com.kassemitani.newsapi.model.Article;
import com.kassemitani.newsapi.model.Info;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class MainFragment extends Fragment {

    private MainActivity context;
    private ProgressBar progressBar;
    private RecyclerView rvArticle;
    private Adapter adapter;
    private List<Article> articlesList = new ArrayList<>();
    private ArticleService service;
    private CompositeDisposable disposable = new CompositeDisposable();

    public static MainFragment getInstance(MainActivity context) {
        MainFragment mainFragment = new MainFragment();
        mainFragment.context = context;
        return mainFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mFragmentRootView = inflater.inflate(R.layout.fragment_main, container, false);
        service = ServiceGenerator.createService(ArticleService.class);
        progressBar = mFragmentRootView.findViewById(R.id.progressbar);
        rvArticle = mFragmentRootView.findViewById(R.id.rvArticle);
        rvArticle.setLayoutManager(new LinearLayoutManager(context));
        adapter = new Adapter(articlesList, context, context);
        rvArticle.setAdapter(adapter);
        if (articlesList.size() == 0) {
            progressBar.setVisibility(View.VISIBLE);
            Flowable<Info> flowable = service.getInfoRX(Constants.getKEYWORD(), Constants.getDateFrom(), Constants.getDateTo(), Constants.getSortBy(), Constants.getApiKey());
            disposable.add(flowable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSubscriber<Info>() {
                        @Override
                        public void onNext(Info info) {
                            articlesList.addAll(info.getArticles());
                            adapter.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Throwable t) {
                            Log.e("ERROR", t.toString());
                            progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onComplete() {
                            Toast.makeText(context, "Complete", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }));
        }
        return mFragmentRootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}
