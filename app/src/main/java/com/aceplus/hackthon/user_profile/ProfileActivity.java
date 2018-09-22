package com.aceplus.hackthon.user_profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.aceplus.hackthon.R;
import com.aceplus.shared.VO.UserVO;
import com.aceplus.shared.model.BackendModel;
import com.aceplus.shared.modelcallback.ModelCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kyawthetwin on 9/22/18.
 */

public class ProfileActivity extends AppCompatActivity implements ProfileContract.View {

    @BindView(R.id.pp_password)
    EditText edt_passordpp;
    @BindView(R.id.pp_department)
    Spinner edt_departmentpp;
    @BindView(R.id.pp_userName)
    EditText edt_userNamepp;
    ProfileContract.Presenter presenter;
    FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private UserVO userVO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
    }

    private void init() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        firebaseAuth = FirebaseAuth.getInstance();
        presenter = new ProfilePresenter(this);
        presenter.getUser();
        ButterKnife.bind(this);
        setUpDepartmentSpinner();
    }

    @OnClick(R.id.imgV_close)
    public void close() {

        onBackPressed();
    }

    private void setUpDepartmentSpinner() {

        List<String> team = new ArrayList<String>();
        team.add("ARK");
        team.add("B2B");
        team.add("B2C");
        team.add("Hivelocity");
        team.add("Management");
        team.add("Support Team");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.custom_spinner1,
                team);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        edt_departmentpp.setAdapter(dataAdapter);

    }

    @Override
    public void showUser(UserVO userVO) {
        this.userVO = userVO;
        setTextUserProfile(userVO);
        setCheckDepartment(userVO);
    }

    private void setCheckDepartment(UserVO userVO) {
        if (userVO.getUserDepartment() != null) {

            if (userVO.getUserDepartment().equalsIgnoreCase("ARK")) {
                edt_departmentpp.setSelection(0);
            } else if (userVO.getUserDepartment().equalsIgnoreCase("B2B")) {
                edt_departmentpp.setSelection(1);
            } else if (userVO.getUserDepartment().equalsIgnoreCase("B2C")) {
                edt_departmentpp.setSelection(2);
            } else if (userVO.getUserDepartment().equalsIgnoreCase("Hivelocity")) {
                edt_departmentpp.setSelection(3);
            } else if (userVO.getUserDepartment().equalsIgnoreCase("Management")) {
                edt_departmentpp.setSelection(4);
            } else if (userVO.getUserDepartment().equalsIgnoreCase("Support Team")) {
                edt_departmentpp.setSelection(5);
            }
        }

    }

    private void setTextUserProfile(UserVO userProfile) {

        String username = userProfile.getUserName();
        String[] separated = username.split("@");

        edt_userNamepp.setText(separated[0]);

    }

    @OnClick(R.id.btn_saveChanges)
    public void doSaveChange() {

        String password = edt_passordpp.getText().toString();

        if (password.length() != 0) {
            if (password.length() < 6) {
                Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                updatePassword(edt_passordpp.getText().toString());
            }
        }


        updateDepartment(edt_departmentpp.getSelectedItem().toString(),userVO.getUserId(),userVO.getUserName());

    }

    private void updateDepartment(String newDepartment,String userId, String UserName) {

        UserVO userVO = new UserVO();
        userVO.setUserId(userId);
        userVO.setUserName(UserName);
        userVO.setUserDepartment(newDepartment);
        BackendModel.Companion.getInstance().addUser(userVO, new ModelCallback.LoginUserCallback() {
            @Override
            public void loginFailed(@NotNull String message) {

            }

            @Override
            public void loginSucceed(@NotNull UserVO userVO) {
                finish();
            }
        });
    }

    private void updatePassword(String newPassword) {

        user.updatePassword(newPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            finish();
                            Log.d("ProfileActivity", "User password updated.");
                        }
                    }
                });
    }


    @OnClick(R.id.txt_signOut)
    public void goToSignOut() {
        if (firebaseAuth.getCurrentUser() != null) {
            firebaseAuth.signOut();
        }
    }
}
