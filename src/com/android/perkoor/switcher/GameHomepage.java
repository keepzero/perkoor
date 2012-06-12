package com.android.perkoor.switcher;


import com.android.perkoor.R;

import com.wiyun.engine.nodes.Button;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Scene;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.sound.AudioManager;
import com.wiyun.engine.transitions.ColorFadeTransition;
import com.wiyun.engine.transitions.LeftBottomTilesShrinkOutTransition;
import com.wiyun.engine.transitions.ZoomFlipAngularTransition;
import com.wiyun.engine.types.WYColor3B;
import com.wiyun.engine.types.WYRect;
import com.wiyun.engine.types.WYSize;
import com.wiyun.engine.utils.ResolutionIndependent;
import com.wiyun.engine.utils.TargetSelector;

public class GameHomepage extends Scene {	
	GameDifficulty gameDifficulty;
	GameHelp gameHelp;
	GameAbout gameAbout;
	
	Sprite mBackground;
	Button playButton;
	Button aboutButton;
	Button musicButton;
	Button musicDisableButton;
	Button soundButton;
	Button soundDisableButton;
	Button helpButton;

	
	public GameHomepage() {				
		WYSize size = Director.getInstance().getWindowSize();
		
		float adaptX = size.width / 10;
		float adaptY = size.height / 10;

		
		mBackground = Sprite.make(R.drawable.home_background);
		float scaleX = size.width / mBackground.getWidth();
		float scaleY = size.height / mBackground.getHeight();
		mBackground.setScale(scaleX, scaleY);
		mBackground.setPosition(size.width / 2, size.height / 2);
		
		
		//Texture2D 声明
		Texture2D play_normal = Texture2D.makePNG(R.drawable.play_normal);
		Texture2D play_selected = Texture2D.makePNG(R.drawable.play_selected);
		Texture2D about_normal = Texture2D.makePNG(R.drawable.about_normal);
		Texture2D about_selected = Texture2D.makePNG(R.drawable.about_selected);
		
		Texture2D music_normal = Texture2D.makePNG(R.drawable.music_normal);
		Texture2D music_selected = Texture2D.makePNG(R.drawable.music_selected);
		Texture2D music_disable_normal = Texture2D.makePNG(R.drawable.music_disable_normal);
		Texture2D music_disable_selected = Texture2D.makePNG(R.drawable.music_disable_selected);
		
		Texture2D sound_normal = Texture2D.makePNG(R.drawable.sound_normal);
		Texture2D sound_selected = Texture2D.makePNG(R.drawable.sound_selected);
		Texture2D sound_disable_normal = Texture2D.makePNG(R.drawable.sound_disable_normal);
		Texture2D sound_disable_selected = Texture2D.makePNG(R.drawable.sound_disable_selected);
		
		Texture2D help_normal = Texture2D.makePNG(R.drawable.help_normal);
		Texture2D help_selected = Texture2D.makePNG(R.drawable.help_selected);
		
		//PLAY按钮
		Sprite playNormal = Sprite.make(play_normal,
				ResolutionIndependent.resolve(WYRect.make(2, 0, 180, 60)));
		Sprite playSelected = Sprite.make(play_selected,
				ResolutionIndependent.resolve(WYRect.make(0, -2, 180, 60)));
		
		playButton = Button.make(playNormal, playSelected,null, null,
				new TargetSelector(this, "onPlayButtonClicked",null));
		playButton.setPosition((size.width / 2) - (adaptX * 2), (size.height / 2) - (adaptY) );
		
		//关于按钮
		Sprite aboutNormal = Sprite.make(about_normal,
				ResolutionIndependent.resolve(WYRect.make(2, 0, 180, 60)));
		Sprite aboutSelected = Sprite.make(about_selected,
				ResolutionIndependent.resolve(WYRect.make(0, -2, 180, 60)));
		
		aboutButton = Button.make(aboutNormal, aboutSelected, null, null,
				new TargetSelector(this, "onAboutButtonClicked",null));
		aboutButton.setPosition((size.width / 2) + (adaptX * 2), (size.height / 2) - (adaptY));

		//音乐按钮
		Sprite MusicNormal = Sprite.make(music_normal,
				ResolutionIndependent.resolve(WYRect.make(2, 0, 60, 37)));
		Sprite MusicSelected = Sprite.make(music_selected,
				ResolutionIndependent.resolve(WYRect.make(0, -2, 60, 37)));
		Sprite MusicDisableNormal = Sprite.make(music_disable_normal,
				ResolutionIndependent.resolve(WYRect.make(2, 0, 60, 37)));
		Sprite MusicDisableSelected = Sprite.make(music_disable_selected,
				ResolutionIndependent.resolve(WYRect.make(0, -2, 60, 37)));
		
		musicButton = Button.make(MusicNormal, MusicSelected, null, null,
				new TargetSelector(this, "onMusicClicked", null));
		musicDisableButton = Button.make(MusicDisableNormal, MusicDisableSelected, null, null,
				new TargetSelector(this, "onMusicDisableClicked", null));
		
		musicButton.setPosition((size.width / 2), (size.height /5) - (adaptY / 2));
		musicDisableButton.setPosition((size.width / 2), (size.height /5) - (adaptY / 2));
		musicDisableButton.setVisible(false);
		
		//音效按钮
		Sprite SoundNormal = Sprite.make(sound_normal,
				ResolutionIndependent.resolve(WYRect.make(2, 0, 60, 36)));
		Sprite SoundSelected = Sprite.make(sound_selected,
				ResolutionIndependent.resolve(WYRect.make(0, -2, 60, 36)));
		Sprite SoundDisableNormal = Sprite.make(sound_disable_normal,
				ResolutionIndependent.resolve(WYRect.make(2, 0, 60, 36)));
		Sprite SoundDisableSelected = Sprite.make(sound_disable_selected,
				ResolutionIndependent.resolve(WYRect.make(0, -2, 60, 36)));
		
		soundButton = Button.make(SoundNormal, SoundSelected, null, null,
				new TargetSelector(this, "onSoundClicked", null));
		soundDisableButton = Button.make(SoundDisableNormal, SoundDisableSelected, null, null,
				new TargetSelector(this, "onSoundDisableClicked", null));
		
		soundButton.setPosition((size.width / 2) + (adaptX + adaptY), (size.height / 5) - (adaptY / 2));
		soundDisableButton.setPosition((size.width / 2) + (adaptX + adaptY), (size.height / 5) - (adaptY / 2));	
		soundDisableButton.setVisible(false);

		//帮助按钮
		Sprite HelpNormal = Sprite.make(help_normal,
				ResolutionIndependent.resolve(WYRect.make(2, 0, 60, 38)));
		Sprite HelpSelected = Sprite.make(help_selected,
				ResolutionIndependent.resolve(WYRect.make(0, -2, 60, 38)));
		
		helpButton = Button.make(HelpNormal, HelpSelected, null, null,
				new TargetSelector(this, "onHelpClicked", null));
		helpButton.setPosition((size.width / 2) + ((adaptX + adaptY) * 2), (size.height / 5) - (adaptY / 2));
						
		addChild(mBackground);
		
		addChild(playButton);
		addChild(aboutButton);
		addChild(musicButton);
		addChild(musicDisableButton);
		addChild(soundButton);
		addChild(soundDisableButton);
		addChild(helpButton);	
		AudioManager.preloadEffect(R.raw.gameplaying, AudioManager.FORMAT_OGG);
		onPlay();		
	}		
	
