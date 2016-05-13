package com.example.weili.practice;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.widget.caladen.GridViewNested;

import java.util.Arrays;
import java.util.List;

public class TypeListViewActivity extends AppCompatActivity {

    private ListView mListView;
    private Context mContext;

    String[] names = {"北京","上海","南京","武汉"};
    String[] names1 = {"北京11","上海11","南京11","武汉11"};
    String[] names2 = {"北京22","上海22","南京22","武汉22"};
    String[] names3 = {"北京33","上海33","南京33","武汉33","北京44","上海55","南京66","武汉77"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_list_view);
        mContext = this;
        initView();
    }

    private void initView(){
        mListView = (ListView) findViewById(R.id.lv);
        TypeListViewAdapter adapter = new TypeListViewAdapter(Arrays.asList(names),
                Arrays.asList(names1),Arrays.asList(names2),Arrays.asList(names3));
        mListView.setAdapter(adapter);
    }







    public class TypeListViewAdapter extends BaseAdapter{

      private List<String> mList1;
      private List<String> mList2;
      private List<String> mList3;
      private List<String> mList4;

        private TypeListViewAdapter(List<String> name1,List<String> name2,
                                    List<String> name3,List<String> name4){
            this.mList1 = name1;
            this.mList2 = name2;
            this.mList3 = name3;
            this.mList4 = name4;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TypeViewHolder holder = new TypeViewHolder();
            int viewType = getItemViewType(position);
            if(viewType == 0){
                convertView = LayoutInflater.from(mContext).inflate(R.layout.current_location_layout,null);
                TextView location = (TextView) convertView.findViewById(R.id.location);
                GridViewNested currentLocation = (GridViewNested) convertView.findViewById(R.id.currentGrid);
                location.setText("正在地位....");
                GridAdapter mAdapter = new GridAdapter(mList1);
                currentLocation.setAdapter(mAdapter);
            }else if(viewType == 1){
                convertView = LayoutInflater.from(mContext).inflate(R.layout.current_location_layout,null);
                TextView location = (TextView) convertView.findViewById(R.id.location);
                GridViewNested currentLocation = (GridViewNested) convertView.findViewById(R.id.currentGrid);
                location.setText("历史");
                GridAdapter mAdapter1 = new GridAdapter(mList2);
                currentLocation.setAdapter(mAdapter1);
            }else if(viewType == 2){
                convertView = LayoutInflater.from(mContext).inflate(R.layout.current_location_layout,null);
                TextView location = (TextView) convertView.findViewById(R.id.location);
                GridViewNested currentLocation = (GridViewNested) convertView.findViewById(R.id.currentGrid);
                location.setText("热门。。。");
                GridAdapter mAdapter2 = new GridAdapter(mList3);
                currentLocation.setAdapter(mAdapter2);
            }else if(viewType == 3){
                convertView = LayoutInflater.from(mContext).inflate(R.layout.current_location_layout,null);
                TextView location = (TextView) convertView.findViewById(R.id.location);
                location.setText("全部城市。。");

            }else{
                if(convertView == null){
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item,null);
                    holder.location = (TextView) convertView.findViewById(R.id.list_item_text_view);
                    convertView.setTag(holder);
                }else{
                    holder = (TypeViewHolder) convertView.getTag();
                }

                holder.location.setText(mList4.get(position-4));

            }

            return convertView;
        }

        @Override
        public int getCount() {
            return mList4.size()+4;
        }


        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getViewTypeCount() {
            return 5;
        }

        @Override
        public int getItemViewType(int position) {
            return position<4?position:4;
        }


        @Override
        public Object getItem(int position) {

            if(position == 0){
                return mList1;
            }else if(position == 1){
                return  mList2;
            }else if(position == 2){
                return  mList3;
            }

            return mList4;
        }



        class TypeViewHolder{

            TextView location;
            GridViewNested currentLocation;

        }

        class GridAdapter extends BaseAdapter{

            private List<String> mList;
            public GridAdapter(List<String> strings){
                this.mList = strings;
            }


            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public int getCount() {
                return mList.size();
            }

            @Override
            public Object getItem(int position) {
                return mList.get(position);
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                GridViewHolder viewHolder = new GridViewHolder();
                if(convertView == null){
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item,null);
                    viewHolder.mTextView = (TextView) convertView.findViewById(R.id.list_item_text_view);
                    convertView.setTag(viewHolder);
                }else{
                    viewHolder = (GridViewHolder) convertView.getTag();
                }

                viewHolder.mTextView.setText(mList.get(position));
                return convertView;
            }

            class GridViewHolder {

                TextView mTextView;
            }
        }



    }
}
