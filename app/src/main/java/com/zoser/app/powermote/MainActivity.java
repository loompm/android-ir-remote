package com.zoser.app.powermote;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    private IRController _irController;

    private ImageButton _buttonPowerAll;

    private LinearLayout[] _button = new LinearLayout[14];
    private IRMessageRequest[] _buttonRequest = new IRMessageRequest[14];

    private LinearLayout[] _rows = new LinearLayout[5];

    private View _currentClickedView = null;

    private Vibrator _vibrator = null;

    private long _lastBurstTime = 0;     // Microsecconds
    private long _waitTime = 315000;     // Microsecconds
    private long _waitTimeMax = 1000000; // Microsecconds
    private long _waitTimeMin = 300000;  // Microsecconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IRMessages.initialize();

        setContentView(R.layout.main_activity);

        getSupportActionBar().hide();

        _vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);

        _irController = new IRController(getApplicationContext());
        _irController.startWork();

        getRowReferences();

        _buttonPowerAll = (ImageButton) findViewById(R.id.id_Button_ALL);

        _button[0] = createIRButton("1", R.drawable.icon_02, _rows[0], new IRMessageRequest(IRMessages.XIAOMI_TV_4S_1));
        _button[1] = createIRButton("2", R.drawable.icon_03, _rows[0], new IRMessageRequest(IRMessages.XIAOMI_TV_4S_2));
        _button[2] = createIRButton("3", R.drawable.icon_05, _rows[0], new IRMessageRequest(IRMessages.XIAOMI_TV_4S_3));
    
        _button[3] = createIRButton("4", R.drawable.icon_07, _rows[1], new IRMessageRequest(IRMessages.XIAOMI_TV_4S_4));
        _button[4] = createIRButton("5", R.drawable.icon_08, _rows[1], new IRMessageRequest(IRMessages.XIAOMI_TV_4S_5));
        _button[5] = createIRButton("6", R.drawable.icon_09, _rows[1], new IRMessageRequest(IRMessages.XIAOMI_TV_4S_6));

        _button[6] = createIRButton("7", R.drawable.icon_10, _rows[2], new IRMessageRequest(IRMessages.XIAOMI_TV_4S_7));
        _button[7] = createIRButton("8", R.drawable.icon_10, _rows[2], new IRMessageRequest(IRMessages.XIAOMI_TV_4S_8));
        _button[8] = createIRButton("9", R.drawable.icon_10, _rows[2], new IRMessageRequest(IRMessages.XIAOMI_TV_4S_9));

        _button[9] = createIRButton("0", R.drawable.icon_01, _rows[3], new IRMessageRequest(IRMessages.XIAOMI_TV_4S_0));
        _button[10] = createIRButton("INPUT", R.drawable.icon_06, _rows[3], new IRMessageRequest(IRMessages.XIAOMI_TV_4S_INPUT));
        _button[11] = createIRButton("MUTE", R.drawable.icon_04, _rows[3], new IRMessageRequest(IRMessages.XIAOMI_TV_4S_MUTE));

        _button[12] = createIRButton("FWD", R.drawable.icon_10, _rows[4], new IRMessageRequest(IRMessages.XIAOMI_TV_4S_FWD));
        _button[13] = createIRButton("REW", R.drawable.icon_10, _rows[4], new IRMessageRequest(IRMessages.XIAOMI_TV_4S_REW));

        _lastBurstTime = System.nanoTime();

        for (int a = 0; a < 14; a++) {
            _button[a].setOnTouchListener(this);
        }

        _buttonPowerAll.setOnTouchListener(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Zoser", "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d("Zoser", "onRestart");
    }

    @Override
    protected void onDestroy() {
        Log.d("Zoser", "onDestroy");
        _irController.stopWork();
        super.onDestroy();
    }

    private void getRowReferences() {
        _rows[0] = (LinearLayout) findViewById(R.id.id_Layout_Row_01);
        _rows[1] = (LinearLayout) findViewById(R.id.id_Layout_Row_02);
        _rows[2] = (LinearLayout) findViewById(R.id.id_Layout_Row_03);
        _rows[3] = (LinearLayout) findViewById(R.id.id_Layout_Row_04);
        _rows[4] = (LinearLayout) findViewById(R.id.id_Layout_Row_05);

    }

    private LinearLayout createIRButton(String title, int imageResource, LinearLayout row, IRMessageRequest request) {
        View child = getLayoutInflater().inflate(R.layout.widget_button, null);

        RelativeLayout mainLayout = (RelativeLayout) child.findViewById(R.id.layout_main);
        LinearLayout buttonLayout = (LinearLayout) child.findViewById(R.id.layout_button);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
        lp.weight = 1;
        mainLayout.setLayoutParams(lp);

        ImageView imageView = (ImageView) child.findViewById(R.id.image_main);
        TextView titleView = (TextView) child.findViewById(R.id.text_title);

        imageView.setImageResource(imageResource);
        titleView.setText(title);

        row.addView(child);

        buttonLayout.setTag(request);

        return buttonLayout;
    }

    public boolean onTouch(View v, MotionEvent event) {
        _waitTime = Math.max(_waitTime, _waitTimeMin);
        _waitTime = Math.min(_waitTime, _waitTimeMax);

        if ((System.nanoTime() - _lastBurstTime) > (_waitTime * 1000)) {
            int ev = event.getActionMasked();

            if (ev == MotionEvent.ACTION_MOVE) {
                _lastBurstTime = System.nanoTime();

                if (v == _buttonPowerAll) {
                    _waitTime = sendIRMessage(new IRMessageRequest(IRMessages.HDMI_SPLITTER_ON, IRMessages.HOME_LG_TV_ON, IRMessages.HOME_SONY_HT_ON));
                } else {
                    _waitTime = sendIRMessage(((IRMessageRequest) v.getTag()));
                }
                return true;
            }
        }
        return false;
    }

    public long sendIRMessage(IRMessageRequest request) {
        _vibrator.vibrate(60);
        return _irController.sendMessage(request);
    }
}
