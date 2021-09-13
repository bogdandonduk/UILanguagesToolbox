
# UILanguagesToolbox

  Utility library that simplifies UI language management on Android. It allows you to make the language of your application totally independent from the system language by managing custom preferences and dynamic loading of the default or alternative string **locale** resources whenever you decide. It also conveniently organizes the dynamic language switching via corresponding interface.
  ![](https://github.com/bogdandonduk/UIThemesToolbox/blob/master/device-2021-09-13-001700.png)![](https://github.com/bogdandonduk/UIThemesToolbox/blob/master/device-2021-09-13-133728.png)
![](https://github.com/bogdandonduk/UIThemesToolbox/blob/master/PicsArt_09-13-01.32.32.png)![](https://github.com/bogdandonduk/UIThemesToolbox/blob/master/PicsArt_09-13-01.33.26.png)
## Include in your project  
**Gradle dependency**  
  
Add this in your **app**-level **build.gradle** file:  
```groovy
dependencies {  
	...  
  
	def latest_version_tag = 2.0
	implementation "com.github.bogdandonduk:UILanguagesToolbox:$latest_version_tag"  
  
	...  
}  
```  
You can always find the **latest_version_tag** [here](https://github.com/bogdandonduk/UIThemesToolbox/releases).  
  
Also make sure you have this repository in your **project**-level **build.gradle** file:  
```groovy  
allprojects {  
	repositories {  
		...  
  
		maven { url 'https://jitpack.io' }  
	}  
}  
```  

# Examples of usage
```kotlin 
// Static methods of UILanguagesToolbox object provide you various utilities for theme management

// detect currently set app language this method. It returns the setting of your application or system language if there is none set
val currentLanguage: String = UILangugesToolbox.getAppLanguageCode(context, uiLanguagesHandler = null)

// it is also possible to detect whether the language setting is set manually or automatic (system)
val isLanguageSetByMyUser = !UILanguagesToolbox.isAppLanguageCodeSettingAutomatic(context)

// set the language like this
UILanguagesToolbox.setAppLanguageCode(context, languageCode = "fr", uiLanguagesHandler = null)

// you can remove the manual settings of theme and delegate the language management in your app back to system
UILanguagesToolbox.removeManualSetting(context, uiLanguagesHandler = null)

// the null arguments in the two methods above are of type UILanguagesHandler, which is interface. 
// You can implement it and its method initUILanguage() where you should do configurations of all your views for the new language (refreshing). 
// Then you can pass your uiLanguagesHandler to two methods above and whenever the language setting changes, your whole UI will update instantly.

// you can dynamically and automatically load string resources (default or alternative) for the language setting of your app like this:
val titleString: String = UILanguagesToolbox.resolveStringResourceAuto(context, R.string.title)

// you can also explicitly specify configuration:

val titleString: String = UILanguagesToolbox.resolveStringResourceManual(context, R.string.title, languageCode = "de")
```
