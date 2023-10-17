package com.paulolima.namegenerator.feature.babyname

import androidx.annotation.StringRes
import com.paulolima.namegenerator.domain.model.BabyNameItem

object BabyNameContract {
    sealed class State {

        object Loading : State()

        data class Loaded(
            val babyNameList: List<BabyNameItem> = emptyList(),
            val selectedName: String? = null,
            val errorDialog: ErrorDialog? = null
        ) : State()
    }

    sealed class Action {
        object SelectFemale : Action()
        object SelectMale : Action()
        object DismissErrorDialog : Action()
    }

    data class ErrorDialog(
        @StringRes
        val title: Int,
        @StringRes
        val content: Int
    )
}