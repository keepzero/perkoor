package com.android.perkoor.roof;

import com.wiyun.engine.box2d.Box2D;
import com.wiyun.engine.box2d.dynamics.World;
public class RoofFactory {
	
    protected World mWorld;
	protected Box2D mBox2d;
	
	public RoofFactory(World world,Box2D box2d){
		
		mWorld = world;
		mBox2d = box2d;
	}
	
	public Roof createRoof(int i){
		
		switch(i){
		
		case 1: 
			return new Roof1(mWorld, mBox2d);
		case 2:
			return new Roof2(mWorld, mBox2d);
		case 3:
			return new Roof3(mWorld, mBox2d);
		case 4:
			return new Roof4(mWorld, mBox2d);
		case 5:
			return new Roof5(mWorld, mBox2d);
		case 6:
			return new Roof6(mWorld, mBox2d);
		case 7:
			return new Roof7(mWorld, mBox2d);
			
		}
		return new Roof1(mWorld, mBox2d);
		
	}

}
