// Generated by view binder compiler. Do not edit!
package com.ios7.wallify.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.ios7.wallify.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class MainBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final LinearLayout bottombarroot;

  /**
   * This binding is not available in all configurations.
   * <p>
   * Present:
   * <ul>
   *   <li>layout/</li>
   * </ul>
   *
   * Absent:
   * <ul>
   *   <li>layout-large/</li>
   * </ul>
   */
  @Nullable
  public final BottomNavigationView bottomnav1;

  @NonNull
  public final TextView button1;

  @NonNull
  public final TextView button2;

  @NonNull
  public final LinearLayout linear1;

  @NonNull
  public final LinearLayout linear2;

  @NonNull
  public final LinearLayout linear4;

  /**
   * This binding is not available in all configurations.
   * <p>
   * Present:
   * <ul>
   *   <li>layout-large/</li>
   * </ul>
   *
   * Absent:
   * <ul>
   *   <li>layout/</li>
   * </ul>
   */
  @Nullable
  public final NavigationView navview1;

  /**
   * This binding is not available in all configurations.
   * <p>
   * Present:
   * <ul>
   *   <li>layout-large/</li>
   * </ul>
   *
   * Absent:
   * <ul>
   *   <li>layout/</li>
   * </ul>
   */
  @Nullable
  public final TextView textView15;

  @NonNull
  public final TextView textview1;

  @NonNull
  public final ViewPager viewpager1;

  private MainBinding(@NonNull LinearLayout rootView, @NonNull LinearLayout bottombarroot,
      @Nullable BottomNavigationView bottomnav1, @NonNull TextView button1,
      @NonNull TextView button2, @NonNull LinearLayout linear1, @NonNull LinearLayout linear2,
      @NonNull LinearLayout linear4, @Nullable NavigationView navview1,
      @Nullable TextView textView15, @NonNull TextView textview1, @NonNull ViewPager viewpager1) {
    this.rootView = rootView;
    this.bottombarroot = bottombarroot;
    this.bottomnav1 = bottomnav1;
    this.button1 = button1;
    this.button2 = button2;
    this.linear1 = linear1;
    this.linear2 = linear2;
    this.linear4 = linear4;
    this.navview1 = navview1;
    this.textView15 = textView15;
    this.textview1 = textview1;
    this.viewpager1 = viewpager1;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static MainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static MainBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
      boolean attachToParent) {
    View root = inflater.inflate(R.layout.main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static MainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bottombarroot;
      LinearLayout bottombarroot = ViewBindings.findChildViewById(rootView, id);
      if (bottombarroot == null) {
        break missingId;
      }

      id = R.id.bottomnav1;
      BottomNavigationView bottomnav1 = ViewBindings.findChildViewById(rootView, id);

      id = R.id.button1;
      TextView button1 = ViewBindings.findChildViewById(rootView, id);
      if (button1 == null) {
        break missingId;
      }

      id = R.id.button2;
      TextView button2 = ViewBindings.findChildViewById(rootView, id);
      if (button2 == null) {
        break missingId;
      }

      id = R.id.linear1;
      LinearLayout linear1 = ViewBindings.findChildViewById(rootView, id);
      if (linear1 == null) {
        break missingId;
      }

      id = R.id.linear2;
      LinearLayout linear2 = ViewBindings.findChildViewById(rootView, id);
      if (linear2 == null) {
        break missingId;
      }

      id = R.id.linear4;
      LinearLayout linear4 = ViewBindings.findChildViewById(rootView, id);
      if (linear4 == null) {
        break missingId;
      }

      id = R.id.navview1;
      NavigationView navview1 = ViewBindings.findChildViewById(rootView, id);

      id = R.id.textView15;
      TextView textView15 = ViewBindings.findChildViewById(rootView, id);

      id = R.id.textview1;
      TextView textview1 = ViewBindings.findChildViewById(rootView, id);
      if (textview1 == null) {
        break missingId;
      }

      id = R.id.viewpager1;
      ViewPager viewpager1 = ViewBindings.findChildViewById(rootView, id);
      if (viewpager1 == null) {
        break missingId;
      }

      return new MainBinding((LinearLayout) rootView, bottombarroot, bottomnav1, button1, button2,
          linear1, linear2, linear4, navview1, textView15, textview1, viewpager1);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
