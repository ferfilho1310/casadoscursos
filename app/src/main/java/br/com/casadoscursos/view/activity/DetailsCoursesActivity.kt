package br.com.casadoscursos.view.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casadoscursos.R
import br.com.casadoscursos.databinding.DetailsCoursesActivityBinding
import br.com.casadoscursos.models.Cursos
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.android.gms.ads.AdRequest

class DetailsCoursesActivity : AppCompatActivity() {

    private val binding by lazy {
        DetailsCoursesActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        val details: Cursos.Curso? = intent.extras?.getParcelable("curso")

        binding.composeviewDetailsCourse.setContent {
            DetailsCourse(curso = details, object : DetailsCourseListener {
                override fun onClickSendPageWeb(urlAfiliate: String) {
                    sendPageWeb(urlAfiliate)
                }

                override fun onClickBack() {
                    finish()
                }

            })
        }
        loadAds()
    }

    @Composable
    private fun DetailsCourse(
        curso: Cursos.Curso?,
        listener: DetailsCourseListener? = null
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.colorStatusBar))

        ) {

            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
            ) {

                Box {
                    AsyncImage(
                        model = ImageRequest
                            .Builder(LocalContext.current)
                            .data(curso?.imageCurso)
                            .build(),
                        contentDescription = "",
                        modifier = Modifier.padding(
                            start = 0.dp,
                            top = 0.dp,
                            end = 0.dp,
                            bottom = 8.dp
                        )
                    )

                    Image(
                        painter = painterResource(id = R.drawable.ic_back),
                        alignment = Alignment.TopStart,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(top = 8.dp, start = 8.dp)
                            .clickable { listener?.onClickBack() }
                            .clip(CircleShape),
                    )
                }
                Text(
                    text = curso?.titleCurso.orEmpty(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier.padding(4.dp)
                )

                if(curso?.descriptionCurso.isNullOrEmpty()) {
                    ExpandableCard(
                        title = curso?.subtitleCurso.orEmpty(),
                        detailsCourse = "Apresentação",
                        stateExpanded = true
                    )
                }

                if(!curso?.descriptionCurso.isNullOrEmpty()){
                    ExpandableCard(
                        title = curso?.subtitleCurso.orEmpty(),
                        detailsCourse = "Apresentação",
                        stateExpanded = false
                    )

                    ExpandableCard(
                        title = curso?.descriptionCurso.orEmpty().replace("\\n", "\n"),
                        detailsCourse = "Conteúdo",
                        stateExpanded = true
                    )
                }


                binding.btComprar.apply {
                    setOnClickListener {
                        listener?.onClickSendPageWeb(curso?.linkCurso.orEmpty())
                    }
                    text = "Comprar ${curso?.precoCurso}"
                }
            }
        }
    }

    @Composable
    fun ExpandableCard(
        title: String,
        detailsCourse: String? = null,
        stateExpanded: Boolean = false
    ) {

        var expanded by remember { mutableStateOf(stateExpanded) }

        Card(
            shape = RoundedCornerShape(8.dp),
            elevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .clickable {
                    expanded = !expanded
                }
                .animateContentSize(
                    spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Column(Modifier.background(Color.White)) {
                if (expanded) {
                    ArrowRotate(angle = 180f, detailsCourse.orEmpty())
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(4.dp)
                            .background(Color.White)
                    )
                } else {
                    ArrowRotate(angle = 0f, detailsCourse.orEmpty())
                }

            }
        }
    }

    @Composable
    private fun ArrowRotate(angle: Float, titleExpanded: String) {
        Row {
            Text(
                text = titleExpanded,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(8.dp)
            )

            Row(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(end = 8.dp, top = 4.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow),
                    contentDescription = "",
                    modifier = Modifier.rotate(angle)
                )
            }
        }
    }

    private fun loadAds() {
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }

    private fun sendPageWeb(urlAfiliate: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(urlAfiliate.trim()))
        startActivity(browserIntent)
    }

    interface DetailsCourseListener {
        fun onClickSendPageWeb(urlAfiliate: String)
        fun onClickBack()
    }

    @Preview(showBackground = true)
    @Composable
    fun Preview() {
        DetailsCourse(
            curso = Cursos.Curso(
                titleCurso = "Teste 1",
                subtitleCurso = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.",
                imageCurso = "https://iili.io/HibZChJ.jpg",
                precoCurso = "R$ 79,90"
            )
        )
    }

}





