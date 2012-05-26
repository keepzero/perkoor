package com.android.perkoor;

import com.android.perkoor.layer.BGLayer;
import com.android.perkoor.layer.BackgroudLayer;
import com.android.perkoor.layer.CharacterLayer;
import com.android.perkoor.layer.CloudLayer;
import com.wiyun.engine.nodes.Scene;

/*
 * # Android 命名规范                                                                      
 * - 非公有，非静态字段以 m 开头   
 * - 静态域命名以 s 开头    
 * - 其他字段以小写字母开头    
 * - public static final 字段（常量）全部大写，并用下划线连起来
 */

public class PerkoorActivity extends WiEngineTestActivity {

	protected void createScene() {
		mScene = Scene.make();
		//mScene.addChild(new BackgroudLayer());
		mScene.addChild(new BGLayer());
		mScene.addChild(new CloudLayer());
		//mScene.addChild(new road_Layer());
		mScene.addChild(new CharacterLayer());
		mScene.autoRelease(true);
	}
}