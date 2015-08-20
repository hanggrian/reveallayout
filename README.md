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
        android:id="@+id/layout" <!-- this layout naming is important -->
        android:layout_width="match_parent"
        android:layout_height="match_parent">
        
        <!-- content of activity should be here -->
        
    <FrameLayout/>

</io.codetail.widget.RevealFrameLayout>
```
