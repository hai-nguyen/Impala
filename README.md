# Impala an example of Android Architecture with MVP

There are many guidelines/examples about MVP as well as Android Architecture, but not many of them have a view on how to put everything together.

In this small sample I demo how MVP implemented with:

1. Unit Test
2. Automation test
3. Dagger 2
4. RxJava
5. AutoValue

I chose Apprium as Automation test framework, in this example you will see that Automation will automatically get apk to run automation, also with this approach you have fully end to end CI enabled with gradle commands

# To run automation Test
1. Run `./gradlew assembleAutomationDebug`
2. cd to acceptance
3. Run `./gradlew testDebugUnitTest`

# Todo

1. Add more advance Dagger 2 implementation
2. More Rxjava
3. Improve automation test with BDD and Page Object