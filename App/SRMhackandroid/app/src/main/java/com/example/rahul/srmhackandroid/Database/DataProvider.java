package com.example.rahul.srmhackandroid.Database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class DataProvider extends ContentProvider {
    DatabaseHelper mdb;
    SQLiteDatabase database;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int DYSLEC = 100;
    private static final int DYSLEC_ID = 101;

    static {
        sUriMatcher.addURI(DyslecContract.CONTENT_AUTHORITY, DyslecContract.PATH_DYSLEC, DYSLEC);
        sUriMatcher.addURI(DyslecContract.CONTENT_AUTHORITY, DyslecContract.PATH_DYSLEC + "/#", DYSLEC_ID);
    }

    /** Tag for the log messages */
    public static final String LOG_TAG = DataProvider.class.getSimpleName();

    @Override
    public boolean onCreate() {
        mdb = new DatabaseHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        // Get readable database
        SQLiteDatabase database = mdb.getReadableDatabase();

        // This cursor will hold the result of the query
        Cursor cursor;

        // Figure out if the URI matcher can match the URI to a specific code
        int match = sUriMatcher.match(uri);
        switch (match) {
            case DYSLEC:
                cursor = database.query(DyslecContract.DyslecEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case DYSLEC_ID:
                selection = DyslecContract.DyslecEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                cursor = database.query(DyslecContract.DyslecEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case DYSLEC:
                return DyslecContract.DyslecEntry.CONTENT_LIST_TYPE;
            case DYSLEC_ID:
                return DyslecContract.DyslecEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case DYSLEC:
                return insertDyslec(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertDyslec(Uri uri, ContentValues values) {


        database = mdb.getWritableDatabase();
        long id = database.insert(DyslecContract.DyslecEntry.TABLE_NAME,null,values);

        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = mdb.getWritableDatabase();

        final int match = sUriMatcher.match(uri);
        int rowsDeleted;
        switch (match) {
            case DYSLEC:
                rowsDeleted = database.delete(DyslecContract.DyslecEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case DYSLEC_ID:
                selection = DyslecContract.DyslecEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                rowsDeleted = database.delete(DyslecContract.DyslecEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case DYSLEC:
                return updateDyslec(uri, contentValues, selection, selectionArgs);
            case DYSLEC_ID:
                selection = DyslecContract.DyslecEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return updateDyslec(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    private int updateDyslec(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        database = mdb.getWritableDatabase();
        int rowsUpdated = database.update(DyslecContract.DyslecEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }
}
