## Build tools & versions used

Retrofit/Moshi Converter - 2.9.0
Moshi - 1.11.1
Hilt-Dagger/ Hilt-Dagger-kapt - 2.42
Kotlin - 1.6.21

Emulator - Pixel 3a Api 30
Android Studio - Chipmunk
TargetSdk - 32

## Steps to run the app

API 30 or above. Any Pixel.

## What areas of the app did you focus on?

Architecture

## What was the reason for your focus? What problems were you trying to solve?

I chose MVVM to make testing easier and to allow for future extension of the project.
I also chose to use Hilt for dependency injection.

Using MVVM aloud me to separate my concerns and test small code logic in the view model

## How long did you spend on this project?

4.5 hours (including set up and build error fixing)

## Did you make any trade-offs for this project? What would you have done differently with more time?

TestCoroutineScope is deprecated but I didn't have the time to fully read the updated blog post for the new
TestScope/runTest recommendation. If I had more time, I'd upgrade that scope after reading more on the update

I didn't spend as much time working with the UI. I'd probably spend more time doing that as well.

## What do you think is the weakest part of your project?

The UI probably

## Did you copy any code or dependencies? Please make sure to attribute them here!

The 'Resource' class I've used before.
TestCoroutineRule I've used before but originally got from MindOrks

## Is there any other information you’d like us to know?
I think a warning should be given for that git clean -fdx command

'git clean -fdxn' would've been safer :/

Thanks!
