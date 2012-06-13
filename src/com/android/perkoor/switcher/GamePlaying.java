package com.android.perkoor.switcher;

import com.android.perkoor.R;
import com.android.perkoor.layer.BGLayer;
import com.android.perkoor.layer.CharacterLayer;
import com.android.perkoor.layer.CloudLayer;
import com.wiyun.engine.nodes.Button;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Label;
import com.wiyun.engine.nodes.Layer;
import com.wiyun.engine.nodes.Scene;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.sound.AudioManager;
import com.wiyun.engine.types.WYRect;
import com.wiyun.engine.types.WYSize;
import com.wiyun.engine.utils.ResolutionIndependent;
import com.wiyun.engine.utils.TargetSelector;

public class GamePlaying extends Scene {
	protected Button pauseButton;
	Label scoreLabel;
	Scene gameHomepage;
	
	Layer gamePause;
	
	public boolean isMusicPlay = GameHomepage.isMusicPlay;
	public boolean isSoundPlay = GameHomepage.isSoundPlay;
	
	public GamePlaying(Scene gameHomepage) {
		gamePause = new GamePause(gameHomepage);
		this.gameHomepage = gameHomepage;
		WYSize size = Director.getInstance().getWindowSize();
		float adaptX = size.width / 10;
		float adaptY = size.height / 10;
		
		Texture2D pause_normal = Texture2D.makePNG(R.drawable.pause_normal);
		Texture2D pause_selected = Texture2D.makePNG(R.drawable.pause_selected);
		
		Sprite pauseNormal = Sprite.make(pause_normal,
				ResolutionIndependent.resolve(WYRect.make(2, 0,	60, 56)));
		Sprite pauseSelected = Sprite.make(pause_selected,
				ResolutionIndependent.resolve(WYRect.make(0, -2, 60, 56)));
		
		pauseButton = Button.make(pauseNormal, pauseSelected, null, null,
				new TargetSelector(this, "onPauseButtonClicked", null));
		
		pauseButton.setPosition((adaptX / 4 * 3), (size.height - (adaptY / 4 * 3)));
		
		addChild(new BGLayer());
		addChild(new CloudLayer());
		addChild(new CharacterLayer(gameHomepage));
		addChild(pauseButton);
		addChild(gamePause);
		gamePause.setVisible(false);
		AudioManager.preloadEffect(R.raw.gameplaying, AudioManager.FORMAT_OGG);
		if(isMusicPlay == true)
		{
			onStop();
			onPlay();
		}
		else if(isMusicPlay == false){
			onStop();
		}
	}
	
	public void onPauseButtonClicked() {
		Director director = Director.getInstance();
		gamePause.setVisible(true);
		director.pauseUI();
		
	}
	
	@Override
	protected boolean onBackButton() {
		Director director = Director.getInstance();
		gamePause.setVisible(true);
		director.pauseUI();
		
		return true;
	}
	
	public void onPlay() {
		AudioManager.playBackgroundMusic(R.raw.gameplaying, AudioManager.FORMAT_OGG, -1);
		System.out.println("gameplaying is onPlay");
	}

	public void onStop() {
		AudioManager.stopBackgroundMusic();
	}
}
