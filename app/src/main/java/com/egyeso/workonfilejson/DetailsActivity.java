package com.egyeso.workonfilejson;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import static com.egyeso.workonfilejson.Adapter.reviewsArrayList;

public class DetailsActivity extends AppCompatActivity {

    ImageView mDImage;
    TextView mDName,mDPrice;
    ListView mDetailsList;
    String name , image;
    int price;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mDImage = findViewById(R.id.d_image);
        mDName = findViewById(R.id.d_name);
        mDPrice = findViewById(R.id.d_price);
        mDetailsList = findViewById(R.id.details_list);
        name = getIntent().getStringExtra("name");
        price = getIntent().getIntExtra("price",0);
        image = getIntent().getStringExtra("image");
        mDName.setText(name);
        mDPrice.setText(Integer.toString(price));
        Glide.with(getApplicationContext()).load(image).into(mDImage);
        ReviewsAdapter reviewsAdapter = new ReviewsAdapter(this,R.layout.item_review,reviewsArrayList);
        mDetailsList.setAdapter(reviewsAdapter);
    }
}
