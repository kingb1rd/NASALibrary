package com.infoechebo.nasalibrary.common

sealed class UIEvent {
    data class ShowError(val message: String) : UIEvent()
}