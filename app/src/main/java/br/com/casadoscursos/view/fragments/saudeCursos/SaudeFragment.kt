package br.com.casadoscursos.view.fragments.saudeCursos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import br.com.casadoscursos.helpers.Response
import br.com.casadoscursos.models.Cursos
import br.com.casadoscursos.view.activity.DetailsCoursesActivity
import br.com.casadoscursos.viewModels.searchcourses.SearchCoursesViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import org.koin.androidx.viewmodel.ext.android.viewModel

class SaudeFragment : Fragment() {

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
        viewmodel.searchCoursesBemEstar(context, PARAMENTRO_SAUDE)
    }

    @Composable
    fun SearchCourses(
        viewModel: SearchCoursesViewModel
    ) {

        val curso = viewModel.remoteconfigBemEstar.observeAsState()

        curso.value?.let {
            when (it) {
                is Response.LOADING -> {
                    ProgressBar(isVisible = true)
                    CursosItem(listCourses = arrayListOf())
                }

                is Response.SUCCESS -> {
                    ProgressBar(isVisible = false)
                    if (it.data != null) {
                        CursosItem(
                            listCourses = it.data,
                            isVisible = true,
                            listener = ::navigateDetailsCourses
                        )
                    }

                }

                is Response.ERROR -> {

                }
            }

        }
    }

    @Composable
    private fun ProgressBar(isVisible: Boolean) {
        if (isVisible) {
            val progressValue = 0.75f
            val infiniteTransition = rememberInfiniteTransition()

            val progressAnimationValue by infiniteTransition.animateFloat(
                initialValue = 0.0f,
                targetValue = progressValue,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        900
                    )
                )
            )

            CircularProgressIndicator(
                progress = progressAnimationValue,
                color = colorResource(id = R.color.purple_200),
                modifier = Modifier.padding(180.dp)
            )
        }
    }

    private fun navigateDetailsCourses(curso: Cursos.Curso) {
        val intent = Intent(
            requireContext(),
            DetailsCoursesActivity::class.java
        )
        intent.putExtra("curso", curso)
        startActivity(intent)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun CursosItem(
        listCourses: ArrayList<Cursos.Curso>,
        listener: ((Cursos.Curso) -> Unit)? = null,
        isVisible: Boolean = false
    ) {
        if (isVisible) {
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
                            listener?.invoke(curso)
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
        const val PARAMENTRO_SAUDE = "saude"

        fun newInstance(): SaudeFragment {
            return SaudeFragment()
        }
    }
}