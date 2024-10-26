package ramble.sokol.gosbio

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ramble.sokol.gosbio.databinding.FragmentMainBinding
import ramble.sokol.gosbio.databinding.FragmentSplashScreenBinding
import ramble.sokol.gosbio.presentation.fragments.BottomNavigationFragment

class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private var count = 0
    private lateinit var dataStorage: DataStorage

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        dataStorage = DataStorage(requireActivity())
        count = dataStorage.getData("masha")
        if (count == 2){
            binding!!.layoutMasha.visibility = View.GONE
        }

        if(count == 1){
            binding!!.imageMasha.setBackgroundResource(R.drawable.image_help_masha_2)
        }

        if(count == 0){
            binding!!.imageMasha.setBackgroundResource(R.drawable.image_help_masha_1)
        }

        binding!!.buttonVerif1.setOnClickListener {

        }

        binding!!.buttonVerif2.setOnClickListener {

        }

        binding!!.buttonAbout.setOnClickListener {
            openWebsite("https://map.gosuslugi.ru/?layer=co&filter=rbi")
        }

        binding!!.buttonNextMasha.setOnClickListener {
            count += 1
            if (count == 2){
                dataStorage.saveData("masha", 2)
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                val bottomNavigationFragment = BottomNavigationFragment(ServiceFragment())
                transaction.replace(R.id.layout_fragment, bottomNavigationFragment)
                transaction.disallowAddToBackStack()
                transaction.commit()
            }
            if (count == 1){
                binding!!.imageMasha.setBackgroundResource(R.drawable.image_help_masha_2)
                dataStorage.saveData("masha", 1)
            }

        }
    }

    private fun openWebsite(url: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }
        startActivity(intent)
    }


}