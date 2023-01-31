package pl.edu.wat.swimshop.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDateTime;

import pl.edu.wat.swimshop.R;
import pl.edu.wat.swimshop.dto.ProductsRequest;
import pl.edu.wat.swimshop.entity.Products;
import pl.edu.wat.swimshop.retrofit.ProductsApi;
import pl.edu.wat.swimshop.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsForm extends AppCompatActivity {

    EditText nameText;
    EditText priceText;
    EditText producerIdText;
    Button createButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_form);

        nameText = findViewById(R.id.nameText);
        priceText = findViewById(R.id.namePriceText);
        producerIdText = findViewById(R.id.nameAuthorIdText);
        createButton = findViewById(R.id.createButton);


        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductsRequest productsRequest = new ProductsRequest(nameText.getText().toString(),Double.parseDouble(priceText.getText().toString()),producerIdText.getText().toString());
                create(productsRequest);
            }
        });


    }

    private void create(ProductsRequest productsRequest){
        RetrofitService retrofitService = new RetrofitService();
        ProductsApi productsApi = retrofitService.getRetrofit().create(ProductsApi.class);
        Call<Products> call = productsApi.create(productsRequest);

        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
                    Log.e("Response err:", response.message());
                    return;
                }
                Products products = response.body();
                Toast.makeText(getApplicationContext(), products.getName() + " created !", Toast.LENGTH_LONG).show();
                callMain();
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("Response err:", t.getMessage());

            }
        });
    }

    private void callMain() {
        Intent intent = new Intent(getApplicationContext(), ProductsList.class);
        startActivity(intent);
    }


}