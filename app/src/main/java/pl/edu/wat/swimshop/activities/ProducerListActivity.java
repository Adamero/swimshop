package pl.edu.wat.swimshop.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import pl.edu.wat.swimshop.R;
import pl.edu.wat.swimshop.adapter.ProducerAdapter;
import pl.edu.wat.swimshop.entity.Producer;
import pl.edu.wat.swimshop.retrofit.ProducerApi;
import pl.edu.wat.swimshop.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProducerListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producer_list);

        recyclerView = findViewById(R.id.producerItemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton floatingActionButton = findViewById(R.id.addButton);

        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, ProducerForm.class);
            startActivity(intent);
                });

        loadProducer();
    }

    private void loadProducer() {
        RetrofitService retrofitService = new RetrofitService();
        ProducerApi producerApi = retrofitService.getRetrofit().create(ProducerApi.class);
        producerApi.allProducer()
                .enqueue(new Callback<List<Producer>>() {
                    @Override
                    public void onResponse(Call<List<Producer>> call, Response<List<Producer>> response) {
                        listProducers(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Producer>> call, Throwable t) {
                        Toast.makeText(ProducerListActivity.this,"Failed to load producers !!!",Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void listProducers(List<Producer> producerList) {
        ProducerAdapter producerAdapter = new ProducerAdapter(producerList);
        recyclerView.setAdapter(producerAdapter);
    }


}