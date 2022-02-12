package com.github.arisaksen.simplektx.util

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle

/**
 * @author goran on 27/10/2017.
 */
fun String.toInternalFile() : FileHandle = Gdx.files.internal(this)