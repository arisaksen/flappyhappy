package com.github.arisaksen.simplektx.util.debug

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.github.arisaksen.simplektx.util.isKeyPressed
import com.github.arisaksen.simplektx.util.logger

class DebugCameraController {

    companion object {
        @JvmStatic
        private val log = logger<DebugCameraController>()
    }

    // == properties ==
    private val position = Vector2()
    private val startPosition = Vector2()
    private val config = DebugCameraConfig()

    private var zoom = config.zoomStart
        set(value) {
            field = MathUtils.clamp(value, config.maxZoomIn, config.maxZoomOut)
        }

    // == init ==
    init {
        log.debug("$config")
    }


    // == public functions ==
    fun setStartPosition(x: Float, y: Float) {
        startPosition.set(x, y)
        setPosition(x, y)
    }

    fun applyTo(camera: OrthographicCamera) {
        camera.position.set(position, 0f)
        camera.zoom = zoom
        camera.update()
    }

    fun handleDebugInput() {
        val delta = Gdx.graphics.deltaTime

        val moveSpeed = config.moveSpeed * delta
        val zoomSpeed = config.zoomSpeed * delta

        if(config.enableDebugCamera) {
            when {
                // move controls
                config.isLeftPressed() -> moveLeft(moveSpeed)
                config.isRightPressed() -> moveRight(moveSpeed)
                config.isUpPressed() -> moveUp(moveSpeed)
                config.isDownPressed() -> moveDown(moveSpeed)
                // zoom controls
                config.isZoomInPressed() -> zoomIn(zoomSpeed)
                config.isZoomOutPressed() -> zoomOut(zoomSpeed)
                // reset/log controls
                config.isResetPressed() -> reset()
                config.isLogPressed() -> log.debug("position= $position, zoom= $zoom")
            }
        }
    }

    // == private functions ==
    private fun moveCamera(xSpeed: Float, ySpeed: Float) = setPosition(position.x + xSpeed, position.y + ySpeed)

    private fun setPosition(x: Float, y: Float) = position.set(x, y)
    private fun moveLeft(speed: Float) = moveCamera(-speed, 0f)
    private fun moveRight(speed: Float) = moveCamera(speed, 0f)
    private fun moveUp(speed: Float) = moveCamera(0f, speed)
    private fun moveDown(speed: Float) = moveCamera(0f, -speed)

    private fun zoomIn(zoomSpeed: Float) {
        zoom += zoomSpeed
    }

    private fun zoomOut(zoomSpeed: Float) {
        zoom -= zoomSpeed
    }

    private fun reset() {
        position.set(startPosition)
        zoom = config.zoomStart
    }

}