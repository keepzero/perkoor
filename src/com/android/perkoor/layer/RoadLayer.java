package com.android.perkoor.layer;

import com.android.perkoor.R;
import com.android.perkoor.R.drawable;
import com.wiyun.engine.box2d.Box2DRender;
import com.wiyun.engine.box2d.collision.PolygonShape;
import com.wiyun.engine.box2d.dynamics.Body;
import com.wiyun.engine.box2d.dynamics.BodyDef;
import com.wiyun.engine.box2d.dynamics.Fixture;
import com.wiyun.engine.box2d.dynamics.FixtureDef;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.types.WYPoint;
import com.wiyun.engine.types.WYSize;
import com.wiyun.engine.utils.TargetSelector;


public class RoadLayer extends Box2DLayer {
	Fixture f;
	WYSize s;
	public RoadLayer(){
		Box2DRender render = Box2DRender.make();
		mBox2D.setBox2DRender(render);
		setTouchEnabled(false);
		mBox2D.setDebugDraw(false);
		s = Director.getInstance().getWindowSize();

		BodyDef road_df = BodyDef.make();
		road_df.setPosition(s.width,5);
		Body road = mWorld.createBody(road_df);
		road.setType(Body.TYPE_KINEMATIC);
		road.setLinearVelocity(WYPoint.make(-3f, 0));
		road_df.destroy();
		
		PolygonShape shape = PolygonShape.make(); 
		shape.setAsBox(1f, 1f);
		FixtureDef fd = FixtureDef.make();
		fd.setShape(shape);
		f = road.createFixture(fd);
		fd.destroy();
		
		Texture2D text = Texture2D.makePNG(R.drawable.icon);
//		Sprite sprite = Sprite.make(text);
//	
//		Sprite sprite1 = Sprite.make(text);
//		sprite.setPosition(0, 0);
//		sprite1.setPosition(10f, 10f);
//		addChild(sprite1);
//		addChild(sprite);
		
		render.bindTexture(f, text);
		
		
		
		schedule(new TargetSelector(this, "update(float)", new Object[] { 0f }));
	}
	public void update(float delta){
		super.update(delta);
		
		//f.getBody().setTransform(CharacterLayer.carPos.x, 10f, 0f);

	}
}
