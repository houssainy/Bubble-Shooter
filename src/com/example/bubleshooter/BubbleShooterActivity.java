package com.example.bubleshooter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.example.bubleshooter.logic.GameSurface;
import com.example.bubleshooter.logic.Util;

import graph_package.Graph;
import graph_package.Node;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class BubbleShooterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_bubble_shooter);

		((RelativeLayout) findViewById(R.id.game_surface))
				.addView(getGameSurface(getIntent().getStringExtra("fileName")));
	}

	/**
	 * 
	 * @param fileName
	 * @return Game Surface which have the initial balls
	 */
	private GameSurface getGameSurface(String fileName) {
		Graph graph = null;
		Node[][] chart = null;
		try {

			AssetManager am = this.getAssets();
			Scanner in = new Scanner(am.open(fileName));
			int rows = in.nextInt();
			int cols = in.nextInt();

			chart = new Node[rows][cols];

			Node startNode = new Node();
			graph = new Graph(startNode);

			// Build Graph and Node[][]
			int temp;
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					if ((temp = in.nextInt()) != 0) {
						Node newNode = new Node(j, i, Util.getBall(temp));

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

}
