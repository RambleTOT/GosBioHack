package ramble.sokol.gosbio

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import ramble.sokol.gosbio.databinding.FragmentGosuslugiLoginBinding
import ramble.sokol.gosbio.presentation.fragments.BottomNavigationFragment


class GosuslugiLoginFragment : Fragment() {

    private var binding: FragmentGosuslugiLoginBinding? = null
    private lateinit var webView: WebView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGosuslugiLoginBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        webView = binding!!.webvidew
//
//
//        // enable the javascript to load the url
//        webView.getSettings().setJavaScriptEnabled(true)
//        webView.setWebViewClient(WebViewClient())
//
//
//        // add the url of gif
//        webView.loadUrl("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pinterest.com%2Fpin%2F1089237859860707971%2F&psig=AOvVaw0m9qKCBeVLOa2DrqNNSRzT&ust=1730022245743000&source=images&cd=vfe&opi=89978449&ved=0CBMQjRxqFwoTCODl2Jzhq4kDFQAAAAAdAAAAABAE")

        Handler().postDelayed(Runnable {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            val bottomNavigationFragment = BottomNavigationFragment(MainFragment())
            transaction.replace(ramble.sokol.gosbio.R.id.layout_fragment, bottomNavigationFragment)
            transaction.disallowAddToBackStack()
            transaction.commit()
        }, 2000)
    }


}