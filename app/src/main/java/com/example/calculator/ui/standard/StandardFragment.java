package com.example.calculator.ui.standard;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.calculator.R;
import com.example.calculator.ui.Controller;

public class StandardFragment extends Fragment {
    View root;
    @SuppressLint("StaticFieldLeak")
    static Controller controller;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_standard, container, false);
        if (savedInstanceState==null)
        controller=new Controller(root);
        else {
            Controller controller1=new Controller(root);
            controller1.setAngleMeasureSystem(controller.getAngleMeasureSystem());
            controller1.setArrayList(controller.getArrayList());
            controller1.setExpression(controller.getExpression());
            controller1.setNumber(controller.getNumber());
        }
        return root;
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}