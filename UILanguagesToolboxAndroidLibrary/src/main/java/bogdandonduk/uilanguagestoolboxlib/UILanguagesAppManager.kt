package bogdandonduk.uilanguagestoolboxlib

import java.util.*

/**
 * This is a manager of supported languages in app. It is meant to be attached to a live persistable object (e.g: Application, singletons)
 * It holds supported languages set, provides them in a list with various configurations.
*/

class UILanguagesAppManager(var defaultLanguageCode: String, vararg supportedLanguagesCodes: String) {
    companion object {
        fun getDisplayName(languageCode: String, codeOfLanguageToTranslateIn: String, capitalizeFirstLetter: Boolean) =
            StringBuilder().apply {
                val name = Locale.Builder().setLanguage(languageCode).build().getDisplayLanguage(Locale.Builder().setLanguage(codeOfLanguageToTranslateIn).build())

                append(
                    if(capitalizeFirstLetter && name.isNotEmpty()) {
                        name.first().uppercaseChar() + name.substring(1)
                    } else
                        name
                )
            }.toString()
    }

    var languageCodes = mutableListOf<String>().apply {
        add(defaultLanguageCode)

        supportedLanguagesCodes.forEach {
            add(it)
        }
    }

    var languageItems = mutableListOf<UILanguageItem>().apply {
        languageCodes.forEach {
            add(
                UILanguageItem(it)
            )
        }
    }

    fun constrainToDefaultIfUnsupported(languageCode: String) : String {
        var code = languageItems.first().languageCode

        languageItems.forEach {
            if(it.languageCode == languageCode) code = languageCode
        }

        return code
    }

    fun getSupportedLanguagesNames(codeOfLanguageToTranslateIn: String, constrainToDefaultIfUnsupported: Boolean = true, capitalizeFirstLetter: Boolean = true) = mutableListOf<String>().apply {
        val targetCode = if(constrainToDefaultIfUnsupported) constrainToDefaultIfUnsupported(codeOfLanguageToTranslateIn) else codeOfLanguageToTranslateIn

        languageItems.forEach {
            add(it.getDisplayName(targetCode, capitalizeFirstLetter))
        }
    }
}