package com.android.perkoor.layer;

import android.util.Log;

import com.android.perkoor.R;
import com.wiyun.engine.box2d.Box2DRender;
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

public class BackgroudLayer extends Box2DLayer {

	// 背景贴图 Fixture
	Fixture f_back1, f_back2;
	
	// 屏幕大小
	WYSize size;

	float width, height;
	// 绑定图片
	Box2DRender render;
	static float SPEED = -2;

	public BackgroudLayer() {
		
		// place box2d to center of bottom edge
		// mBox2D.setPosition(size.width / 2, size.height / 2);
		
		render = Box2DRender.make();
		mBox2D.setBox2DRender(render);
		setTouchEnabled(false);
		mBox2D.setDebugDraw(false);
		size = Director.getInstance().getWindowSize();
		width  = size.width;   //30
		height = size.height;  //20
		
		{
			//背景定义，移动，速度
			BodyDef back_df = BodyDef.make();
			//背景位置定义：
			back_df.setPosition(mBox2D.pixel2Meter(width / 2),
					mBox2D.pixel2Meter(height / 2));
			//System.out.println(mBox2D.pixel2Meter(width / 2));
			Log.i("position", 
					String.valueOf(mBox2D.pixel2Meter(width / 2))
					+ " " + String.valueOf(mBox2D.pixel2Meter(height / 2)));
			back_df.setType(Body.TYPE_DYNAMIC);
			//创建背景刚体
			Body background1 = mWorld.createBody(back_df);
			//设置速度
			background1.setLinearVelocity(WYPoint.make(SPEED, 0));
			back_df.destroy();

			//背景形状：多边形
			PolygonShape shape = PolygonShape.make(); 
			//设置为一个矩形 .setAsBox(float x, float, y) x:宽的一半 y:高的一半
			shape.setAsBox(mBox2D.pixel2Meter(width / 2),
					mBox2D.pixel2Meter(height / 2));
			FixtureDef fd = FixtureDef.make();
			fd.setShape(shape);
			f_back1 = background1.createFixture(fd);
			fd.destroy();

			//背景贴图：到现在才贴上图
			Texture2D text = Texture2D.makeJPG(R.drawable.background);
			render.bindTexture(f_back1, text);
			text.autoRelease();
		}

		{
			BodyDef back_df = BodyDef.make();
			back_df.setPosition(mBox2D.pixel2Meter(width + (width / 2)),
					mBox2D.pixel2Meter(height / 2));
			back_df.setType(Body.TYPE_DYNAMIC);
			Body background2 = mWorld.createBody(back_df);
			background2.setLinearVelocity(WYPoint.make(SPEED, 0));
			back_df.destroy();

			PolygonShape shape = PolygonShape.make();
			shape.setAsBox(mBox2D.pixel2Meter(width / 2),
					mBox2D.pixel2Meter(height / 2));
			FixtureDef fd = FixtureDef.make();
			fd.setShape(shape);
			f_back2 = background2.createFixture(fd);
			fd.destroy();

			Texture2D text = Texture2D.makeJPG(R.drawable.background);
			render.bindTexture(f_back2, text);
			text.autoRelease();
		}

		// 调度？
		schedule(new TargetSelector(this, "update(float)", new Object[] { 0f }));
	}

	public void update(float delta) {
		super.update(delta);
		// f_back.getBody().setTransform(Test_Box2d.carPos.x/2, 10f, 0f);
		
		running();
	}

	public void running() {

		if (f_back1.getBody().getPosition().x < mBox2D.pixel2Meter(-width / 2)) {
			Texture2D text = Texture2D.makeJPG(R.drawable.background);// 不同背景切换
			render.bindTexture(f_back1, text);
			f_back1.getBody().setTransform(                    //刚体位置
					mBox2D.pixel2Meter(width + (width / 2)),
					mBox2D.pixel2Meter(height / 2), 0);
			text.autoRelease();
		}
		if (f_back2.getBody().getPosition().x < mBox2D.pixel2Meter(-width / 2)) {
			Texture2D text = Texture2D.makeJPG(R.drawable.background);// 不同背景切换
			render.bindTexture(f_back1, text);
			f_back2.getBody().setTransform(
					mBox2D.pixel2Meter(width + (width / 2)),
					mBox2D.pixel2Meter(height / 2), 0);
			text.autoRelease();
		}
	}

	/*public int mapImg(int i) {
		int id = 0;
		switch (i) {
		case 1:
			id = R.drawable.back;
			break;
		case 2:
			id = R.drawable.back;
			break;
		}
		return id;
	}*/
}
