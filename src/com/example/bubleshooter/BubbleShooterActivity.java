package com.example.bubleshooter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import graph_package.Graph;
import graph_package.Node;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
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

		((RelativeLayout) findViewById(R.id.game_surface))
				.addView(getGameSurface(getIntent().getStringExtra("fileName")));
	}

	private GameSurface getGameSurface(String fileName) {
		Graph graph = null;
		Node[][] chart = null;
		try {

			AssetManager am = this.getAssets();
			Scanner in = new Scanner(am.open(fileName));
			int rows = in.nextInt();
			int cols = in.nextInt();

			chart = new Node[10][cols];

			Node startNode = new Node();
			graph = new Graph(startNode);

			// Build Graph and Node[][]
			int temp;
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					if ((temp = in.nextInt()) != 0) {
						Node newNode = new Node(j, i, getBall(temp));

						if (i - 1 >= 0) { // Connect to previous rows
							if (j - 1 >= 0 && chart[i - 1][j - 1] != null) {
								// left corner
								graph.addAdjacentNode(chart[i - 1][j - 1],
										newNode);
							}

							if (j + 1 < cols && chart[i - 1][j + 1] != null) {
								// right corner
								graph.addAdjacentNode(chart[i - 1][j + 1],
										newNode);
							}
						}

						if (j - 2 >= 0 && chart[i][j - 2] != null) {
							// Left
							graph.addAdjacentNode(chart[i][j - 2], newNode);
						}

						chart[i][j] = newNode;
					}
				}
			}

			// Connect start node to the first row
			for (int i = 0; i < chart[0].length; i++)
				if (chart[1][i] != null)
					graph.addAdjacentNode(startNode, chart[1][i]);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new GameSurface(this, graph, chart);
	}

	private int getBall(int ballNumber) {
		switch (ballNumber) {
		case 1:
			return R.drawable.ball_1;
		case 2:
			return R.drawable.ball_2;
		case 3:
			return R.drawable.ball_3;
		}

		return 0;
	}

	private class GameSurface extends SurfaceView implements
			SurfaceHolder.Callback {

		private Graph graph;
		private Node[][] chart;

		private int xRatio = 0;
		private int yRatio = 0;

		private SurfaceHolder holder;

		public GameSurface(Context context, Graph graph, Node[][] chart) {
			super(context);

			this.graph = graph;
			this.chart = chart;

			holder = getHolder();
			holder.addCallback(this);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			for (int i = 0; i < chart.length; i++) {
				for (int j = 0; j < chart[i].length; j++) {
					if (chart[i][j] != null) {
						Bitmap bitmap = BitmapFactory.decodeResource(
								this.getResources(), chart[i][j].getColour());

						canvas.drawBitmap(bitmap,
								j * xRatio - bitmap.getWidth() / 2, i * yRatio
										- bitmap.getHeight() / 2, null);

					}
				}
			}
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

		@SuppressLint("WrongCall")
		@Override
		public void surfaceCreated(SurfaceHolder holders) {
			// TODO Auto-generated method stub

			Canvas canvas = holder.lockCanvas();
			xRatio = canvas.getWidth() / chart[0].length;
			yRatio = canvas.getHeight() / chart.length;

			onDraw(canvas);
			holder.unlockCanvasAndPost(canvas);

		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			// TODO Auto-generated method stub

		}
	}
}
