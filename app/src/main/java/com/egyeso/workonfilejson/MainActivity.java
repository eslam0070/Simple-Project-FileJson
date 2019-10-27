package com.egyeso.workonfilejson;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecycler;
    Adapter adapter;
    List<Model> modelList = new ArrayList<>();;
    ArrayList<Reviews>reviewsArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycler = findViewById(R.id.recycler);
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        addMenuItemsFromJson();


    }

    private void addMenuItemsFromJson() {
        try {
            String jsonDataString = readJsonDataFromFile();
            JSONObject menuItemsObject = new JSONObject(jsonDataString);
            JSONArray menuItemsArray = menuItemsObject.getJSONArray("data");
            for (int i = 0 ; i < menuItemsArray.length() ; i++){
                Model model = new Model();
                JSONObject menuItemObject = menuItemsArray.getJSONObject(i);
                model.setId(menuItemObject.getString("id"));
                model.setName(menuItemObject.getString("name"));
                model.setImage(menuItemObject.getString("imgUrl"));
                model.setPrice(menuItemObject.getInt("price"));
                model.setType(menuItemObject.getInt("type"));
                model.setGender(menuItemObject.getInt("gender"));
                model.setCompanyId(menuItemObject.getString("companyId"));
                JSONArray jsonArray1 =menuItemObject.getJSONArray("reviews");
                reviewsArrayList = new ArrayList<>();
                for (int u = 0 ; u < jsonArray1.length() ; u++){
                    Reviews reviews = new Reviews();
                    JSONObject jsonObject =jsonArray1.getJSONObject(u);
                    reviews.setUsername(jsonObject.getString("username"));
                    reviews.setRate(jsonObject.getDouble("rate"));
                    reviewsArrayList.add(reviews);
                }
                model.setReviews(reviewsArrayList);
                modelList.add(model);
            }
            adapter = new Adapter(modelList,this);
            mRecycler.setAdapter(adapter);
            onClickItemListener();
        }catch (IOException | JSONException e){
            Log.e(MainActivity.class.getName(),"Unable to parse JSON file",e);
        }
    }

    private void onClickItemListener() {
        adapter.setOnItemClickListener(new Adapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Model model = modelList.get(position);
                Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                intent.putExtra("name",model.getName());
                intent.putExtra("price",model.getPrice());
                intent.putExtra("image",model.getImage());
                reviewsArrayList = model.getReviews();
                startActivity(intent);
            }
        });
    }

    private String readJsonDataFromFile() throws IOException{
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();
        try {
            String jsonDataString;
            inputStream = getAssets().open("test2.json");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            while ((jsonDataString = bufferedReader.readLine()) != null){
                builder.append(jsonDataString);
            }
        }finally {
            if (inputStream != null)
                inputStream.close();
        }
        return new String(builder);
    }
}
