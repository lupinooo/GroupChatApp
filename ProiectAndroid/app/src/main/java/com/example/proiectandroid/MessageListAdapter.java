package com.example.proiectandroid;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MessageListAdapter extends BaseAdapter {
    private Context mContext;
    private List<MessageClass> messList;

    public MessageListAdapter(Context mContext, List<MessageClass> messList) {
        this.mContext = mContext;
        this.messList = messList;
    }

    @Override
    public int getCount() {
        return messList.size();
    }

    @Override
    public Object getItem(int position) {
        return messList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=View.inflate(mContext, R.layout.sent_message, null);
        TextView tvUser=(TextView)v.findViewById(R.id.tvUsername);
        TextView tvMess=(TextView)v.findViewById(R.id.tvMessage);
        TextView tvTime=(TextView)v.findViewById(R.id.tvTime);

        tvUser.setText(messList.get(position).getSender());
        tvMess.setText(messList.get(position).getMessage());
        tvTime.setText(messList.get(position).getTime());

        return v;
    }
}
