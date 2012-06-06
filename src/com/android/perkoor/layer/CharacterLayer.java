package com.android.perkoor.layer;

import android.R.integer;
import android.util.Log;
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

	Body bodyroof;
	Body body; // 申明人物刚体
	Fixture f; // 声明刚体附加属性
	WYSize s; // 声明屏幕尺寸
	float Y_sta = 0, Y_end = 0; // 初始与终止 y 轴 坐标 触摸点声明
	float X_sta = 0, X_end = 0; // 初始与终止x 轴 坐标 触摸点声明
	float tital_x = 0, tital_y = 0; //手指滑动距离
	static float hight = 5; // 跳跃高度声明
	static float highter = 12;
	static float SPEED = 15; 

	static int Speed = 20; 
	
	//box2d 的位置
	float mLocation;

	public CharacterLayer() {
		
		s = Director.getInstance().getWindowSize();// 获取屏幕尺寸
		mLocation = s.width;
		
		mWorld.setGravity(0, -30);// 设置世界的重力加速度
		mBox2D.setDebugDraw(false);// 设置刚体贴图模式，表示可以进行贴图
		mBox2D.setPosition(0, 0);// 初始位置
		Box2DRender render = Box2DRender.make();// 获取绑定render，用于贴图与刚体的绑定
		mBox2D.setBox2DRender(render);// mBox2D设置绑定
		setTouchEnabled(true);// 设置是否触摸 
		
		{// picture
			BodyDef bd = BodyDef.make();
			bd.setFixedRotation(true);
			//bd.setAngle(2);
			bd.setPosition(0f,10f);
			bd.setType(Body.TYPE_DYNAMIC);// 刚体类型，必须设置类型才能有相应的质量等
			body = mWorld.createBody(bd);
			body.setLinearVelocity(WYPoint.make(SPEED, 0f));
			bd.destroy();

			PolygonShape box1 = PolygonShape.make();
			box1.setAsBox(0.6f, 0.6f);// 设置形状
			FixtureDef fd = FixtureDef.make();
			fd.setFriction(0.5f);
			fd.setDensity(1.0f);
			fd.setShape(box1);
			f = body.createFixture(fd);
			fd.destroy();
			FixtureAnimation anim = FixtureAnimation.make(0.2f,
					R.drawable.fan_run1, R.drawable.fan_run2, R.drawable.fan_run3, R.drawable.fan_run4);// 动画帧等版定和设置

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
		//System.out.println(body.getFirstFixture().getFriction()+"");
		
		// 设置 Box2D 世界跟随人物
		mBox2D.setPosition(-pX + s.width/3 ,0);
		
		// 屋顶生成
		if(pX > mLocation){
			mLocation += s.width;
			RoofFactory.createRoof(Roof.getRandom(7), mWorld, mBox2D, mLocation);
	    }
		
		//TODO 循环屋顶
		//线程
		//判断box2d 位置
		//更新屋顶
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
		
		//移到外面，成为类字段
		//float tital_x = 0, tital_y = 0; 
		tital_y = Y_end - Y_sta; // y轴距离差
		tital_x = X_end - X_sta; // x轴距离差

		if (tital_x > 10 && tital_y > 10) { // 判断是否起跳
			System.out.println("jump");
			jump();
		}
		
		if (tital_x > 0 && tital_y < 0) { // 判断下蹲
			squat();
		}
		
		return true;
	}

	public void jump() { // 起跳
		
		WYPoint WH;
		if(tital_y > 250){
			WH = WYPoint.make(SPEED, highter);
			body.setLinearVelocity(WH); // 设置速度
		}else if (tital_y > 100) {
			WH = WYPoint.make(SPEED, hight);
			body.setLinearVelocity(WH); // 设置速度
		}
	}
	
	public void setRoof(){
		
		RoofFactory.createRoof(1, mWorld, mBox2D, 0);
		RoofFactory.createRoof(1, mWorld, mBox2D, s.width);
		//roof.setLocation(s.width/3);
		
		/*for(int i = 1; i <= 7; i++){
			RoofFactory.createRoof(Roof.getRandom(7), mWorld, mBox2D, s.width * i);
			System.out.println(s.width * i);
			//roof.setLocation(s.width * i);
			
			Log.i("location", String.valueOf(s.width * i));
		}*/
		
		//Log.i("location", String.valueOf(s.width * 2.5f));

	}

	public void changeFatty(){
		
	}
	
	public void fresh(){

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
