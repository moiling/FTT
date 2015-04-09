package com.moi.freetimetabletest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by moi on 2015/4/3.
 *
 */
public class TableListAdapter extends ArrayAdapter<Table> {

    private int resouceId;


    public TableListAdapter(Context context, int resource, List<Table> objects) {
        super(context, resource, objects);
        resouceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Table table = getItem(position);
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
        viewHolder.textView.setText("  ●  " + table.getName());
        return view;
    }

    private class ViewHolder {
        TextView textView;
    }
}
