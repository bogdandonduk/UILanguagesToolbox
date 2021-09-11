package bogdandonduk.uilanguagestoolboxlib

import android.view.View
import android.widget.ImageView

interface UILanguageHandler {
    fun initUILanguage()

    fun setUILanguageToggle(view: View, icon: ImageView, action: ((view: View) -> Unit)? = null) {

    }
}