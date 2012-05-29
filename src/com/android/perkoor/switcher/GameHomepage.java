package com.android.perkoor.switcher;

import com.android.perkoor.R;
import com.wiyun.engine.afcanim.MWSprite;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Layer;
import com.wiyun.engine.nodes.Scene;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.particle.ParticleSystem;
import com.wiyun.engine.types.WYSize;

public class GameHomepage extends Layer {
	public Scene mScene;
	WYSize s;
	MWSprite m_sSprite1;
	
	GameHomepage() {
		s = Director.getInstance().getWindowSize();
		mScene = Scene.make();
		
	}
	
	public void GHbc() {
		Texture2D GHbc = Texture2D.makePNG(R.drawable.home_background);
		Sprite GHbc2 = Sprite.make(GHbc);		
		GHbc.autoRelease();
		GHbc2.autoRelease();
		GHbc2.setPosition(GHbc2.getWidth() / 2, GHbc2.getHeight() / 2);
		mScene.addChild(GHbc2);
	}
	
	
}
