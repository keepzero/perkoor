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

	Body body;    // 申明人物刚体
	Fixture f;    //声明刚体附加属性
	WYSize s;     //声明屏幕尺寸
	float Y_sta=0,Y_end=0;   //初始与终止 y 轴   坐标 触摸点声明
	float X_sta=0,X_end=0;   //初始与终止x 轴   坐标 触摸点声明
	static float hight =10;  //跳跃高度声明
	public CharacterLayer(){
		s = Director.getInstance().getWindowSize();//获取屏幕尺寸
		mWorld.setGravity(0, -10);//设置世界的重力加速度
		mBox2D.setDebugDraw(false);//设置刚体贴图模式，表示可以进行贴图
		mBox2D.setPosition(s.width / 2, 0);//初始位置
		Box2DRender render = Box2DRender.make();//获取绑定render，用于贴图与刚体的绑定
		mBox2D.setBox2DRender(render);//mBox2D设置绑定
		setTouchEnabled(true);//设置是否触摸


		{//road
			BodyDef bd_road = BodyDef.make();//刚体属性定义
			bd_road.setPosition(0, 0);//位置
			Body b_road = mWorld.createBody(bd_road);//创建刚体，赋予BodyDef属性
			bd_road.destroy();//摧毁BodyDef，每次创建完必须摧毁
			
			EdgeShape es = EdgeShape.make();//形状声明
			
			FixtureDef fixDef = FixtureDef.make();//声明关联属性，可进行对刚体属性添加
			fixDef.setShape(es);//FixtureDef设置形状属性
		

			
			es.setEndpoints(-20f, 1.5f, mBox2D.pixel2Meter(s.width),1.5f);//位置，
			b_road.createFixture(fixDef);//创建Fixture
			fixDef.destroy();
			
			
		}
		
		
		
		{//picture
			BodyDef bd = BodyDef.make();
			bd.setPosition(0f, 3f);
			bd.setType(Body.TYPE_DYNAMIC);//刚体类型，必须设置类型才能有相应的质量等
			body = mWorld.createBody(bd); 
			bd.destroy();
			
			
			PolygonShape box1 = PolygonShape.make();
			box1.setAsBox(0.5f, 0.5f);//设置形状
			FixtureDef fd = FixtureDef.make();
			fd.setDensity(1.0f);
			fd.setShape(box1);
			f = body.createFixture(fd);
			fd.destroy();
			FixtureAnimation anim = FixtureAnimation.make(0.2f,R.drawable.moving1,R.drawable.moving2,R.drawable.moving3);//动画帧等版定和设置
			
			anim.setLoop(true);//设置是否循环显示
			anim.start(f);//启动动画显示
			
//			Texture2D text = Texture2D.makePNG(R.drawable.icon);
//			render.bindTexture(f, text);
		}
		
		

		schedule(new TargetSelector(this, "update(float)", new Object[] { 0f }));
	}
	public void update(float delta) {
		super.update(delta);
		
		// move the scene, keep the car center
		//WYSize s1 = Director.getInstance().getWindowSize();
		
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
		tital_y = Y_end-Y_sta;     //y轴距离差
		tital_x =X_end-X_sta;      //x轴距离差
		
		
		if(tital_x>0 && tital_y>0){  //判断是否起跳
			System.out.println("jump");
			jump();
		}
		if(tital_x>0 && tital_y<0){  //判断下蹲
			squat();
		}
		return true;
	}
	public void jump(){  //起跳
		if(body.getLinearVelocity().y==0){  //防止连跳，判断y的速度是否为0
			WYPoint WH = WYPoint.make(0f, hight);
			body.setLinearVelocity(WH);   //设置速度
		}
		
		//body.setLinearVelocity(WYPoint.make(0, 0));
	}
	public void squat(){ //下蹲
		
	}
}
