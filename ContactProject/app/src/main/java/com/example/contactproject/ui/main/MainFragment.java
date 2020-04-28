package com.example.contactproject.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.widget.EditText;
import android.widget.TextView;
import com.example.contactproject.Product;
import android.widget.Button;
import androidx.lifecycle.Observer;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.contactproject.R;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private ProductListAdapter adapter;

    private EditText productName;
    private EditText productQuantity;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        productName = getView().findViewById(R.id.productName);
        productQuantity = getView().findViewById(R.id.productQuantity);

        System.out.println("THE ID IS: " + this.getId());

        observerSetup();
        recyclerSetup();
    }

    private void clearFields() {
        productName.setText("");
        productQuantity.setText("");
    }

    private void observerSetup() {

        mViewModel.getAllProducts().observe(this, new Observer<List<Product>>()  {
            @Override
            public void onChanged(@Nullable final List<Product> products) {
                adapter.setProductList(products);
            }
        });

        mViewModel.getSearchResults().observe(this, new Observer<List<Product>>()  {
            @Override
            public void onChanged(@Nullable final List<Product> products) {
                if (products.size() > 0) {
                    adapter.setProductList(products);
                } else {
                    LayoutInflater inflater = getLayoutInflater();
                    showToast(inflater, "No matches found");
                }
            }
        });
    }

    private void recyclerSetup() {
        RecyclerView recyclerView;

        adapter = new ProductListAdapter(R.layout.product_list_item);
        recyclerView = getView().findViewById(R.id.product_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new ProductListAdapter.onItemClickListener() {
            @Override
            public void onClick(String str){
                mViewModel.deleteProduct(str);
                clearFields();
            }
        });
    }

    public void getAllProducts() {
        List<Product> products = mViewModel.getAllProducts().getValue();
        adapter.setProductList(products);
    }

    public void addContact() {
        String name = productName.getText().toString();
        String quantity = productQuantity.getText().toString();

        if (!name.equals("") && !quantity.equals("")) {
            Product product = new Product(name, quantity);
            mViewModel.insertProduct(product);
            clearFields();
        } else {
            LayoutInflater inflater = getLayoutInflater();
            showToast(inflater, "You must enter a name and phone number");
        }
    }

    public void findContact() {
        if (productName.getText().toString().matches("")){
            LayoutInflater inflater = getLayoutInflater();
            showToast(inflater, "You must enter a name");
        } else {
            mViewModel.findProduct(productName.getText().toString());
        }
    }

    public void sortAZ() {
        List<Product> products = mViewModel.getAllProducts().getValue();
        Collections.sort(products, new Comparator<Product>(){
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        adapter.setProductList(products);
    }

    public void sortZA() {
        List<Product> products = mViewModel.getAllProducts().getValue();
        Collections.sort(products, new Comparator<Product>(){
            @Override
            public int compare(Product o1, Product o2) {
                return o2.getName().compareToIgnoreCase(o1.getName());
            }
        });
        adapter.setProductList(products);
    }

    private void showToast(LayoutInflater inflater, String toastText){
        View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) getActivity().findViewById(R.id.custom_toast_container));
        TextView text = layout.findViewById(R.id.text);
        text.setText(" " + toastText);
        Toast toast = new Toast(getActivity().getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setView(layout);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }


}
