package com.android.perkoor.switcher;

import com.android.perkoor.R;
import com.wiyun.engine.nodes.Button;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.INodeVirtualMethods;
import com.wiyun.engine.nodes.Layer;
import com.wiyun.engine.nodes.Scene;

import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.transitions.RightPushInTransition;

import com.wiyun.engine.types.WYRect;
import com.wiyun.engine.types.WYSize;
import com.wiyun.engine.utils.ResolutionIndependent;
import com.wiyun.engine.utils.TargetSelector;

public class GameHomeButton extends Layer implements INodeVirtualMethods {
	Button playButton;
	Button aboutButton;
	Button musicButton;
	Button musicDisableButton;
	Button soundButton;
	Button soundDisableButton;
	Button helpButton;
	public GameHomeButton() {
		WYSize size = Director.getInstance().getWindowSize();
		float scaleX = size.width / 10;
		float scaleY = size.height / 10;
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
				ResolutionIndependent.resolve(WYRect.make(2, 0, 166, 54)));
		Sprite playSelected = Sprite.make(play_selected,
				ResolutionIndependent.resolve(WYRect.make(0, -2, 166, 54)));
		
		playButton = Button.make(playNormal, playSelected,null, null,
				new TargetSelector(this, "onPlayButtonClicked",null));
		playButton.setPosition((size.width /4) - (scaleX / 3), (size.height / 2) - (scaleY / 2) );
		
		//关于按钮
		Sprite aboutNormal = Sprite.make(about_normal,
				ResolutionIndependent.resolve(WYRect.make(2, 0, 175, 59)));
		Sprite aboutSelected = Sprite.make(about_selected,
				ResolutionIndependent.resolve(WYRect.make(0, -2, 175, 59)));
		
		aboutButton = Button.make(aboutNormal, aboutSelected, null, null);
		aboutButton.setPosition((size.width /4) - (scaleX / 3), (size.height / 5) - (scaleY / 5));

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
		
		musicButton.setPosition((size.width / 2), (size.height /5) - (scaleY / 2));
		musicDisableButton.setPosition((size.width / 2), (size.height /5) - (scaleY / 2));
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
		
		soundButton.setPosition((size.width / 2) + (scaleX + scaleY), (size.height / 5) - (scaleY / 2));
		soundDisableButton.setPosition((size.width / 2) + (scaleX + scaleY), (size.height / 5) - (scaleY / 2));
		soundDisableButton.setVisible(false);

		//帮助按钮
		Sprite HelpNormal = Sprite.make(help_normal,
				ResolutionIndependent.resolve(WYRect.make(2, 0, 60, 38)));
		Sprite HelpSelected = Sprite.make(help_selected,
				ResolutionIndependent.resolve(WYRect.make(0, -2, 60, 38)));
		
		helpButton = Button.make(HelpNormal, HelpSelected, null, null,
				new TargetSelector(this, "onHelpClicked", null));
		helpButton.setPosition((size.width / 2) + ((scaleX + scaleY) * 2), (size.height / 5) - (scaleY / 2));
		
		addChild(playButton);
		addChild(aboutButton);
		addChild(musicButton);
		addChild(musicDisableButton);
		addChild(soundButton);
		addChild(soundDisableButton);
		addChild(helpButton);

		
	}
	
	public void onPlayButtonClicked() {
		Scene playScene = Scene.make();
		playScene.addChild(new GameDifficulty());
		playScene.addChild(new GameDiffButton());
		Director.getInstance().pushScene(RightPushInTransition.make(1, playScene));
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