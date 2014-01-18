package com.example.morninggloryobservationdiary;

import android.R.color;
import android.os.Bundle;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.graphics.Point;
import android.text.InputType;
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
	//ボタン
	Button button = null;
	
	//レイアウト
	LinearLayout ll = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //画面のサイズ
        Point outSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(outSize);
        
        int margin = outSize.x/15;
        
        //フルスクリーンの設定
        requestWindowFeature( Window.FEATURE_NO_TITLE );
        
        //レイアウト
        ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setBackgroundColor( Color.rgb(245, 245, 245) );
        ll.setLayoutParams(new LinearLayout.LayoutParams(MP, MP ));
                
        LinearLayout ll1 = new LinearLayout(this);
        ll1.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams tv_param = new LinearLayout.LayoutParams( MP , WC );
        tv_param.setMargins(margin, margin, margin, 0);        
        
	        //テキストの入力
	        EditText tv = new EditText(this);     
	        tv.setInputType( InputType.TYPE_CLASS_TEXT );	      
	        ll1.addView(tv, outSize.x*6/10, WC);
	        
	        //日時
	        TextView tv2 = new TextView(this);     
	        tv2.setText("2014/1/19");
	        tv2.setGravity( Gravity.LEFT );
	        tv2.setTextSize(20);
	        ll1.addView(tv2, outSize.x*4/10, WC);
	        
	        
	    ll.addView(ll1, tv_param);
                
        //テキストの入力
        editText = new EditText(this);     
        editText.setOnClickListener(this);
        editText.setLines(4);
        editText.setGravity(Gravity.TOP);
        
        LinearLayout.LayoutParams edit_param = new LinearLayout.LayoutParams( MP , WC );
        edit_param.setMargins(margin, 0, margin, margin/4);
        	        
	    ll.addView(editText , edit_param);	        

	    //カメラの画面の大きさを指定する
        cameraView = new CameraView(this); 
        	   	    
        LinearLayout.LayoutParams camera_param = new LinearLayout.LayoutParams( MP , (outSize.x - margin*2)*4/3 );
        camera_param.setMargins(margin, margin/4, margin, 0);

	    ll.addView(cameraView, camera_param );
	        
        
        ll.setFocusable(true);
        
        setContentView( ll );
    }
    
	@Override
	public void onClick(View v) {
	
		if( v == editText )
		{
		}	
		if( v == button )
		{
		}		
	}
    
}
