package com.android.perkoor.switcher;

import com.android.perkoor.R;
import com.android.perkoor.layer.BGLayer;
import com.android.perkoor.layer.CharacterLayer;
import com.android.perkoor.layer.CloudLayer;
import com.wiyun.engine.nodes.Button;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.INodeVirtualMethods;
import com.wiyun.engine.nodes.Layer;
import com.wiyun.engine.nodes.Scene;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.types.WYRect;
import com.wiyun.engine.types.WYSize;
import com.wiyun.engine.utils.ResolutionIndependent;
import com.wiyun.engine.utils.TargetSelector;

public class GamePause extends Layer implements INodeVirtualMethods {
	Sprite mPauseBackground;
	Sprite mAlphaBackground;
	
	Button restartButton;
	Button resumeButton;
	Button homeButton;
	
	public GamePause() {
		WYSize size = Director.getInstance().getWindowSize();			
		
		mPauseBackground = Sprite.make(R.drawable.pause_background);		
		if(size.width < 800 || size.height < 480)
		{
			mPauseBackground.setScale(size.width / 800, size.height / 480);
		}
		else {
			mPauseBackground.setScale(640 / size.width, 384 / size.height);
		}
		mPauseBackground.setPosition(size.width / 2, size.height / 2);
		float zoomX = mPauseBackground.getWidth() / 10;
		float zoomY = mPauseBackground.getHeight() / 10;
		
		mAlphaBackground = Sprite.make(R.drawable.white);
		mAlphaBackground.setAlpha(128);
		//mAlphaBackground.setScale(size.width, size.height);
		Texture2D restart_normal = Texture2D.makePNG(R.drawable.restart_normal);
		Texture2D restart_selected = Texture2D.makePNG(R.drawable.restart_selected);
		
		Texture2D continue_normal = Texture2D.makePNG(R.drawable.continue_normal);
		Texture2D continue_selected = Texture2D.makePNG(R.drawable.continue_selected);
		
		Texture2D home_normal = Texture2D.makePNG(R.drawable.home_normal);
		Texture2D home_selected = Texture2D.makePNG(R.drawable.home_selected);
		
		Sprite restartNormal = Sprite.make(restart_normal,
				ResolutionIndependent.resolve(WYRect.make(2, 0, 70, 65)));
		Sprite restartSelected = Sprite.make(restart_selected,
				ResolutionIndependent.resolve(WYRect.make(0, -2, 70, 65)));
		restartButton = Button.make(restartNormal, restartSelected, null, null,
				new TargetSelector(this, "onRestartButtonClicked", null));
		restartButton.setPosition((size.width / 2) - (zoomX + zoomY), (size.height / 2) - (zoomY * 3 / 2));
		
		Sprite resumeNormal = Sprite.make(continue_normal,
				ResolutionIndependent.resolve(WYRect.make(2, 0, 70, 65)));
		Sprite resumeSelected = Sprite.make(continue_selected,
				ResolutionIndependent.resolve(WYRect.make(0, -2, 70, 65)));
		resumeButton = Button.make(resumeNormal, resumeSelected, null, null,
				new TargetSelector(this, "onResumeButtonClicked", null));
		resumeButton.setPosition((size.width / 2), (size.height / 2) - (zoomY * 3 / 2));
		
		Sprite homeNormal = Sprite.make(home_normal,
				ResolutionIndependent.resolve(WYRect.make(2, 0, 70, 65)));
		Sprite homeSelected = Sprite.make(home_selected,
				ResolutionIndependent.resolve(WYRect.make(0, -2, 70, 65)));
		homeButton = Button.make(homeNormal, homeSelected, null, null,
				new TargetSelector(this, "onHomeButtonClicked", null));
		homeButton.setPosition((size.width / 2 ) + (zoomX + zoomY), (size.height / 2) - (zoomY * 3 / 2));		
		
		addChild(mAlphaBackground);
		addChild(mPauseBackground);
		addChild(restartButton);
		addChild(resumeButton);
		addChild(homeButton);
		
	}

	public void onRestartButtonClicked() {
		Scene perkoorScene;
		perkoorScene = Scene.make();
		perkoorScene.addChild(new BGLayer());
		perkoorScene.addChild(new CloudLayer());
		perkoorScene.addChild(new CharacterLayer());
		perkoorScene.addChild(new GamePlayingButton());
		perkoorScene.autoRelease(true);
		Director.getInstance().pushScene(perkoorScene); 
	}
	
	//恢复游戏
	public void onResumeButtonClicked() {		
		
	}
	
	//返回主界面
	public void onHomeButtonClicked() {
		Scene homeScene = Scene.make();
		homeScene = Scene.make();
		homeScene.addChild(new GameHomepage());
		homeScene.addChild(new GameHomeButton());
		Director.getInstance().pushScene(homeScene);
	}
	
	@Override
	public void jDraw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jOnEnter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jOnEnterTransitionDidFinish() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jOnExit() {
		// TODO Auto-generated method stub
		
	}
}
