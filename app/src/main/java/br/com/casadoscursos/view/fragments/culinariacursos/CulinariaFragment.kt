package br.com.casadoscursos.view.fragments.culinariacursos

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import br.com.casadoscursos.R
import br.com.casadoscursos.models.Cursos
import br.com.casadoscursos.viewModels.searchcourses.SearchCoursesViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import org.koin.androidx.viewmodel.ext.android.viewModel

class CulinariaFragment : Fragment() {

    private val viewmodel: SearchCoursesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.curso_fragment, container, false).apply {
            findViewById<ComposeView>(R.id.composeView).setContent {
                SearchCourses(viewModel = viewmodel)
            }

            val adRequest = AdRequest.Builder().build()
            findViewById<AdView>(R.id.adView).loadAd(adRequest)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.searchCoursesCulinaria(context, PARAMENTRO_CULINARIA)
    }

    @Composable
    fun SearchCourses(
        viewModel: SearchCoursesViewModel
    ) {

        val curso = viewModel.searchCoursesCulinaria.observeAsState()

        curso.value?.data.let {
            if (it != null) {
                CursosItem(listCourses = it) { urlAffiliate ->
                    sendPageWeb(urlAffiliate)
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun CursosItem(
        listCourses: ArrayList<Cursos.Curso>,
        listener: ((String) -> Unit)? = null
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.backgroundrecycler))
                .padding(10.dp)
        ) {
            items(items = listCourses) { curso ->

                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .background(colorResource(R.color.backgroundrecycler)),
                    onClick = {
                        listener?.invoke(curso.linkCurso.orEmpty())
                    }
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(curso.imageCurso)
                            .build(),
                        contentDescription = ""
                    )
                    Text(
                        text = curso.titleCurso.orEmpty(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        modifier = Modifier.padding(4.dp)
                    )
                    Text(
                        text = curso.precoCurso.orEmpty(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        }
    }

    private fun sendPageWeb(urlAfiliate: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(urlAfiliate.trim()))
        startActivity(browserIntent)
    }

    @Preview(showBackground = true)
    @Composable
    fun preview() {
        CursosItem(
            listCourses = arrayListOf(
                Cursos.Curso(
                    titleCurso = "Teste 1",
                    subtitleCurso = "Testando o card 1"
                ),
                Cursos.Curso(
                    titleCurso = "Teste 1",
                    subtitleCurso = "Testando o card 1"
                )
            )
        )
    }

    companion object {
        const val PARAMENTRO_CULINARIA = "culinaria"

        fun newInstance(): CulinariaFragment {
            return CulinariaFragment()
        }
    }

}