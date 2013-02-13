package com.johnwrinkle.areyouworking1;

import android.app.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import android.widget.TextView;

public class MainActivity extends Activity {
	// private Button mYButton;
	// private Button mNButton;
	private TextView mMainText;
	// goal timer is their award, this is in seconds
	//doing them 10 and 5 seconds just to test it out, eventually will be 15*60 for the cool down and 
	//60 for punishment
	
	//private int goalCoolDownTimer = 10;
	//private int punishmentTimer = 5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// mYButton = (Button)findViewById(R.id.yes);
		// mNButton = (Button)findViewById(R.id.no);
		mMainText = (TextView) findViewById(R.id.mainText);

		boolean working = false;
		while (working == false) {
			// Make an alarm go off
			// Notification
			mMainText.setText("Are you working?");

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/** Called when the user clicks the Yes button */
	public void sendYes(View view) {

		mMainText.setText("Great job!");
		/*
		 * here we will set working to to true then the sure will get a goal
		 * cool down timer which is basically an extended break from being
		 * bothered again
		 * 
		 * for some reason the wait does to work i will have to figure out
		 * another solutionwait2(goalCoolDownTimer);
		 * mMainText.setText("Text has reset");
		 */

	}

	/** Called when the user clicks the No button */
	public void sendNo(View view) {

		mMainText.setText("No answer");
		/*
		 * here we will set working to true even though they are not working it
		 * will stop the while loop? not sure on this after that they will get
		 * the punishmentTimer which will be set on a minute by default probably
		 * a guilt scalding working will then be set to false
		 */
	}

	//this is a wait method i pulled off the Internet, i changed it so it worked in seconds instead of milliseconds 
	public static void wait2(int n) {
		long t0, t1;
		n = n * 1000;
		t0 = System.currentTimeMillis();
		do {
			t1 = System.currentTimeMillis();
		} while (t1 - t0 < n);
	}
}
