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

	private SurfaceHolder 	holder;	//�z���_�[
	private Camera			camera;	//�J����
	
	public CameraView(Context context) {
		super(context);
		
		//�T�[�t�F�X�z���_�[�̍쐬
		holder = getHolder();
		holder.addCallback(this);
		
		//�v�b�V���o�b�t�@�̎w��
		holder.setType( SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS );		
	}

	//�T�[�t�F�X�X�V�C�x���g�̏���
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {

		//�J�����v���r���[�̊J�n
		camera.startPreview();
	}

	//�T�[�t�F�X�����C�x���g�̏���
	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		
		//�J�����̏�����
		try{
			camera = Camera.open();
			camera.setPreviewDisplay(holder);
		}
		catch( Exception e )
		{			
		}	
	}

	//�T�[�t�F�X����C�x���g�̏���
	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {

		//�J�����v���r���[�̒�~
		camera.setPreviewCallback(null);
		camera.stopPreview();
		camera.release();
		camera=null;		
	}
	
	//�^�b�`�̎��ɌĂ΂��
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		if( event.getAction() == MotionEvent.ACTION_DOWN )
		{
			//�J�����̃X�N���[���V���b�g�̎擾
			camera.takePicture(null, null, this);
		}
		return true;
	}

	//�ʐ^�B�e���ɌĂ΂��
	@Override
	public void onPictureTaken(byte[] data, Camera camera) {
		
		//SD�J�[�h�ւ̃f�[�^�ۑ�
		try
		{
			String path = Environment.getExternalStorageDirectory()+"/test.jpg";
			data2file(data,path );
		}		
		catch( Exception e )
		{			
		}
		
		//�v���r���[�̍ĊJ
		camera.startPreview();
	}
	
	//�o�C�g�f�[�^���t�@�C��
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