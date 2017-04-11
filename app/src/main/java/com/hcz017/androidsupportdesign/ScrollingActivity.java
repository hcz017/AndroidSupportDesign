package com.hcz017.androidsupportdesign;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar 作为actionBar使用 style里面已经隐藏了actionBar(title也隐藏了)
        setSupportActionBar(toolbar);
        //不会生效，Toolbar被CollapsingToolbarLayout包裹着,要设置collapsingToolbarLayout的title
//        toolbar.setTitle("toolbar");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        customUI();
    }

    /**
     * 自定义显示效果
     */
    public void customUI(){
//        设置标题
//        this.setTitle(R.string.title);
        CollapsingToolbarLayout collapsingToolbarLayout =
                (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle(getString(R.string.title));
//        扩张时title的颜色
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
//        收缩后title的颜色
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.RED);
//        动态修改背景，如果不设置image的话可以fvb找到toolbar或者CollapsingToolbarLayout设置背景
//        findViewById(R.id.img_scroll_bg).setBackgroundResource(R.drawable.ic_menu_camera);
//        图片点击效果
        findViewById(R.id.img_scroll_bg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ScrollingActivity.this, "You just clicked the image", Toast.LENGTH_SHORT).show();
            }
        });
//        collapsingToolbarLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
