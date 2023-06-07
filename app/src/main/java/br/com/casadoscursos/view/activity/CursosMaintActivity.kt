package br.com.casadoscursos.view.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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

    object PREFERENCE {
        const val SKIP_DIALOG_INFORMATION = "skip_dialog_information"
    }
}
