package pl.edu.wat.swimshop.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.logging.Level;
import java.util.logging.Logger;

import pl.edu.wat.swimshop.R;
import pl.edu.wat.swimshop.entity.Producer;
import pl.edu.wat.swimshop.retrofit.ProducerApi;
import pl.edu.wat.swimshop.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProducerForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producer_form);

        initForm();
    }

    private void initForm() {
        TextInputEditText inputEditTextBrand = findViewById(R.id.form_textFieldBrand);
        TextInputEditText inputEditTextCountry = findViewById(R.id.form_textFieldCountry);
        MaterialButton button = findViewById(R.id.buttonSave);

        RetrofitService retrofitService = new RetrofitService();
        ProducerApi producerApi = retrofitService.getRetrofit().create(ProducerApi.class);

        button.setOnClickListener(view -> {
            String brand = String.valueOf(inputEditTextBrand.getText());
            String country = String.valueOf(inputEditTextCountry.getText());

            Producer producer = new Producer();
            producer.setBrand(brand);
            producer.setCountry(country);

            producerApi.save(producer)
                    .enqueue(new Callback<Producer>() {
                        @Override
                        public void onResponse(Call<Producer> call, Response<Producer> response) {
                            Toast.makeText(ProducerForm.this,"Save successful!", Toast.LENGTH_SHORT).show();
                            callMain();
                        }

                        @Override
                        public void onFailure(Call<Producer> call, Throwable t) {
                            Toast.makeText(ProducerForm.this,"Save failed!!!",Toast.LENGTH_SHORT).show();
                            Logger.getLogger(ProducerForm.class.getName()).log(Level.SEVERE, "Error occured",t);
                        }
                    });
        });
    }

    private void callMain() {
        Intent intent = new Intent(getApplicationContext(), ProducerListActivity.class);
        startActivity(intent);
    }
}