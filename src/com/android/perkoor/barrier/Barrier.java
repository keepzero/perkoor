package com.android.perkoor.barrier;

import java.util.Random;

import com.android.perkoor.R;
import com.wiyun.engine.box2d.Box2D;
import com.wiyun.engine.box2d.PELoader;
import com.wiyun.engine.box2d.dynamics.Body;
import com.wiyun.engine.box2d.dynamics.World;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.types.WYSize;

public abstract class Barrier {
	protected World mWorld;
	protected Box2D mBox2d;
	WYSize s; 
	PELoader mLoader;

	protected Sprite sprite;
	public static Body bodyBarrier;
	
	public Barrier(World world, Box2D box2d){
		this.mWorld = world;
		this.mBox2d = box2d;
		s = Director.getInstance().getWindowSize();
		mLoader = PELoader.make(R.raw.shapedefs_barrier);
		mBox2d.setMeterPixels(mLoader.getMeterPixels());
	}
	
	public static int getRandom(int i){
		Random r = new Random();
		if(i < 1)
			return 1;
		return r.nextInt(i) + 1;
	}

}
