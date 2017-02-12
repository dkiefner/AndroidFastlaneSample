# Android fastlane Sample
fastlane is a tool for building and releasing iOS and Android apps (see [fastlane Website](https://fastlane.tools/) for more information).
To setup fastlane for Android, see the [Setup fastlane for Android Guide](https://docs.fastlane.tools/getting-started/android/setup/). I would recommend install fastlane via Ruby.
To get an impression what fastlane is capable of, see [fastlane Actions](https://docs.fastlane.tools/actions/).

To run fastlane just select the lane you want to run, like the `build_and_run_unit_tests_debug` lane, which builds the debug target and run all unit tests:

```bash
fastlane build_and_run_unit_tests_debug
```

## Gradle
### Running Tasks
Like using the normal gradle or gradle wrapper you can run all tasks.
The following example is running the `clean` first, then the `assembleDebug` task and at the end the `testDebugUnitTest` task:

```ruby
gradle(
  task: 'clean assembleDebug testDebugUnitTest'
)
```

This combination can be used to build and run gradle tasks for feature branches.

### Setting gradle Properties
You can also set gradle properties via fastlane and use them on building. E.g. you can set the versionCode and versionName like this:

```ruby
properties: {
    'versionCode' => versionCode,
    'versionName' => version
}
```

Then it will write the following into the `gradle.properties` file:

```bash
versionCode=1
versionName=1.0.0
```

And then you can use it in you `app/build.gradle` like this:

```groovy
versionCode project.property('versionCode') as Integer
versionName project.property('versionName')
```

## Run External Tools
You can run external scripts in a lane.

```ruby
sh('./kill-emulators.sh')
```

## Setting Project Parameters
tbd

## Make Screenshots
tbd

## Writing Meta Data
tbd

## Upload APKs

### Upload to Hockey
tbd

### Upload to Google Beta
tbd

### Upload to Google Playstore
tbd
