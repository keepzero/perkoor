package com.android.perkoor.roof;

import com.android.perkoor.R; 
import com.wiyun.engine.box2d.Box2D;
import com.wiyun.engine.box2d.dynamics.Body;
import com.wiyun.engine.box2d.dynamics.FixtureDef;
import com.wiyun.engine.box2d.dynamics.World;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.types.WYPoint;

public class Roof2 extends Roof{

	public Roof2(World world,Box2D box2d, float x){
		super(world, box2d);
		
		bodyRoof = mLoader.createBodyByName(mBox2d, "roof_2");
		bodyRoof.setType(Body.TYPE_KINEMATIC);
		bodyRoof.getFirstFixture().setFriction(0.2f);
		bodyRoof.getFirstFixture().setDensity(0f);
		bodyRoof.setTransform(mBox2d.pixel2Meter(x), mBox2d.pixel2Meter(0f), 0);

		Sprite sprite = Sprite.make(R.drawable.roof_2);
		sprite.autoRelease();
		
		WYPoint anchor = mLoader.getAnchorPercent("roof_2");
		sprite.setAnchor(anchor.x,anchor.y);
		sprite.setPosition(x, 0f);
		bodyRoof.setUserData(sprite);
		mBox2d.addChild(sprite);
	}
}
