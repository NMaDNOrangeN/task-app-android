package com.example.task_app_android;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class CustomDialogFragment extends DialogFragment {
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder
                .setTitle("It's time to sleep")
                .setIcon(R.drawable.ic_launcher_foreground)
                .setMessage("Are you asleep?")
                .setPositiveButton("Yes", (dialog, which) -> ((NightActivity) requireActivity()).appClose())
                .setNegativeButton("No", (dialog, which) -> ((NightActivity) requireActivity()).toMain())
                .create();
    }
}
