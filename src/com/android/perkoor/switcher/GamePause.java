package com.android.perkoor.switcher;

import android.R.layout;

import com.android.perkoor.R;
import com.wiyun.engine.nodes.Button;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Layer;
import com.wiyun.engine.nodes.Scene;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.sound.AudioManager;
import com.wiyun.engine.types.WYRect;
import com.wiyun.engine.types.WYSize;
import com.wiyun.engine.utils.ResolutionIndependent;
import com.wiyun.engine.utils.TargetSelector;

public class GamePause extends Layer {
	Scene gameHomepage;	
	Scene perkoorScene;
	
	Sprite mPauseBackground;
	Sprite mAlphaBackground;
	
	Button restartButton;
	Button resumeButton;
	Button homeButton;
	
	public boolean isMusicPlay = GameHomepage.isMusicPlay;
	public boolean isSoundPlay = GameHomepage.isSoundPlay;	
	
	public GamePause(Scene gameHomepage) {
		this.gameHomepage = gameHomepage;
		WYSize size = Director.getInstance().getWindowSize();
	
		mPauseBackground = Sprite.make(R.drawable.pause_background);
		
		float adaptX = (size.width / mPauseBackground.getWidth()) / 2;
		float adaptY = (size.height / mPauseBackground.getHeight()) / 2;
		float pauseX = mPauseBackground.getWidth() / 10;
		float pauseY = mPauseBackground.getHeight() / 10;
		
		mPauseBackground.setScale(adaptX, adaptY);
		mPauseBackground.setPosition(size.width / 2, size.height / 2);
		
		mAlphaBackground = Sprite.make(R.drawable.white);
		mAlphaBackground.setAlpha(128);


		mAlphaBackground.setScale(size.width * 2, size.height * 2);
		
		Texture2D restart_normal = Texture2D.makePNG(R.drawable.restart_normal);
		Texture2D restart_selected = Texture2D.makePNG(R.drawable.restart_selected);
		
		Texture2D continue_normal = Texture2D.makePNG(R.drawable.continue_normal);
		Texture2D continue_selected = Texture2D.makePNG(R.drawable.continue_selected);
		
		Texture2D home_normal = Texture2D.makePNG(R.drawable.home_normal);
		Texture2D home_selected = Texture2D.makePNG(R.drawable.home_selected);
		
		Sprite restartNormal = Sprite.make(restart_normal,
				ResolutionIndependent.resolve(WYRect.make(2, 0, 60, 56)));
		Sprite restartSelected = Sprite.make(restart_selected,
				ResolutionIndependent.resolve(WYRect.make(0, -2, 60, 56)));
		restartButton = Button.make(restartNormal, restartSelected, null, null,
				new TargetSelector(this, "onRestartButtonClicked", null));
		restartButton.setPosition((size.width / 2) - (pauseX * 2), (size.height / 2) - (pauseY * 2));
		
		Sprite resumeNormal = Sprite.make(continue_normal,
				ResolutionIndependent.resolve(WYRect.make(2, 0, 60, 56)));
		Sprite resumeSelected = Sprite.make(continue_selected,
				ResolutionIndependent.resolve(WYRect.make(0, -2, 60, 56)));
		resumeButton = Button.make(resumeNormal, resumeSelected, null, null,
				new TargetSelector(this, "onResumeButtonClicked", null));
		resumeButton.setPosition((size.width / 2), (size.height / 2) - (pauseY * 2));
		
		Sprite homeNormal = Sprite.make(home_normal,
				ResolutionIndependent.resolve(WYRect.make(2, 0, 60, 56)));
		Sprite homeSelected = Sprite.make(home_selected,
				ResolutionIndependent.resolve(WYRect.make(0, -2, 60, 56)));
		homeButton = Button.make(homeNormal, homeSelected, null, null,
				new TargetSelector(this, "onHomeButtonClicked", null));
		homeButton.setPosition((size.width / 2 ) + (pauseX * 2), (size.height / 2) - (pauseY * 2));		
		

		addChild(mAlphaBackground);
		addChild(mPauseBackground);
		addChild(restartButton);
		addChild(resumeButton);
		addChild(homeButton);		
	}
	
	public void onRestartButtonClicked() {
		perkoorScene = new GamePlaying(gameHomepage);
		perkoorScene.autoRelease(true);
		Director director = Director.getInstance();
		director.resumeUI();
		Director.getInstance().replaceScene(perkoorScene);
		if(isMusicPlay == true)
		{
			onStop();
			onPlay_Restart();
		}
		else if(isMusicPlay == false){
			onStop();
		}
	}
	
	public void onResumeButtonClicked() {	
		Director director = Director.getInstance();
		director.resumeUI();
		this.setVisible(false);		
	}
	
	//返回主界面
	public void onHomeButtonClicked() {
		Director director = Director.getInstance();
		director.resumeUI();
		Director.getInstance().replaceScene(gameHomepage);
		if(isMusicPlay == true)
		{
			onStop();
			onPlay_Home();
		}
		else if(isMusicPlay == false){
			onStop();
		}
	}
	
	public void onPlay_Home() {
		AudioManager.playBackgroundMusic(R.raw.gameloading, AudioManager.FORMAT_OGG, -1);
	}
	
	public void onPlay_Restart() {
		AudioManager.playBackgroundMusic(R.raw.gameplaying, AudioManager.FORMAT_OGG, -1);
	}

	public void onStop() {
		AudioManager.stopBackgroundMusic();
	}
	
}
