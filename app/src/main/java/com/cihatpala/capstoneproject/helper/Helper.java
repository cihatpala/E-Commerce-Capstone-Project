package com.cihatpala.capstoneproject.helper;

import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Patterns;

import com.cihatpala.capstoneproject.model.request.GetTokenRequest;
import com.cihatpala.capstoneproject.model.servicemodel.UserModel;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {

    public static String doubleFormat(double currencyPrice) {
        NumberFormat format = new DecimalFormat("##.##");
        return format.format(currencyPrice);
    }


    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static boolean isValidPassword(String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static String initGetTokenRequest(String name, String password) {
        Gson gson = new Gson();

        GetTokenRequest request = new GetTokenRequest();
        UserModel userModel = new UserModel();
        userModel.username = "johnd";
        userModel.password = "m38rmF$";
        request.body = userModel;
        String json = gson.toJson(request);
        return json;
    }
}
