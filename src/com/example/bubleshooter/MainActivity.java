package com.example.bubleshooter;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity implements OnClickListener {

	private Button chooseLevel, option, exit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();
	}

	/**
	 * Initialise view and listeners
	 */
	private void init() {
		chooseLevel = (Button) findViewById(R.id.chose_lvl_button);
		chooseLevel.setOnClickListener(this);

		option = (Button) findViewById(R.id.option_button);
		option.setOnClickListener(this);

		exit = (Button) findViewById(R.id.exit_button);
		exit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.chose_lvl_button:
			startActivity(new Intent(
					"com.example.bubleshooter.LEVELCHOOSERACTIVITY"));
			break;
		case R.id.option_button:

			break;
		case R.id.exit_button: // Exit the game
			finish();
			break;
		}

	}

}
