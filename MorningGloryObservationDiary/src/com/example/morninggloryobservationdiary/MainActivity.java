package com.example.morninggloryobservationdiary;

import android.R.color;
import android.os.Bundle;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity implements android.view.View.OnClickListener {

	final int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
	final int MP = LinearLayout.LayoutParams.MATCH_PARENT;
	
	//カメラ描画
	CameraView cameraView = null;
	//テキスト入力
	EditText editText = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //フルスクリーンの設定
        getWindow().clearFlags( WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN );
        getWindow().addFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN );
        
        requestWindowFeature( Window.FEATURE_NO_TITLE );
        
        //レイアウト
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setBackgroundColor( Color.RED );
        ll.setLayoutParams(new LinearLayout.LayoutParams(MP, MP ));
        
        //テキストビューの追加
        TextView tv = new TextView(this);
        tv.setText("カメラのテスト");
        tv.setTextSize(20);
        ll.addView(tv);
        
	        LinearLayout ll2 = new LinearLayout(this);
	        ll2.setOrientation( LinearLayout.HORIZONTAL );
	   	    
	        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams( MP , WC );
	        param.setMargins(10, 10, 10, 10);
	        ll2.setLayoutParams(param);
	        
	        //カメラの画面の大きさを指定する
	        cameraView = new CameraView(this);        
	        ll2.addView(cameraView, MP , MP);
	        
	    ll.addView(ll2 , MP , 700);        
        
	        LinearLayout ll3 = new LinearLayout(this);
	        ll3.setOrientation( LinearLayout.HORIZONTAL );        
	        ll3.setGravity( Gravity.BOTTOM );	        

	        //ボタン
	        Button button = new Button(this);
	        button.setText("送信");
	        button.setWidth(50);
	        ll3.addView(button);
	        
	        //テキストの入力
	        editText = new EditText(this);     
	        editText.setGravity( Gravity.BOTTOM );
	        editText.setOnClickListener(this);
	        ll3.addView( editText , MP , WC ); 
	        
	    ll.addView(ll3,MP,MP);	   	    
        
        ll.setFocusable(true);
        
        setContentView( ll );
    }
    
	@Override
	public void onClick(View v) {
	
		if( v == editText )
		{
			cameraView.invalidate();
		}		
	}
    
}
