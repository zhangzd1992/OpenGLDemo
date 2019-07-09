package com.example.zhangzd.gltest;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * @Description:
 * @Author: zhangzd
 * @CreateDate: 2019-07-09 10:38
 */
public class FGLRender implements GLSurfaceView.Renderer {

    FGLView view;
    Triangle triangle;

    public FGLRender(FGLView fglView) {
        view = fglView;

    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0,0,0,0);
        triangle = new Triangle();
    }


    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }


    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT|GLES20.GL_DEPTH_BUFFER_BIT);
        triangle.onDrawFrame(gl);
    }
}
