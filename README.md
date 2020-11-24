# Most Popular News

A simple news app that retrieves most popular news from NY times written using MVVM clean architecture design pattern

## Main components

* Dependency injection using dagger2 
* Network library: Retrofit
* Image loading: Glide
* Android architecture components: Livedata, Viewmodel, Navigation, Room 
* Android data binding

## Getting Started

Clone the project from Git repo and apply the two steps below:
- Sync Gradle
- Rebuild Project

### Prerequisites

[Android Studio](https://developer.android.com/studio/)

### How to run/install the app

Click on Run button, you should be able to choose between a connected android device or a configured emulator.

## How to run the instrumented test

Right click the MostViewedInstrumentedTest and click Run 'MostViewedInstrumentedTest'

### Instrumented test description

The instrumented test consists of the below 
- Swipe to refresh and fetch news
- Click on each item in the list and go back once details screen is shown until all item in list are tested

## How to run unit test

Right click on a mvvm component in test folder: Ex. right click on repos-> Run 'Tests in 'repos''


### How to generate a coverage report

To get a coverage report, execute the below command in Terminal window of Android Studio:

```
./gradlew createDebugCoverageReport
```

The report will be generated at the following path:
app/build/outputs/reports/coverage/debug/

## Authors

* **Philippe Chami**