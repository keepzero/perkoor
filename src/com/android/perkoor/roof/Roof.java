package com.android.perkoor.roof;

import java.util.Random;

import com.android.perkoor.R;
import com.wiyun.engine.box2d.Box2D;
import com.wiyun.engine.box2d.PELoader;
import com.wiyun.engine.box2d.dynamics.Body;
import com.wiyun.engine.box2d.dynamics.World;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.types.WYSize;

/**
 * @author      Muou        <dragonmuou@gmail.com>
 * @version     0.1         (the version of the package this class was first added to)                   
 * @since       2012-5-29   (a date or the version number of this program)
 */
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
	
	/**
	 * 随机数
	 *
	 * 随机数，用于随机选择房屋
	 *
	 * @param i  随机数范围，从 1 到 i
	 * @return   返回从 1 到参数的随机数
	 */
	public static int getRandom(int i){
		Random r = new Random();
		if(i < 1)
			return 1;
		return r.nextInt(i) + 1;
	}
}
