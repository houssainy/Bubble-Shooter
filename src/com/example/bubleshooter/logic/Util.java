package com.example.bubleshooter.logic;

import com.example.bubleshooter.R;

public class Util {

	public static int getBall(int ballNumber) {
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
}
