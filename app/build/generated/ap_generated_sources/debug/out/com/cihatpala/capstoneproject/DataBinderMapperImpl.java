package com.cihatpala.capstoneproject;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.cihatpala.capstoneproject.databinding.ActivityMainBindingImpl;
import com.cihatpala.capstoneproject.databinding.FragmentForgotPasswordBindingImpl;
import com.cihatpala.capstoneproject.databinding.FragmentLoginBindingImpl;
import com.cihatpala.capstoneproject.databinding.FragmentSignUpBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYMAIN = 1;

  private static final int LAYOUT_FRAGMENTFORGOTPASSWORD = 2;

  private static final int LAYOUT_FRAGMENTLOGIN = 3;

  private static final int LAYOUT_FRAGMENTSIGNUP = 4;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(4);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.cihatpala.capstoneproject.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.cihatpala.capstoneproject.R.layout.fragment_forgot_password, LAYOUT_FRAGMENTFORGOTPASSWORD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.cihatpala.capstoneproject.R.layout.fragment_login, LAYOUT_FRAGMENTLOGIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.cihatpala.capstoneproject.R.layout.fragment_sign_up, LAYOUT_FRAGMENTSIGNUP);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYMAIN: {
          if ("layout/activity_main_0".equals(tag)) {
            return new ActivityMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTFORGOTPASSWORD: {
          if ("layout/fragment_forgot_password_0".equals(tag)) {
            return new FragmentForgotPasswordBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_forgot_password is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTLOGIN: {
          if ("layout/fragment_login_0".equals(tag)) {
            return new FragmentLoginBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_login is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSIGNUP: {
          if ("layout/fragment_sign_up_0".equals(tag)) {
            return new FragmentSignUpBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_sign_up is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(1);

    static {
      sKeys.put(0, "_all");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(4);

    static {
      sKeys.put("layout/activity_main_0", com.cihatpala.capstoneproject.R.layout.activity_main);
      sKeys.put("layout/fragment_forgot_password_0", com.cihatpala.capstoneproject.R.layout.fragment_forgot_password);
      sKeys.put("layout/fragment_login_0", com.cihatpala.capstoneproject.R.layout.fragment_login);
      sKeys.put("layout/fragment_sign_up_0", com.cihatpala.capstoneproject.R.layout.fragment_sign_up);
    }
  }
}
