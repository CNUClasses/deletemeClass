package com.example.keith.deletemeclass;

import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by keith on 3/23/17.
 */

public class CustomAdapter extends BaseAdapter {
    private static final int NUMB_ROWS = 1000;
    private static final long TEN_MILLISECONDS = 100;
    private final MainActivity act;

    private LayoutInflater li;
    private boolean doThreads = true;

    public CustomAdapter(MainActivity act){
        this.act = act;
        li = (LayoutInflater)act.getSystemService(act.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return NUMB_ROWS;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder {
        TextView tv1;
        TextView tv2;
        TextView tv3;
        int myNumberToDouble;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder myVh;

        if (convertView == null) {
            convertView = li.inflate(R.layout.ca, null);

            myVh= new ViewHolder();
            myVh.tv1 = (TextView) convertView.findViewById(R.id.tv1);
            myVh.tv2 = (TextView) convertView.findViewById(R.id.tv2);
            myVh.tv3 = (TextView) convertView.findViewById(R.id.tv3);
            convertView.setTag(myVh);

        }
            myVh= (ViewHolder)convertView.getTag();

        myVh.tv1.setText(Integer.toString(position));
        myVh.tv2.setText(Integer.toString(position));
        myVh.tv3.setText("?");

        if (doThreads == false)
            myVh.tv3.setText(Integer.toString(position+position));
        else
            new calcTask(myVh).execute(position);


        return convertView;
    }

    public class calcTask extends AsyncTask<Integer,Void,Integer>{

        private final int originalBeforethreadLaunched_position;
        private final ViewHolder myvh;

        public calcTask(ViewHolder myvh){
            this.myvh = myvh;
            this.originalBeforethreadLaunched_position = myvh.myNumberToDouble;
        }
        @Override
        protected Integer doInBackground(Integer... params) {

            try {
                Thread.sleep(TEN_MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return params[0] + params[0];
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            if (myvh.myNumberToDouble == originalBeforethreadLaunched_position){
                myvh.tv3.setText(Integer.toString(integer));
            }
        }

    }
}
