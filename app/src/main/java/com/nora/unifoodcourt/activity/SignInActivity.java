package com.nora.unifoodcourt.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.nora.unifoodcourt.databinding.ActivitySignInBinding;
import com.nora.unifoodcourt.managers.DatabaseHelper;
import com.nora.unifoodcourt.managers.MyAlertDialog;

public class SignInActivity extends AppCompatActivity {

    private Context mContext;
    private ActivitySignInBinding mBinding;
    private DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mContext = SignInActivity.this;
        mDatabaseHelper = new DatabaseHelper(mContext);

        mBinding.topBarInclude.titleTxtV.setText("Sign In");
        mBinding.topBarInclude.backImgV.setVisibility(View.GONE);
        mBinding.signUpTxtV.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, SignUpActivity.class);
            startActivity(intent);
            finish();
        });
        mBinding.signInBtn.setOnClickListener(v -> {

            //Extracting the pure text
            final String studentID = mBinding.studentIDETxt.getText().toString().trim();
            final String password = mBinding.passwordETxt.getText().toString().trim();

            //Checking if studentID or password are empty
            if (studentID.isEmpty() || password.isEmpty()) {
                MyAlertDialog.show(mContext, "Please insert all data");
                return;
            }

            //Checking if user exist
            Cursor cursor = mDatabaseHelper.retrieveUser(studentID);
            if (cursor.getCount() < 1) {
                MyAlertDialog.show(mContext, "User not found, you need to register a new account");
                return;
            }

            while (cursor.moveToNext()) {
                if (studentID.equals(cursor.getString(0))) {
                    //Checking if password is correct
                    if (password.equals(cursor.getString(1))) {
                        MainActivity.STUDENT_ID = studentID;
                        Toast.makeText(mContext, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext, MainActivity.class);
                        startActivity(intent);
                    } else {
                        MyAlertDialog.show(mContext, "Wrong password");
                    }
                }
            }
        });

    }
}