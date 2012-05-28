package com.android.perkoor.layer;

import android.view.MotionEvent; 

import com.android.perkoor.R;
import com.android.perkoor.roof.*;
import com.wiyun.engine.box2d.Box2DRender;
import com.wiyun.engine.box2d.FixtureAnimation;
import com.wiyun.engine.box2d.collision.PolygonShape;
import com.wiyun.engine.box2d.dynamics.Body;
import com.wiyun.engine.box2d.dynamics.BodyDef;
import com.wiyun.engine.box2d.dynamics.Fixture;
import com.wiyun.engine.box2d.dynamics.FixtureDef;
import com.wiyun.engine.box2d.dynamics.World.IContactListener;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.types.WYPoint;
import com.wiyun.engine.types.WYSize;
import com.wiyun.engine.utils.TargetSelector;

public class CharacterLayer extends Box2DLayer implements IContactListener {

	/*
	 * # Android 命名规范 - 非公有，非静态字段以 m 开头 - 静态域命名以 s 开头 - 其他字段以小写字母开头 - public
	 * static final 字段（常量）全部大写，并用下划线连起来
	 */

	Body bodyroof;
	Body body; // 申明人物刚体
	Fixture f; // 声明刚体附加属性
	WYSize s; // 声明屏幕尺寸
	float Y_sta = 0, Y_end = 0; // 初始与终止 y 轴 坐标 触摸点声明
	float X_sta = 0, X_end = 0; // 初始与终止x 轴 坐标 触摸点声明
	static float hight = 12; // 跳跃高度声明
	static int Speed = 10;
	Roof1 roof_1;
	Roof2 roof_2;
	Roof3 roof_3;
	Roof4 roof_4;
	Roof5 roof_5;
	Roof6 roof_6;
	Roof7 roof_7;

	public CharacterLayer() {
		s = Director.getInstance().getWindowSize();// 获取屏幕尺寸
		mWorld.setGravity(0, -15);// 设置世界的重力加速度
		mBox2D.setDebugDraw(false);// 设置刚体贴图模式，表示可以进行贴图
		mBox2D.setPosition(s.width/3, 0);// 初始位置
		Box2DRender render = Box2DRender.make();// 获取绑定render，用于贴图与刚体的绑定
		mBox2D.setBox2DRender(render);// mBox2D设置绑定
		setTouchEnabled(true);// 设置是否触摸

//		{// road
//			BodyDef bd_road = BodyDef.make();// 刚体属性定义
//			bd_road.setPosition(0, 0);// 位置
//			Body b_road = mWorld.createBody(bd_road);// 创建刚体，赋予BodyDef属性
//			bd_road.destroy();// 摧毁BodyDef，每次创建完必须摧毁
//
//			EdgeShape es = EdgeShape.make();// 形状声明
//
//			FixtureDef fixDef = FixtureDef.make();// 声明关联属性，可进行对刚体属性添加
//			fixDef.setFriction(0.6f);
//			fixDef.setShape(es);// FixtureDef设置形状属性
//
//			es.setEndpoints(-20f, -1f, mBox2D.pixel2Meter(s.width*2), -1f);// 位置，
//			b_road.createFixture(fixDef);// 创建Fixture
//			fixDef.destroy();
//
//		}

		{// picture
			BodyDef bd = BodyDef.make();
			bd.setFixedRotation(true);
			bd.setPosition(0f, 10f);
			bd.setType(Body.TYPE_DYNAMIC);// 刚体类型，必须设置类型才能有相应的质量等
			body = mWorld.createBody(bd);
			body.setLinearVelocity(WYPoint.make(Speed, 0f));
			bd.destroy();

			PolygonShape box1 = PolygonShape.make();
			box1.setAsBox(0.5f, 0.5f);// 设置形状
			FixtureDef fd = FixtureDef.make();
			fd.setFriction(0f);
			fd.setDensity(1f);
			fd.setShape(box1);
			f = body.createFixture(fd);
			fd.destroy();
			FixtureAnimation anim = FixtureAnimation.make(0.2f,
					R.drawable.moving1, R.drawable.moving2, R.drawable.moving3);// 动画帧等版定和设置

			anim.setLoop(true);// 设置是否循环显示
			anim.start(f);// 启动动画显示

		}

		setRoof();

		schedule(new TargetSelector(this, "update(float)", new Object[] { 0f }));
	}

	public void update(float delta) {
		super.update(delta);
		fresh();
		WYPoint carPos = body.getPosition();
		float pX = mBox2D.meter2Pixel(carPos.x);
		mBox2D.setPosition(-pX+s.width/3 ,0);
		
		
		
	}

	@Override
	public boolean wyTouchesBegan(MotionEvent event) {
		// TODO Auto-generated method stub
		WYPoint loc = Director.getInstance().convertToGL(event.getX(),
				event.getY());
		X_sta = loc.x;
		Y_sta = loc.y;
		return true;
	}

	@Override
	public boolean wyTouchesEnded(MotionEvent event) {
		// TODO Auto-generated method stub
		WYPoint loc = Director.getInstance().convertToGL(event.getX(),
				event.getY());
		X_end = loc.x;
		Y_end = loc.y;
		float tital_x = 0, tital_y = 0;
		tital_y = Y_end - Y_sta; // y轴距离差
		tital_x = X_end - X_sta; // x轴距离差

		if (tital_x > 0 && tital_y > 0) { // 判断是否起跳
			System.out.println("jump");
			jump();
		}
		if (tital_x > 0 && tital_y < 0) { // 判断下蹲
			squat();
		}
		return true;
	}

	public void jump() { // 起跳
//		if (body.getLinearVelocity().y == 0) { // 防止连跳，判断y的速度是否为0
			WYPoint WH = WYPoint.make(Speed, hight);
			body.setLinearVelocity(WH); // 设置速度
//		}


	}
	
	public void setRoof(){
		
		roof_1 = new Roof1(mWorld, mBox2D);
		roof_1.set_border(-s.width/3);
		
		roof_2 = new Roof2(mWorld, mBox2D);
		roof_2.set_border(s.width/2);
		
		roof_3 = new Roof3(mWorld, mBox2D);
		roof_3.set_border(s.width);
		
		roof_4 = new Roof4(mWorld, mBox2D);
		roof_4.set_border(s.width*2);
		
		roof_5 = new Roof5(mWorld, mBox2D);
		roof_5.set_border(s.width*2.5f);
		
		roof_6 = new Roof6(mWorld, mBox2D);
		roof_6.set_border(s.width*3.5f);
		
		roof_7 = new Roof7(mWorld, mBox2D);
		roof_7.set_border(s.width*4);
		
		
	}

	
	public void fresh(){
		roof_1.updata_img();
		roof_2.updata_img();
	}
	public void squat() { // 下蹲

	}

	@Override
	public void beginContact(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void endContact(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postSolve(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preSolve(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}
}
