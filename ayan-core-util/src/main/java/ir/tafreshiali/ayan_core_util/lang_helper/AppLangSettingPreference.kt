package ir.tafreshiali.ayan_core_util.lang_helper

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.edit
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class AppLangSettingPreference
@Inject
constructor(
    private val context: Context,
    private val externalScope: CoroutineScope
) : AppLangSetting {

    override val langState: MutableState<AppLangState>
    override var appLang: AppLangState by AppLangPreferenceDelegate("app_lang", AppLangState.NotSet)


    private val preferences: SharedPreferences =
        context.getSharedPreferences("app_lang", Context.MODE_PRIVATE)

    init {
        langState = mutableStateOf(appLang)
    }

    inner class AppLangPreferenceDelegate(
        private val name: String,
        private val default: AppLangState
    ) : ReadWriteProperty<Any?, AppLangState> {
        override fun getValue(thisRef: Any?, property: KProperty<*>): AppLangState =
            AppLangState.fromOrdinal(preferences.getInt(name, default.ordinal))


        override fun setValue(thisRef: Any?, property: KProperty<*>, value: AppLangState) {
            langState.value = value
            preferences.edit {
                putInt(name, value.ordinal)
            }
        }
    }

}













