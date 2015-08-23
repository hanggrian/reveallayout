CircularRevealActivity
======================

Activity and Dialog implementation of ozodrukh's wonderful <a href="https://github.com/ozodrukh/CircularReveal">CircularReveal<a/> library

<img src="http://i.imgur.com/JrUkWYe.gif" />   <img src="http://i.imgur.com/rnq0HOa.gif" />

Note
----

This is my first release of my first GitHub contribution, all thanks to:
- ozodrukh's <a href="https://github.com/ozodrukh/CircularReveal">CircularReveal<a/>
- Jake Wharton's <a href="https://github.com/JakeWharton/NineOldAndroids">NineOldAndroids<a/>

For any concern, please email me: hendraanggrian@gmail.com

Requirements
------------

Minimum SDK level of API 11 (3.0+). However as of this writing, the animation will only occur on API 21 (5.0+). When implemented in API below 21, normal activity transition will occur.

Importing
---------

The easiest method is to import as jar <a href="https://github.com/HendraAnggrian/circular-reveal-activity-dialog/blob/master/circularrevealactivitydialog/release/circular-reveal-activity-dialog-0.3.jar?raw=true">circular-reveal-activity-dialog-0.3.jar<a/>

or

Download <a href="https://github.com/HendraAnggrian/CircularRevealActivity/tree/master/circularrevealactivitydialog">library<a/> and import it in Project Structure as gradle project

Using CircularRevealActivity
----------------------------

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

#####Static starting points are pre-defined into 9 possible points: Top Left, Top, Top Right, Center Left, Center, Center Right, Bottom Left, Bottom, Bottom Right

```java
public class MyActivity extends StaticCircularRevealActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // do not setContentView(...) here, instead put the layout res id below
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_layout;
    }

    @Override
    protected RevealProperties getRevealProperties() {
        RevealProperties prop = new RevealProperties();
        prop.setViewResId(R.id.layout);
        prop.setGravity(getIntent().getExtras().getInt("EXTRA_GRAVITY"));
        prop.setDuration(500); // default value if not defined is 500
        prop.setAnimateExit(true); // default value if not defined is false
        return prop;
    }
}
```

Calling it

```java
Bundle bundle = new Bundle();
bundle.putInt("EXTRA_GRAVITY", RevealGravity.TOP_LEFT);

Intent intent = new Intent(context, MyActivity.class);
intent.putExtras(bundle);
startActivity(intent);
```

#####Dynamic starting points are defined by int X and Y point

```java
public class MyActivity extends DynamicCircularRevealActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // do not setContentView(...) here, instead put the layout res id below
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_layout;
    }

    @Override
    protected RevealProperties getRevealProperties() {
        RevealProperties prop = new RevealProperties();
        prop.setViewResId(R.id.layout);
        prop.setX(getIntent().getExtras().getInt("EXTRA_X"));
        prop.setY(getIntent().getExtras().getInt("EXTRA_Y"));
        prop.setDuration(500); // default value if not defined is 500
        prop.setAnimateExit(true); // default value if not defined is false
        return prop;
    }
}
```

You can get these X and Y points from OnTouchListener

```java
View mView = findViewById(R.id.view);
Bundle bundle = new Bundle();

mView.setOnTouchListener(new View.OnTouchListener() {
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        bundle.putInt("EXTRA_X", (int) event.getRawX());
        bundle.putInt("EXTRA_Y", (int) event.getRawY());
        return false;
    }
});
        
mView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context, MyActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
});
```

#####Styling

To animate the ActionBar, put toolbar view in your layout, then apply this styling

```xml
<style name="MyTheme" parent="Theme.AppCompat.Light.NoActionBar">
    <!-- your styling -->

    <item name="android:windowIsTranslucent">true</item>
    <item name="android:windowBackground">@android:color/transparent</item>
</style>
```

License
--------

    The MIT License (MIT)

    Copyright (c) 2015 Hendra Anggrianto Wijaya
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