	public void onMusicClicked() {		
		musicButton.setVisible(false);
		musicDisableButton.setVisible(true);
		onStop();
	}
	
	public void onMusicDisableClicked() {
		musicDisableButton.setVisible(false);
		musicButton.setVisible(true);
		onPlay();
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
		gameHelp = new GameHelp(GameHomepage.this);
		gameHelp.autoRelease(true);
		Director.getInstance().replaceScene(ZoomFlipAngularTransition.make(1f, gameHelp, true, 0.5f));
	}
	
	//按Play跳转到难度选择
	public void onPlayButtonClicked() {			
		gameDifficulty = new GameDifficulty(GameHomepage.this);
		gameDifficulty.autoRelease(true);
		Director.getInstance().replaceScene(ColorFadeTransition.make(1f, gameDifficulty, new WYColor3B(0, 0, 0)));
		//Director.getInstance().replaceScene(LeftBottomTilesShrinkOutTransition.make(1, gameDifficulty));
	}
	
	public void onAboutButtonClicked() {
		gameAbout = new GameAbout(GameHomepage.this);
		gameAbout.autoRelease(true);
		Director.getInstance().replaceScene(ColorFadeTransition.make(1f, gameAbout, new WYColor3B(0, 0, 0)));
	}
	
	public void onPlay() {
		AudioManager.playBackgroundMusic(R.raw.gameplaying, AudioManager.FORMAT_OGG, -1);
	}

	public void onStop() {
		AudioManager.stopBackgroundMusic();
	}
}
