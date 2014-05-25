package com.example.bubleshooter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LevelChooserActivity extends Activity implements OnClickListener {
	
	private Button levelOne;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.acitvity_level_chooser);
		
		levelOne = (Button)findViewById(R.id.level_one);
		levelOne.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		Intent intent = null;
		switch (v.getId()) {
		case R.id.level_one:
			intent = new Intent(
					"com.example.bubleshooter.BUBBLESHOOTERACTIVITY");
			intent.putExtra("fileName", "levels//levelOne.txt");

			break;
		}
		startActivity(intent);
	}
}
