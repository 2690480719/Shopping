package com.lqb.android.shopping.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.lqb.android.shopping.R;
import com.lqb.android.shopping.home.bean.GoodsBean;
import com.lqb.android.shopping.shoppingcart.adapter.ShopCartAdapter;
import com.lqb.android.shopping.shoppingcart.utils.CartProvider;
import com.lqb.android.shopping.shoppingcart.view.NumberAddSubView;
import com.lqb.android.shopping.utils.Constants;

import java.util.List;

public class GoodsInfoActivity extends Activity implements View.OnClickListener {
    private ImageButton ibGoodInfoBack;
    private ImageButton ibGoodInfoMore;
    private ImageView goods_img;
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoDesc;
    private TextView tvGoodInfoPrice;
    private TextView tvGoodInfoStore;
    private TextView tvGoodInfoStyle;
    private TextView tvGoodInfoCallcenter;
    private TextView tvGoodInfoCollection;
    private TextView tvGoodInfoCart;
    private TextView tvMoreShare;
    private TextView tvMoreSearch;
    private TextView tvMoreHome;
    private TextView goods_desc;
    private TextView goods_price;
    private TextView tv_count;
    private WebView wbGoodInfoMore;
    private LinearLayout ll_root;
    private RelativeLayout ll_goods_show;
    private Button btnGoodInfoAddcart;
    private Button btn_more;
    private Button goods_cancel;
    private Button goods_confirm;

    private GoodsBean goods_bean;

