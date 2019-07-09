package com.example.zhangzd.gltest;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * @Description:
 * @Author: zhangzd
 * @CreateDate: 2019-07-09 10:29
 */
public class FGLView extends GLSurfaceView {

    public FGLView(Context context) {
        super(context);
    }

    public FGLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setEGLContextClientVersion(2);
        setRenderer(new FGLRender(this));
        setRenderMode(RENDERMODE_WHEN_DIRTY);
    }
}
