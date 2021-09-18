package com.example.apprendevcursoll.webviewsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.apprendevcursoll.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
    }
    private fun setUpComponent() = with(binding)
    {
        webView.webViewClient= WebViewClient()
        webView.loadUrl("https://www.google.com.mx/")
        webView.settings.javaScriptEnabled = true
    }

    override fun onBackPressed()
    {
        if (binding.webView.canGoBack())
        {
            binding.webView.goBack()
        } else
        {
            super.onBackPressed()
        }
    }
}