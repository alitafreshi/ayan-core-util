package ir.tafreshiali.ayan_core_util.helper

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ir.tafreshiali.ayan_core.util.UiText

@Composable
fun UiText.asString(): String = when (this) {

    is UiText.DynamicString -> value

    is UiText.StringResource -> stringResource(id = resId, *args)
}


fun UiText.asString(context: Context): String = when (this) {

    is UiText.DynamicString -> value

    is UiText.StringResource -> context.getString(resId, *args)
}