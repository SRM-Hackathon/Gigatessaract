package com.example.rahul.srmhackandroid.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rahul.srmhackandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConnectFragment extends Fragment {


    public ConnectFragment() {
        // Required empty public constructor
    }

    TextView DescText;
    Boolean aBoolean = false;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_connect, container, false);


        DescText = rootView.findViewById(R.id.descText);
        listView = rootView.findViewById(R.id.chatHistoryList);
        DescText.setText("Chat with mentors");

        rootView.findViewById(R.id.switchButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aBoolean = !aBoolean;
                if(aBoolean) {
                    DescText.setText("Chat with peers");
                    //TODO get online peers
                }
                else {
                    DescText.setText("Chat with mentors");
                    //TODO get online mentors
                }
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Connect");
    }

}
