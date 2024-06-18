package com.despol.attractions;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class MainActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageView coverImageView = findViewById(R.id.coverImageView);
        TextView tvName = findViewById(R.id.tvName);
        TextView tvCountry = findViewById(R.id.tvCountry);
        TextView tvCity = findViewById(R.id.tvCity);
        TextView tvCrDate = findViewById(R.id.tvCrDate);
        TextView tvCategory = findViewById(R.id.tvCategory);
        TextView tvDescription = findViewById(R.id.tvDescription);
        Button mapButton = findViewById(R.id.mapButton);

        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        String country = intent.getStringExtra("Country");
        String city = intent.getStringExtra("City");
        String crDate = intent.getStringExtra("CrDate");
        String category = intent.getStringExtra("Category");
        String description = intent.getStringExtra("Description");
        String coverImage = intent.getStringExtra("CoverImage");

        tvName.setText(name);
        tvCountry.setText(country);
        tvCity.setText(city);
        tvCrDate.setText(crDate);
        tvCategory.setText(category);
        tvDescription.setText(description);

        Picasso.get()
                .load(coverImage)
                .into(coverImageView);

        mapButton.setOnClickListener(v -> {
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(name + ", " + city + ", " + country));
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            }
        });
    }
}
