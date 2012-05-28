package com.android.perkoor.layer;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

import com.android.perkoor.R;
import com.wiyun.engine.actions.MoveTo;
import com.wiyun.engine.actions.RepeatForever;
import com.wiyun.engine.actions.Sequence;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.INodeVirtualMethods;
import com.wiyun.engine.nodes.Layer;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.opengl.Primitives;
import com.wiyun.engine.types.WYPoint;
import com.wiyun.engine.types.WYSize;
import com.wiyun.engine.utils.ResolutionIndependent;

public class BGLayer extends Layer implements INodeVirtualMethods {

	// 全局白色背景
	Sprite mWhite;
	
	// 浅背景，即远景
	Sprite mLightBG1;
	Sprite mLightBG2;
	Sprite mLightBG3;
	
	// 深背景，即前景
	Sprite mDarkBG1;
	Sprite mDarkBG2;
	Sprite mDarkBG3;
	
	int mDarkSpeed = 55;
	int mLightSpeed = 88;

	public BGLayer() {
		setJavaVirtualMethods(this);
		setTouchEnabled(false);

		// 设置是否绘制节点
		setNoDraw(true);
		WYSize size = Director.getInstance().getWindowSize();

		mWhite = Sprite.make(R.drawable.white);
		
		mDarkBG1 = Sprite.make(R.drawable.dark_bg_1);
		mDarkBG2 = Sprite.make(R.drawable.dark_bg_2);
		mDarkBG3 = Sprite.make(R.drawable.dark_bg_1);	
		
		mLightBG1 = Sprite.make(R.drawable.light_bg_1);
		mLightBG2 = Sprite.make(R.drawable.light_bg_2);
		mLightBG3 = Sprite.make(R.drawable.light_bg_1);
		
		// 背景缩放尺寸
		float scaleX = size.width / mDarkBG1.getWidth();
		float scaleY = size.height / mDarkBG1.getHeight();

		// 全局白色背景
		{
			mWhite.setScale(size.width, size.height);
			addChild(mWhite);
			mWhite.setPosition(size.width / 2, size.height / 2);
		}
		
		// 远景1
		{
			mLightBG1.setScale(scaleX, scaleY);
			addChild(mLightBG1);
			mLightBG1.setPosition(size.width / 2, size.height / 2);

			// MoveTo 动作
			MoveTo move = (MoveTo) MoveTo.make(
					mLightSpeed, // 第一个参数，动作持续时间
					mLightBG1.getPositionX(), mLightBG1.getPositionY(), //起点
					mLightBG1.getPositionX() - 2 * size.width, mLightBG1.getPositionY())//终点
					.autoRelease(); 

			MoveTo reMove = (MoveTo) MoveTo.make(
					mLightSpeed,
					mLightBG1.getPositionX(), mLightBG1.getPositionY(),
					mLightBG1.getPositionX() - 2 * size.width, mLightBG1.getPositionY())
					.autoRelease();
			
			Sequence seq = (Sequence) Sequence.make(move, reMove).autoRelease();
			RepeatForever repeat = (RepeatForever) RepeatForever.make(seq).autoRelease();
			mLightBG1.runAction(repeat);
		}

		// 远景2
		{
			mLightBG2.setScale(scaleX, scaleY);
			addChild(mLightBG2);
			mLightBG2.setPosition(size.width / 2 + 1 * size.width, size.height / 2);

			MoveTo move = (MoveTo) MoveTo.make(
					mLightSpeed, 
					mLightBG2.getPositionX(),mLightBG2.getPositionY(),
					mLightBG2.getPositionX() - 2 * size.width, mLightBG2.getPositionY())
					.autoRelease();
			
			MoveTo reMove = (MoveTo) MoveTo.make(
					mLightSpeed, 
					mLightBG2.getPositionX(), mLightBG2.getPositionY(),
					mLightBG2.getPositionX() - 2 * size.width, mLightBG2.getPositionY())
					.autoRelease();
			
			Sequence seq = (Sequence) Sequence.make(move, reMove).autoRelease();
			RepeatForever repeat = (RepeatForever) RepeatForever.make(seq).autoRelease();
			mLightBG2.runAction(repeat);
		}

		// 远景3
		{
			mLightBG3.setScale(scaleX, scaleY);
			addChild(mLightBG3);
			Log.i("size", size.toString());
			mLightBG3.setPosition(size.width / 2 + 2 * size.width, size.height / 2);

			MoveTo move = (MoveTo) MoveTo.make(
					mLightSpeed, 
					mLightBG3.getPositionX(), mLightBG3.getPositionY(), 
					mLightBG3.getPositionX() - 2 * size.width, mLightBG3.getPositionY())
					.autoRelease();
			
			MoveTo reMove = (MoveTo) MoveTo.make(
					mLightSpeed, 
					mLightBG3.getPositionX(), mLightBG3.getPositionY(),
					mLightBG3.getPositionX() - 2 * size.width, mLightBG3.getPositionY())
					.autoRelease();
			
			Sequence seq = (Sequence) Sequence.make(move, reMove).autoRelease();
			RepeatForever repeat = (RepeatForever) RepeatForever.make(seq).autoRelease();
			mLightBG3.runAction(repeat);
		}
				
		// 近景1
		{
			mDarkBG1.setScale(scaleX, scaleY);
			addChild(mDarkBG1);
			Log.i("wy_size", size.toString());
			Log.i("wy_size", String.valueOf(size.width));
			Log.i("wy_size", String.valueOf(size.height));
			mDarkBG1.setPosition(size.width / 2, size.height / 2);
			Log.i("bg_size", String.valueOf(mDarkBG1.getWidth()));
			Log.i("bg_size", String.valueOf(mDarkBG1.getHeight()));

			// MoveTo 动作
			MoveTo move = (MoveTo) MoveTo.make(
					mDarkSpeed, // 第一个参数，动作持续时间
					mDarkBG1.getPositionX(), mDarkBG1.getPositionY(), //起点
					mDarkBG1.getPositionX() - 2 * size.width, mDarkBG1.getPositionY())//终点
					.autoRelease(); 

			MoveTo reMove = (MoveTo) MoveTo.make(
					mDarkSpeed,
					mDarkBG1.getPositionX(), mDarkBG1.getPositionY(),
					mDarkBG1.getPositionX() - 2 * size.width, mDarkBG1.getPositionY())
					.autoRelease();
			
			Sequence seq = (Sequence) Sequence.make(move, reMove).autoRelease();
			RepeatForever repeat = (RepeatForever) RepeatForever.make(seq).autoRelease();
			mDarkBG1.runAction(repeat);
		}

		// 近景2
		{
			mDarkBG2.setScale(scaleX, scaleY);
			addChild(mDarkBG2);
			mDarkBG2.setPosition(size.width / 2 + 1 * size.width, size.height / 2);

			MoveTo move = (MoveTo) MoveTo.make(
					mDarkSpeed, 
					mDarkBG2.getPositionX(),mDarkBG2.getPositionY(),
					mDarkBG2.getPositionX() - 2 * size.width, mDarkBG2.getPositionY())
					.autoRelease();
			
			MoveTo reMove = (MoveTo) MoveTo.make(
					mDarkSpeed, 
					mDarkBG2.getPositionX(), mDarkBG2.getPositionY(),
					mDarkBG2.getPositionX() - 2 * size.width, mDarkBG2.getPositionY())
					.autoRelease();
			
			Sequence seq = (Sequence) Sequence.make(move, reMove).autoRelease();
			RepeatForever repeat = (RepeatForever) RepeatForever.make(seq).autoRelease();
			mDarkBG2.runAction(repeat);
		}

		// 近景3
		{
			mDarkBG3.setScale(scaleX, scaleY);
			addChild(mDarkBG3);
			Log.i("size", size.toString());
			mDarkBG3.setPosition(size.width / 2 + 2 * size.width, size.height / 2);

			MoveTo move = (MoveTo) MoveTo.make(
					mDarkSpeed, 
					mDarkBG3.getPositionX(), mDarkBG3.getPositionY(), 
					mDarkBG3.getPositionX() - 2 * size.width, mDarkBG3.getPositionY())
					.autoRelease();
			
			MoveTo reMove = (MoveTo) MoveTo.make(
					mDarkSpeed, 
					mDarkBG3.getPositionX(), mDarkBG3.getPositionY(),
					mDarkBG3.getPositionX() - 2 * size.width, mDarkBG3.getPositionY())
					.autoRelease();
			
			Sequence seq = (Sequence) Sequence.make(move, reMove).autoRelease();
			RepeatForever repeat = (RepeatForever) RepeatForever.make(seq).autoRelease();
			mDarkBG3.runAction(repeat);
		}
	}

	private float DP(float v) {
		return ResolutionIndependent.resolveDp(v);
	}

	@Override
	public void jDraw() {
		// draw pin point
		WYSize s = Director.getInstance().getWindowSize();
		GL10 gl = Director.getInstance().gl;
		gl.glColor4f(1, 0, 0, 1);
		gl.glPointSize(5);
		Primitives.drawPoint(DP(100), s.height - DP(100));

		// draw anchor point
		WYPoint anchor = mDarkBG1.convertToWorldSpace(
				mDarkBG1.getAnchorX(), mDarkBG1.getAnchorY());
		Primitives.drawPoint(anchor.x, anchor.y);

		// draw line between pin point and anchor point
		gl.glColor4f(0, 1, 0, 1);
		Primitives.drawDashLine(DP(100), s.height - DP(100), anchor.x,
				anchor.y, 5);
	}

	@Override
	public void jOnEnter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void jOnEnterTransitionDidFinish() {
		// TODO Auto-generated method stub

	}

	@Override
	public void jOnExit() {
		// TODO Auto-generated method stub

	}

}
