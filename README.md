![logo](/art/logo.png) Circular Reveal Extensions
=================================================

Circular reveal animation extension library of ozodrukh's <a href="https://github.com/ozodrukh/CircularReveal">CircularReveal<a/>.
Built for even lazier programmers.

Download
--------
Library are hosted in [jCenter](https://bintray.com/hendraanggrian/maven/circular-reveal-extensions).

```gradle
compile 'io.github.hendraanggrian:circular-reveal-extensions:0.2.0'
```

Simple
------
<img src="https://raw.githubusercontent.com/hendraanggrian/CircularRevealAnimator/master/art/demo1.gif" width="256">

```xml
<io.codetail.widget.RevealFrameLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <View
        android:id="@+id/target"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
</io.codetail.widget.RevealFrameLayout>
```

```java
Animator animator = CircularReveal.create(target);
animator.start();
```

With path animation
-------------------
<img src="https://raw.githubusercontent.com/hendraanggrian/CircularRevealAnimator/master/art/demo2.gif" width="256">

```xml
<io.codetail.widget.RevealFrameLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <View
        android:id="@+id/source"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

    <View
        android:id="@+id/target"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

</io.codetail.widget.RevealFrameLayout>
```

```java
AnimatorSet set = CircularReveal.createSet(source, target);
set.start();
```


Activity transition
-------------------
<img src="https://raw.githubusercontent.com/hendraanggrian/CircularRevealAnimator/master/art/demo3.gif" width="256">

```java
// from previous activity
Intent intent = CircularReveal.createIntent(view, this, NextActivity.class);
startActivity(intent);

public class NextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        view.post(new Runnable() {
            @Override
            public void run() {
                CircularReveal.createFromIntent(getIntent(), layout).start();
            }
        });
    }
}
```