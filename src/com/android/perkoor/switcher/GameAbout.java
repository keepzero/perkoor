package com.android.perkoor.switcher;

import com.android.perkoor.R;
import com.wiyun.engine.nodes.Button;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Scene;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.transitions.RightTopTilesShrinkOutTransition;
import com.wiyun.engine.types.WYRect;
import com.wiyun.engine.types.WYSize;
import com.wiyun.engine.utils.ResolutionIndependent;
import com.wiyun.engine.utils.TargetSelector;

public class GameAbout extends Scene {
	Scene gameHomepage;
	Sprite gameAbout;
	
	Button backButton;
	public GameAbout(Scene gameHomepage) {
		this.gameHomepage = gameHomepage;
		WYSize size = Director.getInstance().getWindowSize();
		gameAbout = Sprite.make(R.drawable.about_background);
		float scaleX = size.width / gameAbout.getWidth();
		float scaleY = size.height / gameAbout.getHeight();
		gameAbout.setScale(scaleX, scaleY);
		gameAbout.setPosition(size.width / 2, size.height / 2);
		
		Texture2D back_normal = Texture2D.makePNG(R.drawable.back_normal);
		Texture2D back_selected = Texture2D.makePNG(R.drawable.back_selected);
				
		Sprite backNormal = Sprite.make(back_normal,
				ResolutionIndependent.resolve(WYRect.make(2, 0, 60, 56)));
		Sprite backSelected = Sprite.make(back_selected,
				ResolutionIndependent.resolve(WYRect.make(0, -2, 60, 56)));
		backButton = Button.make(backNormal, backSelected, null, null,
				new TargetSelector(this, "onBackButtonClicked", null));
		backButton.setPosition((size.width / 6), (size.height / 6));
		
		addChild(gameAbout);
		addChild(backButton);
	}
	
	public boolean onBackButtonClicked() {
		Director.getInstance().replaceScene(RightTopTilesShrinkOutTransition.make(1, gameHomepage));
		return true;

	}
	
	 @Override
	protected boolean onBackButton() {
		Director.getInstance().replaceScene(RightTopTilesShrinkOutTransition.make(1, gameHomepage));
		return true;
	 }
}
