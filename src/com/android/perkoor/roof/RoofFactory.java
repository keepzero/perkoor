package com.android.perkoor.roof;

import com.wiyun.engine.box2d.Box2D;
import com.wiyun.engine.box2d.dynamics.World;

public class RoofFactory {

	/*
	 * protected static World mWorld; protected static Box2D mBox2d;
	 * 
	 * public RoofFactory(World world,Box2D box2d){ mWorld = world; mBox2d =
	 * box2d; }
	 */

	public static Roof createRoof(int i, World w, Box2D b, float x) {

		switch (i) {

		case 1:
			return new Roof1(w, b, x);
		case 2:
			return new Roof2(w, b, x);
		case 3:
			return new Roof3(w, b, x);
		case 4:
			return new Roof4(w, b, x);
		case 5:
			return new Roof5(w, b, x);
		case 6:
			return new Roof6(w, b, x);
		case 7:
			return new Roof7(w, b, x);

		}
		return new Roof1(w, b, x);

	}

}
