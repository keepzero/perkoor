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

public class GamePlayingButton extends Layer implements INodeVirtualMethods {
	Button pauseButton;
	Label scoreLabel;
	public GamePlayingButton() {
		WYSize size = Director.getInstance().getWindowSize();
		float scaleX = size.width / 10;
		float scaleY = size.height / 10;
		
		Texture2D pause_normal = Texture2D.makePNG(R.drawable.pause_normal);
		Texture2D pause_selected = Texture2D.makePNG(R.drawable.pause_selected);
		
		Sprite pauseNormal = Sprite.make(pause_normal,
				ResolutionIndependent.resolve(WYRect.make(2, 0,	70, 65)));
		Sprite pauseSelected = Sprite.make(pause_selected,
				ResolutionIndependent.resolve(WYRect.make(0, -2, 70, 65)));
		
		pauseButton = Button.make(pauseNormal, pauseSelected, null, null,
				new TargetSelector(this, "onPauseButtonClicked", null));
		
		pauseButton.setPosition(scaleX, (size.height - scaleY));
		
		addChild(pauseButton);
	}
	
	public void onPauseButtonClicked() {
		addChild(new GamePause());				
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
