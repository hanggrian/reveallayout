package io.github.hendraanggrian.circularrevealanimator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.codetail.animation.ViewAnimationUtils;

/**
 * Created by hendraanggrian on 25-Jul-16.
 */
public class CircularRevealAnimator {

    public static final int DURATION_LONG = 350;
    public static final int DURATION_SHORT = 200;

    public static Builder of(Activity activity) {
        return new Builder(activity).duration(DURATION_LONG); // default
    }

    public static class Builder {
        private static final String TAG_SOURCE = CircularRevealAnimator.class.getSimpleName() + "_source";
        private static final String TAG_TARGET = CircularRevealAnimator.class.getSimpleName() + "_target";

        private Activity activity;
        private int duration;
        private List<AnimatorListenerAdapter> listenerAdapters;
        private float maskElevation;

        public Builder(Activity activity) {
            this.activity = activity;
            this.listenerAdapters = new ArrayList<>();
        }

        /**
         * @param duration of the animation, default value is {@link CircularRevealAnimator#DURATION_LONG}.
         */
        public Builder duration(int duration) {
            this.duration = duration;
            return this;
        }

        /**
         * @param listenerAdapter add listener to the animation.
         */
        public Builder addListener(AnimatorListenerAdapter listenerAdapter) {
            this.listenerAdapters.add(listenerAdapter);
            return this;
        }

