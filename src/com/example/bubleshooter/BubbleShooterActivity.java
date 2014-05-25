package com.example.bubleshooter;

import graph_package.Graph;
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.RelativeLayout;

public class BubbleShooterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_bubble_shooter);

		GameSurface gameSurface = new GameSurface(this,
				getGraphFromFile(getIntent().getStringExtra("fileName")));

		((RelativeLayout) findViewById(R.id.game_surface)).addView(gameSurface);
	}

	private Graph getGraphFromFile(String stringExtra) {
		// TODO Auto-generated method stub
		return null;
	}

	private class GameSurface extends SurfaceView implements
			SurfaceHolder.Callback {

		private Graph graph;
		private SurfaceHolder holder;

		public GameSurface(Context context, Graph graph) {
			super(context);

			this.graph = graph;

			holder = getHolder();
			holder.addCallback(this);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			// TODO
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

			canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),
					R.drawable.ball_1), 100, 100 + coutn * 10, null);
			holder.unlockCanvasAndPost(canvas);

		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			// TODO Auto-generated method stub

		}
	}
}
