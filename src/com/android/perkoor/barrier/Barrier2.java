package com.android.perkoor.barrier;

import com.android.perkoor.R;
import com.wiyun.engine.box2d.Box2D;
import com.wiyun.engine.box2d.dynamics.Body;
import com.wiyun.engine.box2d.dynamics.World;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.types.WYPoint;

public class Barrier2 extends Barrier{
	
	public Barrier2(World world,Box2D box2d, float x){
		
		super(world, box2d);
		
		bodyBarrier = mLoader.createBodyByName(mBox2d, "barrier2");
		bodyBarrier.setType(Body.TYPE_KINEMATIC);
		
			
		bodyBarrier.setTransform(mBox2d.pixel2Meter(x), mBox2d.pixel2Meter(0f), 0);

		Sprite sprite = Sprite.make(R.drawable.barrier2);
		sprite.autoRelease();
		
		WYPoint anchor = mLoader.getAnchorPercent("barrier2");
		sprite.setAnchor(anchor.x,anchor.y);
		sprite.setPosition(x, 0f);
		bodyBarrier.setUserData(sprite);


		mBox2d.addChild(sprite,0);
	}

}
