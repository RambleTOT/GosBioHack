package ramble.sokol.gosbio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ramble.sokol.gosbio.databinding.FragmentCurrentBinding
import ramble.sokol.gosbio.databinding.FragmentCurrentGosBinding
import ramble.sokol.gosbio.presentation.fragments.BottomNavigationFragment


/**
 * A simple [Fragment] subclass.
 * Use the [CurrentGosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CurrentGosFragment : Fragment() {

    private var binding: FragmentCurrentGosBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCurrentGosBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.buttonLogin.setOnClickListener{
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            val verifFragment = VerifFragment()
            transaction.replace(R.id.layout_fragment, verifFragment)
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
        binding!!.buttonBack.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            val bottomNavigationFragment = BottomNavigationFragment(MainFragment())
            transaction.replace(R.id.layout_fragment, bottomNavigationFragment)
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
    }


}