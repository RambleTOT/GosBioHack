package ramble.sokol.gosbio.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ramble.sokol.gosbio.HelpFragment
import ramble.sokol.gosbio.MainFragment
import ramble.sokol.gosbio.ProfileFragment
import ramble.sokol.gosbio.R
import ramble.sokol.gosbio.ServiceFragment
import ramble.sokol.gosbio.databinding.FragmentBottomNavigationBinding

class BottomNavigationFragment(
    val currentFragment: Fragment
) : Fragment() {

    private var binding: FragmentBottomNavigationBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBottomNavigationBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        replaceFragment(currentFragment)
        binding!!.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId){
                R.id.navbar_main -> replaceFragment(MainFragment())
                R.id.navbar_service -> replaceFragment(ServiceFragment())
                R.id.navbar_help -> replaceFragment(HelpFragment())
                R.id.navbar_profile -> replaceFragment(ProfileFragment())
                else -> {}
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment){

        val fragmentManager = parentFragmentManager
        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.frame_layout, fragment)
        fragmentTransition.commit()

    }
}