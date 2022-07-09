package com.cihatpala.capstoneproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowInsetsCompat;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.cihatpala.capstoneproject.callback.TWListener;
import com.cihatpala.capstoneproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private TWListener twListener;
    public final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            int len = charSequence.length();
            System.out.println("onTextChanged len -> " + len + "--- charSequence -> " + charSequence);
            if (twListener.getWatched().getId() == R.id.et_name && len > 0) {
                System.out.println("onTextChanged et_name");
//                if (len > 16) {
//                    charSequence = charSequence.subSequence(0, 10);
//                    ((EditText) twListener.getWatched()).setText(charSequence);
//                    ((EditText) twListener.getWatched()).setFocusable(View.FOCUSABLE);
//                }
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void listenText(TWListener twListener) {
        this.twListener = twListener;
        if (twListener.getWatched().getTag() == null || twListener.getWatched().getTag().equals(false)) {
            twListener.getWatched().setTag(true);
            if (twListener.getWatched() instanceof EditText) {
                ((EditText) twListener.getWatched()).addTextChangedListener(textWatcher);
            }
        }
    }
}