# UtilsLibrary
Utils to save coding time

Step 1. Add the JitPack repository to your build file

Gradle
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.vicky-droid:UtilsLibrary:Tag'
	}

Step 3. Intilize in application class

    class app: Application() {
        override fun onCreate() {
             super.onCreate()
             UtilsLib.initialize(this)
    }  }


Now you are ready to use to Utils Library.

Call a Date picker or Time picker in one line of code.

    Edittext.setTimepicker()
    Edittext.setDatePickerET()

Need Retrofit instance with few lines of code we got your back. try this
        
    val commonService by lazy(mode = LazyThreadSafetyMode.NONE) {
            .setBaseUrl("https://api.github.com/users/vicky-droid/")     //set Base Url
            .setAuthToken(authToken)    //set authToken
            .enableDebug(false)         //enable disable HttpLoggingInterceptor  
            .enableChucker(true)        //enable disable chucker debug library
            .setPrettyJson(true)      // beautifully print json as Human readable in Logcat
            .create(ApiCommonService::class.java)

    }
then this can simply used like this

    commonService.getGithubRepos() // where getGithubRepos() is interface 

### **_Yes_ we made it, Now you can call server and get api response as json in single of code.**

Check the example NetworkActivity for  implementation.


Show Progress Loader with this single line 

    showNewLoader() // to show
    hideNewLoader() // to hide







[![](https://jitpack.io/v/vicky-droid/UtilsLibrary.svg)](https://jitpack.io/#vicky-droid/UtilsLibrary)

	
