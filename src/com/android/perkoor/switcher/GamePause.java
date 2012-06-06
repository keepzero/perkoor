package com.android.perkoor.switcher;

import com.android.perkoor.R;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.INodeVirtualMethods;
import com.wiyun.engine.nodes.Layer;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.types.WYSize;

public class GamePause extends Layer implements INodeVirtualMethods {
	Sprite mPauseBackground;
	Sprite mAlphaBackground;
	public GamePause() {
		WYSize size = Director.getInstance().getWindowSize();
		
		mPauseBackground = Sprite.make(R.drawable.pause_background);				
		mPauseBackground.setPosition(size.width / 2, size.height / 2);
		
		mAlphaBackground = Sprite.make(R.drawable.white);
		mAlphaBackground.setAlpha(128);
		mAlphaBackground.setScale(size.width, size.height);
		addChild(mAlphaBackground);
		addChild(mPauseBackground);
		
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
