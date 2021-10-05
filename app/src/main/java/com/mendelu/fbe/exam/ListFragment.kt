package com.mendelu.fbe.exam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mendelu.fbe.exam.databinding.FragmentListBinding
import com.mendelu.fbe.exam.databinding.RowRideBinding
import com.mendelu.fbe.gettingdone.ListViewModel
import com.mendelu.fbe.termoverview.database.Ride
import cz.mendelu.fbe.mytodo.architecture.BaseFragment
import kotlinx.coroutines.launch

class ListFragment : BaseFragment<FragmentListBinding, ListViewModel>(ListViewModel::class){
    override val bindingInflater: (LayoutInflater) -> FragmentListBinding
        get() = FragmentListBinding::inflate

    private val rideList: MutableList<Ride> = mutableListOf()
    private lateinit var adapter: ExpenseAdapter
    private lateinit var layoutManager: LinearLayoutManager


    inner class ExpenseAdapter : RecyclerView.Adapter<ExpenseAdapter.TaskViewHolder>() {

        inner class TaskViewHolder(val binding: RowRideBinding) : RecyclerView.ViewHolder(binding.root){

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
            return TaskViewHolder(RowRideBinding.inflate(layoutInflater, parent, false))
        }

        override fun getItemCount(): Int {
            return rideList.size
        }

        override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
            val ride = rideList.get(position)
            holder.binding.price.text = "price: "+ ride.price.toString()
            holder.binding.numberOfKilometers.text = "range: "+ride.kilometers.toString()
            holder.binding.start.text = "latitude: "+ride.startOfRideLat.toString() +" longitude: "+ ride.startOfRideLong.toString()
            holder.binding.end.text = "latitude: "+ride.endOfRideLat.toString() +" longitude: "+ ride.endOfRideLong.toString()
            if(ride.kilometers != null)
                if (ride.kilometers!! in 0..100)
                    holder.binding.imageOfRide0100.visibility = View.VISIBLE
                else if (ride.kilometers!! in 100..2000)
                    holder.binding.imageofRide1002000.visibility = View.VISIBLE
                else
                    holder.binding.imageOfRide2000.visibility = View.VISIBLE



            holder.binding.root.setOnClickListener {
                val action = ListFragmentDirections.actionListToAddRide(rideList.get(holder.adapterPosition).id!!)
                findNavController().navigate(action)
            }

//            holder.binding.delete.setOnClickListener{
//                lifecycleScope.launch{
//                    viewModel.delete(rideList.get(holder.adapterPosition).id!!)
//                }
//            }
        }
    }
    
    inner class ExpenseDiffUtils(private val oldList: MutableList<Ride>,
                                 private val newList: MutableList<Ride>) : DiffUtil.Callback() {


        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

    }

    override fun initViews() {
        binding.addRide.setOnClickListener{
            findNavController().navigate(R.id.action_list_to_add_ride)
        }
        adapter = ExpenseAdapter()
        layoutManager = LinearLayoutManager(requireContext())

        binding.listOfRides.layoutManager = layoutManager
        binding.listOfRides.adapter = adapter
        viewModel.getAll().observe(viewLifecycleOwner, Observer {
            var totalPrice: Int = 0
            var totalKms: Int = 0
            var totalRides: Int = 0
            for (ride in it)
            {
                totalRides += 1
                totalPrice += ride.price!!
                totalKms += ride.kilometers!!
            }

            binding.totalPrice.text = "Total price: "+ totalPrice.toString()
            binding.sumOfKilometers.text = "Total km: "+totalKms.toString()
            binding.numberOfRides.text = "Total rides: "+totalRides.toString()
            it?.let {
                val diffCallback = ExpenseDiffUtils(rideList, it)
                val diffResult = DiffUtil.calculateDiff(diffCallback)
                diffResult.dispatchUpdatesTo(adapter)
                rideList.clear()
                rideList.addAll(it)
            }
        })
    }

}