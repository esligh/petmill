package com.yujian.petmii.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.NumberKeyListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * @author lisc
 * ui工具类
 */
public class UIToolsUtils {
		
	private static InputFilter emojiFilter = new InputFilter() {  
        Pattern emoji = Pattern.compile(  
        "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", 
        Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);  
        @Override  
        public CharSequence filter(CharSequence source, int start, 
        		int end, Spanned dest, int dstart,int dend) {  
            Matcher emojiMatcher = emoji.matcher(source);
            if (emojiMatcher.find()){ 
            	return "";  
            }  
            return null; 
        }  
    };  
    
    public static void setNomojiEt(final EditText et,int maxLength)
    {
    	InputFilter[] emojiFilters = {emojiFilter,new InputFilter.LengthFilter(maxLength)};
    	et.setFilters(emojiFilters);
    }
    
	public static void setViewPressSelector(final View view,final int normalResId,final int pressResId)
	{
		view.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch(event.getAction()){
					case MotionEvent.ACTION_DOWN:
						if( view instanceof ImageButton 
							|| view instanceof ImageView){
							((ImageView)view).setImageResource(pressResId);
						}else {
							view.setBackgroundResource(pressResId);
						}
						break;
					case MotionEvent.ACTION_UP:
							if(view instanceof ImageButton 
								|| view instanceof ImageView){
								((ImageView)view).setImageResource(normalResId);
							}else {
								view.setBackgroundResource(normalResId);
							}
						break;
				}
				return false;
			}
		});
	}
	
	public static void setRegion( final EditText et, final int min,final int max){ 
        et.addTextChangedListener(new TextWatcher() { 
            
        	@Override 
            public void onTextChanged(CharSequence s, int start, int before, int count) { 
                if (start >= 1) {  
                	try{
	                  int num = Integer.parseInt(s.toString()); 
	                  if (num > max) { 
	                      s = String.valueOf(max); 
	                      et.setText(s); 
	                      et.setSelection(s.length());
	                  }else if(num < min){ 
	                      s = String.valueOf(min);
	                      et.setText(s);
	                      et.setSelection(s.length());
	                  }else if(num==0){
	                	  et.setText("0");
	                	  et.setSelection(1);
	                  }
                	}catch(NumberFormatException e){
                		e.printStackTrace();
                	}
	                return;                 
                } else if(count >0 && before ==0){//insert
                	if(!TextUtils.isEmpty(s)){
                		try{
                			int num = Integer.parseInt(s.toString());
                			String text ; 
                			if(num > max){
                				text = String.valueOf(max);
                			}else if(num<min){
                				text = String.valueOf(min);
        	                	
                			}else{
        	                	text = String.valueOf(num);
                			}
                			et.setText(text);
    	                	et.setSelection(text.length());
                		}catch(NumberFormatException e){
                			e.printStackTrace();
                		}
                	}
                } else if(count == 0 && before >0){//delete
                	if(!TextUtils.isEmpty(s)){
                		try{
		                	int num = Integer.parseInt(s.toString());    	
		                	String text = String.valueOf(num);
		                	et.setText(text);
		                	et.setSelection(text.length());
                		}catch(NumberFormatException e){
	                		e.printStackTrace();
                		}
                	}
                }
            } 
            
            @Override 
            public void beforeTextChanged(CharSequence s, int start, int count, 
                    int after) { 
            	
            } 

            @Override 
            public void afterTextChanged(Editable s) 
            { 
                if (s != null && !s.equals("")){ 
                     int markVal = 0; 
                     try{ 
                         markVal = Integer.parseInt(s.toString()); 
                     }catch (NumberFormatException e){ 
                         markVal = 0; 
                     } 
                     if(markVal > max){
                    	 String text = String.valueOf(max);
                         et.setText(text);
                         et.setSelection(text.length());
                     }
                     return;  
                } 
            } 
        }); 
	}
	
	public static void setRangeEt(final EditText et, final int min,final int max){
		et.setFilters(new InputFilter[]{new InputFilterMinMax(min >= 10 ?  "0" : String.valueOf(min), max >-10 ? String.valueOf(max) :"0" )});

		//and at same time must check range in the TextWatcher()
		et.addTextChangedListener(new TextWatcher() {

		      @Override
		      public void afterTextChanged (Editable editable)
		      {
		         String tmpstr = et.getText().toString();
		         if (!tmpstr.isEmpty() && !tmpstr.equals("-") ) {
		             int datavalue = Integer.parseInt(tmpstr);
		             if ( datavalue >= min || datavalue <= max) {
		               // accept data ...     
		             }
		         }
		      }

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}
		 });
	}
		
	public static void setphoneEt(final EditText et)
	{
		et.setFilters(new InputFilter[]{ new InputFilter.LengthFilter(13)});
		et.addTextChangedListener(new TextWatcher() {
			
			int beforeTextLength = 0;  
            int onTextLength = 0;  
            boolean isChanged = false;  

            int location = 0;// 记录光标的位置  
            private char[] tempChar;  
            private StringBuffer buffer = new StringBuffer();  
            int konggeNumberB = 0;  

            @Override  
            public void beforeTextChanged(CharSequence s, int start, int count,  
                    int after) {  
                beforeTextLength = s.length();  
                if (buffer.length() > 0) {  
                    buffer.delete(0, buffer.length());  
                }  
                konggeNumberB = 0;  
                for (int i = 0; i < s.length(); i++) {  
                    if (s.charAt(i) == ' ') {  
                        konggeNumberB++;  
                    }  
                }  
            }  

            @Override  
            public void onTextChanged(CharSequence s, int start, int before,  
                    int count) {  
                onTextLength = s.length();  
                buffer.append(s.toString());  
                if (onTextLength == beforeTextLength || onTextLength <= 3  
                        || isChanged) {  
                    isChanged = false;  
                    return;  
                }  
                isChanged = true;  
            }  

            @Override  
            public void afterTextChanged(Editable s) {  
                if (isChanged) {  
                    location = et.getSelectionEnd();  
                    int index = 0;  
                    while (index < buffer.length()) {  
                        if (buffer.charAt(index) == ' ') {  
                            buffer.deleteCharAt(index);  
                        } else {  
                            index++;  
                        }  
                    }  

                    index = 0;  
                    int konggeNumberC = 0;  
                    while (index < buffer.length()) {  
                        if ((index == 3 || index == 8)) {  
                            buffer.insert(index, ' ');  
                            konggeNumberC++;  
                        }  
                        index++;  
                    }  

                    if (konggeNumberC > konggeNumberB) {  
                        location += (konggeNumberC - konggeNumberB);  
                    }  

                    tempChar = new char[buffer.length()];  
                    buffer.getChars(0, buffer.length(), tempChar, 0);  
                    String str = buffer.toString();  
                    if (location > str.length()) {  
                        location = str.length();  
                    } else if (location < 0) {  
                        location = 0;  
                    }  

                    et.setText(str);  
                    Editable etable = et.getText();  
                    Selection.setSelection(etable, location);  
                    isChanged = false;  
                }  
            }  
		});
	}
	
	public static void setIpEt(EditText et)
	{
		et.setKeyListener(new NumberKeyListener(){
			@Override
			public int getInputType() {
				// TODO Auto-generated method stub
				return android.text.InputType.TYPE_CLASS_NUMBER;  
			}

			@Override
			protected char[] getAcceptedChars() {
				// TODO Auto-generated method stub
				return new char[]{'1','2','3','4','5','6','7','8','9','0','.'};
			}		
		});
	}
	
	public static class InputFilterMinMax implements InputFilter {

	    private int min, max;

	    public InputFilterMinMax(int min, int max) {
	        this.min = min;
	        this.max = max;
	    }

	    public InputFilterMinMax(String min, String max) {
	        this.min = Integer.parseInt(min);
	        this.max = Integer.parseInt(max);
	    }

	    @Override
	    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
	           try {
	               // Remove the string out of destination that is to be replaced
	               int input;
	               String newVal = dest.toString() + source.toString();
	               if (newVal.length() == 1 && newVal.charAt(0) == '-') {
	                   input = min; //allow
	               }
	               else {
	                   newVal = dest.toString().substring(0, dstart) + dest.toString().substring(dend, dest.toString().length());
	                   // Add the new string in
	                   newVal = newVal.substring(0, dstart) + source.toString() + newVal.substring(dstart, newVal.length());
	                   input = Integer.parseInt(newVal);
	               }

	               //int input = Integer.parseInt(dest.toString() + source.toString());

	               if (isInRange(min, max, input))
	                   return null;
	           } catch (NumberFormatException nfe) {
	           }
	           return "";
	       }

	    private boolean isInRange(int a, int b, int c) {

	        return b > a ? c >= a && c <= b : c >= b && c <= a;
	    }
	}	
}
