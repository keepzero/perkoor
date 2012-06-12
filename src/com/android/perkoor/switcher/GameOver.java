package com.android.perkoor.switcher;

import com.android.perkoor.R;
import com.wiyun.engine.nodes.Button;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.INodeVirtualMethods;
import com.wiyun.engine.nodes.Label;
import com.wiyun.engine.nodes.Layer;
import com.wiyun.engine.nodes.Scene;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.types.WYRect;
import com.wiyun.engine.types.WYSize;
import com.wiyun.engine.utils.ResolutionIndependent;
import com.wiyun.engine.utils.TargetSelector;

public class GameOver extends Layer {
	Scene gameHomepage;
	Scene perkoorScene;
	
	Sprite mGameoverground;	
	
	Button restartButton;
	Button homeButton;
	Label highscoreLabel;
	Label totalscoreLabel;
	
	public GameOver(Scene gameHomepage) {
		this.gameHomepage = gameHomepage;
		WYSize size = Director.getInstance().getWindowSize();			
		
		mGameoverground = Sprite.make(R.drawable.gameover_background);	
		float adaptX = (size.width / mGameoverground.getWidth()) / 2;
		float adaptY = (size.height / mGameoverground.getHeight()) / 2;
		
		mGameoverground.setScale(adaptX, adaptY);
		mGameoverground.setPosition(size.width / 2, size.height / 2);
		float zoomX = mGameoverground.getWidth() / 10;
		float zoomY = mGameoverground.getHeight() / 10;
		
		Texture2D restart_normal = Texture2D.makePNG(R.drawable.restart_normal);
		Texture2D restart_selected = Texture2D.makePNG(R.drawable.restart_selected);
		
		Texture2D home_normal = Texture2D.makePNG(R.drawable.home_normal);
		Texture2D home_selected = Texture2D.makePNG(R.drawable.home_selected);
		
		Sprite restartNormal = Sprite.make(restart_normal,
				ResolutionIndependent.resolve(WYRect.make(2, 0, 60, 56)));
		Sprite restartSelected = Sprite.make(restart_selected,
				ResolutionIndependent.resolve(WYRect.make(0, -2, 60, 56)));
		restartButton = Button.make(restartNormal, restartSelected, null, null,
				new TargetSelector(this, "onRestartButtonClicked", null));
		restartButton.setPosition((size.width / 2) - (zoomX + zoomY), (size.height / 2) - (zoomY * 2));
		
		Sprite homeNormal = Sprite.make(home_normal,
				ResolutionIndependent.resolve(WYRect.make(2, 0, 60, 56)));
		Sprite homeSelected = Sprite.make(home_selected,
				ResolutionIndependent.resolve(WYRect.make(0, -2, 60, 56)));
		homeButton = Button.make(homeNormal, homeSelected, null, null,
				new TargetSelector(this, "onHomeButtonClicked", null));
		homeButton.setPosition((size.width / 2 ) + (zoomX + zoomY), (size.height / 2) - (zoomY * 2));
		
		highscoreLabel = Label.make("High Score：", 18, "Comic Sans MS.tff");
		highscoreLabel.setPosition((size.width / 2) - (zoomY * 2), (size.height) / 2 - (zoomY / 3));
		
		totalscoreLabel = Label.make("Total Score：", 18, "Comic Sans MS.tff");
		totalscoreLabel.setPosition((size.width / 2) - (zoomY * 2), (size.height) / 2 + (zoomY / 2));
		
		addChild(mGameoverground);
		addChild(highscoreLabel);
		addChild(totalscoreLabel);
		addChild(restartButton);
		addChild(homeButton);		
	}
	
	public void onRestartButtonClicked() {
		perkoorScene = new GamePlaying(gameHomepage);
		perkoorScene.autoRelease(true);
		Director director = Director.getInstance();
		director.resumeUI();
		Director.getInstance().replaceScene(perkoorScene);
	}
	
	public void onHomeButtonClicked() {
		Director director = Director.getInstance();
		director.resumeUI();
		Director.getInstance().replaceScene(gameHomepage);
	}
	
	protected boolean onBackButton() {
		Director director = Director.getInstance();
		director.resumeUI();
		Director.getInstance().replaceScene(gameHomepage);
		return true;
	}
}
