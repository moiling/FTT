package com.moi.freetimetabletest.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.moi.freetimetabletest.objectclass.Member;
import com.moi.freetimetabletest.R;

import java.util.List;

/**
 * Created by moi on 2015/4/9.
 *
 */
public class MemberListAdapter extends ArrayAdapter<Member> {

    private int resouceId;

    public MemberListAdapter(Context context, int resource, List<Member> objects) {
        super(context, resource, objects);
        resouceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Member member = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resouceId, null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) view.findViewById(R.id.tv_item);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textView.setText("  ●  " + member.getName());
        return view;
    }

    private class ViewHolder {
        TextView textView;
    }
}
