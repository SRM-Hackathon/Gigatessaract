package com.example.rahul.srmhackandroid.Chat;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class ChatMessageCustomAdapter extends ArrayAdapter<ChatMessage> {
    public ChatMessageCustomAdapter(Context context, int resource, List<ChatMessage> objects) {
        super(context, resource, objects);
    }
}
