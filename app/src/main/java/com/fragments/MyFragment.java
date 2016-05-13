package com.fragments;

import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.weili.practice.R;

/**
 * Created by wei.li on 2015/11/2.
 */
public class MyFragment extends Fragment {

    private ImageView imageView;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.my_fragment, null);

        imageView = (ImageView) view.findViewById(R.id.img88);
        button = (Button) view.findViewById(R.id.anima);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setBackgroundResource(R.drawable.list_anim);
                AnimationDrawable drawable = (AnimationDrawable) imageView.getBackground();
                drawable.start();

                ObjectAnimator valueAnimator = ObjectAnimator.ofFloat(button, "alpha", 1f, 0f);
                valueAnimator.setDuration(1000);
                valueAnimator.start();



            }
        });


//      /*  imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
////                builder.setMessage("申请成功，请耐心等待对方回复")
////                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
////                            @Override
////                            public void onClick(DialogInterface dialog, int which) {
////                                getActivity().finish();
////                            }
////                        })
////                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
////                            @Override
////                            public void onClick(DialogInterface dialog, int which) {
////                                dialog.dismiss();
////                            }
////                        });
////                builder.show();
//
//            }
//        });*/
        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            String str = getArguments().getString("str");
        }
    }

    public static MyFragment newInstance(String str){
        MyFragment myFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("str", str);
        myFragment.setArguments(bundle);
        return myFragment;
    }



}
