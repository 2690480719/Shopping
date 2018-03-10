package com.lqb.android.shopping.app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import com.lqb.android.shopping.base.BaseFragment;
import com.lqb.android.shopping.shoppingcart.fragment.ShoppingCartFragment;

import static com.lqb.android.shopping.R.layout.fragment_shoppingcart;

public class ShoppingCartActivity extends FragmentActivity {
    public FragmentTransaction transaction;
    private BaseFragment SCFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(fragment_shoppingcart);

        SCFragment = new ShoppingCartFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(android.R.id.content, SCFragment).commit();
    }
}