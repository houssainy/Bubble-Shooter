package com.example.bubleshooter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {

	private SurfaceHolder holder;

	public GameSurface(Context context, AttributeSet attrs) {
		super(context, attrs);

		holder = getHolder();
		holder.addCallback(this);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//TODO
		return super.onTouchEvent(event);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	int coutn = 0;

	@Override
	public void surfaceCreated(SurfaceHolder holders) {
		// TODO Auto-generated method stub
		
		Canvas canvas = holder.lockCanvas();

		canvas.drawBitmap(
				BitmapFactory.decodeResource(getResources(), R.drawable.ball_1),
				100, 100 + coutn * 10, null);
		holder.unlockCanvasAndPost(canvas);

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}
}