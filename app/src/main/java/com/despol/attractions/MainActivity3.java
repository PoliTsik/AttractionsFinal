package com.despol.attractions;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.despol.attractions.ApiService;
import com.despol.attractions.AttractionsModel;
import com.despol.attractions.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity3 extends AppCompatActivity {
    private EditText nameEditText, countryEditText, cityEditText, crDateEditText, categoryEditText, descriptionEditText, coverImageEditText;
    private Button saveButton, takePhotoButton;
    private ImageView photoImageView;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        nameEditText = findViewById(R.id.nameEditText);
        countryEditText = findViewById(R.id.countryEditText);
        cityEditText = findViewById(R.id.cityEditText);
        crDateEditText = findViewById(R.id.crDateEditText);
        categoryEditText = findViewById(R.id.categoryEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        saveButton = findViewById(R.id.saveButton);
        takePhotoButton = findViewById(R.id.takePhotoButton);
        photoImageView = findViewById(R.id.photoImageView);

        takePhotoButton.setOnClickListener(v -> {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        });

        saveButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            String country = countryEditText.getText().toString().trim();
            String city = cityEditText.getText().toString().trim();
            String crDate = crDateEditText.getText().toString().trim();
            String category = categoryEditText.getText().toString().trim();
            String description = descriptionEditText.getText().toString().trim();
            String coverImage = coverImageEditText.getText().toString().trim();

            AttractionsModel newAttraction = new AttractionsModel(0,name, country, city, crDate, category, description, coverImage);

            ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
            Call<Void> call = apiService.saveAttraction(newAttraction);
            saveAttractionToServer();

            // Αποθήκευση των στοιχείων που εισήγαγε ο χρήστης
            // (Λογική αποθήκευσης στο server ή τοπικά)
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK ) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            photoImageView.setImageBitmap(imageBitmap);
        }
    }
    private void saveAttractionToServer() {
        String name = nameEditText.getText().toString();
        String country = countryEditText.getText().toString();
        String city = cityEditText.getText().toString();
        String date = crDateEditText.getText().toString();
        String category = categoryEditText.getText().toString();
        String description = descriptionEditText.getText().toString();


        String imageUrl = ""; // Εδώ θα βάλετε το URL της εικόνας αφού την ανεβάσετε σε έναν server

        AttractionsModel newAttraction = new AttractionsModel(0,name, country, city, date, category, description, imageUrl);

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<Void> call = apiService.saveAttraction(newAttraction);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity3.this, "Attraction saved successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(MainActivity3.this, "Failed to save attraction", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity3.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
