package com.android.perkoor.layer;

import java.util.Random;

import android.util.Log;

import com.android.perkoor.R;
import com.wiyun.engine.actions.MoveTo;
import com.wiyun.engine.actions.RepeatForever;
import com.wiyun.engine.actions.Sequence;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.INodeVirtualMethods;
import com.wiyun.engine.nodes.Layer;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.types.WYSize;

public class CloudLayer extends Layer  implements INodeVirtualMethods {
	
	Sprite mCloud;

	public CloudLayer() {
		setJavaVirtualMethods(this);

		// 设置是否绘制节点
		setNoDraw(true);
		WYSize size = Director.getInstance().getWindowSize();
		
		mCloud = Sprite.make(R.drawable.cloud);
		
		float scaleX = (size.width / mCloud.getWidth());
		float scaleY = (size.height / mCloud.getHeight());
		
		//添加云层
		{
			mCloud.setScale(scaleX, scaleY);
			addChild(mCloud);
			mCloud.setPosition(2 * size.width, 
					size.height * 0.6f);
			
			MoveTo move = (MoveTo)MoveTo.make(
					getRandom(100) + 5, 
					mCloud.getPositionX(), mCloud.getPositionY(), 
					0 - size.width, size.height * 0.8f);
			
			MoveTo reMove = (MoveTo)MoveTo.make(
					getRandom(25) + 5, 
					mCloud.getPositionX(), mCloud.getPositionY(), 
					0 - size.width, size.height * 0.4f);
			
			Sequence seq = (Sequence) Sequence.make(move, reMove)
					.autoRelease();
			RepeatForever repeat = (RepeatForever) RepeatForever.make(seq)
					.autoRelease();
			mCloud.runAction(repeat);
		}
		
		
	}
	
	@Override
	public void jDraw() {
		// TODO Auto-generated method stub
		
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
	
	private int getRandom(int i){
		Random random = new Random(3);
		int result = random.nextInt(i);
		Log.i("random", String.valueOf(result));
		return result;
	}

}
