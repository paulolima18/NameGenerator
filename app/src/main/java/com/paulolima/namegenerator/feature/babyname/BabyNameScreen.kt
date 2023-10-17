package com.paulolima.namegenerator.feature.babyname

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.paulolima.namegenerator.architecture.Store
import com.paulolima.namegenerator.architecture.store
import com.paulolima.namegenerator.ui.components.LoadingBar

typealias SendActionCallback = (BabyNameContract.Action) -> Unit

@Composable
fun BabyNameScreen(
    store: Store<BabyNameContract.Action, BabyNameContract.State> = store(),
) {
    val state = store.state.collectAsState().value

    val sendAction: SendActionCallback = { action: BabyNameContract.Action -> store.send(action) }

    if (state is BabyNameContract.State.Loaded) {
        Content(
            state = state,
            sendAction = sendAction
        )
    } else {
        LoadingBar()
    }
}

@Composable
fun Content(
    state: BabyNameContract.State.Loaded,
    sendAction: SendActionCallback
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

            Button(onClick = { sendAction(BabyNameContract.Action.SelectFemale) }) {
                Text(text = "Female")
            }

            Button(onClick = { sendAction(BabyNameContract.Action.SelectMale) }) {
                Text(text = "Male")
            }
        }

        if (state.babyNameList.isNotEmpty()) {
            Text(text = "Baby Name List Loaded")
        }

        state.selectedName?.let { name ->
            Text(text = name)
        }
    }
}