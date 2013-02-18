package com.johnwrinkle.areyouworking1;

import android.app.Activity;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Button mYButton;
	private Button mNButton;
	private TextView mMainText;
	// goal timer is their award, this is in seconds
	// doing them 10 and 5 seconds just to test it out, eventually will be 15*60
	// for the cool down and
	// 60 for punishment

	// private int goalCoolDownTimer = 10;
	// private int punishmentTimer = 5;
	//
	// can get rid of this line when i figure out alarm
	Uri notification = RingtoneManager
			.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
	boolean working = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mMainText = (TextView) findViewById(R.id.mainText);
		mYButton = (Button) findViewById(R.id.yes);
		mNButton = (Button) findViewById(R.id.no);

		mYButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				working = true;
				final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(),
						notification);
				//this message goes away right away and changes to seconds remaining
				//i could ignore this problem and just do Great job: seconds remain
				//or i could figure out how to make it wait ~5 seconds then go onto the next message...
				// something to do with ontick idk not gonna mess with it now
				mMainText.setText("Great job!");
				new CountDownTimer(10000, 1000) {

					public void onTick(long millisUntilFinished) {
						mMainText.setText("seconds remaining: " + millisUntilFinished
								/ 1000);
					}

					public void onFinish() {
						mMainText.setText("done!");
						r.play();
						//this is super sloppy i feel like there is a better way with a while loop?
						mMainText.setText("Are you working?");

					}
				}.start();
				//right now this does nothing!
				//it wont loop
				//maybe make the loop into some kind of class?
				//yea a class i can call onto to start
				
				//working = false;
				
				
			}
		});

		mNButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				working = true;
				final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(),
						notification);
				mMainText.setText("You really should be working right now!");
				new CountDownTimer(5000, 1000) {

					public void onTick(long millisUntilFinished) {
						mMainText.setText("seconds remaining: " + millisUntilFinished
								/ 1000);
					}

					public void onFinish() {
						mMainText.setText("done!");
						r.play();
						//this is super sloppy i feel like there is a better way with a while loop?
						mMainText.setText("Are you working?");

					}
				}.start();
				//working = false;
				
				
			}
		});

		
		/*
		while (working = false) {
			// do stuff
			mMainText.setText("Are you working right now?");
			

		}
		
		*/
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}




}
