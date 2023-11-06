package com.example.laba4

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext


class MainActivity : AppCompatActivity() {
    companion object {
        const val ACTION_CHECK_INDEX = "com.example.laba4.CHECK_INDEX"
        private var camUri: Uri? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_settings)
            .setOnClickListener {
                this.openDisplaySettings()
            }

        findViewById<Button>(R.id.button_maps)
            .setOnClickListener {
                this.openMaps()
            }

        findViewById<Button>(R.id.button_picture)
            .setOnClickListener {
                this.takePicture("temp.jpeg")
            }

        findViewById<Button>(R.id.button_index)
            .setOnClickListener {
                this.checkIndex()
            }
    }

    private fun openDisplaySettings() {
        val intent = Intent(Settings.ACTION_DISPLAY_SETTINGS)
        intent.resolveActivity(packageManager)?.let {
            startActivity(intent)
        }
    }

    private fun openMaps() {
        val uri = Uri.parse("geo:0,0?q=51.109171069277956, 17.060467524964384(PWr - W4N)")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.google.android.apps.maps")
        intent.resolveActivity(packageManager)?.let {
            startActivity(intent)
        }
    }

    private fun takePicture(targetFilename: String) {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera")
        camUri = contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            values
        )

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            putExtra(MediaStore.EXTRA_OUTPUT, camUri);
        }
        intent.resolveActivity(packageManager)?.let {
            getTakePictureResult.launch(intent)
        }
    }

    private val getTakePictureResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val thumbnail: Bitmap? = it?.data?.getParcelableExtra("data")
                findViewById<ImageView>(R.id.image).setImageURI(camUri)
            }
        }

    private fun checkIndex() {
        val intent = Intent("org.jastka4.laba4.CHECK_INDEX")
        intent.resolveActivity(packageManager)?.let {
            startActivity(intent)
        }
    }
}