package com.yujian.petmii.utils;

import com.yujian.petmii.PetmiiApplication;

public class ResourceUtils {

	public static class ResourceType{
		public static String DRAWABLE = "drawable";
		public static String STRING  = "string";
		public static String DIMENS = "dimen";
		public static String RAW = "raw";
	}
	public static int getResIdByName(String name, String defType) {
		String packageName = PetmiiApplication.getContext().getApplicationInfo().packageName;
		return PetmiiApplication.getContext().getResources().getIdentifier(name, defType, packageName);
	}

	public static float getDimen(int resId)
	{
		return PetmiiApplication.getContext().getResources().getDimension(resId);
	}
	
	public static String getString(int resId)
	{
		return PetmiiApplication.getContext().getResources().getString(resId);
	}
	
	public static String[] getStringArray(int resId)
	{
		return PetmiiApplication.getContext().getResources().getStringArray(resId);
	}
	
	public static int getColor(int resId)
	{
		return PetmiiApplication.getContext().getResources().getColor(resId);
	}
	
	
}
