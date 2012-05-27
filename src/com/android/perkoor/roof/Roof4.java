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

public class Roof4 {

protected World mWorld;
	
	protected Box2D mBox2d;
	
	protected Sprite sprite;
	
	protected Body bodyroof_4;
	WYSize s;
	public Roof4(World world,Box2D box2d){
		
		mWorld = world;
		mBox2d = box2d;
		s = Director.getInstance().getWindowSize();
		
	}
	
	public void set_border (float x){
		PELoader mLoader = PELoader.make(R.raw.shapedefs_roof);
		mBox2d.setMeterPixels(mLoader.getMeterPixels());
		bodyroof_4 = mLoader.createBodyByName(mBox2d, "roof_4");
		bodyroof_4.setType(Body.TYPE_KINEMATIC);

		bodyroof_4.setTransform(mBox2d.pixel2Meter(x), mBox2d.pixel2Meter(0f), 0);

		Sprite sprite = Sprite.make(R.drawable.roof_4);
		sprite.autoRelease();
		
		WYPoint anchor = mLoader.getAnchorPercent("roof_4");
		sprite.setAnchor(anchor.x,anchor.y);
		sprite.setPosition(x, 0f);
		bodyroof_4.setUserData(sprite);
		mBox2d.addChild(sprite);
	}
	
	public void updata_img( ){
		
		Sprite sprite = (Sprite) bodyroof_4.getUserData();
			sprite.setPosition(mBox2d.meter2Pixel(bodyroof_4.getPosition().x),
					mBox2d.meter2Pixel(bodyroof_4.getPosition().y));
			System.out.println(sprite.getPositionX());
		}
}
