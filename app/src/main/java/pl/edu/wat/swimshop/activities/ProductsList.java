package pl.edu.wat.swimshop.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import pl.edu.wat.swimshop.R;
import pl.edu.wat.swimshop.adapter.ProductsAdapter;
import pl.edu.wat.swimshop.entity.Products;
import pl.edu.wat.swimshop.retrofit.ProductsApi;
import pl.edu.wat.swimshop.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsList extends AppCompatActivity {

    List<Products> products;
    ListView listView;
    FloatingActionButton createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);
        listView = findViewById(R.id.list_item);
        createButton = findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callCreate();
            }
        });

        allProducts();
    }


    private void allProducts() {
        RetrofitService retrofitService = new RetrofitService();
        ProductsApi productsApi = retrofitService.getRetrofit().create(ProductsApi.class);

        productsApi.showAll()
                .enqueue(new Callback<List<Products>>() {
                    @Override
                    public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                        if(!response.isSuccessful()){
                            Log.e("Response err: ", response.message());
                            return;
                        }
                        products = response.body();
                        ProductsAdapter productsAdapter = new ProductsAdapter(products, getApplicationContext());
                        listView.setAdapter(productsAdapter);
                        products.forEach(p -> Log.i("Books: ",p.toString()));

                        System.out.println("XD");


                    }

                    @Override
                    public void onFailure(Call<List<Products>> call, Throwable t) {
                        Toast.makeText(ProductsList.this,"Failed to load Authors",Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void callCreate() {
        Intent intent = new Intent(getApplicationContext(), ProductsForm.class);
        startActivity(intent);
    }

}

