package com.despol.attractions;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.despol.attractions.ApiService;
import com.despol.attractions.RetrofitClient;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements com.despol.attractions.RecyclerViewInterface {
    RecyclerView recyclerView;
    Spinner filterSpinner;
    Button addButton;
    ArrayList<AttractionsModel> attractionsModels = new ArrayList<>();
    AT_recyclerview_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.mRecycleView);
        filterSpinner= findViewById(R.id.filterSpinner);
        addButton= findViewById(R.id.addButton);

        setUpFilterSpinner();

        adapter = new AT_recyclerview_adapter(this, attractionsModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchAttractions();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });
    }

    private void fetchAttractions() {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<List<AttractionsModel>> call = apiService.getAttractions();

        call.enqueue(new Callback<List<AttractionsModel>>() {
            @Override
            public void onResponse(Call<List<AttractionsModel>> call, Response<List<AttractionsModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    attractionsModels.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AttractionsModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setUpFilterSpinner() {
        String[] categories = getResources().getStringArray(R.array.Categories);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(adapter);

        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = categories[position];
                filterAttractions(selectedCategory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    private void filterAttractions(String category) {
        ArrayList<AttractionsModel> filteredList = new ArrayList<>();
        for (AttractionsModel model : attractionsModels) {
            if (model.getAttractionCategory().equalsIgnoreCase(category)) {
                filteredList.add(model);
            }
        }
        adapter.updateList(filteredList);
    }
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);

        intent.putExtra("Name", attractionsModels.get(position).getAttractionName());
        intent.putExtra("Country", attractionsModels.get(position).getAttractionCountry());
        intent.putExtra("City", attractionsModels.get(position).getAttractionCity());
        intent.putExtra("CrDate", attractionsModels.get(position).getAttractionCrDate());
        intent.putExtra("Category", attractionsModels.get(position).getAttractionCategory());
        intent.putExtra("Description", attractionsModels.get(position).getDescription());
        intent.putExtra("CoverImage", attractionsModels.get(position).getCoverImage());

        startActivity(intent);
    }
}
