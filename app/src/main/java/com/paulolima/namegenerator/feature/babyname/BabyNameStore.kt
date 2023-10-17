package com.paulolima.namegenerator.feature.babyname

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.paulolima.namegenerator.architecture.Store
import com.paulolima.namegenerator.repository.BabyNameRepositoryInterface
import com.paulolima.namegenerator.service.DispatcherServiceInterface
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BabyNameStore @AssistedInject constructor(
    dispatcher: DispatcherServiceInterface,
    private val monopolyRepository: BabyNameRepositoryInterface,
    @Assisted
    private val navController: NavController
) : Store<BabyNameContract.Action, BabyNameContract.State>(
    BabyNameContract.State.Loading,
    dispatcher
) {

    private var observeBabyNamesJob: Job? = null

    override fun onCleared() {
        super.onCleared()
        observeBabyNamesJob?.cancel()
    }

    init {
        setup()
    }

    private fun setup() {
        observeBabyNamesJob?.cancel()
        observeBabyNamesJob = viewModelScope.launch(Dispatchers.IO) {
            val babyList = monopolyRepository.getDatabase()
            if (babyList.isEmpty()) return@launch

            val stateLoaded = BabyNameContract.State.Loaded(
                babyNameList = babyList
            )

            updateState { oldState ->
                oldState.ifLoaded()?.copy(babyNameList = babyList) ?: stateLoaded
            }
        }
    }

    override suspend fun processAction(action: BabyNameContract.Action) {
        when (action) {
            BabyNameContract.Action.DismissErrorDialog -> {} // TODO:
            BabyNameContract.Action.SelectMale -> handleSelectMale()
            BabyNameContract.Action.SelectFemale -> handleSelectFemale()
        }
    }

    private fun handleSelectMale() {
        val maleList = state.value.ifLoaded()?.babyNameList?.filter { it.gender == "MALE" }

        updateState { oldState -> oldState.ifLoaded()?.copy(selectedName = maleList?.random()?.name) ?: oldState }
    }

    private fun handleSelectFemale() {
        val maleList = state.value.ifLoaded()?.babyNameList?.filter { it.gender == "FEMALE" }

        updateState { oldState -> oldState.ifLoaded()?.copy(selectedName = maleList?.random()?.name) ?: oldState }
    }

    private fun BabyNameContract.State.ifLoaded(): BabyNameContract.State.Loaded? =
        this as? BabyNameContract.State.Loaded
}