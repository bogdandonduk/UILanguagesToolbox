package bogdandonduk.uilanguagestoolboxlib

import java.util.*

class UILanguageItem(var languageCode: String) {
    var locale = Locale.Builder().setLanguage(languageCode).build()

    fun getDisplayName(codeOfLanguageToTranslateIn: String, capitalizeFirstLetter: Boolean) =
        StringBuilder().apply {
            val name = locale.getDisplayLanguage(Locale.Builder().setLanguage(codeOfLanguageToTranslateIn).build())

            append(
                if(capitalizeFirstLetter && name.isNotEmpty()) {
                    name.first().uppercaseChar() + name.substring(1)
                } else
                    name
            )
        }.toString()
}