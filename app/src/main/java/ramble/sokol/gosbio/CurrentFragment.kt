package ramble.sokol.gosbio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ramble.sokol.gosbio.databinding.FragmentCurrentBinding
import ramble.sokol.gosbio.databinding.FragmentStorisBinding

class CurrentFragment : Fragment() {

    private var binding: FragmentCurrentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCurrentBinding.inflate(inflater, container, false)
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
    }
}