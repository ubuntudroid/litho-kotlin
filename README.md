# litho-kotlin
A simple project showing how to set up an Android project to work with Kotlin AND Facebook's [Litho](http://fblitho.com/).

## Gist
Apart from the regular Kotlin and Litho setup two changes are important:

In your app module's `build.gradle`:

```kotlin
apply plugin: 'kotlin-kapt'

dependencies {
...

  def lithoVersion = '0.2.0'
  compile "com.facebook.litho:litho-core:$lithoVersion"
  compile "com.facebook.litho:litho-widget:$lithoVersion"
  provided "com.facebook.litho:litho-annotations:$lithoVersion"
  kapt "com.facebook.litho:litho-annotations:$lithoVersion" // in addition(!) to the "provided" line
  kapt "com.facebook.litho:litho-processor:$lithoVersion" // kapt instead of annotationprocessor or apt
  compile "com.facebook.soloader:soloader:$lithoVersion"
  debugCompile "com.facebook.litho:litho-stetho:$lithoVersion"
  compile "com.facebook.litho:litho-fresco:$lithoVersion"
  testCompile("com.facebook.litho:litho-testing:$lithoVersion") {
      exclude group: 'com.google.code.findbugs'
  }
}
```
    
In your Litho component specs (thanks to [@grandstaish](https://github.com/grandstaish) for the `@JvmStatic` hint):

```kotlin
@LayoutSpec
class ListItemSpec { // no object!!!

  companion object {

      @JvmStatic // important!!!
      @OnCreateLayout
      fun onCreateLayout(context: ComponentContext): ComponentLayout = Column.create(context)
              .paddingDip(YogaEdge.ALL, 16)
              .backgroundColor(Color.WHITE)
              .child(
                      Text.create(context)
                              .text("Hello world")
                              .textSizeSp(40f)
              )
              .child(
                      Text.create(context)
                              .text("Litho tutorial")
                              .textSizeSp(20f)
              )
              .build()
  }
}
```

## License
```
Copyright 2017 Sven Bendel

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
