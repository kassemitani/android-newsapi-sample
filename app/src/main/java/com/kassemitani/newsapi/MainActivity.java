package com.kassemitani.newsapi;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kassemitani.newsapi.fragments.DetailFragment;
import com.kassemitani.newsapi.fragments.MainFragment;
import com.kassemitani.newsapi.viewmodel.ArticleViewModel;

import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity implements DialogInterface.OnClickListener, MainListener {

    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(MainFragment.getInstance(this));

        alertDialog = new AlertDialog.Builder(this).setTitle("Exit App")
                .setMessage("Are you sure you want to exit this app?")
                .setPositiveButton(android.R.string.yes, this)
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert).create();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            if(!alertDialog.isShowing())
                alertDialog.show();
        }
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int which) {
        super.onBackPressed();
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment);
        if(!(fragment instanceof MainFragment))
            ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }

    public Fragment getCurrentFragment() {
        List<Fragment> a = getSupportFragmentManager().getFragments();
        ListIterator<Fragment> li = a.listIterator(a.size());
        while (li.hasPrevious()) {
            return li.previous();
        }
        return null;
    }

    @Override
    public void onArticlePressed(ArticleViewModel articleViewModel) {
        replaceFragment(DetailFragment.getInstance(articleViewModel));
    }
}
