package com.aceplus.hackthon.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.aceplus.hackthon.MainActivity;
import com.aceplus.hackthon.R;

import org.jetbrains.annotations.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;


public class LoginActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.edt_loginEmail)
    EditText edt_email;
    @Nullable
    @BindView(R.id.edt_loginPassword)
    EditText edt_password;
   /* private DatabaseReference mDatabase;
    private FirebaseAuth auth;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init(){
        ButterKnife.bind(this);
       /* auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }*/
    }
    @Optional
    @OnClick(R.id.txt_signUp)
    public void goToSignUp(){

        Intent i = new Intent(this,SignUpActivity.class);
        this.startActivity(i);
    }
    @Optional
    @OnClick(R.id.txt_forgotPassword)
    public void goToResetPassword(){

        Intent i = new Intent(this,ResetPasswordActivity.class);
        this.startActivity(i);
    }
    @Optional
    @OnClick(R.id.btn_login)
    public void goToLogin(){

        final String email = edt_email.getText().toString();
        final String password = edt_password.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        /*auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            if (password.length() < 6) {
                                edt_password.setError("Password too short, enter minimum 6 characters!");
                            } else {
                                Toast.makeText(LoginActivity.this,"Authentication failed.", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            final String UUID = task.getResult().getUser().getUid();

                            Login login = new Login();
                            login.setUuid(mDatabase.child("isLoginUser").push().getKey());
                            mDatabase.child("isLoginUser").child(login.getUuid()).setValue(email);
                            Log.i("UUID", UUID);
                        }
                    }
                });*/
    }
}