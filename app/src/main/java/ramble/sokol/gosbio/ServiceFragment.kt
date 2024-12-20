package ramble.sokol.gosbio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ramble.sokol.gosbio.databinding.FragmentMainBinding
import ramble.sokol.gosbio.databinding.FragmentServiceBinding


class ServiceFragment : Fragment() {

    private var binding: FragmentServiceBinding? = null
    private lateinit var dataStorage: DataStorage

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentServiceBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        dataStorage = DataStorage(requireActivity())
        binding!!.imageClick.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            val currentFragment = CurrentFragment()
            transaction.replace(R.id.layout_fragment, currentFragment)
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
        binding!!.imageClick2.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            val currentFragment = CurrentGosFragment()
            transaction.replace(R.id.layout_fragment, currentFragment)
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
        binding!!.buttonNextMasha.setOnClickListener {
            dataStorage.saveData("masha", 3)
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            val currentFragment = CurrentFragment()
            transaction.replace(R.id.layout_fragment, currentFragment)
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
    }

}