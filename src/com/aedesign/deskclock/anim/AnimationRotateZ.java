// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AnimationRotateZ.java

package com.aedesign.deskclock.anim;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.*;

public class AnimationRotateZ extends Animation
{

	private Camera mCamera;
	private float mCenterX;
	private float mCenterY;
	private float mDistance;

	public AnimationRotateZ(float f)
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
		float f1 = mDistance * f;
		camera.rotateZ(f1);
		mCamera.getMatrix(matrix);
		float f2 = -mCenterX;
		float f3 = -mCenterY;
		matrix.preTranslate(f2, f3);
		float f4 = mCenterX;
		float f5 = mCenterY;
		matrix.postTranslate(f4, f5);
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
