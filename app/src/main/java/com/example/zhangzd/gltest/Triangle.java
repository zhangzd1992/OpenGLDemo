package com.example.zhangzd.gltest;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * @Description:
 * @Author: zhangzd
 * @CreateDate: 2019-07-09 16:39
 */
public class Triangle {

    //创建定点数组
    private float[] triangleCoords = {
            0.5f, 0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f
    };

    private FloatBuffer vertexBuffer;
    private int program;

    private String vertexShaderCode = "attribute vec4 vPosition;\n" +
            "void main(){\n" +
            "gl_Position=vPosition;\n" +
            "}";

    private final String fragmentShaderCode = "precision mediump float;\n" +
            "uniform  vec4 vColor;\n" +
            "void main(){\n" +
            "gl_FragColor=vColor;\t\n" +
            "}";

    public Triangle() {
        //初始化
        ByteBuffer bb = ByteBuffer.allocateDirect(triangleCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();

        //将顶点数据传到buffer
        vertexBuffer.put(triangleCoords);
        vertexBuffer.position(0);

        // 创建顶点着色器
        int vertexShader = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
        GLES20.glShaderSource(vertexShader,vertexShaderCode);
        GLES20.glCompileShader(vertexShader);


        //创建片元着色器
        int fragmentShadow = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
        GLES20.glShaderSource(fragmentShadow,fragmentShaderCode);
        GLES20.glCompileShader(fragmentShadow);

        //创建程序
        program = GLES20.glCreateProgram();
        GLES20.glAttachShader(program,vertexShader);
        GLES20.glAttachShader(program,fragmentShadow);
//连接到着色器程序
        GLES20.glLinkProgram(program);
    }





    float color[] = { 1.0f, 1.0f, 1.0f, 1.0f };


    public void onDrawFrame(GL10 gl) {
        //渲染

        GLES20.glUseProgram(program);
        int mPositionHandle = GLES20.glGetAttribLocation(program, "vPosition");

        // 打开 允许对变量读写
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        GLES20.glVertexAttribPointer(mPositionHandle, 3,GLES20.GL_FLOAT, false, 3 * 4, vertexBuffer);
        int mColorHandle = GLES20.glGetUniformLocation(program, "vColor");
        GLES20.glUniform4fv(mColorHandle,1,color,0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES,0,3);

        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }
}
