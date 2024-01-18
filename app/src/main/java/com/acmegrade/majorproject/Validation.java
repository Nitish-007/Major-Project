package com.acmegrade.majorproject;

import android.app.Activity;
import android.content.Context;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class Validation {


        private Context context;

        public Validation(Context context){this.context=context;}

        public boolean isInputEditTextFilled(EditText textInputEditText, TextInputLayout textInputLayout, String message)
        { String value=textInputEditText.getText().toString().trim();
            if(value.isEmpty()) {
                textInputLayout.setError(message);
                hideKeyboardFrom(textInputEditText);
                return false;
            }
            else {
                textInputLayout.setErrorEnabled(false);
            }
            return true;
        }

        public boolean isInputEditTextEmail(EditText textInputEditText, TextInputLayout textInputLayout, String message)
        {
            String value=textInputEditText.getText().toString().trim();
            if(value.isEmpty()|| !Patterns.EMAIL_ADDRESS.matcher(value).matches()){
                textInputLayout.setError(message);
                hideKeyboardFrom(textInputEditText);
                return false;
            }

            else {
                textInputLayout.setErrorEnabled(false);
            }
            return true;
        }
        public boolean isInputEditTextMatches(EditText textInputEditText1,EditText textInputText2,TextInputLayout txt1,String message){
            String value1=textInputEditText1.getText().toString().trim();
            String value2=textInputText2.getText().toString().trim();
            if(!value1.contentEquals(value2)){
                txt1.setError(message);
                hideKeyboardFrom(textInputText2);
                return false;
            }else{
                txt1.setErrorEnabled(false);
            }
            return true;

        }

        private void hideKeyboardFrom(View view) {
            InputMethodManager imn=(InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            imn.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }

    }


