package com.android.perkoor.switcher;

import com.android.perkoor.R;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.INodeVirtualMethods;
import com.wiyun.engine.nodes.Layer;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.types.WYSize;

public class GameOver extends Layer implements INodeVirtualMethods {
	Sprite mBackground;
	public GameOver() {
		mBackground = Sprite.make(R.drawable.gameover_background);
		WYSize size = Director.getInstance().getWindowSize();
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
