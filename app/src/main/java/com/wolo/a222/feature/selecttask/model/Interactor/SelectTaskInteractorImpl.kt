package com.wolo.a222.feature.selecttask.model.Interactor

import com.wolo.a222.WoloApp
import com.wolo.a222.feature.common.di.Scope.PerFeature
import com.wolo.a222.feature.common.model.Cards
import javax.inject.Inject

@PerFeature
class SelectTaskInteractorImpl @Inject constructor() : SelectTaskInteractor {

    override fun setChoosedPack(pack: Cards) {
        WoloApp.game.choosedPack = pack
    }
}