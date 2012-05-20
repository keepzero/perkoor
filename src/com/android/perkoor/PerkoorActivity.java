package com.android.perkoor;

import com.android.perkoor.layer.BackgroudLayer;
import com.android.perkoor.layer.CharacterLayer;
import com.wiyun.engine.nodes.Scene;

public class PerkoorActivity extends WiEngineTestActivity {

	protected void createScene() {
		mScene = Scene.make();
		mScene.addChild(new BackgroudLayer());
		//mScene.addChild(new road_Layer());
		mScene.addChild(new CharacterLayer());
		mScene.autoRelease(true);
	}
}