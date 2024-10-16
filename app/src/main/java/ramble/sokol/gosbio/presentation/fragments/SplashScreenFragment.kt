package ramble.sokol.gosbio.presentation.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import ramble.sokol.gosbio.R
import ramble.sokol.gosbio.StorisFragment
import ramble.sokol.gosbio.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment() {

    private var binding: FragmentSplashScreenBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fadeInAnimation = AnimationUtils.loadAnimation(requireActivity(), R.anim.splash_screen_animation)
        binding!!.linearSplash.startAnimation(fadeInAnimation)
        Handler().postDelayed(Runnable {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            val storisFragment = StorisFragment()
            transaction.replace(R.id.layout_fragment, storisFragment)
            transaction.disallowAddToBackStack()
            transaction.commit()
        }, 3000)
    }

}