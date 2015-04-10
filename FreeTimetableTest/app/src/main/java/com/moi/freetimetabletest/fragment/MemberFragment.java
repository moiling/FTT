package com.moi.freetimetabletest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

import com.gc.materialdesign.views.ButtonFloat;
import com.moi.freetimetabletest.BounceListView;
import com.moi.freetimetabletest.R;
import com.moi.freetimetabletest.adpter.MemberListAdapter;
import com.moi.freetimetabletest.objectclass.Member;

import java.util.ArrayList;
import java.util.List;


public class MemberFragment extends Fragment implements View.OnClickListener{

    private BounceListView mListView;

    private MemberListAdapter adapter;

    private List<Member> memberList = new ArrayList<Member>();

    // private static FloatingActionButton actionButton;

    private ButtonFloat menuButton;

    private ButtonFloat addMemberButton;

    private ButtonFloat clearMembersButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_member, container, false);

        initMember();
        mListView = (BounceListView) view.findViewById(R.id.list_view);
        adapter = new MemberListAdapter(getActivity().getBaseContext(), R.layout.item_table_list, memberList);
        mListView.setAdapter(adapter);

        menuButton = (ButtonFloat) view.findViewById(R.id.menu_button);
        menuButton.setBackgroundColor(getResources().getColor(R.color.accent_color));
        menuButton.setOnClickListener(this);
        addMemberButton = (ButtonFloat) view.findViewById(R.id.btn_add_member);
        addMemberButton.setBackgroundColor(getResources().getColor(R.color.accent_color));
        addMemberButton.setVisibility(View.GONE);
        clearMembersButton = (ButtonFloat) view.findViewById(R.id.btn_clear_members);
        clearMembersButton.setBackgroundColor(getResources().getColor(R.color.accent_color));
        clearMembersButton.setVisibility(View.GONE);
        /*// CircularFloatingActionMenu
        ImageView icon = new ImageView(getActivity().getBaseContext());
        icon.setImageDrawable(getResources().getDrawable(R.mipmap.button));

        actionButton = new FloatingActionButton.Builder(getActivity())
                .setContentView(icon)
                .build();
        MemberFragment.getFloatingActionButton().setVisibility(View.GONE);

        // Create menu items
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(getActivity());
        // repeat many times:
        ImageView itemIcon = new ImageView(getActivity().getBaseContext());
        itemIcon.setImageDrawable(getResources().getDrawable(R.mipmap.button_small));
        SubActionButton button1 = itemBuilder.setContentView(itemIcon).build();
        ImageView itemIcon2 = new ImageView(getActivity().getBaseContext());
        itemIcon.setImageDrawable(getResources().getDrawable(R.mipmap.button_small));
        SubActionButton button2 = itemBuilder.setContentView(itemIcon2).build();
        ImageView itemIcon3 = new ImageView(getActivity().getBaseContext());
        itemIcon.setImageDrawable(getResources().getDrawable(R.mipmap.button_small));
        SubActionButton button3 = itemBuilder.setContentView(itemIcon3).build();
        ImageView itemIcon4 = new ImageView(getActivity().getBaseContext());
        itemIcon.setImageDrawable(getResources().getDrawable(R.mipmap.button_small));
        SubActionButton button4 = itemBuilder.setContentView(itemIcon4).build();
        // Create menu with the items:
        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(getActivity())
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .addSubActionView(button4)
                .attachTo(actionButton)
                .build();*/

        return view;
    }

    private void initMember() {
        for (int i = 0; i < 20; i++) {
            Member a = new Member("test_" + i);
            memberList.add(a);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_button:

                if (addMemberButton.getVisibility() == View.GONE) {
                    Animation rotateAnimation = new RotateAnimation(360f, 0f, 0f, 0f);
                    rotateAnimation.setDuration(500);
                    addMemberButton.startAnimation(rotateAnimation);
                    clearMembersButton.startAnimation(rotateAnimation);
                    addMemberButton.setVisibility(View.VISIBLE);
                    clearMembersButton.setVisibility(View.VISIBLE);
                } else {
                    Animation rotateAnimation = new RotateAnimation(0f, 360f, 0f, 0f);
                    rotateAnimation.setDuration(500);
                    addMemberButton.startAnimation(rotateAnimation);
                    clearMembersButton.startAnimation(rotateAnimation);
                    addMemberButton.setVisibility(View.GONE);
                    clearMembersButton.setVisibility(View.GONE);
                }
                break;
        }
    }

/*    public static FloatingActionButton getFloatingActionButton() {
        return actionButton;
    }*/

}
