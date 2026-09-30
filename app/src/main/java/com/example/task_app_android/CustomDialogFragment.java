package com.example.task_app_android;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class CustomDialogFragment extends DialogFragment {

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder
                .setTitle("Button 3")
                .setIcon(R.drawable.test_icon)
                .setMessage("Ready or not?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    if (getActivity() instanceof MainActivity) {
                        ((MainActivity) getActivity()).makeButtonsTextRed();
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    Toast.makeText(getActivity(), "Dialog was closed", Toast.LENGTH_SHORT).show();
                })
                .create();
    }
}
