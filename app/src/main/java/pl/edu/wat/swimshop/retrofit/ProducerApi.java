package pl.edu.wat.swimshop.retrofit;

import java.util.List;

import pl.edu.wat.swimshop.entity.Producer;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ProducerApi {

    @GET("api/v1/producer/show")
    Call<List<Producer>> allProducer();

    @Headers("Content-Type: application/json")
    @POST("api/v1/producer/add")
    Call<Producer> save(@Body Producer producer);
}
