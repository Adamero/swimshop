package pl.edu.wat.swimshop.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import pl.edu.wat.swimshop.R;
import pl.edu.wat.swimshop.dialogfragment.DeleteFragment;
import pl.edu.wat.swimshop.entity.Products;
import pl.edu.wat.swimshop.retrofit.DeleteInterface;
import pl.edu.wat.swimshop.retrofit.ProducerApi;
import pl.edu.wat.swimshop.retrofit.ProductsApi;
import pl.edu.wat.swimshop.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsDetail extends AppCompatActivity implements DeleteInterface {

    TextView nameText;
    TextView priceText;
    Button editButton;
    Button deleteButton;
    Products products;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_detail);


        nameText = findViewById(R.id.nameText);
        priceText = findViewById(R.id.priceText);
        editButton = findViewById(R.id.editButton);
        String id = getIntent().getExtras().getString("id");
        deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteDialog(products.getId());
            }
        });



        oneById(id);
    }
    private void oneById(String id) {
        RetrofitService retrofitService = new RetrofitService();
        ProductsApi productsApi = retrofitService.getRetrofit().create(ProductsApi.class);
        productsApi.oneById(id)
                .enqueue(new Callback<Products>() {
                    @Override
                    public void onResponse(Call<Products> call, Response<Products> response) {
                        if(!response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
                            Log.e("Response err:", response.message());
                            return;
                        }
                        products = response.body();
                        nameText.setText(String.valueOf(products.getName()));
                        priceText.setText(String.valueOf(products.getPrice()));
                    }

                    @Override
                    public void onFailure(Call<Products> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("Response err:", t.getMessage());
                    }
                });
    }

    @Override
    public void showDeleteDialog(String id) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DeleteFragment deleteFragment = new DeleteFragment("Delete book ", products.getId(), this);
        deleteFragment.show(fragmentManager, "Alert");
    }

    @Override
    public void delete(String id) {
        RetrofitService retrofitService = new RetrofitService();
        ProductsApi productsApi = retrofitService.getRetrofit().create(ProductsApi.class);
        Call<Void> call = productsApi.delete(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
                    Log.e("Response err:", response.message());
                    return;
                }
                //book = response.body();
                Toast.makeText(getApplicationContext(), products.getName() + " deleted!!", Toast.LENGTH_LONG).show();
                callMain();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("Response err:", t.getMessage());
            }
        });
    }

    private void callMain(){
        Intent intent = new Intent(getApplicationContext(), ProductsList.class);
        startActivity(intent);
    }

}