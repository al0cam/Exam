package com.mendelu.fbe.exam

import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mendelu.fbe.exam.databinding.FragmentAddRideBinding
import com.mendelu.fbe.gettingdone.AddRideViewModel
import cz.mendelu.fbe.mytodo.architecture.BaseFragment
import kotlinx.coroutines.launch
import java.util.*

class AddRideFragment : BaseFragment<FragmentAddRideBinding, AddRideViewModel>(AddRideViewModel::class) {
    override val bindingInflater: (LayoutInflater) -> FragmentAddRideBinding
        get() = FragmentAddRideBinding::inflate

    private val arguments: AddRideFragmentArgs by navArgs()

    override fun initViews() {
        val id = arguments.id
        viewModel.id = id
        suspend {viewModel.ride = viewModel.findById(id)}

        binding.price.setText(viewModel.ride.price.toString())
        binding.rangeInKm.setText(viewModel.ride.kilometers.toString())
        binding.startLat.setText(viewModel.ride.startOfRideLat.toString())
        binding.StartLong.setText(viewModel.ride.startOfRideLong.toString())
        binding.endLat.setText(viewModel.ride.endOfRideLat.toString())
        binding.endLong.setText(viewModel.ride.endOfRideLong.toString())



        binding.saveRide.setOnClickListener{
            val price = binding.price.text.toString()
            val range = binding.rangeInKm.text.toString()
            val startingLat = binding.startLat.text.toString()
            val startingLong = binding.StartLong.text.toString()
            val endingLat = binding.endLat.text.toString()
            val endingLong = binding.endLong.text.toString()
            if(price.isNotEmpty() && range.isNotEmpty() && startingLat.isNotEmpty()&& startingLong.isNotEmpty()&& endingLat.isNotEmpty()&& endingLong.isNotEmpty())
            {
                viewModel.ride.price = price.toInt()
                viewModel.ride.kilometers = range.toInt()
                viewModel.ride.startOfRideLat = startingLat.toDouble()
                viewModel.ride.startOfRideLong = startingLong.toDouble()
                viewModel.ride.endOfRideLat = endingLat.toDouble()
                viewModel.ride.endOfRideLong = endingLong.toDouble()

                lifecycleScope.launch{
                    val id = viewModel.insert(viewModel.ride)
                }.invokeOnCompletion {
                    findNavController().popBackStack()
                }
            }
            else
            {
                binding.price.setError("must not be empty")
                binding.rangeInKm.setError("must not be empty")
                binding.startLat.setError("must not be empty")
                binding.StartLong.setError("must not be empty")
                binding.endLat.setError("must not be empty")
                binding.endLong.setError("must not be empty")
            }
        }
    }


}