package ir.tafreshiali.ayan_core_util.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

/**
 * GOAL = TO HAVE GENERIC PATTERN THAT CONTAINS ALL SCENARIOS
 * @param [ViewState] [Events] [AppEvents] [UiComponent] these are generic unique types for each screen
 * also all the related states for the whole app handles here (like canceling requests / retrying requests etc) */

abstract class BaseViewModel<ViewState, Events, AppEvents, UiComponent> :
    ViewModel() {
    private val _viewState: MutableState<ViewState> = mutableStateOf(this.initNewViewState())

    private val _events = MutableSharedFlow<AppEvents>()
    private val events = _events.asSharedFlow()

    fun getCurrentViewStateOrNew(): ViewState = _viewState.value ?: initNewViewState()

    fun setViewState(viewState: ViewState) {
        _viewState.value = viewState
    }

    fun observeAppEvents(): SharedFlow<AppEvents> = events

    fun emitAppEvent(event: AppEvents) {
        handleSuspendEvent { _events.emit(value = event) }
    }

    fun handleSuspendEvent(suspendBlock: (suspend () -> Unit)) {
        viewModelScope.launch {
            suspendBlock()
        }
    }

    fun openUrl(context: Context, url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        context.startActivity(browserIntent)
    }

    fun shareApp(
        context: Context,
        content: String = "برای دانلود اپلیکیشن میتوانید از لینک زیر استفاده کنید:\n http://cafebazaar.ir/app/?id=ir.ayantech.justicesharesinquiry"
    ) {
        val shareIntent = Intent().apply {
            this.action = Intent.ACTION_SEND
            this.type = "text/plain"
            this.putExtra(Intent.EXTRA_TEXT, content)
        }
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        context.startActivity(shareIntent)

    }

    abstract fun onTriggerEvent(event: Events)

    abstract fun initNewViewState(): ViewState
}
