package com.example.calculator.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class ExampleDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Check your internet connection.\nConversion cannot be done without connection.\nPress the reload button to reload rates.")
                .setPositiveButton("Ok", (DialogInterface.OnClickListener) (dialog, id) -> {
                    // FIRE ZE MISSILES!
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
