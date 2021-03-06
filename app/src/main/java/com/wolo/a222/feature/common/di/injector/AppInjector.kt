package com.wolo.a222.feature.common.di.injector

import android.content.Context
import com.wolo.a222.feature.common.di.component.AppComponent
import com.wolo.a222.feature.common.di.component.DaggerAppComponent
import com.wolo.a222.feature.common.di.module.AppModule
import com.wolo.a222.feature.deleteplayer.di.component.DeletePlayerFeatureComponent
import com.wolo.a222.feature.deleteplayer.di.component.DeletePlayerScreenComponent
import com.wolo.a222.feature.gamezone.di.component.GameZoneFeatureComponent
import com.wolo.a222.feature.gamezone.di.component.GameZoneScreenComponent
import com.wolo.a222.feature.rules.di.component.RulesFeatureComponent
import com.wolo.a222.feature.rules.di.component.RulesScreenComponent
import com.wolo.a222.feature.selecttask.di.component.SelectTaskFeatureComponent
import com.wolo.a222.feature.selecttask.di.component.SelectTaskScreenComponent
import com.wolo.a222.feature.shop.di.component.ShopFeatureComponent
import com.wolo.a222.feature.shop.di.component.ShopScreenComponent
import com.wolo.a222.feature.splashscreen.di.component.SplashScreenFeatureComponent
import com.wolo.a222.feature.task.di.component.TaskFeatureComponent
import com.wolo.a222.feature.task.di.component.TaskScreenComponent
import ru.ireca.kitchen.feature.auth.di.component.AuthFeatureComponent
import ru.ireca.kitchen.feature.auth.di.component.AuthScreenComponent
import com.wolo.a222.feature.splashscreen.di.component.SplashScreenScreenComponent

class AppInjector(context: Context): Injector {

    private val appComponent: AppComponent = DaggerAppComponent.builder()
            .appModule(AppModule(context))
            .build()


    override fun getAppComponent() = appComponent


    private var authFeatureComponent: AuthFeatureComponent? = null
    private var authScreenComponent: AuthScreenComponent? = null
    private var splashScreenFeatureComponent: SplashScreenFeatureComponent? = null
    private var splashScreenScreenComponent: SplashScreenScreenComponent? = null
    private var gameZoneFeatureComponent: GameZoneFeatureComponent? = null
    private var gameZoneScreenComponent: GameZoneScreenComponent? = null
    private var selectTaskFeatureComponent: SelectTaskFeatureComponent? = null
    private var selectTaskScreenComponent: SelectTaskScreenComponent? = null
    private var taskFeatureComponent: TaskFeatureComponent? = null
    private var taskScreenComponent: TaskScreenComponent? = null
    private var shopFeatureComponent: ShopFeatureComponent? = null
    private var shopScreenComponent: ShopScreenComponent? = null
    private var rulesFeatureComponent: RulesFeatureComponent? = null
    private var rulesScreenComponent: RulesScreenComponent? = null
    private var deletePlayerFeatureComponent: DeletePlayerFeatureComponent? = null
    private var deletePlayerScreenComponent: DeletePlayerScreenComponent? = null


    override fun getAuthScreen(): AuthScreenComponent {
        return authScreenComponent ?: let {
            getAuthFeature().plusAuthScreen()
                    .also { newComponent -> authScreenComponent = newComponent }
        }
    }

    override fun releaseAuthScreen() {
        authScreenComponent = null
    }

    override fun getAuthFeature(): AuthFeatureComponent {
        return authFeatureComponent ?: let {
            getAppComponent().plusAuthFeature()
                    .also { newComponent -> authFeatureComponent = newComponent }
        }
    }

    override fun releaseAuthFeature() {
        authFeatureComponent = null
    }

    override fun getSplashScreenFeature(): SplashScreenFeatureComponent {
        return splashScreenFeatureComponent ?: let {
            getAppComponent().plusSplashScreenFeature()
                    .also { newComponent -> splashScreenFeatureComponent = newComponent }
        }
    }

    override fun releaseSplashScreenFeature() {
        splashScreenFeatureComponent = null
    }

    override fun getSplashScreenScreen(): SplashScreenScreenComponent {
        return splashScreenScreenComponent ?: let {
            getSplashScreenFeature().plusSplashScreenScreen()
                    .also { newComponent -> splashScreenScreenComponent = newComponent }
        }
    }

