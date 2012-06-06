package com.android.perkoor;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;

import com.android.perkoor.switcher.GameHomepage;
import com.wiyun.engine.nodes.Button;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Scene;
import com.wiyun.engine.nodes.Sprite;


public class HomepageActivity extends WiEngineTestActivity {
	Scene difficultyScene;
	Sprite mBackground;
	
	Button playButton;
	Button aboutButton;
	Button musicButton;
	Button musicDisableButton;
	Button soundButton;
	Button soundDisableButton;
	Button helpButton;
	@Override
	protected void createScene() {
		mScene = new MyScene();
		mScene.addChild(new GameHomepage());
		mScene.setKeyEnabled(true);
		mScene.autoRelease(true);
		Director.getInstance().replaceScene(mScene);
	}
	
	//按Play跳转到难度选择
	public void onPlayButtonClicked() {
		Intent intent = new Intent(HomepageActivity.this, GameDiffActivity.class);	
		HomepageActivity.this.startActivity(intent);
		System.out.println("Play Clicked!");
		
	}
	
	public void onMusicClicked() {
		musicButton.setVisible(false);
		musicDisableButton.setVisible(true);
	}
	
	public void onMusicDisableClicked() {
		musicDisableButton.setVisible(false);
		musicButton.setVisible(true);
	}
	
	public void onSoundClicked() {
		soundButton.setVisible(false);
		soundDisableButton.setVisible(true);
	}
	
	public void onSoundDisableClicked() {
        soundDisableButton.setVisible(false);
        soundButton.setVisible(true);
    }
	
	public void onHelpClicked() {
		
	}
	
	public void showExitConfirm() {
		runOnUiThread(new Runnable() {
			public void run() {
				AlertDialog.Builder builder = new Builder(HomepageActivity.this);
				builder.setMessage("Do you really want to exit current demo?")
				.setNegativeButton("No", null)
				.setPositiveButton("Yes", new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				}).show();
			}
		});
	}
	
	
	
	class MyScene extends Scene {
			public MyScene() {
			setKeyEnabled(true);	       
	        autoRelease(true);
		}
        @Override
    	protected boolean onBackButton() {
    		showExitConfirm();
    		return true;
    	}
        
	}
	
}
