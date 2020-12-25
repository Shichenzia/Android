package com.example.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
        import android.widget.TextView;
import com.example.sms.R;
import com.example.util.Student;

import java.util.List;

public class StudentAdapter extends BaseAdapter implements View.OnClickListener {
    // private final View.OnClickListener listener;
    private Context mContext;
    private final List<Student>         dataList;

    public StudentAdapter(Context mContext ,List<Student> dataList) {
        this.mContext= mContext;
        this.dataList = dataList;
    }

    private BottomBtnOnclick bottomBtnOnclick;

    public BottomBtnOnclick getBottomBtnOnclick() {
        return bottomBtnOnclick;
    }

    public void setBottomBtnOnclick(BottomBtnOnclick bottomBtnOnclick) {
        this.bottomBtnOnclick = bottomBtnOnclick;
    }

    public interface BottomBtnOnclick {
        public void click(View view, int index);
    }


    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_student_item, parent,false);

            holder.studentPhoto = (ImageView) convertView.findViewById(R.id.photo);
            holder.s_studentid = (TextView) convertView.findViewById(R.id.s_studentid);
            holder.s_name = (TextView) convertView.findViewById(R.id.s_name);
            holder.s_sex = (TextView) convertView.findViewById(R.id.s_sex);
            holder.s_classname = (TextView) convertView.findViewById(R.id.s_classname);
            holder.delete = (Button) convertView.findViewById(R.id.delete);
            holder.add = (Button) convertView.findViewById(R.id.add);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.s_studentid.setText(String.valueOf(dataList.get(position).getS_studentid()));
        holder.s_name.setText(dataList.get(position).getS_name());
        holder.s_sex.setText(dataList.get(position).getS_sex());
        holder.s_classname.setText(dataList.get(position).getS_classname());

        if(dataList.get(position).getS_sex().equals("男")){
            holder.studentPhoto.setImageResource(R.drawable.boy);
        }else {
            holder.studentPhoto.setImageResource(R.drawable.girl);
        }

        //给要被点击的控件加入点击监听，具体事件在创建adapter的地方实现
        holder.delete.setOnClickListener(this);
        //通过setTag 将被点击控件所在条目的位置传递出去
        holder.delete.setTag(position);

        //给要被点击的控件加入点击监听，具体事件在创建adapter的地方实现
        holder.add.setOnClickListener(this);
        //通过setTag 将被点击控件所在条目的位置传递出去
        holder.add.setTag(position);

        return convertView;
    }

    @Override
    public void onClick(View view) {
        bottomBtnOnclick.click(view,(int)view.getTag());
    }


    class ViewHolder {
        ImageView studentPhoto;
        TextView s_studentid;
        TextView s_name;
        TextView s_sex;
        TextView s_classname ;
        Button delete;
        Button add;

    }
}



