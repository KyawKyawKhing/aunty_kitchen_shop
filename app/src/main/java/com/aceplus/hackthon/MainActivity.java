package com.aceplus.hackthon;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.aceplus.hackthon.adapter.HorizontalPagerAdapter;
import com.aceplus.hackthon.login.LoginActivity;
import com.aceplus.hackthon.user_profile.ProfileActivity;
import com.aceplus.shared.VO.UserVO;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View{

    @BindView(R.id.txt_userName)
    TextView txtUserName;
    @BindView(R.id.teamName)
    TextView txtTeamName;
    @Nullable
    @BindView(R.id.vp_category)
    HorizontalInfiniteCycleViewPager vpCategory;
    private FirebaseAuth auth;
    private String message;
    private MainActivityContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        ButterKnife.bind(this);
        getExtra();
        presenter = new MainActivityPresenter(this);
        auth = FirebaseAuth.getInstance();
        presenter.getUser();
        FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }else {
                    String userName = firebaseAuth.getCurrentUser().getEmail();
                    String[] separated = userName.split("@");
                    txtUserName.setText(separated[0]);
                }
            }
        };
        auth.addAuthStateListener(authListener);
        setUpVpCategory();
    }

   /* @OnClick(R.id.btn_logout)
    public void goToLogout(){
        if (auth.getCurrentUser() != null) {
            auth.signOut();
        }
    }*/

    private void getExtra(){
        if (getIntent() != null) {
            Intent intent = getIntent();
            message = intent.getStringExtra("message");
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (manager != null) {
                manager.cancelAll();
            }
        }
    }

    @OnClick(R.id.nameLayout)
    public void gotoEditProfile(){

        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }

    private void setUpVpCategory(){
        vpCategory.setAdapter(new HorizontalPagerAdapter(getApplicationContext()));
    }

    @Override
    public void showUser(UserVO userVO) {
        txtTeamName.setText(userVO.getUserDepartment());
    }
}
