package com.example.surview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class SurfaceViewView extends SurfaceView
	implements Callback, Runnable
{
	
	private SurfaceHolder holder;
	private Thread thread;
	private int pos_x = 0;
	private Paint paint = new Paint();

	public SurfaceViewView(Context context) {
		super(context);
		holder = getHolder();
		holder.addCallback(this);
	}

	@Override
	public void run() {
		paint.setTextSize(30);
		while(thread != null){
			Canvas canvas = holder.lockCanvas();
			if(canvas == null){
				break;
			}
			canvas.drawColor(Color.WHITE);
			canvas.drawText("Shiori", pos_x, 100, paint);
			holder.unlockCanvasAndPost(canvas);
			
			pos_x -= 20;
			if(pos_x < 0){
				pos_x = getWidth();
			}
			try {
				Thread.sleep(100);
			} catch (Exception e) {
			}
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		thread = null;
	}

}
