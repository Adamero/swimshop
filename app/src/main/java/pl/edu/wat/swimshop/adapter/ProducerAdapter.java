package pl.edu.wat.swimshop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.edu.wat.swimshop.R;
import pl.edu.wat.swimshop.entity.Producer;

public class ProducerAdapter extends RecyclerView.Adapter<ProducerHolder> {

    private List<Producer> producerList;

    public ProducerAdapter(List<Producer> producerList) {
        this.producerList = producerList;
    }

    @NonNull
    @Override
    public ProducerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.producer_item, parent, false);
        return new ProducerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProducerHolder holder, int position) {
        Producer producer = producerList.get(position);
        holder.brand.setText(producer.getBrand());
        holder.country.setText(producer.getCountry());
    }

    @Override
    public int getItemCount() {
        return producerList.size();
    }
}
