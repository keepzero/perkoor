package com.android.perkoor.roof;

import com.android.perkoor.R;
import com.wiyun.engine.box2d.Box2D;
import com.wiyun.engine.box2d.PELoader;
import com.wiyun.engine.box2d.dynamics.Body;
import com.wiyun.engine.box2d.dynamics.World;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.types.WYPoint;
import com.wiyun.engine.types.WYSize;

public class Roof1 extends Roof{

	protected Sprite sprite;
	
	protected Body bodyRoof;
	
	public Roof1(World world,Box2D box2d, float x){
		super(world, box2d);
		
		bodyRoof = mLoader.createBodyByName(mBox2d, "roof_1");
		bodyRoof.setType(Body.TYPE_KINEMATIC);
		bodyRoof.setTransform(mBox2d.pixel2Meter(x), mBox2d.pixel2Meter(0f), 0);

		Sprite sprite = Sprite.make(R.drawable.roof_1);
		sprite.autoRelease();
		
		WYPoint anchor = mLoader.getAnchorPercent("roof_1");
		sprite.setAnchor(anchor.x,anchor.y);
		sprite.setPosition(x, 0f);
		bodyRoof.setUserData(sprite);
		mBox2d.addChild(sprite);
	}
}
