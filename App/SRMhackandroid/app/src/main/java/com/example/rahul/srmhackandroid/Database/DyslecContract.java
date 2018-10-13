package com.example.rahul.srmhackandroid.Database;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class DyslecContract {

    private DyslecContract(){}

    public static final String CONTENT_AUTHORITY = "com.example.rahul.srmhackandroid";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);


    public static final String PATH_DYSLEC = "Dyslec";

    public static final class DyslecEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_DYSLEC);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DYSLEC;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DYSLEC;

        public static final String TABLE_NAME = "History";

        public static final String ID = BaseColumns._ID;
        public static final String TEXT = "Text";
        public static final String SRC = "Srx";
        public static final String DATE = "Date";
        public static final String TIME = "Time";
        public static final String UPDATE = "ModifiedDateTime";
        public static final int SORT_DATE = 1;
        public static final int SORT_NAME = 2;
        public static final int SORT_ID = 0;
    }

}
