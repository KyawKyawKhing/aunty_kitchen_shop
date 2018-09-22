package com.aceplus.hackthon.today_menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aceplus.hackthon.R;
import com.aceplus.hackthon.adapter.TodayMenuRecyclerViewAdapter;
import com.aceplus.shared.VO.AvailableItemVO;
import com.aceplus.shared.VO.UserVO;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class TodayMenuActivity extends AppCompatActivity implements TodayMenuContract.View {

    @BindView(R.id.todayMenu_rcv)
    RecyclerView rcv_TodayMenu;
    TodayMenuContract.Presenter presenter;
    TodayMenuRecyclerViewAdapter todayMenuRecyclerViewAdapter;
    private LinearLayoutManager mLayoutManager;
    private DatabaseReference dref;
    private List<AvailableItemVO> itemList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_menu);
        presenter = new TodayMenuPresenter(this);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        setUpRcv_TodayMenu();
        presenter.getTodayAvailableItem();
        presenter.getUser();
    }

    @OnClick(R.id.imgBack)
    public void goBack() {
        onBackPressed();
    }

    private void setUpRcv_TodayMenu() {
        todayMenuRecyclerViewAdapter = new TodayMenuRecyclerViewAdapter(this, TodayMenuActivity.this);
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rcv_TodayMenu.setLayoutManager(mLayoutManager);
        rcv_TodayMenu.setAdapter(todayMenuRecyclerViewAdapter);
        /*dref= FirebaseDatabase.getInstance().getReference();
        dref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                itemList1.add(dataSnapshot.getValue(AvailableItemVO.class));
                todayMenuRecyclerViewAdapter.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                itemList1.remove(dataSnapshot.getValue(AvailableItemVO.class));
                todayMenuRecyclerViewAdapter.notifyDataSetChanged();
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });*/
    }

    @Override
    public void displayTodayAvailableItem(List<AvailableItemVO> itemList) {
        this.itemList1 = itemList;
        todayMenuRecyclerViewAdapter.setTodayMenuList(itemList1);
    }

    @Override
    public void showUser(UserVO userVO) {
        todayMenuRecyclerViewAdapter.setUserVo(userVO);
    }
}
