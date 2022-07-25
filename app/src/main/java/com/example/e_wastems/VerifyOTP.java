package com.example.e_wastems;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyOTP extends AppCompatActivity {

    ActivityVerifyOtpBinding binding;
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerifyOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.textmobile.setText(String.format("+254",getIntent().getStringExtra("mobile")));
        setupOTPInputs();

        verificationId=getIntent().getStringExtra("verificationId");
        binding.verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.code1.getText().toString().trim().isEmpty()
                        ||binding.code2.getText().toString().trim().isEmpty()
                        || binding.code3.getText().toString().trim().isEmpty()
                        || binding.code4.getText().toString().trim().isEmpty()
                        || binding.code5.getText().toString().trim().isEmpty()
                        || binding.code6.getText().toString().trim().isEmpty()){
                    Toast.makeText(VerifyOTP.this, "Please Enter Valid code", Toast.LENGTH_SHORT).show();
                    return;
                }
                String code = binding.code1.getText().toString() +
                        binding.code2.getText().toString() +
                        binding.code3.getText().toString() +
                        binding.code4.getText().toString() +
                        binding.code5.getText().toString() +
                        binding.code6.getText().toString();

                if (verificationId != null){
                    binding.progressbar.setVisibility(View.VISIBLE);
                    binding.verify.setVisibility(View.VISIBLE);

                    PhoneAuthCredential authCredential  = PhoneAuthProvider.getCredential(
                            verificationId,code
                    );
                    FirebaseAuth.getInstance().signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            binding.progressbar.setVisibility(View.GONE);
                            binding.verify.setVisibility(View.VISIBLE);
                            if (task.isSuccessful()){
                                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(VerifyOTP.this, "Verification code Entered was invalid", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        binding.resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+254"+ getIntent().getStringExtra("mobile"),
                        60,
                        TimeUnit.SECONDS,
                        VerifyOTP.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(VerifyOTP.this,e.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCodeSent(@NonNull String newverificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                verificationId = newverificationId;
                                Toast.makeText(VerifyOTP.this, "OTP Send", Toast.LENGTH_SHORT).show();
                            }
                        }
                );

            }
        });
    }
    private void setupOTPInputs(){
        binding.code1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().isEmpty()){
                    binding.code2.requestFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.code2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().isEmpty()){
                    binding.code3.requestFocus();
                }
            }


            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.code3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().isEmpty()){
                    binding.code4.requestFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.code4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().isEmpty()){
                    binding.code5.requestFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.code5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().isEmpty()){
                    binding.code6.requestFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}