    private void findViews() {
        ibGoodInfoBack = (ImageButton)findViewById( R.id.ib_good_info_back );
        ibGoodInfoMore = (ImageButton)findViewById( R.id.ib_good_info_more );
        ivGoodInfoImage = (ImageView)findViewById( R.id.iv_good_info_image );
        tvGoodInfoName = (TextView)findViewById( R.id.tv_good_info_name );
        tvGoodInfoDesc = (TextView)findViewById( R.id.tv_good_info_desc );
        tvGoodInfoPrice = (TextView)findViewById( R.id.tv_good_info_price );
        tvGoodInfoStore = (TextView)findViewById( R.id.tv_good_info_store );
        tvGoodInfoStyle = (TextView)findViewById( R.id.tv_good_info_style );
        tvGoodInfoCallcenter = (TextView)findViewById( R.id.tv_good_info_callcenter );
        tvGoodInfoCollection = (TextView)findViewById( R.id.tv_good_info_collection );
        tvGoodInfoCart = (TextView)findViewById( R.id.tv_good_info_cart );
        tvMoreShare = (TextView)findViewById( R.id.tv_more_share );
        tvMoreSearch = (TextView)findViewById( R.id.tv_more_search );
        tvMoreHome = (TextView)findViewById( R.id.tv_more_home );
        goods_desc = (TextView)findViewById( R.id.goods_desc );
        goods_price = (TextView)findViewById( R.id.goods_price );
        tv_count = (TextView) findViewById(R.id.tv_count);
        wbGoodInfoMore = (WebView)findViewById( R.id.wb_good_info_more );
        btnGoodInfoAddcart = (Button)findViewById( R.id.btn_good_info_addcart );
        btn_more = (Button)findViewById( R.id.btn_more );
        goods_cancel = (Button)findViewById( R.id.goods_cancel );
        goods_confirm = (Button)findViewById( R.id.goods_confirm );
        ll_root = (LinearLayout) findViewById(R.id.ll_root);
        ll_goods_show = (RelativeLayout) findViewById(R.id.ll_goods_show);
        goods_img = (ImageView)findViewById( R.id.goods_img);

        ibGoodInfoBack.setOnClickListener( this );
        ibGoodInfoMore.setOnClickListener( this );
        btnGoodInfoAddcart.setOnClickListener( this );

        tvMoreShare.setOnClickListener( this );
        tvMoreSearch.setOnClickListener( this );
        tvMoreHome.setOnClickListener( this );
        btn_more.setOnClickListener( this );

        tvGoodInfoCallcenter.setOnClickListener(this);
        tvGoodInfoCollection.setOnClickListener(this);
        tvGoodInfoCart.setOnClickListener(this);
        btnGoodInfoAddcart.setOnClickListener(this);

        goods_cancel.setOnClickListener(this);
        goods_confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == ibGoodInfoBack) {
            finish();
        } else if (v == ibGoodInfoMore) {
            if (ll_root.getVisibility() == View.VISIBLE) {
                ll_root.setVisibility(View.GONE);
            } else {
                ll_root.setVisibility(View.VISIBLE);
            }
        } else if (v == btn_more) {
            ll_root.setVisibility(View.GONE);
        } else if (v == tvMoreShare) {
            Toast.makeText(GoodsInfoActivity.this, "分享", Toast.LENGTH_SHORT).show();
        } else if (v == tvMoreSearch) {
            Toast.makeText(GoodsInfoActivity.this, "搜索", Toast.LENGTH_SHORT).show();
        } else if (v == tvMoreHome) {
            Constants.isBackHome = true;
            finish();
        } else if (v == tvGoodInfoCallcenter) {
            Toast.makeText(GoodsInfoActivity.this, "客服", Toast.LENGTH_SHORT).show();
        } else if (v == tvGoodInfoCollection) {
            Toast.makeText(GoodsInfoActivity.this, "收藏", Toast.LENGTH_SHORT).show();
        } else if (v == tvGoodInfoCart) {
            Intent intent = new Intent(this, ShoppingCartActivity.class);
            startActivity(intent);
        } else if (v == btnGoodInfoAddcart) {
            //弹出购买界面
            if (ll_goods_show.getVisibility() == View.VISIBLE) {
                ll_goods_show.setVisibility(View.GONE);
            } else {
                ll_goods_show.setVisibility(View.VISIBLE);
            }
            showPopwindow();
        } else if (v == goods_cancel) {
            ll_goods_show.setVisibility(View.GONE);
        } else if (v == goods_confirm) {
            //添加购物车
            ll_goods_show.setVisibility(View.GONE);

            int count = Integer.parseInt(tv_count.getText().toString().trim());
            goods_bean.setNumber(count);
            CartProvider.getInstance().addData(goods_bean);
            Toast.makeText(GoodsInfoActivity.this, "添加购物车成功", Toast.LENGTH_SHORT).show();
        }
    }

    private void showPopwindow() {
        goods_img.setImageDrawable(ivGoodInfoImage.getDrawable());
        goods_desc.setText(tvGoodInfoName.getText());
        goods_price.setText(tvGoodInfoPrice.getText());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        findViews();

        //取出 intent
        Intent intent = getIntent();
        goods_bean = (GoodsBean) intent.getSerializableExtra("goods_bean");
        if (goods_bean != null) {
            //本地获取存储的数据
            setDataFormView(goods_bean);
        }
    }

    private void setWebView(String product_id) {
        if (product_id != null) {
            wbGoodInfoMore.loadUrl("http://www.jd.com");

            //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
            wbGoodInfoMore.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    //返回值是true的时候控制去WebVie打开，为false调用系统浏览器或第三方浏览器
                    view.loadUrl(url);
                    return true;
                }
            });

            //启用支持javascript
            WebSettings settings = wbGoodInfoMore.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setUseWideViewPort(true);

            //优先使用缓存
            wbGoodInfoMore.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
    }

    public void setDataFormView(GoodsBean goodsBean) {
        String name = goodsBean.getName();
        String cover_price = goodsBean.getCover_price();
        String figure = goodsBean.getFigure();
        String product_id = goodsBean.getProduct_id();

        Glide.with(this).load(Constants.BASE_URl_IMAGE + figure).into(ivGoodInfoImage);
        if (name != null) {
            tvGoodInfoName.setText(name);
        }
        if (cover_price != null) {
            tvGoodInfoPrice.setText("￥" + cover_price);
        }
        setWebView(product_id);
    }
}
