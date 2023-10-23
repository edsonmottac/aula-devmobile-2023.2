package com.example.firebasebucket;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btGaleria, btUpload;
    private ImageView imgView;

    ActivityResultLauncher<Intent> startResultActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btGaleria = findViewById(R.id.btGaleria);
        btUpload = findViewById(R.id.btUpload);
        imgView = findViewById(R.id.imgView);

        startResultActivity = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

                if (result.getResultCode() == Activity.RESULT_OK) {

                    Toast.makeText(MainActivity.this, "CHEGOU AQUI!", Toast.LENGTH_SHORT).show();


                    Bitmap image = null;

                    Uri local = result.getData().getData();
                    ImageDecoder.Source source = ImageDecoder.createSource(getContentResolver(), local);

                    try {
                        image = ImageDecoder.decodeBitmap(source);
                        imgView.setImageBitmap(image);

                    } catch (Exception ex) {
                        Toast.makeText(MainActivity.this, "DEU RUIM!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startResultActivity.launch(i);
            }
        });

        btUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "UPLOAD DO ARQUIVO PARA O FIREBASE", Toast.LENGTH_SHORT).show();
            }
        });

    }
}