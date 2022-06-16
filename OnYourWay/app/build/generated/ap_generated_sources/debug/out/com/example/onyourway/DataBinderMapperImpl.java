package com.example.onyourway;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.example.onyourway.databinding.ActivityMainBindingImpl;
import com.example.onyourway.databinding.FragmentAnswerRequestBindingImpl;
import com.example.onyourway.databinding.FragmentLoginFragBindingImpl;
import com.example.onyourway.databinding.FragmentMainPageBindingImpl;
import com.example.onyourway.databinding.FragmentMapsBindingImpl;
import com.example.onyourway.databinding.FragmentPackageProductsBindingImpl;
import com.example.onyourway.databinding.FragmentQrCodeBindingImpl;
import com.example.onyourway.databinding.FragmentRequestsBindingImpl;
import com.example.onyourway.databinding.FragmentSignUpBindingImpl;
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

  private static final int LAYOUT_FRAGMENTANSWERREQUEST = 2;

  private static final int LAYOUT_FRAGMENTLOGINFRAG = 3;

  private static final int LAYOUT_FRAGMENTMAINPAGE = 4;

  private static final int LAYOUT_FRAGMENTMAPS = 5;

  private static final int LAYOUT_FRAGMENTPACKAGEPRODUCTS = 6;

  private static final int LAYOUT_FRAGMENTQRCODE = 7;

  private static final int LAYOUT_FRAGMENTREQUESTS = 8;

  private static final int LAYOUT_FRAGMENTSIGNUP = 9;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(9);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.onyourway.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.onyourway.R.layout.fragment_answer_request, LAYOUT_FRAGMENTANSWERREQUEST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.onyourway.R.layout.fragment_login_frag, LAYOUT_FRAGMENTLOGINFRAG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.onyourway.R.layout.fragment_main_page, LAYOUT_FRAGMENTMAINPAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.onyourway.R.layout.fragment_maps, LAYOUT_FRAGMENTMAPS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.onyourway.R.layout.fragment_package_products, LAYOUT_FRAGMENTPACKAGEPRODUCTS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.onyourway.R.layout.fragment_qr_code, LAYOUT_FRAGMENTQRCODE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.onyourway.R.layout.fragment_requests, LAYOUT_FRAGMENTREQUESTS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.onyourway.R.layout.fragment_sign_up, LAYOUT_FRAGMENTSIGNUP);
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
        case  LAYOUT_FRAGMENTANSWERREQUEST: {
          if ("layout/fragment_answer_request_0".equals(tag)) {
            return new FragmentAnswerRequestBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_answer_request is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTLOGINFRAG: {
          if ("layout/fragment_login_frag_0".equals(tag)) {
            return new FragmentLoginFragBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_login_frag is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTMAINPAGE: {
          if ("layout/fragment_main_page_0".equals(tag)) {
            return new FragmentMainPageBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_main_page is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTMAPS: {
          if ("layout/fragment_maps_0".equals(tag)) {
            return new FragmentMapsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_maps is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPACKAGEPRODUCTS: {
          if ("layout/fragment_package_products_0".equals(tag)) {
            return new FragmentPackageProductsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_package_products is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTQRCODE: {
          if ("layout/fragment_qr_code_0".equals(tag)) {
            return new FragmentQrCodeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_qr_code is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTREQUESTS: {
          if ("layout/fragment_requests_0".equals(tag)) {
            return new FragmentRequestsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_requests is invalid. Received: " + tag);
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
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(9);

    static {
      sKeys.put("layout/activity_main_0", com.example.onyourway.R.layout.activity_main);
      sKeys.put("layout/fragment_answer_request_0", com.example.onyourway.R.layout.fragment_answer_request);
      sKeys.put("layout/fragment_login_frag_0", com.example.onyourway.R.layout.fragment_login_frag);
      sKeys.put("layout/fragment_main_page_0", com.example.onyourway.R.layout.fragment_main_page);
      sKeys.put("layout/fragment_maps_0", com.example.onyourway.R.layout.fragment_maps);
      sKeys.put("layout/fragment_package_products_0", com.example.onyourway.R.layout.fragment_package_products);
      sKeys.put("layout/fragment_qr_code_0", com.example.onyourway.R.layout.fragment_qr_code);
      sKeys.put("layout/fragment_requests_0", com.example.onyourway.R.layout.fragment_requests);
      sKeys.put("layout/fragment_sign_up_0", com.example.onyourway.R.layout.fragment_sign_up);
    }
  }
}
