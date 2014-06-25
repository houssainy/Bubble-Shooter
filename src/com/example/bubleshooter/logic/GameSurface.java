package com.example.bubleshooter.logic;

import graph_package.Graph;
import graph_package.Node;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {

	private Graph graph;
	private Node[][] chart;

	private int xRatio = 0;
	private int yRatio = 0;

	private int startX, startY;

	private SurfaceHolder holder;
	private Canvas canvas;

	private Activity activity;

	public GameSurface(Activity activity, Graph graph, Node[][] chart) {
		super(activity);

		this.activity = activity;

		this.graph = graph;
		this.chart = chart;

		startX = chart[0].length / 2;
		startY = chart.length - 1;

		holder = getHolder();
		holder.addCallback(this);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// Clear
		canvas.drawColor(0, android.graphics.PorterDuff.Mode.CLEAR);

		super.onDraw(canvas);
		drawChart(canvas);
	}

	/**
	 * Draw the chart of balls
	 * 
	 * @param canvas
	 */
	private void drawChart(Canvas canvas) {
		for (int i = 0; i < chart.length; i++) {
			for (int j = 0; j < chart[i].length; j++) {
				if (chart[i][j] != null) {
					Bitmap bitmap = BitmapFactory.decodeResource(
							this.getResources(), chart[i][j].getColour());

					canvas.drawBitmap(bitmap, j * xRatio - bitmap.getWidth()
							/ 2, i * yRatio - bitmap.getHeight() / 2, null);
				}
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX() / xRatio;
		int y = (int) event.getY() / yRatio;

		new MoveTask().execute(x, y);

		return super.onTouchEvent(event);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder1) {

		canvas = holder.lockCanvas();
		xRatio = canvas.getWidth() / chart[0].length;
		yRatio = canvas.getHeight() / chart.length;
		onDraw(canvas);
		holder.unlockCanvasAndPost(canvas);

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

	/**
	 * Connect the given node to its adjacents
	 * 
	 * @param newNode
	 */
	private void connectNewNode(Node newNode) {
		int xPos = newNode.getxPos();
		int yPos = newNode.getyPos();

		if (xPos - 1 >= 0 && chart[yPos][xPos - 1] != null)// Left
			chart[yPos][xPos - 1].addAdjacent(newNode);

		if (xPos + 1 < chart[0].length && chart[yPos][xPos + 1] != null)// Right
			chart[yPos][xPos + 1].addAdjacent(newNode);

		if (yPos - 1 >= 0 && chart[yPos - 1][xPos] != null)// Up
			chart[yPos - 1][xPos].addAdjacent(newNode);

		if (yPos - 1 >= 0 && xPos - 1 >= 0 && chart[yPos - 1][xPos - 1] != null)// Up
																				// left
			chart[yPos - 1][xPos - 1].addAdjacent(newNode);

		if (yPos - 1 >= 0 && xPos + 1 < chart[0].length
				&& chart[yPos - 1][xPos + 1] != null)// Up Right
			chart[yPos - 1][xPos + 1].addAdjacent(newNode);
	}

	/**
	 * Check if the game is ended( win or loose )
	 */
	public void checkGame() {

	}

	/**
	 * Show win message, and show the option to continue to next level or replay
	 */
	public void showWinMessage() {

	}

	/**
	 * Show loose message and show option to replay
	 */
	public void showLooseMessage() {
		AlertDialog alert = new AlertDialog.Builder(getContext()).create();
		alert.setCancelable(false);

		alert.setMessage("You Loose!");
		alert.setButton(AlertDialog.BUTTON_POSITIVE, "Replay",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

					}

				});
		alert.setButton(AlertDialog.BUTTON_NEGATIVE, "Exit",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						activity.finish();
					}

				});

		alert.show();

	}

	/**
	 * 
	 * @author Mohamed
	 * 
	 *         AsyncTask to handle movement in Canvas, and check the state of
	 *         the game after execution
	 */
	private class MoveTask extends AsyncTask<Integer, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Integer... params) {
			int touchX = params[0];
			int touchY = params[1];

			return moveUp();

		}

		/**
		 * Move the ball up in straight line
		 * 
		 * @return game updated?
		 */
		private Boolean moveUp() {
			boolean loose = true;

			int x = startX;
			int y = startY;

			Node newNode = new Node(x, y, Util.getBall(1));

			while (y >= 0 && chart[y][x] == null) {
				if (y + 1 < chart.length)
					chart[y + 1][x] = null;

				chart[y][x] = newNode;

				canvas = holder.lockCanvas();
				onDraw(canvas);
				postInvalidate();
				holder.unlockCanvasAndPost(canvas);

				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				newNode.setyPos(--y);

				loose = false;
			}

			if (loose)// No Update
				return false;

			connectNewNode(newNode);
			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);

			if (result) {// The game is still maybe continue
				checkGame();
			} else {// Game ended
				showLooseMessage();
			}
		}

	}
}
