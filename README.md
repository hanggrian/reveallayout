RevealLayout
============
Circular reveal animation for even lazier programmers. An extension of ozodrukh's <a href="https://github.com/ozodrukh/CircularReveal">CircularReveal<a/>.
Built for even lazier programmers.

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

Download
--------
```gradle
repositories {
    jcenter()
    maven { url "https://maven.google.com" }
    maven { url 'https://jitpack.io' }
}

dependencies {
    compile 'com.hendraanggrian:reveallayout:0.4.0'
}
```

License
-------
    Copyright 2017 Hendra Anggrian

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
