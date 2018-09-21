package com.aceplus.hackthon;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aceplus.hackthon.adapter.HorizontalPagerAdapter;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;

import org.jetbrains.annotations.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.vp_category)
    HorizontalInfiniteCycleViewPager vpCategory;
    //private FirebaseAuth auth;
    private String message;
   // private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        ButterKnife.bind(this);
        getExtra();
       /* auth = FirebaseAuth.getInstance();
        FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };
        auth.addAuthStateListener(authListener);*/
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

    private void setUpVpCategory(){
        vpCategory.setAdapter(new HorizontalPagerAdapter(getApplicationContext()));
    }
}