    override fun releaseSplashScreenScreen() {
        splashScreenScreenComponent = null
    }

    override fun getGameZoneFeature(): GameZoneFeatureComponent {
        return gameZoneFeatureComponent ?: let {
            getAppComponent().plusGameZoneFeature()
                    .also { newComponent -> gameZoneFeatureComponent = newComponent }
        }
    }

    override fun releaseGameZoneFeature() {
        gameZoneFeatureComponent = null
    }

    override fun getGameZoneScreen(): GameZoneScreenComponent {
        return gameZoneScreenComponent ?: let {
            getGameZoneFeature().plusGameZoneScreen()
                    .also { newComponent -> gameZoneScreenComponent = newComponent }
        }
    }

    override fun releaseGameZoneScreen() {
        gameZoneScreenComponent = null
    }

    override fun getSelectTaskFeature(): SelectTaskFeatureComponent {
        return selectTaskFeatureComponent ?: let {
            getAppComponent().plusSelectTaskFeature()
                    .also { newComponent -> selectTaskFeatureComponent = newComponent }
        }
    }

    override fun releaseSelectTaskFeature() {
        selectTaskFeatureComponent = null
    }

    override fun getSelectTaskScreen(): SelectTaskScreenComponent {
        return selectTaskScreenComponent ?: let {
            getSelectTaskFeature().plusSelectTaskScreen()
                    .also { newComponent -> selectTaskScreenComponent = newComponent }
        }
    }

    override fun releaseSelectTaskScreen() {
        selectTaskScreenComponent = null
    }

    override fun getTaskFeature(): TaskFeatureComponent {
        return taskFeatureComponent ?: let {
            getAppComponent().plusTaskFeature()
                    .also { newComponent -> taskFeatureComponent = newComponent }
        }
    }

    override fun releaseTaskFeature() {
        taskFeatureComponent = null
    }

    override fun getTaskScreen(): TaskScreenComponent {
        return taskScreenComponent ?: let {
            getTaskFeature().plusTaskScreen()
                    .also { newComponent -> taskScreenComponent = newComponent }
        }
    }

    override fun releaseTaskScreen() {
        taskScreenComponent = null
    }


    override fun getShopFeature(): ShopFeatureComponent {
        return shopFeatureComponent ?: let {
            getAppComponent().plusShopFeature()
                    .also { newComponent -> shopFeatureComponent = newComponent }
        }
    }

    override fun releaseShopFeature() {
        shopFeatureComponent = null
    }


    override fun getShopScreen(): ShopScreenComponent {
        return shopScreenComponent ?: let {
            getShopFeature().plusShopScreen()
                    .also { newComponent -> shopScreenComponent = newComponent }
        }
    }

    override fun releaseShopScreen() {
        shopScreenComponent = null
    }


    override fun getRulesFeature(): RulesFeatureComponent {
        return rulesFeatureComponent ?: let {
            getAppComponent().plusRulesFeature()
                .also { newComponent -> rulesFeatureComponent = newComponent }
        }
    }

    override fun releaseRulesFeature() {
        rulesFeatureComponent = null
    }

    override fun getRulesScreen(): RulesScreenComponent {
       return rulesScreenComponent ?: let {
           getRulesFeature().plusRulesScreen()
               .also { newComponent -> rulesScreenComponent = newComponent }
       }
    }

    override fun releaseRulesScreen() {
      rulesScreenComponent = null
    }

    override fun getDeletePlayerFeature(): DeletePlayerFeatureComponent {
        return deletePlayerFeatureComponent ?: let {
            getAppComponent().plusDeletePlayerFeature()
                .also { newComponent -> deletePlayerFeatureComponent = newComponent }
        }
    }

    override fun releaseDeletePlayerFeature() {
        deletePlayerFeatureComponent = null
    }

    override fun getDeletePlayerScreen(): DeletePlayerScreenComponent {
        return deletePlayerScreenComponent ?: let {
            getDeletePlayerFeature().plusDeletePlayerScreen()
                .also { newComponent -> deletePlayerScreenComponent = newComponent }
        }
    }

    override fun releaseDeletePlayerScreen() {
        deletePlayerScreenComponent = null
    }



}