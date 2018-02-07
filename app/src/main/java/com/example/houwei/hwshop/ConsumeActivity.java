//package com.example.houwei.hwshop;
//
//import android.os.Bundle;
//
//import android.support.v4.app.FragmentTransaction;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.View;
//
//import com.example.houwei.hwshop.fragment.ConsumeFragment;
//
//public class ConsumeActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_consume);
//        FragmentTransaction ft = getSupportFragmentManager()
//                .beginTransaction();
//        ConsumeFragment consumeFragment = new ConsumeFragment();
//        ft.add(R.id.main, consumeFragment, "consumeFragment");
//        ft.commit();
//
//    }
//
//}
