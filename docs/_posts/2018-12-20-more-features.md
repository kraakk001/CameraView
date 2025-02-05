---
layout: page
title: "More features"
subtitle: "Undocumented features & more"
description: "Undocumented features & more"
category: docs
order: 13
date: 2018-12-20 20:41:20
disqus: 1
---

### Extra controls

```xml
<com.otaliastudios.cameraview.CameraView
    app:cameraPlaySounds="true|false"
    app:cameraGrid="off|draw3x3|draw4x4|drawPhi"
    app:cameraGridColor="@color/black"
    app:cameraAutoFocusResetDelay="0"
    app:cameraUseDeviceOrientation="true"/>
```

##### cameraPlaySounds

Controls whether we should play platform-provided sounds during certain events
(shutter click, focus completed). Please note that:

- on API < 16, this flag is always set to `false`
- the Camera1 engine will always play shutter sounds regardless of this flag

Defaults to true.

```java
cameraView.setPlaySounds(true);
cameraView.setPlaySounds(false);
```

##### cameraGrid

Lets you draw grids over the camera preview. Supported values are `off`, `draw3x3` and `draw4x4`
for regular grids, and `drawPhi` for a grid based on the golden ratio constant, often used in photography.
Defaults to `OFF`.

```java
cameraView.setGrid(Grid.OFF);
cameraView.setGrid(Grid.DRAW_3X3);
cameraView.setGrid(Grid.DRAW_4X4);
cameraView.setGrid(Grid.DRAW_PHI);
```

##### cameraGridColor

Lets you choose the color for grid lines. 
Defaults to a shade of grey.

```java
cameraView.setGridColor(Color.WHITE);
cameraView.setGridColor(Color.BLACK);
```

##### cameraAutoFocusMarker

Lets you set a marker for drawing on screen in response to auto focus events.
In XML, you should pass the qualified class name of your marker.

```java
cameraView.setAutoFocusMarker(null);
cameraView.setAutoFocusMarker(marker);
```

We offer a default marker (similar to the old `focusWithMarker` attribute in v1),
which you can set in XML using the `@string/cameraview_default_autofocus_marker` resource,
or programmatically:

```java
cameraView.setAutoFocusMarker(new DefaultAutoFocusMarker());
```

##### cameraAutoFocusResetDelay

Lets you control how an auto-focus operation is reset after completed.
Setting a value <= 0 or == Long.MAX_VALUE will not reset the auto-focus.
This is useful for low end devices that have slow auto-focus capabilities.
Defaults to 3 seconds.

```java
cameraView.setCameraAutoFocusResetDelay(1000);  // 1 second
cameraView.setCameraAutoFocusResetDelay(0);  // NO reset
cameraView.setCameraAutoFocusResetDelay(-1);  // NO reset
cameraView.setCameraAutoFocusResetDelay(Long.MAX_VALUE);  // NO reset
```

##### cameraUseDeviceOrientation

Controls whether we should consider the device orientation for picture and video outputs.
This defaults to true, but can be set to false for specific usages, where you don't want the
output to be rotated based on the device rotation at the moment of capturing.
Defaults to true.

```java
cameraView.setUseDeviceOrientation(true); // rotate media 
cameraView.setUseDeviceOrientation(false); // don't
```

### UI Orientation

Within a Camera app, it's common to rotate buttons and other UI elements as the device is tilted around.
We offer a handy callback giving you the right rotation that should be applied to UI elements for them
to be consistent with what the user is seeing:

```java
cameraView.addCameraListener(new CameraListener() {
    @Override
    public void onOrientationChanged(int orientation) {
        // orientation is the counter-clockwise rotation that a View should have
        // based on current device tilting and native activity orientation.
    }
});
```

### Location APIs

You can plug in location tags into picture EXIF (for JPEGs) and video metadata by simply using `setLocation`.
The location can be obtained from any location provider after getting appropriate permissions.
This is not guaranteed to be appended into snapshots.

|Method|Description|
|------|-----------|
|`setLocation(Location)`|Sets location data to be appended to picture/video metadata.|
|`setLocation(double, double)`|Sets latitude and longitude to be appended to picture/video metadata.|
|`getLocation()`|Retrieves location data previously applied with setLocation().|

### Undocumented features

Some features and APIs were not documented in this document, including:

- `CameraUtils` utilities
- `CameraOptions` options

For informations, please take a look at the javadocs or the source code. 

