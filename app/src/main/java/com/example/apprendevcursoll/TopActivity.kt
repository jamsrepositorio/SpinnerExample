package com.example.apprendevcursoll

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.apprendevcursoll.databinding.ActivityTopBinding
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.login.LoginManager
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider


class TopActivity : AppCompatActivity() {
    private var _binding: ActivityTopBinding? = null
    private val binding get() = _binding!!
    val callbackManager = CallbackManager.Factory.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityTopBinding.inflate(layoutInflater)
        setContentView(binding.root)
        notification()
        firebaseAnalytics()
        setUp()
        sesion()
    }

    private fun sesion() {
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)
        if (email != null && provider != null) {
            showProfile(email, ProviderType.valueOf(provider))
        }

    }

    private fun setUp() {
        title = "Autentication"

        binding.signUpButton.setOnClickListener {
            if (binding.emailText.text.toString()
                    .isNotEmpty() && binding.passwordText.text.toString().isNotEmpty()
            ) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.emailText.text.toString(),
                    binding.passwordText.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showProfile(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }
        }

        binding.loginButton.setOnClickListener {
            if (binding.emailText.text.toString()
                    .isNotEmpty() && binding.passwordText.text.toString().isNotEmpty()
            ) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    binding.emailText.text.toString(),
                    binding.passwordText.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showProfile(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }
        }

        binding.facebookButton.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))
            LoginManager.getInstance().registerCallback(
                callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(result: LoginResult?) {
                        result?.let {
                            val token = it.accessToken
                            val credential = FacebookAuthProvider.getCredential(token.token)
                            FirebaseAuth.getInstance().signInWithCredential(credential)
                                .addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        showProfile(
                                            it.result?.user?.email ?: "",
                                            ProviderType.FACEBOOK
                                        )
                                    } else {
                                        showAlert()
                                    }
                                }
                        }
                    }

                    override fun onCancel() {}

                    override fun onError(error: FacebookException?) {
                        showAlert()
                    }

                }
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun showProfile(email: String, provider: ProviderType) {
        val profileIntent = Intent(this, ProfileActivityll::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(profileIntent)
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Acepter", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun firebaseAnalytics() {
        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message_notification", "FirebaseAnalytics completo")
        analytics.logEvent("FirstScreen", bundle)
    }

    private fun notification() {
        FirebaseMessaging.getInstance().subscribeToTopic("clase")
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            println("Este es el token del dispositivo: ${task.result}")
        }

        val url = intent.getStringExtra("url")
        url.let {
            println("Ha llegado información en una push: $it")
        }
    }
}