package io.github.kenneycode.fusion.input

import android.graphics.Bitmap

import io.github.kenneycode.fusion.process.RenderPipeline
import io.github.kenneycode.fusion.texture.Texture
import io.github.kenneycode.fusion.texture.TexturePool
import java.nio.ByteBuffer

/**
 *
 * Coded by kenney
 *
 * http://www.github.com/kenneycode/fusion
 *
 * 图片输入源
 *
 */

class FusionImage(private val image: Bitmap) : RenderPipeline.Input, InputReceiver {

    override fun onInputReady(input: Texture, data: MutableMap<String, Any>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var inputReceiver: InputReceiver
    private lateinit var imageTexture: Texture

    override fun setInputReceiver(inputReceiver: InputReceiver) {
        this.inputReceiver = inputReceiver
    }

    override fun onInit() {
        val buffer = ByteBuffer.allocate(image.width * image.height * 4)
        image.copyPixelsToBuffer(buffer)
        buffer.position(0)
        imageTexture = TexturePool.obtainTexture(image.width, image.height).apply {
            retain = true
            setData(buffer)
        }
    }

    override fun onUpdate(data: MutableMap<String, Any>) {
    }

    override fun start() {
        inputReceiver.onInputReady(imageTexture)
    }

    override fun onRelease() {
        imageTexture.decreaseRef()
    }

}