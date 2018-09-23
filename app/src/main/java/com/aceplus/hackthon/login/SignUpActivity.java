package com.aceplus.hackthon.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.aceplus.hackthon.R;
import com.aceplus.shared.VO.UserVO;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity implements LoginContract.View {


    @BindView(R.id.progressLoading)
    LinearLayout progressLoading;
    @BindView(R.id.sp_department)
    Spinner spDepartment;
    @BindView(R.id.edt_signUpEmail)
    EditText inputEmail;
    @BindView(R.id.edt_signUpPassword)
    EditText inputPassword;
    private FirebaseAuth auth;
    DatabaseReference databaseReference;
    LoginContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        presenter = new LoginPresenter(this);
        auth = FirebaseAuth.getInstance();
        setUpDepartmentSpinner();
    }

    private void setUpDepartmentSpinner(){

        List<String> team = new ArrayList<String>();
        team.add("ARK");
        team.add("B2B");
        team.add("B2C");
        team.add("Hivelocity");
        team.add("Management");
        team.add("Support Team");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.custom_spinner,
                team);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spDepartment.setAdapter(dataAdapter);

    }

    @OnClick(R.id.imgBack)
    public void goBack(){

        finish();
    }
    @OnClick(R.id.btn_signUp)
    public void signUp() {

        String email = inputEmail.getText().toString() + "@aceplussolutions.com";
        String password = inputPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }
       auth.createUserWithEmailAndPassword(email,password)
               .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       Toast.makeText(SignUpActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                       if (!task.isSuccessful()) {
                           Toast.makeText(SignUpActivity.this, "Authentication failed." + task.getException(),
                                   Toast.LENGTH_SHORT).show();
                       } else {
                           startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                           UserVO userVO = new UserVO();
                           userVO.setUserDepartment(spDepartment.getSelectedItem().toString());
                           userVO.setUserId(task.getResult().getUser().getUid());
                           userVO.setUserName(task.getResult().getUser().getEmail());
                           presenter.addUser(userVO);
                           finish();
                       }
                   }
               });

    }

    @Override
    public void loginSuccess() {
        Toast.makeText(getApplicationContext(), "Register Successful!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading(boolean active) {
        if (active){
            progressLoading.setVisibility(View.VISIBLE);
        }else {
            progressLoading.setVisibility(View.GONE);
        }
    }
}
