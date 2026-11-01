package com.example.task_app_android;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button buttonAnswer = view.findViewById(R.id.buttonAnswer);

        String[] options = {"Java", "XML", "SQL", "HTML"};

        buttonAnswer.setOnClickListener(v -> {
            new AlertDialog.Builder(requireContext())
                    .setTitle("Choose the correct answer")
                    .setItems(options, (dialog, which) -> {
                        if (which == 1) {
                            Toast.makeText(requireContext(), "Correct!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(requireContext(), "Wrong answer", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .show();
        });
    }
}