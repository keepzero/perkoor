package com.android.perkoor.switcher;

import com.wiyun.engine.nodes.Scene;

public class BackButtonListener extends Scene {
	class MyScene extends Scene {
		public MyScene() {
			setKeyEnabled(true);	       
	        autoRelease(true);
		}
	}
}
