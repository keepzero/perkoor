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

	Sprite mWhite;
	
	// 背景精灵
	Sprite mBackground1;
	Sprite mBackground2;
	Sprite mBackground3;

	public BGLayer() {
		setJavaVirtualMethods(this);
		setTouchEnabled(false);

		// 设置是否绘制节点
		setNoDraw(true);
		WYSize size = Director.getInstance().getWindowSize();

		mBackground1 = Sprite.make(R.drawable.background1);
		mBackground2 = Sprite.make(R.drawable.background2);
		mBackground3 = Sprite.make(R.drawable.background1);	
		
		mWhite = Sprite.make(R.drawable.white);

		float scaleX = size.width / mBackground1.getWidth();
		float scaleY = size.height / mBackground1.getHeight();

		{
			mWhite.setScale(size.width, size.height);
			addChild(mWhite);
			mWhite.setPosition(size.width / 2, size.height / 2);
		}
		
		// 添加背景精灵1
		{
			mBackground1.setScale(scaleX, scaleY);
			addChild(mBackground1);
			Log.i("wy_size", size.toString());
			Log.i("wy_size", String.valueOf(size.width));
			Log.i("wy_size", String.valueOf(size.height));
			mBackground1.setPosition(size.width / 2, size.height / 2);
			Log.i("bg_size", String.valueOf(mBackground1.getWidth()));
			Log.i("bg_size", String.valueOf(mBackground1.getHeight()));

			// MoveTo
			MoveTo move = (MoveTo) MoveTo.make(
					55, // 第一个参数，动作持续时间
					mBackground1.getPositionX(), mBackground1.getPositionY(),
					mBackground1.getPositionX() - 2 * size.width,
					mBackground1.getPositionY()).autoRelease();
			// move.setPinPoint(DP(100), size.height - DP(100));
			// move.setPinAngleDelta(90);

			// MoveTo back = (MoveTo)move.reverse().autoRelease();
			MoveTo reMove = (MoveTo) MoveTo.make(
					55,
					mBackground1.getPositionX(), mBackground1.getPositionY(),
					mBackground1.getPositionX() - 2 * size.width,
					mBackground1.getPositionY()).autoRelease();
			Sequence seq = (Sequence) Sequence.make(move, reMove)
					.autoRelease();
			RepeatForever repeat = (RepeatForever) RepeatForever.make(seq)
					.autoRelease();
			mBackground1.runAction(repeat);
		}

		// 背景精灵2
		{
			mBackground2.setScale(scaleX, scaleY);
			addChild(mBackground2);
			Log.i("size", size.toString());
			mBackground2.setPosition(size.width / 2 + 1 * size.width,
					size.height / 2);

			// MoveTo
			MoveTo move = (MoveTo) MoveTo.make(
					55, 
					mBackground2.getPositionX(),
					mBackground2.getPositionY(),
					mBackground2.getPositionX() - 2 * size.width,
					mBackground2.getPositionY()).autoRelease();
			// move.setPinPoint(DP(100), size.height - DP(100));
			// move.setPinAngleDelta(90);

			// MoveTo back = (MoveTo)move.reverse().autoRelease();
			MoveTo reMove = (MoveTo) MoveTo.make(
					55, // 第一个参数，动作持续时间
					mBackground2.getPositionX(), mBackground2.getPositionY(),
					mBackground2.getPositionX() - 2 * size.width,
					mBackground2.getPositionY()).autoRelease();
			Sequence seq = (Sequence) Sequence.make(move, reMove)
					.autoRelease();
			RepeatForever repeat = (RepeatForever) RepeatForever.make(seq)
					.autoRelease();
			mBackground2.runAction(repeat);
		}

		// 背景精灵3
		{
			mBackground3.setScale(scaleX, scaleY);
			addChild(mBackground3);
			Log.i("size", size.toString());
			mBackground3.setPosition(size.width / 2 + 2 * size.width,
					size.height / 2);

			// MoveTo
			MoveTo move = (MoveTo) MoveTo.make(
					55, 
					mBackground3.getPositionX(),
					mBackground3.getPositionY(),
					mBackground3.getPositionX() - 2 * size.width,
					mBackground3.getPositionY()).autoRelease();
			// move.setPinPoint(DP(100), size.height - DP(100));
			// move.setPinAngleDelta(90);

			// MoveTo back = (MoveTo)move.reverse().autoRelease();
			MoveTo reMove = (MoveTo) MoveTo.make(
					55, // 第一个参数，动作持续时间
					mBackground3.getPositionX(), mBackground3.getPositionY(),
					mBackground3.getPositionX() - 2 * size.width,
					mBackground3.getPositionY()).autoRelease();
			Sequence seq = (Sequence) Sequence.make(move, reMove)
					.autoRelease();
			RepeatForever repeat = (RepeatForever) RepeatForever.make(seq)
					.autoRelease();
			mBackground3.runAction(repeat);
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
		WYPoint anchor = mBackground1.convertToWorldSpace(
				mBackground1.getAnchorX(), mBackground1.getAnchorY());
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
