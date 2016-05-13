package com.example.weili.practice;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BlueTeethActivity extends AppCompatActivity {

    private Button start,close;
    private ListView listView;
    private BluetoothAdapter btAdapter;
    private BluetoothDevice  btDevice;
    private List<String>  datas = new ArrayList<>();
    private ArrayAdapter<String> listAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_teeth);

        initView();

    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }

    private void initView(){
        start = (Button) findViewById(R.id.start);
        close = (Button) findViewById(R.id.close);
        listView = (ListView) findViewById(R.id.lv);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btAdapter = BluetoothAdapter.getDefaultAdapter();
                if(btAdapter.getState() == BluetoothAdapter.STATE_OFF){
                    btAdapter.enable();
                }

                listAdapter = new ArrayAdapter<String>(BlueTeethActivity.this,android.R.layout.simple_expandable_list_item_1,datas);
                listView.setAdapter(listAdapter);
                Toast.makeText(BlueTeethActivity.this,listAdapter.getCount()+"",Toast.LENGTH_SHORT).show();
                IntentFilter filter = new IntentFilter();
                filter.addAction(BluetoothDevice.ACTION_FOUND);
                filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
                registerReceiver(receiver,filter);



            }
        });


    }


    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Bundle bundle = intent.getExtras();
            Object[] listNames = bundle.keySet().toArray();

            for(int i = 0;i< listNames.length;i++){
                String keyName = listNames[i].toString();
                Log.e(keyName, String.valueOf(bundle.get(keyName)));
            }


            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if(device.getBondState() == BluetoothDevice.BOND_NONE){
                    String name = device.getName();
                    datas.add(name);
                    listAdapter.notifyDataSetChanged();
                }
            }

        }
    };
}
