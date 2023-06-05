package br.com.casadoscursos.view.fragments.cursonavigatebottomsheet

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.casadoscursos.databinding.CursoInformationNavigateBottomSheetFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CursoInformationNavigateBottomSheet(var url: String) : BottomSheetDialogFragment() {

    private var _binding: CursoInformationNavigateBottomSheetFragmentBinding? = null
    private val binding get() = _binding!!

    var isDismiss = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CursoInformationNavigateBottomSheetFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCountDown()
        setButtonClose()
    }

    private fun setCountDown() {
        val timer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.tvCountDown.text = "${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                if (!isDismiss) {
                    sendPageWeb(url)
                    dismiss()
                }
            }
        }
        timer.start()
    }


    private fun sendPageWeb(urlAfiliate: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(urlAfiliate.trim()))
        startActivity(browserIntent)
    }

    private fun setButtonClose() {
        binding.imgClose.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        isDismiss = true
    }
}