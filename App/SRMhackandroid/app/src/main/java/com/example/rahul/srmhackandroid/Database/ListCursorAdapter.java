package com.example.rahul.srmhackandroid.Database;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rahul.srmhackandroid.R;
import com.example.rahul.srmhackandroid.ResultDetailsActivity;
import com.squareup.picasso.Picasso;

public class ListCursorAdapter extends CursorAdapter {

    public ListCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.history_item, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        final TextView text =  view.findViewById(R.id.text);
        TextView date =  view.findViewById(R.id.date);
        TextView time =  view.findViewById(R.id.time);
        ImageView imageView = view.findViewById(R.id.history_image);
        LinearLayout linearLayout = view.findViewById(R.id.layout);
        // Extract properties from cursor
        final String Text = cursor.getString(cursor.getColumnIndexOrThrow(DyslecContract.DyslecEntry.TEXT));
        final String src = cursor.getString(cursor.getColumnIndexOrThrow(DyslecContract.DyslecEntry.SRC));
        String Date = cursor.getString(cursor.getColumnIndexOrThrow(DyslecContract.DyslecEntry.DATE));
        String Time = cursor.getString(cursor.getColumnIndexOrThrow(DyslecContract.DyslecEntry.TIME));
        // Populate fields with extracted properties

        date.setText(Date);
        time.setText(Time);

        text.setText(Text);
        Picasso.get().load(src).into(imageView);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(context,ResultDetailsActivity.class);
                i.putExtra("path",src);
                i.putExtra("text",Text);
                context.startActivity(i);
            }
        });
    }
}
