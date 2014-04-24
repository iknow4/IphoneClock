// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AnimationRotateZResume.java

package com.aedesign.deskclock.anim;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.*;

public class AnimationRotateZResume extends Animation
{

	private Camera mCamera;
	private float mCenterX;
	private float mCenterY;
	private float mDistance;

	public AnimationRotateZResume(float f)
	{
		Camera camera = new Camera();
		mCamera = camera;
		mDistance = f;
	}

	protected void applyTransformation(float f, Transformation transformation)
	{
		Matrix matrix = transformation.getMatrix();
		mCamera.save();
		Camera camera = mCamera;
		float f1 = mDistance;
		float f2 = mDistance * f;
		float f3 = f1 - f2;
		camera.rotateZ(f3);
		mCamera.getMatrix(matrix);
		float f4 = -mCenterX;
		float f5 = -mCenterY;
		matrix.preTranslate(f4, f5);
		float f6 = mCenterX;
		float f7 = mCenterY;
		matrix.postTranslate(f6, f7);
		mCamera.restore();
	}

	public void initialize(int i, int j, int k, int l)
	{
		super.initialize(i, j, k, l);
		float f = (float)i * 0x3f000000;
		mCenterX = f;
		float f1 = (float)j * 0x3f000000;
		mCenterY = f1;
		AccelerateDecelerateInterpolator acceleratedecelerateinterpolator = new AccelerateDecelerateInterpolator();
		setInterpolator(acceleratedecelerateinterpolator);
	}
}
