![logo](/art/logo.png) RevealLayout
===================================
Circular reveal animation for even lazier programmers. An extension of ozodrukh's <a href="https://github.com/ozodrukh/CircularReveal">CircularReveal<a/>.
Built for even lazier programmers.

Download
--------
Library are hosted in [jCenter](https://bintray.com/hendraanggrian/maven/reveal-layout).
```gradle
compile 'com.hendraanggrian:reveal-layout:0.3.1'
```

Simple
------
<img src="https://raw.githubusercontent.com/HendraAnggrian/reveallayout/master/art/demo1.gif" width="256">

Simply add target reveal id attribute in `RevealFrameLayout` or `RevealLinearLayout`.
```xml
<com.hendraanggrian.reveallayout.RevealFrameLayout
    android:id="@+id/layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:revealId="@+id/target"
    app:revealDuration="500">

    <View
        android:id="@id/target"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
</com.hendraanggrian.reveallayout.RevealFrameLayout>
```

This animation can also be triggered programmatically.
```java
RevealFrameLayout layout = (RevealFrameLayout) findById(R.id.layout);
View target = findById(R.id.target);

Animator animator = layout.animate(target);
animator.setDuration(500);
animator.start();
```

With path animation
-------------------
<img src="https://raw.githubusercontent.com/hendraanggrian/reveallayout/master/art/demo2.gif" width="256">

```xml
<com.hendraanggrian.reveallayout.RevealFrameLayout
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
</com.hendraanggrian.reveallayout.RevealFrameLayout>
```

```java
RevealFrameLayout layout = (RevealFrameLayout) findById(R.id.layout);
View source = findById(R.id.source);
View target = findById(R.id.target);

AnimatorSet set = layout.animateTo(source, target);
set.start();
```


Activity transition
-------------------
<img src="https://raw.githubusercontent.com/hendraanggrian/reveallayout/master/art/demo3.gif" width="256">

See example.
