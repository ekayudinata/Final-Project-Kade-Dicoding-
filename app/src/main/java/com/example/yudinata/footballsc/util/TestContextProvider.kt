package com.example.yudinata.footballsc.util

import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

class TestContextProvider:CoroutineContextProvider() {
    override val main: CoroutineContext = Unconfined
}