package pl.edu.wat.swimshop.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pl.edu.wat.swimshop.R;

public class ProducerHolder extends RecyclerView.ViewHolder {

    TextView brand, country;

    public ProducerHolder(@NonNull View itemView) {
        super(itemView);

        brand = itemView.findViewById(R.id.producerBrandItem);
        country = itemView.findViewById(R.id.producerCountryItem);
    }
}
