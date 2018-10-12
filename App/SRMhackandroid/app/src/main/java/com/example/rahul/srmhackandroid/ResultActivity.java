package com.example.rahul.srmhackandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.net.URL;
import java.sql.Timestamp;

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



        mFirebaseStorage = FirebaseStorage.getInstance();
        mChatPhotosStorageReference = mFirebaseStorage.getReference().child("photos");

        int time = (int) (System.currentTimeMillis());
        Timestamp tsTemp = new Timestamp(time);
        String ts = tsTemp.toString();

        // Get a reference to store file at chat_photos/<FILENAME>
        final StorageReference photoRef = mChatPhotosStorageReference.child(ts);

        UploadTask uploadTask = photoRef.putFile(Uri.parse(path));

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
                } else {
                    // Handle failures
                    // ...
                }
            }
        });
    }


}

