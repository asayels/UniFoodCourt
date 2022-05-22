package com.nora.unifoodcourt.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.nora.unifoodcourt.databinding.ActivitySignUpBinding;
import com.nora.unifoodcourt.managers.DatabaseHelper;
import com.nora.unifoodcourt.managers.MyAlertDialog;

public class SignUpActivity extends AppCompatActivity {

    private DatabaseHelper mDatabaseHelper;
    private Context mContext;
    private ActivitySignUpBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mContext = SignUpActivity.this;
        mDatabaseHelper = new DatabaseHelper(mContext);

        mBinding.topBarInclude.titleTxtV.setText("Sign Up");
        mBinding.topBarInclude.backImgV.setVisibility(View.GONE);
        mBinding.signInTxtV.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, SignInActivity.class);
            startActivity(intent);
            finish();
        });

        mBinding.signUpBtn.setOnClickListener(v -> {
            //Extracting the pure text
            final String studentID = mBinding.studentIDETxt.getText().toString().trim().trim();
            final String password = mBinding.passwordETxt.getText().toString().trim();
            final String confirmPassword = mBinding.confirmPasswordETxt.getText().toString().trim();
            final String email = mBinding.emailETxt.getText().toString().trim().trim();
            final String name = mBinding.fullNameETxt.getText().toString().trim();
            final String phone = mBinding.phoneETxt.getText().toString().trim();

            //Checking if all values are inserted
            if (studentID.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()||
                    email.isEmpty() || name.isEmpty() || phone.isEmpty()) {
                MyAlertDialog.show(mContext, "Please insert all data");
                return;
            }

            //Checking if password match the confirm password
            if (!password.equals(confirmPassword)) {
                MyAlertDialog.show(mContext, "Password and confirm password doesn't match");
                return;
            }

            //Checking email format validation
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                MyAlertDialog.show(mContext, "Please insert a valid email");
                return;
            }


            //insert new user in the database
            boolean insert = mDatabaseHelper.insertNewUser(studentID, password, name, phone, email);

            //Check insertion success
            if (!insert) {
                MyAlertDialog.show(mContext, "There is a user with the same username");
                return;
            }

            //Navigate to MainActivity
            Toast.makeText(mContext, "Registered Successfully", Toast.LENGTH_SHORT).show();
            MainActivity.STUDENT_ID = studentID;
            Intent intent = new Intent(mContext, MainActivity.class);
            startActivity(intent);
        });

    }
}