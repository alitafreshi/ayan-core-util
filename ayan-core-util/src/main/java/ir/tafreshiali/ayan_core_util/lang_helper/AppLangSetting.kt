package ir.tafreshiali.ayan_core_util.lang_helper

import androidx.compose.runtime.MutableState

enum class AppLangState {
    NotSet,
    Persian,
    English;

    companion object {
        fun fromOrdinal(ordinal: Int) = values()[ordinal]
    }
}

interface AppLangSetting {
    val langState: MutableState<AppLangState>
    var appLang: AppLangState
}




