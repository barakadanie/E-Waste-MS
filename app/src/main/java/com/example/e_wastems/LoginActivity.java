package com.example.e_wastems;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginUserBinding binding;

    private FirebaseAuth firebaseAuth;
    ProgressDialog pd;
    String admin = "ndolwavincent@gmail@@@@";
    String keyIn = "com/0034/18";
    String PhoneOtp="+2540794234138";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();
        pd= new ProgressDialog(this);
        pd.setTitle("Please wait...");
        pd.setCanceledOnTouchOutside(false);
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = binding.email.getText().toString().trim();
                String Pass = binding.password.getText().toString().trim();
                if (Email.equals(admin) || Pass.equals(keyIn)){
                    sendAdminOtp();
                }
                else {
                    userLoginMethod();
                }
            }

            private void sendAdminOtp() {
                pd.setMessage("Requesting OTP...");
                pd.show();
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        PhoneOtp,
                        60,
                        TimeUnit.SECONDS,
                        LoginActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                pd.dismiss();

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                pd.dismiss();
                                Toast.makeText(LoginActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                pd.dismiss();
                                Intent intent = new Intent(getApplicationContext(), VerifyOTP.class);
                                intent.putExtra("mobile",PhoneOtp);
                                intent.putExtra("verificationId",verificationId);
                                startActivity(intent);
                            }
                        }
                );
            }

            private void userLoginMethod() {
                String Email = binding.email.getText().toString().trim();
                String Pass = binding.password.getText().toString().trim();
                if (Email.isEmpty()){
                    binding.email.setError("Full Name is required");
                    binding.email.requestFocus();
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                    binding.email.setError("Please Provide valid Email");
                    binding.email.requestFocus();
                }
                else if (Pass.isEmpty()){
                    binding.password.setError("Password is required");
                    binding.password.requestFocus();
                }
                else if (Pass.length()<6){
                    binding.password.setError("Weak Password");
                    binding.password.requestFocus();
                }
                else {
                    pd.setMessage("Login....");
                    pd.show();
                    firebaseAuth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Logged in Successfully !", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                intent.putExtra("email",Email);
                                startActivity(intent);
                                pd.dismiss();
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "Failed to Login Please Enable Internet connection and check credentials and try again", Toast.LENGTH_LONG).show();
                                pd.dismiss();
                            }
                        }
                    });
                }
            }
        });
        binding.forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));
                finish();
            }
        });
        binding.reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                finish();
            }
        });

    }
}