package bogdandonduk.uilanguagestoolboxlib

import android.content.Context
import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import java.util.*

object UILanguagesToolbox {
    private const val delimiter = "_"

    private const val LIBRARY_SHARED_PREFS_SUFFIX = "${delimiter}shared${delimiter}prefs${delimiter}bogdandonduk.uilanguagestoolboxlib"
    private const val APP_LANGUAGE_CODE = "app${delimiter}language${delimiter}code$delimiter$LIBRARY_SHARED_PREFS_SUFFIX"

    fun isAppLanguageCodeSettingAutomatic(context: Context) = !getPreferences(context).contains(APP_LANGUAGE_CODE)

    private fun getPreferences(context: Context) =
        context.getSharedPreferences(context.packageName + LIBRARY_SHARED_PREFS_SUFFIX, Context.MODE_PRIVATE)

    fun getAppLanguageCode(context: Context) =
        getPreferences(context).getString(APP_LANGUAGE_CODE, Locale.getDefault().language)!!

    fun getAppLanguageCode(context: Context, uiLanguagesAppManager: UILanguagesAppManager) : String {
        val defaultCode: String

        Locale.getDefault().language.run {
            defaultCode = if(uiLanguagesAppManager.languageCodes.contains(this))
                this
            else
                uiLanguagesAppManager.defaultLanguageCode
        }

        return getPreferences(context).getString(APP_LANGUAGE_CODE, defaultCode)!!
    }


    fun setAppLanguageCode(context: Context, languageCode: String, uiLanguageHandler: UILanguageHandler?) {
        getPreferences(context).edit().putString(APP_LANGUAGE_CODE, languageCode).apply()

        uiLanguageHandler?.initUILanguage()
    }

    fun removeManualSetting(context: Context, uiLanguagesHandler: UILanguageHandler?) {
        getPreferences(context).edit().remove(APP_LANGUAGE_CODE).apply()

        uiLanguagesHandler?.initUILanguage()
    }

    private fun getConfiguredResources(context: Context, languageCode: String) =
        context.createConfigurationContext(Configuration(context.resources.configuration).apply { setLocale(Locale(languageCode)) }).resources

    fun resolveStringResourceManual(context: Context, @StringRes stringResId: Int, languageCode: String) =
        getConfiguredResources(context, languageCode).getString(stringResId)

    fun resolveStringResourceAuto(context: Context, @StringRes stringResId: Int) =
        getConfiguredResources(context, getAppLanguageCode(context)).getString(stringResId)

    fun getUILanguagesFlagsHolderPopularLanguagesInstance(context: Context) = UILanguagesFlagsHolder(
        UILanguagesFlagItem("en", ResourcesCompat.getDrawable(context.resources, R.drawable.ic_flag_en, null)!!),
        UILanguagesFlagItem("de", ResourcesCompat.getDrawable(context.resources, R.drawable.ic_flag_de, null)!!),
        UILanguagesFlagItem("es", ResourcesCompat.getDrawable(context.resources, R.drawable.ic_flag_es, null)!!),
        UILanguagesFlagItem("fr", ResourcesCompat.getDrawable(context.resources, R.drawable.ic_flag_fr, null)!!),
        UILanguagesFlagItem("it", ResourcesCompat.getDrawable(context.resources, R.drawable.ic_flag_it, null)!!),
        UILanguagesFlagItem("ru", ResourcesCompat.getDrawable(context.resources, R.drawable.ic_flag_ru, null)!!),
    )
}