package com.johnwrinkle.areyouworking1;

import android.app.Activity;
import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	//private Button mYButton;
	//private Button mNButton;
	//private Button mCoolDownButton;
	private EditText mCoolDownNum;
	private TextView mMainText;

	// goal timer is their award, this is in seconds
	// doing them 10 and 5 seconds just to test it out, eventually will be 15*60
	// for the cool down and
	// 60 for punishment

	// let the user set these in the settings
	// *60 so the user can set minutes
	private int goalCoolDownTimer = 5;
	private int punishmentTimer = 5;
	//
	// can get rid of this line when i figure out alarm
	Uri notification = RingtoneManager
			.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
	boolean working = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//mYButton = (Button) findViewById(R.id.yes);
		//mNButton = (Button) findViewById(R.id.no);
		//mCoolDownButton = (Button) findViewById(R.id.coolDownButton);
		mCoolDownNum = (EditText) findViewById(R.id.coolDownNum);
		mMainText = (TextView) findViewById(R.id.mainText);
		
	}
	
	public void sendYes(View view){
		working = true;
		final Ringtone r = RingtoneManager.getRingtone(
		getApplicationContext(), notification);
		
		// this message goes away right away and changes to seconds
		// remaining
		// i could ignore this problem and just do Great job: seconds
		// remain
		// or i could figure out how to make it wait ~5 seconds then go
		// onto the next message...
		// something to do with ontick idk not gonna mess with it now

		//why is this being set to just be overwritten
		//mMainText.setText("Great job!");
	

		new CountDownTimer(goalCoolDownTimer * 1000 *60, 1000) {

			public void onTick(long millisUntilFinished) {
				mMainText.setText("seconds remaining: "
						+ millisUntilFinished / 1000);
			}

			public void onFinish() {
				mMainText.setText("done!");
				r.play();
				/*
				Alarm does not work
				Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
				i.putExtra(AlarmClock.EXTRA_HOUR, 0);
				i.putExtra(AlarmClock.EXTRA_MINUTES, goalCoolDownTimer);
				startActivity(i);
				http://stackoverflow.com/questions/4989757/how-to-set-alarm-using-alarm-clock-class
				*/
				// this is super sloppy i feel like there is a better
				// way with a while loop?
				mMainText.setText("Are you working?");

			}
		}.start();
		// right now this does nothing!
		// it wont loop
		// maybe make the loop into some kind of class?
		// yea a class i can call onto to start

		// working = false;

	}
	
	public void sendNo(View view){
		working = true;
		final Ringtone r = RingtoneManager.getRingtone(
				getApplicationContext(), notification);
		mMainText.setText("You really should be working right now!");
		new CountDownTimer(punishmentTimer * 1000, 1000) {

			public void onTick(long millisUntilFinished) {
				mMainText.setText("seconds remaining: "
						+ millisUntilFinished / 1000);
			}

			public void onFinish() {
				mMainText.setText("done!");
				r.play();
				// this is super sloppy i feel like there is a better
				// way with a while loop?
				mMainText.setText("Are you working?");

			}
		}.start();
		// working = false;

	}
	
	/**
	 * while (working = false) { // do stuff
	 * mMainText.setText("Are you working right now?");
	 * 
	 * 
	 * }
	 */
	
	public void sendCoolDown(View view){
		// First we caste mCoolDownNum(user input) to a string
		String casteCoolDownNum = mCoolDownNum.getText().toString();
		// Then we caste that result (casteCoolDownNum) into an int
		int intCoolDownNum = Integer.parseInt(casteCoolDownNum);
		// then we will make sure the number is greater than 0
		if (intCoolDownNum > 0) {
			// if the number is greater than 0 we will set it as our
			// goalCoolDownTimer
			goalCoolDownTimer = intCoolDownNum;
			// Give them a toast that it was successful
			Context context = getApplicationContext();
			CharSequence text = "Working cool down set to "
					+ intCoolDownNum + " seconds";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
			toast.show();

		}

		/*
		 * 
		 * need to figure out the proper way to say this.
		 * 
		 * 
		 * if(goalCoolDownTimer >0) { goalCoolDownTimer = value of the
		 * mcoolDownNum; }
		 */
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
