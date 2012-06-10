package com.android.perkoor.barrier;

import com.wiyun.engine.box2d.Box2D;
import com.wiyun.engine.box2d.dynamics.World;
import com.wiyun.engine.types.WYPoint;

public class BarrierFactory {

	public static Barrier createBarrier(int i, World w, Box2D b, WYPoint x) {

		switch (i) {

		case 1:
			return new Barrier1(w, b, x);

		case 2:
			return new Barrier2(w, b, x);
		case 3:
			return new Barrier3(w, b, x);
		case 4:
			return new Barrier4(w, b, x);
		case 5:
			return new Barrier5(w, b, x);		
		}
		return new Barrier1(w, b, x);

	}

}
