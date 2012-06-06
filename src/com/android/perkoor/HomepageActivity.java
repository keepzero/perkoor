package com.android.perkoor;

import com.android.perkoor.switcher.GameHomeButton;
import com.android.perkoor.switcher.GameHomepage;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Scene;

public class HomepageActivity extends WiEngineTestActivity {
	protected void createScene() {
		mScene = Scene.make();
		mScene.addChild(new GameHomepage());
		mScene.addChild(new GameHomeButton());
		mScene.autoRelease(true);
		Director.getInstance().runWithScene(mScene);
	}
}
