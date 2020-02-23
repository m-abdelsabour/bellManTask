package com.bellman.task.presentation.features.home


import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bellman.task.R
import com.bellman.task.presentation.core.BaseFragment
import com.bellman.task.presentation.core.LocationHelper
import com.bellman.task.presentation.core.showMessage
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment(), LocationHelper.LocationListner {


    private lateinit var viewModel: HomeViewModel
    private lateinit var locationHelper: LocationHelper
    private val REQUEST_COARSE_LOCATION = 3

    private val hotspotsAdapter by lazy {
        HotspotsAdapter()
    }

    private val eventsAdapter by lazy {
        EventsAdapter()
    }

    private val attractionsAdapter by lazy {
        AttractionsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        observeViewModel()
        /*savedInstanceState?.let {
            viewModel.getHomeData()
        }*/

    }

    private fun observeViewModel() {
        viewModel.getHomeData()
        viewModel.getLiveData().observe(this, Observer {
            hotspotsAdapter.addData(it.hot_spots)
            eventsAdapter.addData(it.hot_spots)
            attractionsAdapter.addData(it.attractions)
        })
        viewModel.getError().observe(this, errorObserver)
        viewModel.getLoading().observe(this, loadingObserver)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        locationHelper = LocationHelper(activity, this)
        initializeRecyclerView(view)

        return view
    }

    override fun onStart() {
        super.onStart()
        locationHelper.init()
    }


    private fun initializeRecyclerView(view: View) {
        view.recycleHotsPots.apply {
            adapter = hotspotsAdapter
        }


        //TODO("because there is no data in event i set same as hotspots")
        view.recycleEvents.apply {
            adapter = eventsAdapter
        }


        view.recycleAttractions.apply {
            adapter = attractionsAdapter
        }
    }


    private val errorObserver = Observer<Throwable> {
        showMessage(it)
    }


    private val loadingObserver = Observer<Boolean> {
        if (it)
            showProgressDialog()
        else
            dismissProgressDialg()

    }


    override fun getLocation(location: Location?) {
        if (location != null) {
            locationHelper.stopLocationUpdate(activity)
            textLocation.text=getCountryName(location.latitude,location.longitude)
        }
    }

    override fun sendRequsestPermission() {
        requestPermissions(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ), REQUEST_COARSE_LOCATION
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_COARSE_LOCATION) {
            if (ContextCompat.checkSelfPermission(
                    this!!.activity!!,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                    this!!.activity!!,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                locationHelper.init()
            }
        }
    }

}
