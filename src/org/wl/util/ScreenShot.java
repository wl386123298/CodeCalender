package org.wl.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.View;

public class ScreenShot {
	// ��ȡָ��Activity�Ľ��������浽png�ļ� 
	private static Bitmap takeScreenShot(Activity activity){
		//View������Ҫ��ͼ��View 
		View view = activity.getWindow().getDecorView(); 
		view.setDrawingCacheEnabled(true); 
		view.buildDrawingCache(); 
		Bitmap b1 = view.getDrawingCache(); 
	
	
		//��ȡ״̬���߶� 
		Rect frame = new Rect(); 
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame); 
		int statusBarHeight = frame.top; 
		System.out.println(statusBarHeight); 
	
		//��ȡ��Ļ���͸� 
		int width = activity.getWindowManager().getDefaultDisplay().getWidth(); 
		int height = activity.getWindowManager().getDefaultDisplay().getHeight(); 
	
	
		//ȥ�������� 
		//Bitmap b = Bitmap.createBitmap(b1, 0, 25, 320, 455); 
		Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height - statusBarHeight); 
		//Bitmap b = Bitmap.createBitmap(b1); 
		view.destroyDrawingCache(); 
		return b; 
	} 


	//���浽sdcard 
	private static void savePic(Bitmap b,String strFileName){ 
		File file = new File(strFileName);
		FileOutputStream fos = null; 
		
		if (!file.exists()) {
			file.mkdir();
		}
		
		try { 
			fos = new FileOutputStream(strFileName); 
			if (null != fos){ 
				b.compress(Bitmap.CompressFormat.PNG, 90, fos); 
				fos.flush(); 
				fos.close(); 
			} 
		} catch (FileNotFoundException e) { 
			e.printStackTrace(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
	} 

	private static String getDate(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		return dateFormat.format(new Date());
	}

	//������� 
	public static void shoot(Activity activity){ 
		ScreenShot.savePic(ScreenShot.takeScreenShot(activity), "sdcard/ChinaCodeCalender/"+getDate()+".png"); 
	}
}