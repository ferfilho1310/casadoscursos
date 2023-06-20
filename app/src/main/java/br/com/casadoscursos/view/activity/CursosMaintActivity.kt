package br.com.casadoscursos.view.activity

import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import br.com.casadoscursos.R
import br.com.casadoscursos.databinding.CursosMainActivityBinding
import br.com.casadoscursos.view.activity.CursosMaintActivity.PREFERENCE.SKIP_DIALOG_INFORMATION
import br.com.casadoscursos.view.activity.adapter.CursosAdapter
import com.google.android.material.tabs.TabLayoutMediator
import java.lang.StringBuilder

class CursosMaintActivity : AppCompatActivity() {

    private val binding by lazy {
        CursosMainActivityBinding.inflate(layoutInflater)
    }
    private val cursosAdapter by lazy {
        CursosAdapter(supportFragmentManager, lifecycle)
    }
    private lateinit var sharedPreferences: SharedPreferences

    val titles = arrayListOf("Beleza", "Culinária", "Educação", "Bem Estar")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorStatusBar)

        sharedPreferences = getSharedPreferences(SKIP_DIALOG_INFORMATION, Context.MODE_PRIVATE)

        binding.viewPager.adapter = cursosAdapter
        binding.apply {
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = titles[position]
            }.attach()
        }

        if (!sharedPreferences.getBoolean(SKIP_DIALOG_INFORMATION, false)) {
            setDialogInformation()
        }
            requestPermission()
    }

    private fun setDialogInformation() {
        val messageInformation = StringBuilder()
        messageInformation.append("1. A Casa dos cursos é apenas um catálogo e NÃO possui responsabilidade nos pagamentos e qualidade dos produtos.\n")
        messageInformation.append("2. Todos os cursos e e-books estão na Hotmart, uma plataforma segura e confiável.\n")
        messageInformation.append("3. Para acessar o curso adquirido basta efetuar o pagamento e seguir as informações que serão passadas.")

        AlertDialog.Builder(this)
            .setTitle("Informação")
            .setMessage(
                messageInformation
            )
            .setPositiveButton(
                "Entendi"
            ) { dialog, _ ->
                preferencesSkipped()
                dialog?.dismiss()
            }
            .show()
    }

    private fun preferencesSkipped() {
        val result = sharedPreferences.edit()
        result.putBoolean(SKIP_DIALOG_INFORMATION, true)
        result.apply()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 22) {
            if (grantResults.isNotEmpty())
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("TAG", "Permission granted")
                } else {
                    Log.i("TAG", "Permission denied")
                }
        }
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    22
                )
            }
        }
    }

    object PREFERENCE {
        const val SKIP_DIALOG_INFORMATION = "skip_dialog_information"
    }
}
