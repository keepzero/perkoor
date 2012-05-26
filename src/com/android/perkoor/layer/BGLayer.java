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

public class BGLayer extends Layer implements INodeVirtualMethods{

	//背景精灵
	Sprite mBackground1;
	Sprite mBackground2;
	
	public BGLayer() {
		setJavaVirtualMethods(this);
		
		//设置是否绘制节点
		setNoDraw(true);
		WYSize size = Director.getInstance().getWindowSize();
		
		mBackground1 = Sprite.make(R.drawable.background);
		mBackground2 = Sprite.make(R.drawable.background);
		
		float scaleX = size.width / mBackground1.getWidth();
		float scaleY = size.height / mBackground1.getHeight();
		
		//添加背景精灵1
		{
			mBackground1.setScale(scaleX, scaleY);
			addChild(mBackground1);
			Log.i("wy_size", size.toString());
			Log.i("wy_size", String.valueOf(size.width));
			Log.i("wy_size", String.valueOf(size.height));
			mBackground1.setPosition(size.width / 2, size.height / 2 );
			Log.i("bg_size", String.valueOf(mBackground1.getWidth()));
			Log.i("bg_size", String.valueOf(mBackground1.getHeight()));
			
			//MoveTo
			MoveTo move = (MoveTo)MoveTo.make(5, //第一个参数，动作持续时间
					mBackground1.getPositionX(), 
					mBackground1.getPositionY(), 
					0 - size.width / 2, 
					mBackground1.getPositionY()).autoRelease();
	        //move.setPinPoint(DP(100), size.height - DP(100));
	        //move.setPinAngleDelta(90);
	        
			//MoveTo back = (MoveTo)move.reverse().autoRelease();
			MoveTo reMove = (MoveTo)MoveTo.make(5, 
					mBackground1.getPositionX(), 
					mBackground1.getPositionY(), 
					0 - size.width / 2, 
					mBackground1.getPositionY()).autoRelease();
	        Sequence seq1 = (Sequence)Sequence.make(move, reMove).autoRelease();
	        RepeatForever repeat1 = (RepeatForever)RepeatForever.make(seq1).autoRelease();
	        mBackground1.runAction(repeat1);
		}
        
		//背景精灵2
		{
			mBackground2.setScale(scaleX, scaleY);
			addChild(mBackground2);
			Log.i("size", size.toString());
			mBackground2.setPosition(size.width + size.width / 2, size.height / 2 );
			
			//MoveTo
			MoveTo move = (MoveTo)MoveTo.make(5, //第一个参数，动作持续时间
					mBackground2.getPositionX(), 
					mBackground2.getPositionY(), 
					size.width / 2, 
					mBackground2.getPositionY()).autoRelease();
	        //move.setPinPoint(DP(100), size.height - DP(100));
	        //move.setPinAngleDelta(90);
	        
			//MoveTo back = (MoveTo)move.reverse().autoRelease();
			MoveTo reMove = (MoveTo)MoveTo.make(5, 
					mBackground2.getPositionX(), 
					mBackground2.getPositionY(), 
					size.width / 2, 
					mBackground2.getPositionY()).autoRelease();
	        Sequence seq2 = (Sequence)Sequence.make(move, reMove).autoRelease();
	        RepeatForever repeat2 = (RepeatForever)RepeatForever.make(seq2).autoRelease();
	        mBackground2.runAction(repeat2);
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
    	WYPoint anchor = mBackground1.convertToWorldSpace(mBackground1.getAnchorX(), mBackground1.getAnchorY());
    	Primitives.drawPoint(anchor.x, anchor.y);

    	// draw line between pin point and anchor point
    	gl.glColor4f(0, 1, 0, 1);
    	Primitives.drawDashLine(DP(100), s.height - DP(100), anchor.x, anchor.y, 5);
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
