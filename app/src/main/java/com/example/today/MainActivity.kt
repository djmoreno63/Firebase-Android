package com.example.taller02

import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
    }
    private fun setup() {
        title = "Autenticacion"
        singbutton.setOnClickListener {
            if (emaileditText.text.isNotEmpty() && passwordeditText2.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        emaileditText.text.toString(), passwordeditText2.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        inicio(it.result?.user?.email ?: "",ProviderType.BASIC)
                    }else {
                        FuncionAlert()
                    }
                }
            }
        }
        Loginbutton2.setOnClickListener {
            if (emaileditText.text.isNotEmpty() && passwordeditText2.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                        emaileditText.text.toString(),
                        passwordeditText2.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        inicio(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        FuncionAlert()
                    }
                }
            }
        }
    }
    private fun FuncionAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error de autenticación")
        builder.setPositiveButton("Aceptar", null )
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    private fun inicio(email: String, proveedor: ProviderType){
        val inicio = Intent(this, InicioActivity::class.java).apply {
            putExtra("email", email)
            putExtra("proveedor", proveedor.name)
        }
        startActivity(inicio)
    }
}