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

class CursosMaintActivity : AppCompatActivity() {

    private val binding by lazy {
        CursosMainActivityBinding.inflate(layoutInflater)
    }
    private val cursosAdapter by lazy {
        CursosAdapter(supportFragmentManager, lifecycle)
    }
    private lateinit var sharedPreferences: SharedPreferences

    val titles = arrayListOf("Educação", "Culinária", "Beleza", "Bem Estar")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        val  window = this.window;
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorStatusBar)

        sharedPreferences = getSharedPreferences(SKIP_DIALOG_INFORMATION, Context.MODE_PRIVATE)

        binding.viewPager.adapter = cursosAdapter
        binding.apply {
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = titles[position]
            }.attach()
        }

        if(!sharedPreferences.getBoolean(SKIP_DIALOG_INFORMATION,false)) {
            setDialogInformation()
        }
    }

    private fun setDialogInformation() {
        AlertDialog.Builder(this)
            .setTitle("Informação")
            .setMessage(
                "Os Cursos da 'Casa dos cursos' são todos de responsabilidade do seus criadores. " +
                        "O app é apenas uma vitrine para venda dos cursos. Sendo assim, a responsabilidade de qualidade " +
                        "e pagamentos fica a cargo do criador e da plataforma onde o curso está hospedado."
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
