package com.android.perkoor.layer;


import android.view.MotionEvent; 

import com.android.perkoor.R;
import com.android.perkoor.R.drawable;
import com.wiyun.engine.box2d.Box2DRender; 
import com.wiyun.engine.box2d.FixtureAnimation;
import com.wiyun.engine.box2d.collision.EdgeShape;
import com.wiyun.engine.box2d.collision.PolygonShape;
import com.wiyun.engine.box2d.dynamics.Body;
import com.wiyun.engine.box2d.dynamics.BodyDef;
import com.wiyun.engine.box2d.dynamics.Fixture;
import com.wiyun.engine.box2d.dynamics.FixtureDef;
import com.wiyun.engine.nodes.Director;

import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.types.WYPoint;
import com.wiyun.engine.types.WYSize;

import com.wiyun.engine.utils.TargetSelector;

public  class CharacterLayer extends Box2DLayer {

	Body body;
	Fixture f;
	WYSize s;
	float Y_sta=0,Y_end=0;
	float X_sta=0,X_end=0;
	static float hight =10;
	protected static WYPoint carPos=WYPoint.make(0, 0);
	public CharacterLayer(){
		s = Director.getInstance().getWindowSize();
		mWorld.setGravity(0, -10);
		mBox2D.setDebugDraw(false);
		mBox2D.setPosition(s.width / 2, 0);
		Box2DRender render = Box2DRender.make();
		mBox2D.setBox2DRender(render);
		setTouchEnabled(true);


		{//road
			BodyDef bd_road = BodyDef.make();
			bd_road.setPosition(0, 0);
			Body b_road = mWorld.createBody(bd_road);
			bd_road.destroy();
			
			EdgeShape es = EdgeShape.make();
			
			FixtureDef fixDef = FixtureDef.make();
			fixDef.setShape(es);
		

			
			es.setEndpoints(-20f, 1.5f, mBox2D.pixel2Meter(s.width),1.5f);
			b_road.createFixture(fixDef);
			fixDef.destroy();
			
			
		}
		
		
		
		{//picture
			BodyDef bd = BodyDef.make();
			bd.setPosition(0f, 3f);
			bd.setType(Body.TYPE_DYNAMIC);
			body = mWorld.createBody(bd); 
			bd.destroy();
			
			
			PolygonShape box1 = PolygonShape.make();
			box1.setAsBox(0.5f, 0.5f);
			FixtureDef fd = FixtureDef.make();
			fd.setDensity(1.0f);
			fd.setShape(box1);
			f = body.createFixture(fd);
			fd.destroy();
			FixtureAnimation anim = FixtureAnimation.make(0.2f,R.drawable.moving1,R.drawable.moving2,R.drawable.moving3);
			anim.setLoop(true);
			anim.start(f);
			
//			Texture2D text = Texture2D.makePNG(R.drawable.icon);
//			render.bindTexture(f, text);
		}
		
		

		schedule(new TargetSelector(this, "update(float)", new Object[] { 0f }));
	}
	public void update(float delta) {
		super.update(delta);
		
		// move the scene, keep the car center
		//WYSize s1 = Director.getInstance().getWindowSize();
		carPos = body.getPosition();
		
		//float pX = mBox2D.meter2Pixel(carPos.x);
		//mBox2D.setPosition(pX + s1.width / 2, ResolutionIndependent.resolveDp(100));

	    //f.getBody().setLinearVelocity( WYPoint.make(1f,0));
//		f_road.getBody().setTransform(i, 10f, 0f);
//		i++;
//		mBox2D.setPosition(-pX + s1.width / 2, 0);
	}
	@Override
	public boolean wyTouchesBegan(MotionEvent event) {
		// TODO Auto-generated method stub		
		WYPoint loc = Director.getInstance().convertToGL(event.getX(), event.getY());
		X_sta=loc.x;
		Y_sta = loc.y;
		return true;
	}
	@Override
	public boolean wyTouchesEnded(MotionEvent event) {
		// TODO Auto-generated method stub
		WYPoint loc = Director.getInstance().convertToGL(event.getX(), event.getY());
		X_end=loc.x;
		Y_end = loc.y;
		float tital_x=0, tital_y=0;
		tital_y = Y_end-Y_sta;
		tital_x =X_end-X_sta;
		System.out.println("jump");
		if(tital_x>0 && tital_y>0){
			System.out.println("jump");
			jump();
		}
		if(tital_x>0 && tital_y<0){
			squat();
		}
		return true;
	}
	public void jump(){
		if(body.getLinearVelocity().y==0){
			WYPoint WH = WYPoint.make(0f, hight);
			body.setLinearVelocity(WH);
		}
		
		//body.setLinearVelocity(WYPoint.make(0, 0));
	}
	public void squat(){
		
	}

}
