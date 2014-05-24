package com.example.bubleshooter;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;

public class MainActivity extends Activity implements OnClickListener {

	private Button chooseLevel, option, exit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.chose_lvl_button:

			break;
		case R.id.option_button:

			break;
		case R.id.exit_button: // Exit the game
			finish();
			break;
		}

	}

}
