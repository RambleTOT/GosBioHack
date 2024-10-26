package ramble.sokol.gosbio

import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import ramble.sokol.gosbio.databinding.FragmentSplashScreenBinding
import ramble.sokol.gosbio.databinding.FragmentStorisBinding

class StorisFragment : Fragment() {

    private var binding: FragmentStorisBinding? = null
    private val images = arrayOf(R.drawable.image_screen_1, R.drawable.image_screen_2, R.drawable.image_screen_3, R.drawable.image_screen_4)
    private var currentIndex = 0
    private val handler = Handler()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStorisBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    private fun startProgress() {
        val progressBars by lazy { listOf(binding!!.progressBar1, binding!!.progressBar2, binding!!.progressBar3, binding!!.progressBar4) }
        updateText()
        progressBars.forEach {
            it.progress = 0
            it.indeterminateDrawable.setColorFilter(
                resources.getColor(android.R.color.darker_gray),
                PorterDuff.Mode.SRC_IN
            )
        }

        val runnable = object : Runnable {
            override fun run() {
                if (currentIndex < progressBars.size) {
                    val progressBar = progressBars[currentIndex]
                    for (i in 0..1000) {
                        handler.postDelayed({
                            progressBar.progress = i
                            if (i == 400) {
                                // Меняем цвет на белый, когда прогресс завершен
                                progressBar.indeterminateDrawable.setColorFilter(
                                    resources.getColor(android.R.color.white),
                                    PorterDuff.Mode.SRC_IN
                                )
                            }
                        }, (i * 40).toLong())
                    }
                    handler.postDelayed({
                        currentIndex++
                        if (currentIndex < progressBars.size) {
                            updateText()
                            startProgress()
                        }
                        if (currentIndex == 4){
                            val transaction = requireActivity().supportFragmentManager.beginTransaction()
                            val gosuslugiLoginBinding = GosuslugiLoginFragment()
                            transaction.replace(R.id.layout_fragment, gosuslugiLoginBinding)
                            transaction.disallowAddToBackStack()
                            transaction.commit()
                        }
                    }, 4000)
                }
            }
        }
        handler.post(runnable)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        startProgress()
    }

    private fun init(){
        binding!!.buttonLogin.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            val gosuslugiLoginBinding = GosuslugiLoginFragment()
            transaction.replace(R.id.layout_fragment, gosuslugiLoginBinding)
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
    }

    private fun updateText() {
        Log.d("MyLog", images[currentIndex].toString())
        binding!!.imageStory.setBackgroundResource(images[currentIndex])
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }


}