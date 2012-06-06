package com.android.perkoor;

import android.content.Intent;

import com.android.perkoor.layer.BGLayer;
import com.android.perkoor.layer.CharacterLayer;
import com.android.perkoor.layer.CloudLayer;
import com.android.perkoor.switcher.BackButtonListener;
import com.android.perkoor.switcher.GameDifficulty;
import com.android.perkoor.switcher.GamePause;
import com.android.perkoor.switcher.GamePlaying;
import com.wiyun.engine.nodes.Button;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Scene;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.transitions.ColorFadeTransition;
import com.wiyun.engine.transitions.LeftBottomTilesShrinkOutTransition;
import com.wiyun.engine.types.WYColor3B;

public class GameDiffActivity extends WiEngineTestActivity {
	Scene mScene;
	Scene perkoorScene;
	Scene pauseScene;
	
	Sprite mBackground;
	
	Button easyButton;
	Button normalButton;
	Button hardButton;
	Button backButton;
	protected void createScene() {
		mScene  = Scene.make();
		mScene.addChild(new GameDifficulty());
		mScene.setKeyEnabled(true);
		mScene.autoRelease(true);
		Director.getInstance().replaceScene(LeftBottomTilesShrinkOutTransition.make((float) 1, mScene));				
	}
	
	public void onEasyButtonClicked() {
		perkoorScene = Scene.make();
		//perkoorScene.addChild(new BackgroudLayer());
		perkoorScene.addChild(new BGLayer());
		perkoorScene.addChild(new CloudLayer());
		//perkoorScene.addChild(new road_Layer());
		perkoorScene.addChild(new CharacterLayer());
		perkoorScene.addChild(new GamePlaying());
		perkoorScene.autoRelease(true);
		//Director.getInstance().pushScene(RightTopTilesShrinkOutTransition.make((float) 1, perkoorScene));
		Director.getInstance().replaceScene(ColorFadeTransition.make((float) 1, perkoorScene, new WYColor3B(0, 0, 0)));
	}	
	
	public void onNormalButtonClicked() {
		
	}
	
	public void onHardButtonClicked() {
	
	}
	
	
	class DifficultyBack extends BackButtonListener {
		@Override
		protected boolean onBackButton() {
			
			return true;
		}
	}
}
