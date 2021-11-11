fastlane documentation
================
# Installation

Make sure you have the latest version of the Xcode command line tools installed:

```
xcode-select --install
```

Install _fastlane_ using
```
[sudo] gem install fastlane -NV
```
or alternatively using `brew install fastlane`

# Available Actions
## Android
### android test
```
fastlane android test
```
Runs all the tests
### android beta
```
fastlane android beta
```
Submit a new Beta Build to Crashlytics Beta
### android build_for_screengrab
```
fastlane android build_for_screengrab
```
Build debug and test APK for screenshots
### android increment_vc
```
fastlane android increment_vc
```
Increment version code
### android release_alpha
```
fastlane android release_alpha
```
Upload alpha test
### android release_beta
```
fastlane android release_beta
```
Upload beta test
### android release_app_sharing
```
fastlane android release_app_sharing
```
Upload to internal app sharing
### android deploy
```
fastlane android deploy
```
Deploy a new version to the Google Play
### android distribution
```
fastlane android distribution
```
Disxfftribution test

----

This README.md is auto-generated and will be re-generated every time [_fastlane_](https://fastlane.tools) is run.
More information about fastlane can be found on [fastlane.tools](https://fastlane.tools).
The documentation of fastlane can be found on [docs.fastlane.tools](https://docs.fastlane.tools).
