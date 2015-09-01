package com.example.star.popwinddialogfragmentdemo;

import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class BottomFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String TAG = "BottomFragment";

    private String mParam1;
    private String mParam2;

    ListView mListView;
    private ArrayList<Bean> mData;
    private ArrayAdapter<String> mArrayAdapter;
    private ProgressDialog mPD;


    private void showProgressDialog(int resId) {
        mPD = new ProgressDialog(getActivity());
        mPD.setCancelable(false);
        mPD.setMessage(getResources().getString(resId));
        mPD.show();
    }

    public static BottomFragment newInstance(String param1, String param2) {
        BottomFragment fragment = new BottomFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public BottomFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.CustomDialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom, container, false);
        mListView = (ListView) view.findViewById(android.R.id.list);
        mData = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Bean bean = new Bean();
            bean.resId = R.drawable.ic_msg_tools_profile;
            bean.title = "title " + (i + 1);
            mData.add(bean);
        }

        mListView = (ListView) view.findViewById(android.R.id.list);

        MyAdapter myAdapter = new MyAdapter(getActivity(), mData);

        mListView.setAdapter(myAdapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "listView onItemClick " + mData.get(position).title, Toast.LENGTH_SHORT).show();
            }
        });

        WindowManager.LayoutParams p = getDialog().getWindow().getAttributes();
        p.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        p.width = WindowManager.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes(p);

        return view;
    }


    class Bean {
        public int resId;
        public String title;
    }

    //ViewHolder静态类
    static class ViewHolder {
        public ImageView img;
        public TextView title;
    }

    public class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater = null;
        List<Bean> data;

        public MyAdapter(Context context, List<Bean> data) {
            //根据context上下文加载布局，这里的是Demo17Activity本身，即this
            this.data = data;
            this.mInflater = LayoutInflater.from(context);
        }


        @Override
        public int getCount() {
            //How many items are in the data set represented by this Adapter.
            //在此适配器中所代表的数据集中的条目数
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            // Get the data item associated with the specified position in the data set.
            //获取数据集中与指定索引对应的数据项
            return position;
        }

        @Override
        public long getItemId(int position) {
            //Get the row id associated with the specified position in the list.
            //获取在列表中与指定索引对应的行id
            return position;
        }

        //Get a View that displays the data at the specified position in the data set.
        //获取一个在数据集中指定索引的视图来显示数据
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            //如果缓存convertView为空，则需要创建View
            if (convertView == null) {
                holder = new ViewHolder();
                //根据自定义的Item布局加载布局
                convertView = mInflater.inflate(R.layout.bottom_list_item, null);
                holder.img = (ImageView) convertView.findViewById(R.id.list_image);
                holder.title = (TextView) convertView.findViewById(R.id.list_title);
                //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.img.setImageResource((Integer) data.get(position).resId);
            holder.title.setText((String) data.get(position).title);

            return convertView;
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        getDialog().setCanceledOnTouchOutside(true);

    }
}
