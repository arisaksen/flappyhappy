package com.github.arisaksen.simplektx

import com.badlogic.gdx.Application
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.github.arisaksen.simplektx.util.logger


class ObstacleAvoidGame : Game() {

    companion object {
        @JvmStatic
        private val log = logger<ObstacleAvoidGame>()
    }

    override fun create(){
        Gdx.app.logLevel = Application.LOG_DEBUG

        log.debug("Init GameScreen")
        setScreen(GameScreen())
    }


}