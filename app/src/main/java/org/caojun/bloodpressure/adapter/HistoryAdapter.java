package org.caojun.bloodpressure.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import org.caojun.bloodpressure.R;
import org.caojun.bloodpressure.ormlite.History;
import java.util.List;

/**
 * Created by CaoJun on 2017/7/10.
 */

public class HistoryAdapter extends BaseAdapter {
    private Context context;
    private List<History> list;

    public HistoryAdapter(Context context, List<History> list) {
        this.context = context;
        setData(list);
    }

    public void setData(List<History> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        if(list == null || list.isEmpty()) {
            return 0;
        }
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        if(list == null || list.isEmpty()) {
            return null;
        }
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_history, null);
            holder = new ViewHolder();
            holder.tvYearPicture = (TextView) view.findViewById(R.id.tvYearPicture);
            holder.tvYearNoPicture = (TextView) view.findViewById(R.id.tvYearNoPicture);
            holder.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        History history = (History) getItem(i);
        holder.tvTitle.setText(history.getTitle());
        String content = context.getString(R.string.th_year_value, history.getYear());
        if (history.getContent().contains("<img ")) {
            holder.tvYearPicture.setText(content);
            holder.tvYearPicture.setVisibility(View.VISIBLE);
            holder.tvYearNoPicture.setVisibility(View.GONE);
        } else {
            holder.tvYearNoPicture.setText(content);
            holder.tvYearNoPicture.setVisibility(View.VISIBLE);
            holder.tvYearPicture.setVisibility(View.GONE);
        }

        return view;
    }

    private class ViewHolder {
        TextView tvYearPicture, tvYearNoPicture, tvTitle;
    }
}
