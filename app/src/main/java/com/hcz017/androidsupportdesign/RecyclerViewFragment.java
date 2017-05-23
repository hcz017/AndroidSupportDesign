package com.hcz017.androidsupportdesign;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerViewFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.rececly_view, container, false);
        RecyclerView mRecyclerView = (RecyclerView) parentView.findViewById(R.id.recycler_view);
        //设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        //设置adapter
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(new MyAdapter());
        return parentView;
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        int[] cats = {R.drawable.pic_1, R.drawable.pic_2, R.drawable.pic_3, R.drawable.pic_4,
                R.drawable.pic_5, R.drawable.pic_6};

        //创建新View，被LayoutManager所调用
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card, parent, false);
            return new ViewHolder(view);
        }

        //将数据与界面进行绑定的操作
        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
            Drawable drawable = getContext().getDrawable(cats[position]);
            holder.textView.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
        }

        //获取数据的数量
        @Override
        public int getItemCount() {
            return cats.length;
        }

        //自定义的ViewHolder，持有每个Item的的所有界面元素
        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView textView;

            ViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.card_txt);
            }
        }
    }
}
