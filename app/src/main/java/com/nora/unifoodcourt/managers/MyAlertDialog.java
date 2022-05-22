package com.nora.unifoodcourt.managers;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

public class MyAlertDialog {
    //Method to prepare AlertDialog
    public static void show(Context context, String message) {
        new AlertDialog.Builder(context)
                .setTitle("Alert")
                .setMessage(message)
                .setPositiveButton("Ok", (dialog, which) -> {
                    dialog.dismiss();
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
