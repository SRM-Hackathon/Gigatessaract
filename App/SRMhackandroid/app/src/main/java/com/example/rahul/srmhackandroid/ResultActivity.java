package com.example.rahul.srmhackandroid;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rahul.srmhackandroid.Database.DyslecContract;
import com.example.rahul.srmhackandroid.RestAPI.ApiClient;
import com.example.rahul.srmhackandroid.RestAPI.ApiInterface;
import com.example.rahul.srmhackandroid.RestAPI.ImageInformation;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity {

    private FirebaseStorage mFirebaseStorage;
    private StorageReference mChatPhotosStorageReference;
    Uri url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String path = getIntent().getExtras().getString("Path");
        Boolean state = getIntent().getBooleanExtra("bool", false);
        Log.i("Received file", path);

        ImageView imageView = findViewById(R.id.display);

        if (state) {
            File imgFile = new File(path);
            if (imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
                path = "file://" + imgFile.getPath();
            }
        } else Picasso.get().load(path).into(imageView);

        sendToServer(path);



        mFirebaseStorage = FirebaseStorage.getInstance();
        mChatPhotosStorageReference = mFirebaseStorage.getReference().child("photos");

        int time = (int) (System.currentTimeMillis());
        Timestamp tsTemp = new Timestamp(time);
        String ts = tsTemp.toString();

        // Get a reference to store file at chat_photos/<FILENAME>
        final StorageReference photoRef = mChatPhotosStorageReference.child(ts);

        UploadTask uploadTask = photoRef.putFile(Uri.parse(path));

        final String Img_path = path;

// Register observers to listen for when the download is done or if it fails
        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return photoRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    url = task.getResult();
                    Log.i("Final URL",url.toString());

//                    sendToServer(url.toString());
                    //TODO send the link to server, get result, display
                    addToDB("Place holder text....... ",Img_path);
                }
            }
        });
    }

    private void sendToServer(String s) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        ImageInformation imageInformation = new ImageInformation(s,0,"jpeg");

        Call<String> exampleCall = apiService.postData(imageInformation);

        exampleCall.enqueue(new Callback<String>()
        {
            @Override
            public void onResponse (Call < String > call, Response< String > response){
//                int res_code = response.body().getRes_code();
//                if(res_code!=200){
//                    Log.v("server_status","SUCCESS");
//                }
//                else{
//                    Log.v("server_status","FAILURE");
//                }

                Log.i("Response String",response.body());
            }

            @Override
            public void onFailure (Call < String > call, Throwable t){
                Log.i("Failed",t.getMessage());
            }
        });

    }

    public void addToDB(String text, String path){
        ContentValues values = new ContentValues();
        values.put(DyslecContract.DyslecEntry.TEXT,text);
        values.put(DyslecContract.DyslecEntry.SRC,path);

        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+5:30"));
        Date currentLocalTime = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm a");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));

        String time = dateFormat.format(currentLocalTime);

        values.put(DyslecContract.DyslecEntry.DATE,date);
        values.put(DyslecContract.DyslecEntry.TIME,time);
        values.put(DyslecContract.DyslecEntry.UPDATE,new Date().getTime());

        Uri newUri = getContentResolver().insert(DyslecContract.DyslecEntry.CONTENT_URI, values);
        // Show a toast message depending on whether or not the insertion was successful
        if (newUri == null) {
            Toast.makeText(this, getString(R.string.editor_insert_failed),
                    Toast.LENGTH_SHORT).show();
        }
    }
}

