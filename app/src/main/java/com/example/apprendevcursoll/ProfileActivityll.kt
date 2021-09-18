package com.example.apprendevcursoll

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.apprendevcursoll.databinding.ActivityProfileActivityllBinding
import com.example.apprendevcursoll.databinding.ActivityProfileBinding
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore


enum class ProviderType {
    BASIC,
    FACEBOOK
}

class ProfileActivityll : AppCompatActivity() {
    private var _binding: ActivityProfileActivityllBinding? = null
    private val binding get() = _binding!!
    private val dbFirestore = FirebaseFirestore.getInstance()
    private val dbRealTime =
        FirebaseDatabase.getInstance().getReference(User::class.java.simpleName)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProfileActivityllBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent.extras.let {
            val email = it?.getString("email") ?: ""
            val provider = it?.getString("provider") ?: ""
            val id = it?.getString("id")?:""
            saveCredentials(email, provider)
            setUp(email, provider)


        }

    }

    private fun setUp(email: String, provider: String) {
        title = "Perfil de usuario"
        binding.emailText.text = email
        binding.providerText.text = provider
        binding.signoutButton.setOnClickListener {
            val prefs =
                getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()
            FirebaseAuth.getInstance().signOut()
            if (provider == ProviderType.FACEBOOK.name) {
                LoginManager.getInstance().logOut()

            }
            onBackPressed()
        }
        binding.saveUserButton.setOnClickListener {
            dbRealTime.push().setValue(
                User(
                    email,
                    provider,
                    binding.phoneText.text.toString(),
                    binding.addressText.text.toString()
                )
            )

            /**
             * Firestore
             */
            /*dbFirestore.collection("users").document(email).set(
                    hashMapOf(
                        "provider" to provider,
                        "address" to binding.addressText.text.toString(),
                        "phone" to binding.phoneText.text.toString()
                    )
                )*/

        }
        val users = mutableListOf<User>()
        binding.getUserButton.setOnClickListener {
            dbRealTime.addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        users.clear()
                        snapshot.children.map {
                            val user = it.getValue(User::class.java)
                            user?.id = it.key.toString()
                            users.add(user ?: User())
                        }
                    }
                    Toast.makeText(
                        binding.getUserButton.context,
                        "El total de usuario es: ${users.size}",
                        Toast.LENGTH_SHORT
                    )
                        .show()

                }


                override fun onCancelled(error: DatabaseError) {
                   Toast.makeText(binding.getUserButton.context,error.message, Toast.LENGTH_SHORT)
                       .show()
                }
            })
            /**
             * Realtime Updated
             */
            /*val users: HashMap<String, Any> = HashMap()
            users["email"] = email
            users["provider"] = provider
            users["phone"] = binding.phoneText.text.toString()
            users["address"] = binding.addressText.text.toString()

            dbRealTime.child("-MjCRDpXv37I5iMStZ_8").updateChildren(users).addOnSuccessListener {
                Toast.makeText(this, "Actualización exitosa", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "La actualizacion no fue exitosa", Toast.LENGTH_SHORT)
                    .show()
            }*/

            /**
             * Firestore
             */
            /*dbFirestore.collection("users").document(email).get()
                .addOnSuccessListener {
                    binding.addressText.setText(it.get("address").toString())
                    binding.phoneText.setText(it.get("phone").toString())
                } */

            /*dbFirestore.collection("users").get().addOnSuccessListener {
                    it.documents.map { users ->
                        users.get("address")
                        users.get("phone")
                    }
                }*/

        }
        binding.deleteUserButton.setOnClickListener {
            dbRealTime.child("-MjCRB-mUDZAsJsUTIId").removeValue()
                .addOnSuccessListener {
                    Toast.makeText(this, "El usuario fue removido", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }


            /**
            FIRESTORE
             */
            // dbFirestore.collection("users").document(email).delete()


        }
    }

    private fun saveCredentials(email: String, provider: String) {
        val prefs =
            getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()
    }
}

data class User(

    val email: String? = "",
    val provider: String? = "",
    val phone: String? = "",
    val address: String? = "",
    var id: String? = ""

)