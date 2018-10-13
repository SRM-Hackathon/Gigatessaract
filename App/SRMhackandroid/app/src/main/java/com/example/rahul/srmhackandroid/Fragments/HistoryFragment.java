package com.example.rahul.srmhackandroid.Fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.example.rahul.srmhackandroid.Database.DyslecContract;
import com.example.rahul.srmhackandroid.Database.ListCursorAdapter;
import com.example.rahul.srmhackandroid.R;
import com.example.rahul.srmhackandroid.ResultDetailsActivity;

public class HistoryFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {


    public HistoryFragment() {
        // Required empty public constructor
    }

    private static final int FAVOURITE_LOADER = 0;
    ListView historyList;
    ListCursorAdapter mAdapter;
    TextView empty;
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);


        historyList = rootView.findViewById(R.id.listView);
        empty = rootView.findViewById(R.id.listEmptyView);
        empty.setText("Your History is empty");
        progressBar = rootView.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);
        swipeRefreshLayout = rootView.findViewById(R.id.swiperefresh);
        mAdapter = new ListCursorAdapter(getActivity(),null);
        historyList.setAdapter(mAdapter);
        historyList.setEmptyView(empty);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        historyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i  = new Intent(getActivity(), ResultDetailsActivity.class);


            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(FAVOURITE_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String sort = DyslecContract.DyslecEntry.UPDATE + " DESC";
        return new CursorLoader(getActivity(), DyslecContract.DyslecEntry.CONTENT_URI, null, null, null,sort);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
