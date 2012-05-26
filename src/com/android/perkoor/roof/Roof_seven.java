package com.android.perkoor.roof;

import com.wiyun.engine.box2d.Box2D;
import com.wiyun.engine.box2d.collision.EdgeShape;
import com.wiyun.engine.box2d.dynamics.Body;
import com.wiyun.engine.box2d.dynamics.BodyDef;
import com.wiyun.engine.box2d.dynamics.FixtureDef;
import com.wiyun.engine.box2d.dynamics.World;

public class Roof_seven {

	protected World mWorld;
	
	protected Box2D mBox2d;
	
	private float imgX;
	private float imgY;
	
	public Roof_seven(World world,Box2D box2d){
		
		mWorld = world;
		mBox2d = box2d;
	}
	
	public void border (float x,float y){
		BodyDef oneBodyDef = BodyDef.make();
		oneBodyDef.setPosition(x, y);
		Body oneBody = mWorld.createBody(oneBodyDef);
		oneBody.setType(Body.TYPE_DYNAMIC);
		oneBodyDef.destroy();
		
		EdgeShape oneEdgeShape = EdgeShape.make();
		FixtureDef fixDef = FixtureDef.make();
		fixDef.setShape(oneEdgeShape);
	}
	
	public void roof_img(){
		
		
		
		
	}

	public void setImgX(float imgX) {
		this.imgX = imgX;
	}

	public void setImgY(float imgY) {
		this.imgY = imgY;
	}
	
	
}
