package com.hcz017.androidsupportdesign;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AnimatedVectorDrawableFragment extends Fragment implements View.OnClickListener {
    private Button mBtnLogin;
    private ImageView mIvBottom;
    private ImageView mIvResult;
    private EditText mEdtUserName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.animated_vector_drawable, container, false);
        mBtnLogin = (Button) parentView.findViewById(R.id.btn_login);
        mIvBottom = (ImageView) parentView.findViewById(R.id.iv_bottom);
        mIvResult = (ImageView) parentView.findViewById(R.id.iv_result);
        mEdtUserName = (EditText) parentView.findViewById(R.id.edt_username);

        ImageView mIvStrimPathStart0 = (ImageView) parentView.findViewById(R.id.iv_strim_path_start0);
        ImageView mIvStrimPathStart1 = (ImageView) parentView.findViewById(R.id.iv_strim_path_start1);
        ImageView mIvStrimPathEnd0 = (ImageView) parentView.findViewById(R.id.iv_strim_path_end0);
        ImageView mIvStrimPathEnd1 = (ImageView) parentView.findViewById(R.id.iv_strim_path_end1);

        AnimatedVectorDrawable start0 = (AnimatedVectorDrawable) getContext().getDrawable(R.drawable.dr_start0);
        AnimatedVectorDrawable start1 = (AnimatedVectorDrawable) getContext().getDrawable(R.drawable.dr_start1);
        AnimatedVectorDrawable end0 = (AnimatedVectorDrawable) getContext().getDrawable(R.drawable.dr_end0);
        AnimatedVectorDrawable end1 = (AnimatedVectorDrawable) getContext().getDrawable(R.drawable.dr_end1);
        mIvStrimPathStart0.setImageDrawable(start0);
        mIvStrimPathStart1.setImageDrawable(start1);
        mIvStrimPathEnd0.setImageDrawable(end0);
        mIvStrimPathEnd1.setImageDrawable(end1);
        ((Animatable) mIvStrimPathStart0.getDrawable()).start();
        ((Animatable) mIvStrimPathStart1.getDrawable()).start();
        ((Animatable) mIvStrimPathEnd0.getDrawable()).start();
        ((Animatable) mIvStrimPathEnd1.getDrawable()).start();

        mBtnLogin.setOnClickListener(this);
        return parentView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                initAnimator();
                ((Animatable) mIvBottom.getDrawable()).start();
                ((Animatable) mBtnLogin.getBackground()).start();
                break;
            default:
                break;
        }
    }

    private void initAnimator() {
        final AnimatedVectorDrawable resultAnimRight = (AnimatedVectorDrawable) getContext().getDrawable(R.drawable.dr_result_right);
        final AnimatedVectorDrawable resultAnimWrong = (AnimatedVectorDrawable) getContext().getDrawable(R.drawable.dr_result_wrong);

        AnimatedVectorDrawable lineProgress = (AnimatedVectorDrawable) getContext().getDrawable(R.drawable.dr_line_progress);
        AnimatedVectorDrawable loginClick = (AnimatedVectorDrawable) getContext().getDrawable(R.drawable.dr_btn_bg);
        mIvBottom.setImageDrawable(lineProgress);
        mBtnLogin.setBackground(loginClick);

        assert lineProgress != null;
        lineProgress.registerAnimationCallback(new Animatable2.AnimationCallback() {
            @Override
            public void onAnimationEnd(Drawable drawable) {
                super.onAnimationEnd(drawable);
                if (checkResult()) {
                    mIvResult.setImageDrawable(resultAnimRight);
                } else {
                    mIvResult.setImageDrawable(resultAnimWrong);
                }
                ((Animatable) mIvResult.getDrawable()).start();

            }
        });
    }

    private boolean checkResult() {
        return mEdtUserName.getText().toString().equals("10086");
    }
}
