// Generated code from Butter Knife. Do not modify!
package io.github.hendraanggrian.circularrevealanimatorsample;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class MainActivity$$ViewBinder<T extends MainActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131492972, "field 'buttonSimple'");
    target.buttonSimple = view;
    view = finder.findRequiredView(source, 2131492973, "field 'buttonFrom'");
    target.buttonFrom = view;
    view = finder.findRequiredView(source, 2131492974, "field 'buttonFull'");
    target.buttonFull = view;
    view = finder.findRequiredView(source, 2131492976, "field 'maskSimple'");
    target.maskSimple = view;
    view = finder.findRequiredView(source, 2131492975, "field 'maskTo'");
    target.maskTo = view;
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends MainActivity> implements Unbinder {
    private T target;

    protected InnerUnbinder(T target) {
      this.target = target;
    }

    @Override
    public final void unbind() {
      if (target == null) throw new IllegalStateException("Bindings already cleared.");
      unbind(target);
      target = null;
    }

    protected void unbind(T target) {
      target.buttonSimple = null;
      target.buttonFrom = null;
      target.buttonFull = null;
      target.maskSimple = null;
      target.maskTo = null;
    }
  }
}
