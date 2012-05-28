package com.android.perkoor.roof;

import com.android.perkoor.R;
import com.wiyun.engine.box2d.Box2D;
import com.wiyun.engine.box2d.PELoader;
import com.wiyun.engine.box2d.dynamics.Body;
import com.wiyun.engine.box2d.dynamics.World;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.types.WYSize;

public abstract class Roof {

	protected World mWorld;
	protected Box2D mBox2d;
	WYSize s; 
	PELoader mLoader;

	protected Sprite sprite;
	protected Body bodyRoof;

	public Roof(World world, Box2D box2d) {
		this.mWorld = world;
		this.mBox2d = box2d;
		s = Director.getInstance().getWindowSize();
		mLoader = PELoader.make(R.raw.shapedefs_roof);
		mBox2d.setMeterPixels(mLoader.getMeterPixels());
	}
}