        public void reveal(final View target, final int startX, final int startY) {
            final Rect trgRect = buildRect(target);
            final Animator animator = ViewAnimationUtils.createCircularReveal(target, startX, startY, 0, getFinalRadius(trgRect));
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.setDuration(duration);
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    target.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    target.setTag(TAG_TARGET);
                }
            });
            for (AnimatorListenerAdapter listenerAdapter : listenerAdapters)
                animator.addListener(listenerAdapter);
            animator.start();
        }

        public void reveal(@IdRes int targetId, int startX, int startY) {
            reveal(activity.findViewById(targetId), startX, startY);
        }

        public void reverse(final View target, final int centerX, final int centerY) {
            final Rect trgRect = buildRect(target);
            final Animator animator = ViewAnimationUtils.createCircularReveal(target, centerX, centerY, getFinalRadius(trgRect), 0);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.setDuration(duration);
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    target.setVisibility(View.INVISIBLE);
                    target.setTag(null);
                }
            });
            for (AnimatorListenerAdapter listenerAdapter : listenerAdapters)
                animator.addListener(listenerAdapter);
            animator.start();
        }

        public void reverse(@IdRes int targetId, int startX, int startY) {
            reverse(activity.findViewById(targetId), startX, startY);
        }

        public void reveal(final View source, final View target) {
            // Cancel all concurrent events on view
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                source.cancelPendingInputEvents();
            source.setEnabled(false);

            // Coordinates with circle initial point
            final Rect srcRect = buildRect(source);
            final Rect trgRect = buildRect(target);

            // Put Mask view at circle initial points
            if (target instanceof CardView) {
                maskElevation = ((CardView) target).getCardElevation();
                ((CardView) target).setCardElevation(0);
            }
            target.setVisibility(View.VISIBLE);
            source.setVisibility(View.INVISIBLE);

            final Animator circularReveal = ViewAnimationUtils.createCircularReveal(target,
                    trgRect.centerX(),
                    trgRect.centerY(),
                    srcRect.width() / 2,
                    getFinalRadius(trgRect),
                    View.LAYER_TYPE_HARDWARE);

            final float c0X = srcRect.centerX() - trgRect.centerX();
            final float c0Y = srcRect.centerY() - trgRect.centerY();
            final AnimatorPath path = new AnimatorPath();
            path.moveTo(c0X, c0Y);
            // path.lineTo(trgRect.left, trgRect.top);
            path.curveTo(c0X, c0Y, 0, c0Y, trgRect.left, trgRect.top);

            ObjectAnimator pathAnimator = ObjectAnimator.ofObject(activity, "maskLocation", new PathEvaluator(), path.getPoints().toArray());

            AnimatorSet set = new AnimatorSet();
            set.playTogether(circularReveal, pathAnimator);
            set.setInterpolator(new FastOutSlowInInterpolator());
            set.setDuration(duration);
            set.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    if (target instanceof CardView)
                        ((CardView) target).setCardElevation(maskElevation);

                    // tag
                    source.setTag(TAG_SOURCE);
                    target.setTag(TAG_TARGET);
                }
            });
            for (AnimatorListenerAdapter listenerAdapter : listenerAdapters)
                set.addListener(listenerAdapter);
            set.start();
        }

        public void reveal(@IdRes int sourceId, @IdRes int targetId) {
            reveal(activity.findViewById(sourceId), activity.findViewById(targetId));
        }

        public void reverse(final View source, final View target) {
            source.setVisibility(View.INVISIBLE);

            // Coordinates with circle initial point
            final Rect srcRect = buildRect(source);
            final Rect trgRect = buildRect(target);

            if (target instanceof CardView) {
                maskElevation = ((CardView) target).getCardElevation();
                ((CardView) target).setCardElevation(0);
            }

            final Animator circularReveal = ViewAnimationUtils.createCircularReveal(target,
                    trgRect.centerX(),
                    trgRect.centerY(),
                    getFinalRadius(trgRect),
                    srcRect.width() / 2,
                    View.LAYER_TYPE_HARDWARE);

            final float c0X = srcRect.centerX() - trgRect.centerX();
            final float c0Y = srcRect.centerY() - trgRect.centerY();
            final AnimatorPath path = new AnimatorPath();
            path.moveTo(trgRect.left, trgRect.top);
            //path.lineTo(c0X, c0Y);
            path.curveTo(trgRect.left, trgRect.top, 0, c0Y, c0X, c0Y);

            final ObjectAnimator pathAnimator = ObjectAnimator.ofObject(activity, "maskLocation", new PathEvaluator(), path.getPoints().toArray());

            AnimatorSet set = new AnimatorSet();
            set.playTogether(circularReveal, pathAnimator);
            set.setInterpolator(new FastOutSlowInInterpolator());
            set.setDuration(duration);
            set.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    if (target instanceof CardView)
                        ((CardView) target).setCardElevation(maskElevation);
                    target.setVisibility(View.INVISIBLE);

                    source.setVisibility(View.VISIBLE);
                    source.setEnabled(true);

                    // tag
                    source.setTag(null);
                    target.setTag(null);
                }
            });
            for (AnimatorListenerAdapter listenerAdapter : listenerAdapters)
                set.addListener(listenerAdapter);
            set.start();
        }

        public void reverse(@IdRes int sourceId, @IdRes int targetId) {
            reverse(activity.findViewById(sourceId), activity.findViewById(targetId));
        }

        public boolean isRevealed(View target) {
            return target.getTag() != null && target.getTag().toString().endsWith(TAG_TARGET);
        }

        public boolean isRevealed(@IdRes int targetId) {
            return isRevealed(activity.findViewById(targetId));
        }

        public boolean isRevealed(View source, View target) {
            return source.getTag() != null && source.getTag().toString().endsWith(TAG_SOURCE) &&
                    target.getTag() != null && target.getTag().toString().endsWith(TAG_TARGET);
        }

        public boolean isRevealed(@IdRes int sourceId, @IdRes int targetId) {
            return isRevealed(activity.findViewById(sourceId), activity.findViewById(targetId));
        }

        public void startActivity(Intent intent, View source) {
            activity.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    .putExtra(CircularRevealAnimator.class.getSimpleName(), buildRect(source)));
        }

        public void startActivity(Intent intent, @IdRes int sourceId) {
            startActivity(intent, activity.findViewById(sourceId));
        }

        public void onCreate(final View target) {
            target.post(new Runnable() {
                @Override
                public void run() {
                    final Rect srcRect = activity.getIntent().getParcelableExtra(CircularRevealAnimator.class.getSimpleName());
                    reveal(target, srcRect.centerX(), srcRect.centerY());
                }
            });
        }

        public void onCreate(@IdRes int targetId) {
            onCreate(activity.findViewById(targetId));
        }

        public void onBackPressed(final View target) {
            target.post(new Runnable() {
                @Override
                public void run() {
                    final Rect srcRect = activity.getIntent().getParcelableExtra(CircularRevealAnimator.class.getSimpleName());
                    addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            activity.finish();
                            activity.overridePendingTransition(0, 0);
                        }
                    }).reverse(target, srcRect.centerX(), srcRect.centerY());
                }
            });
        }

        public void onBackPressed(@IdRes int targetId) {
            onBackPressed(activity.findViewById(targetId));
        }

        private Rect buildRect(View view) {
            final Rect rect = new Rect();
            view.getDrawingRect(rect);
            final ViewGroup parent = (ViewGroup) view.getParent();
            parent.offsetDescendantRectToMyCoords(view, rect);
            return rect;
        }

        private float getFinalRadius(Rect rect) {
            int dx = Math.max(rect.centerX(), rect.width() - rect.centerX());
            int dy = Math.max(rect.centerY(), rect.height() - rect.centerY());
            return (float) Math.hypot(dx, dy);
        }
    }

    public interface OnReveal {
        void setMaskLocation(PathPoint location);
    }
}