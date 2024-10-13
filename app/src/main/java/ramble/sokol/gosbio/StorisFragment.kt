package ramble.sokol.gosbio

import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ramble.sokol.gosbio.databinding.FragmentSplashScreenBinding
import ramble.sokol.gosbio.databinding.FragmentStorisBinding

class StorisFragment : Fragment() {

    private var binding: FragmentStorisBinding? = null
    private val texts = arrayOf("Биометрия, для тебя, лучший сервис онлайн верификации", "Решай любые задачи быстро и просто", "Повышай уровень верификации и открывай возможности")
    private val texts2 = arrayOf("авторизуя, подтверждай документы и всё онлайн, не выходя из дома", "пройдя все уровни верификации пользуйтесь биометрией для разных услуг", "")
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
        val progressBars by lazy { listOf(binding!!.progressBar1, binding!!.progressBar2, binding!!.progressBar3) }
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
                    for (i in 0..100) {
                        handler.postDelayed({
                            progressBar.progress = i
                            if (i == 100) {
                                // Меняем цвет на белый, когда прогресс завершен
                                progressBar.indeterminateDrawable.setColorFilter(
                                    resources.getColor(android.R.color.white),
                                    PorterDuff.Mode.SRC_IN
                                )
                            }
                        }, (i * 100).toLong())
                    }
                    handler.postDelayed({
                        currentIndex++
                        if (currentIndex < progressBars.size) {
                            updateText()
                            startProgress()
                        }
                    }, 5000)
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
        binding!!.mainText.text = texts[currentIndex]
        binding!!.secondText.text = texts2[currentIndex]
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }


}