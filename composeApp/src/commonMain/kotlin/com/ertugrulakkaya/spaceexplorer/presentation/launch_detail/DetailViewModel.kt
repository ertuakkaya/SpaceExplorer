package com.ertugrulakkaya.spaceexplorer.presentation.launch_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.ertugrulakkaya.spaceexplorer.presentation.navigation.Screen
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class DetailViewModel(
  private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val detailArgs = savedStateHandle.toRoute<Screen.Detail>()
    private val _uiState = MutableStateFlow(DetailUiState(
        missionName = detailArgs.missionName,
        launchDate = detailArgs.launchDate,
        rocketName = detailArgs.rocketName,
        missionDescription = detailArgs.missionDescription,
        launchSuccess = detailArgs.launchSuccess,
        badgePhotoUrl = detailArgs.badgePhotoUrl,
        articleUrl = detailArgs.articleUrl,
        wikiUrl = detailArgs.wikiUrl,
        webCastUrl = detailArgs.webCastUrl,
        rocketDescription = detailArgs.rocketDescription
    ))
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()


    private val _effect = Channel<DetailEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: DetailEvent) {
        when (event) {
            DetailEvent.OnBackClick -> {
                sendEffect(DetailEffect.NavigateBack)
            }

           is DetailEvent.OnReadArticleClick -> {
                if (event.url.isNotEmpty()){
                    sendEffect(DetailEffect.OpenArticleUrl(event.url))
                }
            }
           is  DetailEvent.OnWatchVideoClick -> {
               if (event.url.isNotEmpty()){
                   sendEffect(DetailEffect.OpenVideoUrl(event.url))
               }
            }
           is DetailEvent.OnWikiClick -> {
               if (event.url.isNotEmpty()){
                   sendEffect(DetailEffect.OpenWikiUrl(event.url))
               }
            }
        }
    }

    private fun sendEffect(effect: DetailEffect) {
        viewModelScope.launch {
            _effect.send(effect)
        }
    }
}