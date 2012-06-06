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
import com.wiyun.engine.transitions.LeftPushInTransition;
import com.wiyun.engine.types.WYRect;
import com.wiyun.engine.types.WYSize;
import com.wiyun.engine.utils.ResolutionIndependent;
import com.wiyun.engine.utils.TargetSelector;

public class GameDiffButton extends Layer implements INodeVirtualMethods {
	Button easyButton;
	Button normalButton;
	Button hardButton;
	Button backButton;
	
	public GameDiffButton() {
		WYSize size = Director.getInstance().getWindowSize();
		float scaleX = size.width / 10;
		float scaleY = size.height / 10;
		
		Texture2D easy_normal = Texture2D.makePNG(R.drawable.easy_normal);
		Texture2D easy_selected = Texture2D.makePNG(R.drawable.easy_selected);
		
		Texture2D normal_normal = Texture2D.makePNG(R.drawable.normal_normal);
		Texture2D normal_selected = Texture2D.makePNG(R.drawable.normal_selected);
		
		Texture2D hard_normal = Texture2D.makePNG(R.drawable.hard_normal);
		Texture2D hard_selected = Texture2D.makePNG(R.drawable.hard_selected);
		
		Texture2D back_normal = Texture2D.makePNG(R.drawable.back_normal);
		Texture2D back_selected = Texture2D.makePNG(R.drawable.back_selected);

		Sprite easyNormal = Sprite.make(easy_normal,
				ResolutionIndependent.resolve(WYRect.make(2, 0, 260, 60)));
		Sprite easySelected = Sprite.make(easy_selected,
				ResolutionIndependent.resolve(WYRect.make(0, -2, 260, 60)));
		
		easyButton = Button.make(easyNormal, easySelected, null, null,
				new TargetSelector(this, "onEasyButtonClicked", null));
		easyButton.setPosition((size.width / 2) + scaleX * 2, (size.height / 2) + (scaleY * 2));
		
		Sprite normalNormal = Sprite.make(normal_normal,
				ResolutionIndependent.resolve(WYRect.make(2, 0, 260, 60)));
		Sprite normalSelected = Sprite.make(normal_selected,
				ResolutionIndependent.resolve(WYRect.make(0, -2, 260, 60)));
		normalButton = Button.make(normalNormal, normalSelected, null, null,
				new TargetSelector(this, "onNormalButtonClicked", null));
		normalButton.setPosition((size.width / 2) + scaleX * 2, (size.height / 2));
		
		Sprite hardNormal = Sprite.make(hard_normal,
				ResolutionIndependent.resolve(WYRect.make(2, 0, 260, 60)));
		Sprite hardSelected = Sprite.make(hard_selected,
				ResolutionIndependent.resolve(WYRect.make(0, -2, 260, 60)));
		hardButton = Button.make(hardNormal, hardSelected, null, null,
				new TargetSelector(this, "onHardButtonClicked", null));
		hardButton.setPosition((size.width / 2) + scaleX * 2, (size.height / 2) - (scaleY * 2));
		
		Sprite backNormal = Sprite.make(back_normal,
				ResolutionIndependent.resolve(WYRect.make(4, 0, 70, 65)));
		Sprite backSelected = Sprite.make(back_selected,
				ResolutionIndependent.resolve(WYRect.make(0, -4, 70, 65)));
		backButton = Button.make(backNormal, backSelected, null, null,
				new TargetSelector(this, "onBackButtonClicked", null));
		backButton.setPosition((size.width / 6), (size.height / 6));
		
		addChild(easyButton);
		addChild(normalButton);
		addChild(hardButton);
		addChild(backButton);
	}
	
	public void onEasyButtonClicked() {
		Scene perkoorScene;
		perkoorScene = Scene.make();
		//mScene.addChild(new BackgroudLayer());
		perkoorScene.addChild(new BGLayer());
		perkoorScene.addChild(new CloudLayer());
		//mScene.addChild(new road_Layer());
		perkoorScene.addChild(new CharacterLayer());
		perkoorScene.addChild(new GamePlayingButton());
		perkoorScene.autoRelease(true);
		Director.getInstance().pushScene(perkoorScene);
	}
	
	public void onNormalButtonClicked() {
		
	}
	
	public void onHardButtonClicked() {
	
	}

	public void onBackButtonClicked() {
		Scene backHomeScene = Scene.make();
		backHomeScene.addChild(new GameHomepage());
		backHomeScene.addChild(new GameHomeButton());
		Director.getInstance().pushScene(LeftPushInTransition.make((float) 0.5, backHomeScene));
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
