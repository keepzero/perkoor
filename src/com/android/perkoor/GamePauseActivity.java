package com.android.perkoor;

import com.android.perkoor.layer.BGLayer;
import com.android.perkoor.layer.CharacterLayer;
import com.android.perkoor.layer.CloudLayer;
import com.android.perkoor.switcher.GameHomepage;
import com.android.perkoor.switcher.GamePause;
import com.android.perkoor.switcher.GamePlaying;
import com.wiyun.engine.nodes.Button;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Scene;
import com.wiyun.engine.nodes.Sprite;

public class GamePauseActivity extends WiEngineTestActivity {
	Scene mScene;
	Scene perkoorScene;
	
	Sprite mPauseBackground;
	Sprite mAlphaBackground;
	
	Button restartButton;
	Button resumeButton;
	Button homeButton;
	protected void createScene() {
		mScene  = Scene.make();
		mScene.addChild(new GamePause());
		mScene.setKeyEnabled(true);
		mScene.autoRelease(true);
		Director.getInstance().runWithScene(mScene);				
	}
	
	public void onRestartButtonClicked() {
		perkoorScene = Scene.make();
		perkoorScene.addChild(new BGLayer());
		perkoorScene.addChild(new CloudLayer());
		perkoorScene.addChild(new CharacterLayer());
		perkoorScene.addChild(new GamePlaying());
		perkoorScene.autoRelease(true);
		Director.getInstance().replaceScene(perkoorScene); 
	}
	
	//恢复游戏
	public void onResumeButtonClicked() {		
		this.setVisible(false);
	}
	
	//返回主界面
	public void onHomeButtonClicked() {
		mScene = Scene.make();
		mScene.addChild(new GameHomepage());
		Director.getInstance().replaceScene(mScene);
	}	
	
}	
