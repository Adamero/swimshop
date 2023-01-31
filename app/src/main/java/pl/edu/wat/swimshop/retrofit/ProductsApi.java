package pl.edu.wat.swimshop.retrofit;

import java.util.List;

import pl.edu.wat.swimshop.dto.ProductsRequest;
import pl.edu.wat.swimshop.entity.Products;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductsApi {

    @GET("/api/v1/products/show")
    Call<List<Products>> showAll();

    @GET("/api/v1/products/{id}")
    Call<Products> oneById(@Path("id") String id);   //do poprawy

    @POST("/api/v1/products/add")
    Call<Products> create(@Body ProductsRequest productsRequest);

    @PUT("/api/v1/products/{id}")
    Call<Products> edit(@Path("id") String id, @Body ProductsRequest productsRequest);

    @DELETE("/api/v1/products/del/{id}")
    Call<Void> delete(@Path("id") String id);



}
