package com.example.morninggloryobservationdiary;

import java.io.FileOutputStream;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class CameraView extends SurfaceView implements Callback,PictureCallback {

	private SurfaceHolder 	holder;	//ホルダー
	private Camera			camera;	//カメラ
	
	public CameraView(Context context) {
		super(context);
		
		//サーフェスホルダーの作成
		holder = getHolder();
		holder.addCallback(this);
		
		//プッシュバッファの指定
		holder.setType( SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS );		
	}

	//サーフェス更新イベントの処理
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {

		//カメラプレビューの開始
		camera.startPreview();
	}

	//サーフェス生成イベントの処理
	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		
		//カメラの初期化
		try{
			camera = Camera.open();
			camera.setPreviewDisplay(holder);
		}
		catch( Exception e )
		{			
		}	
	}

	//サーフェス解放イベントの処理
	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {

		//カメラプレビューの停止
		camera.setPreviewCallback(null);
		camera.stopPreview();
		camera.release();
		camera=null;		
	}
	
	//タッチの時に呼ばれる
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		if( event.getAction() == MotionEvent.ACTION_DOWN )
		{
			//カメラのスクリーンショットの取得
			camera.takePicture(null, null, this);
		}
		return true;
	}

	//写真撮影時に呼ばれる
	@Override
	public void onPictureTaken(byte[] data, Camera camera) {
		
		//SDカードへのデータ保存
		try
		{
			String path = Environment.getExternalStorageDirectory()+"/test.jpg";
			data2file(data,path );
		}		
		catch( Exception e )
		{			
		}
		
		//プレビューの再開
		camera.startPreview();
	}
	
	//バイトデータ→ファイル
	private void data2file( byte[] w,String fileName ) throws Exception
	{
		
		FileOutputStream out = null;
		try
		{
			out = new FileOutputStream(fileName);
			out.write(w);
			out.close();
		}
		catch(Exception e)
		{
			if( out!=null )out.close();
			throw e;
		}		
	}
	
}
