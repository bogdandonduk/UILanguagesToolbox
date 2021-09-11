package bogdandonduk.uilanguagestoolboxlib

import android.graphics.drawable.Drawable

class UILanguagesFlagsHolder(vararg flags: UILanguagesFlagItem) {
    var flags = mutableMapOf<String, Drawable>().apply {
        flags.forEach {
            this[it.languageCode] = it.flag
        }
    }

    fun getFlag(languageCode: String) = flags[languageCode]

    @Synchronized
    fun removeFlag(languageCode: String) {
        flags.remove(languageCode)
    }

    @Synchronized
    fun addFlag(flagItem: UILanguagesFlagItem) {
        flags[flagItem.languageCode] = flagItem.flag
    }
}