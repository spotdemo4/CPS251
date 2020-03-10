package com.example.navigation;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;


/**
 * A simple {@link Fragment} subclass.
 */
public class main extends Fragment implements View.OnClickListener {

    NavController navController;

    public main() {
        // Required empty public constructor
    }

    private int getDrawableId(ImageView iv) {
        return (Integer) iv.getTag();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        view.findViewById(R.id.frag1).setOnClickListener(this);
        view.findViewById(R.id.frag2).setOnClickListener(this);
        view.findViewById(R.id.frag3).setOnClickListener(this);

        ImageView imageA = getView().findViewById(R.id.imageA);
        imageA.setTag(R.drawable.doge);

        ImageView imageB = getView().findViewById(R.id.imageB);
        imageB.setTag(R.drawable.bluedoge);

        ImageView imageC = getView().findViewById(R.id.imageC);
        imageC.setTag(R.drawable.breaddoge);
    }

    @Override
    public void onClick(View v) {
        ImageView image;
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.frag1:
                image = getView().findViewById(R.id.imageA);
                bundle.putInt("imageID", getDrawableId(image));
                navController.navigate(R.id.action_main_to_fragmentA, bundle);
                break;
            case R.id.frag2:
                image = getView().findViewById(R.id.imageB);
                bundle.putInt("imageID", getDrawableId(image));
                navController.navigate(R.id.action_main_to_fragmentB, bundle);
                break;
            case R.id.frag3:
                image = getView().findViewById(R.id.imageC);
                bundle.putInt("imageID", getDrawableId(image));
                navController.navigate(R.id.action_main_to_fragmentC, bundle);
                break;
        }
    }
}
