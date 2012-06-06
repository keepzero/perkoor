package com.android.perkoor.switcher;

import com.android.perkoor.R;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.INodeVirtualMethods;
import com.wiyun.engine.nodes.Layer;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.types.WYSize;

public class GameDifficulty extends Layer implements INodeVirtualMethods {
	Sprite mBackground;
	public GameDifficulty() {
		mBackground = Sprite.make(R.drawable.difficulty_background);
		WYSize size = Director.getInstance().getWindowSize();
		
		//mBackground.setScale(size.width, size.height);
		mBackground.setPosition(size.width / 2, size.height / 2);
		addChild(mBackground);
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
