package pl.edu.wat.swimshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import pl.edu.wat.swimshop.R;
import pl.edu.wat.swimshop.activities.ProductsDetail;
import pl.edu.wat.swimshop.entity.Products;

public class ProductsAdapter extends BaseAdapter {

    List<Products> products;
    Context context;
    TextView nameText;
    Button viewButton;

    public ProductsAdapter(List<Products> products, Context context){
        this.products = products;
        this.context = context;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        try {
            return Long.parseLong(products.get(position).getId());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.products_item_list, parent, false);
        }

        nameText = convertView.findViewById(R.id.nameText);
        nameText.setText(products.get(position).getName());
        viewButton = convertView.findViewById(R.id.viewButton);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Detail(products.get(position).getId());
            }
        });

        return  convertView;

    }

    private void Detail(String id) {
        Intent intent = new Intent(context, ProductsDetail.class);
        intent.putExtra("id",id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
