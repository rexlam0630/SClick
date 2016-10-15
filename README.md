# SClick

[ ![Download](https://api.bintray.com/packages/rtfrex/maven/sclick/images/download.svg) ](https://bintray.com/rtfrex/maven/sclick/_latestVersion)

###SafeClick - Prevent unwanted double click or any event

This is an Android library written in Java using Android Studio and Gradle, it called SClick (SafeClick).
It is a very simple but useful libaray help you to prevent user double or even triple click your UI elements trigger some unintended result, it can also use to prevent any other event that cannot be triggered more than one within specific interval.


## Feature
- Prevent click / any specific event triggered more than one within specific interval
- Manually lock / unlock an event
- Global interval setting or specific interval on the fly
- Tiny library file size, it just 3KB


## Usage
###Add dependency

####Gradle
The Gradle dependency is available via jCenter. jCenter is the default Maven repository used by Android Studio.

```gradle
dependencies {
    // Other dependencies...

    compile 'hk.ids.gws.android.sclick:library:1.0'    
}
```

###Use it
Add in to first line of you OnClickListener or any place you want to

```java
button1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (!SClick.check(SClick.BUTTON_CLICK)) return; // It will auto unlock after default interval

        // Do something...
    }
});

button2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (!SClick.check(SClick.BUTTON_CLICK, 2000)) return; // It will auto unlock after 2sec

        // Do something...
    }
});
```

```java
private void foo() {
    if (!SClick.checkAndLock("FOO-BAR-SYNC-METHOD")) return; // Manually lock with given tag
    
    // Do something...
    
    SClick.unlock("FOO-BAR-SYNC-METHOD");
}

private void bar() {
    if (!SClick.checkAndLock("FOO-BAR-SYNC-METHOD")) return; // If foo() running, this will return false
    
    // Do something...
    
    SClick.unlock("FOO-BAR-SYNC-METHOD");
}
```

```java
// Double click "Back" button to exit app within 1.25sec
@Override
public void onBackPressed() {
    if (SClick.check("exit-intended", 1250)) {
        // Ask user if really want to quit
        Toast.makeText(this, "", Toast.LENGTH_LONG);
    } else {
        // Quit app
        finishAffinity();
    }
}
```

## Setting
###Global settings

Default interval is 750ms, you can specific your own by

```java
SafeClick.getDefault().setInterval(1000); // Default set to 1sec
```


# About
Just a small unit library, hope you enjoy it.

Welcome to any contribution, tell me if anything I'm wrong and ask if any question, Star if like it :)
