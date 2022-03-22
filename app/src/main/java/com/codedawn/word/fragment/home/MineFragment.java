package com.codedawn.word.fragment.home;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codedawn.word.R;
import com.codedawn.word.litepal.UserData;


public class MineFragment extends Fragment {

    private TextView textNickname;

    private ImageView imageAvatar;

    public MineFragment() {

    }


    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        textNickname = view.findViewById(R.id.text_nickname);
        imageAvatar = view.findViewById(R.id.image_avatar);


        initData();
        return view;
    }

    private void initData() {
        UserData userData = UserData.getInstance();
        textNickname.setText(userData.getNickname());
        Glide.with(getContext()).load(userData.getAvatar()).into(imageAvatar);
    }
}