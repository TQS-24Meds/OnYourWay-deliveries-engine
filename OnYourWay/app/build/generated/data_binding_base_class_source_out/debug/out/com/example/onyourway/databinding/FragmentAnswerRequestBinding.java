// Generated by data binding compiler. Do not edit!
package com.example.onyourway.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.example.onyourway.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentAnswerRequestBinding extends ViewDataBinding {
  @NonNull
  public final Button locationbtn;

  @NonNull
  public final Button nobtn;

  @NonNull
  public final Button productsbtn;

  @NonNull
  public final TextView statictext;

  @NonNull
  public final TextView userDinamic;

  @NonNull
  public final Button yesbtn;

  protected FragmentAnswerRequestBinding(Object _bindingComponent, View _root, int _localFieldCount,
      Button locationbtn, Button nobtn, Button productsbtn, TextView statictext,
      TextView userDinamic, Button yesbtn) {
    super(_bindingComponent, _root, _localFieldCount);
    this.locationbtn = locationbtn;
    this.nobtn = nobtn;
    this.productsbtn = productsbtn;
    this.statictext = statictext;
    this.userDinamic = userDinamic;
    this.yesbtn = yesbtn;
  }

  @NonNull
  public static FragmentAnswerRequestBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_answer_request, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentAnswerRequestBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentAnswerRequestBinding>inflateInternal(inflater, R.layout.fragment_answer_request, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentAnswerRequestBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_answer_request, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentAnswerRequestBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentAnswerRequestBinding>inflateInternal(inflater, R.layout.fragment_answer_request, null, false, component);
  }

  public static FragmentAnswerRequestBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static FragmentAnswerRequestBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentAnswerRequestBinding)bind(component, view, R.layout.fragment_answer_request);
  }
}
