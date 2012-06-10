package com.android.perkoor;

import android.app.Activity; 
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

import com.android.perkoor.switcher.GameHomepage;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Director.IDirectorLifecycleListener;
import com.wiyun.engine.nodes.Layer;
import com.wiyun.engine.nodes.Scene;
import com.wiyun.engine.opengl.WYGLSurfaceView;

public class PerkoorActivity extends Activity implements IDirectorLifecycleListener {
	protected WYGLSurfaceView mGLSurfaceView;
	static PerkoorActivity gameview;  
    static int fenshu, score;
    GameHomepage gameHomepage;
	static {
		System.loadLibrary("wiskia");
		System.loadLibrary("xml2");
		System.loadLibrary("wiengine");
		System.loadLibrary("wiengine_binding");
		System.loadLibrary("lua");
		System.loadLibrary("chipmunk");
		System.loadLibrary("box2d");
		System.loadLibrary("wisound");
	}
	MediaPlayer mMediaPlayer, mMediaPlayer2; 
    
    
	// true表示这个demo已经被启动了
	private boolean mStarted;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 通过程序方式创建content view, 也可以通过xml方式
	    super.onCreate(savedInstanceState);
	    getWindow().addFlags(LayoutParams.FLAG_FULLSCREEN);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    
        /*
         * 可以指定是否使用透明的surface view, 除此之外相应的activity必须要设置android:theme为
         * @android:style/Theme.Translucent. 如果希望通过xml指定是否透明, 则需要在attrs.xml中添加
         * transparent属性, 如下:
         * <declare-styleable name="com.wiyun.engine.opengl.WYGLSurfaceView">
         * 		<attr name="transparent" format="boolean" />
         * </declare-styleable>
         * 
         * 使用透明surface view可能带来兼容性问题, 有些机型可能对此支持不好.
         * 
         * 该功能目前仅可在Android上使用, 因此不具备可移植性
         */
        mGLSurfaceView = new WYGLSurfaceView(this, isTransparent());
        setContentView(mGLSurfaceView);
        
        // 设置显示帧率，程序发布时应该去掉
        Director.getInstance().setDisplayFPS(true);

        // 添加一个生命周期监听器，如果不需要可以不添加
        Director.getInstance().addLifecycleListener(this);
        
        mMediaPlayer = new MediaPlayer();
        /*
         * 取消下面两行代码的注释可以打开基础大小适配模式。关于适配模式的介绍请
         * 查看"WiEngine 3.x综述"中的"屏幕适配"一节。
         */
//        Director.getInstance().setScaleMode(Director.SCALE_MODE_BASE_SIZE_FIT_XY);
//        Director.getInstance().setBaseSize(320, 480);
	}
	public void play1() {  
        
        /*mMediaPlayer = MediaPlayer.create(GameView.this, R.raw.jump);  
        mMediaPlayer.setLooping(false);  
        mMediaPlayer.start();*/
    }  
    public void play2() {  
        /*mMediaPlayer = MediaPlayer.create(GameView.this, R.raw.boost);  
        mMediaPlayer.setLooping(false);  
        mMediaPlayer.start();*/  
    }  
    public void play3() {  
        /*mMediaPlayer = MediaPlayer.create(GameView.this, R.raw.jump2);  
        mMediaPlayer.setLooping(false);  
        mMediaPlayer.start();*/  
    }
    
	protected void createScene() {
		gameHomepage = new GameHomepage();
		gameHomepage.autoRelease(true);
		Director.getInstance().runWithScene(gameHomepage);
	}

	
	protected boolean isTransparent() {
		return false;
	}
	
	/**
	 * 检查这个demo是否需要一些前提条件
	 * 
	 * @return 如果前提条件满足, 返回null, 如果不满足, 返回一个错误信息
	 */
	protected String checkPrecondition() {
		return null;
	}

	@Override
	protected void onPause() {
        super.onPause();

        Director.getInstance().pause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Director.getInstance().resume();
    }

    @Override
    protected void onDestroy() {
    	Director.getInstance().end();
    	
        super.onDestroy();
    }
    
    public void onDirectorPaused() {
    }
    
    public void onDirectorResumed() {
    }
    
    public void onDirectorEnded() {
    }
    
    public void onDirectorScreenCaptured(String path) {
    }
    
    public void onSurfaceChanged(int w, int h) {
    	/*
    	 * 把第一个场景的创建放在这里, 可以避免一些屏幕尺寸问题. 因为一开始WiEngine并不知道
    	 * SurfaceView的尺寸, 所以是假设为全屏大小. 在之后的onSurfaceChanged事件中, 
    	 * 正确的大小会被设置. 但是往往在onSurfaceChanged之前, 场景就已经被创建了, 
    	 * 所以如果因为某种原因不是全屏大小, 那么之前创建的场景位置就有误了.
    	 * 
    	 * 由于onSurfaceChanged有可能被调用多次, 用一个标志来防止多次运行. 而且这可能
    	 * 还不够, 有些情况下onSurfaceChanged可能连续被调用两次, 第一次传入的尺寸不对, 
    	 * 而第二次是对的. 这种情况下, 还需要做更多的检查, 可以通过尺寸大小和屏幕朝向来检查.
    	 * 比如游戏如果是横屏的, 则w必须要大于h才认为是可以的, 反之则必须要h大于w才可以.
    	 * 
    	 * 也就是说, 全面的写法应该这样
    	 * boolean landscape = getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
    	 * if(!mStarted && (landscape && w >= h || !landscape && h >= w)) {
    	 * 		mStarted = true;
    	 * 		createScene();
    	 * 		Director.getInstance().runWithScene(mScene);
    	 * }
    	 * 但是出于演示目的, demo并未限制屏幕朝向, 因此我们不做这个检查
    	 */
    	if(!mStarted) {
    		mStarted = true;
    		createScene();
    		Director.getInstance().runWithScene(gameHomepage);
    	}
    }
    
    public void onSurfaceCreated() {
    	final String error = checkPrecondition();
    	if(!TextUtils.isEmpty(error)) {
    		runOnUiThread(new Runnable() {
	            public void run() {
	            	AlertDialog.Builder builder = new Builder(PerkoorActivity.this);
	            	builder.setMessage(error)
	            	.setNegativeButton("OK", new OnClickListener() {
	            		public void onClick(DialogInterface dialog, int which) {
	            			finish();
	            		}
	            	}).show();
	            }
            });
    	}
    }
	@Override
	public void onSurfaceDestroyed() {
		// TODO Auto-generated method stub
		
	}
    
 
}
