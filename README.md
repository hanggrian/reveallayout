CircularRevealActivity
======================

Activity implementation of ozodrukh's wonderful <a href="https://github.com/ozodrukh/CircularReveal">CircularReveal<a/> library

<img src="http://i.imgur.com/JrUkWYe.gif" />

Note
----

Relies heavily and thanks to:
- ozodrukh's <a href="https://github.com/ozodrukh/CircularReveal">CircularReveal<a/>
- Jake Wharton's <a href="https://github.com/JakeWharton/NineOldAndroids">NineOldAndroids<a/>

Requirements
------------

Minimum SDK level of API 9 (2.3+). However as of this writing, the animation will only occur on API 21 (5.0+). When implemented in API below 21, normal activity transition will occur.

Using
-----

Use `RevealFrameLayout` or `RevealLinearLayout` as root

```xml
<io.codetail.widget.RevealFrameLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    
    <FrameLayout
        android:id="@+id/layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
        
        <!-- content of activity should be here -->
        
    <FrameLayout/>

</io.codetail.widget.RevealFrameLayout>
```

Then you have 2 choices of layout reveal animation starting point: **Static** and **Dynamic**

**Static** starting points are pre-defined into 9 possible points: Top Left, Top, Top Right, Center Left, Center, Center Right, Bottom Left, Bottom, Bottom Right

```java
public class MyActivity extends StaticCircularRevealActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // do not setContentView(...) here, instead put the layout res id below
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_hello_world;
    }

    @Override
    protected StaticCircularReveal getStaticCircularReveal() {
        StaticCircularReveal spec = new StaticCircularReveal();
        spec.setViewResId(R.id.layout);
        spec.setGravity(getIntent().getExtras().getInt("EXTRA_GRAVITY"));
        spec.setDuration(500);
        return spec;
    }
}
```

Calling it

```java
Bundle bundle = new Bundle();
bundle.putInt("EXTRA_GRAVITY", Gravity.TOP_LEFT);

Intent intent = new Intent(rootView.getContext(), HelloWorldStaticActivity.class);
intent.putExtras(bundle);
startActivity(intent);
```

**Dynamic** starting points are defined by int X and Y point captured from OnTouchListener
