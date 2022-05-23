package com.example.mymaster.presentations.servicesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Domain.models.ServicesModel
import com.example.mymaster.databinding.FragmentServicesListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentServicesList : Fragment() {
    private var items: ArrayList<ServicesModel> = ArrayList()
    private var adapter: RecyclerView.Adapter<*> = ServicesAdapter(items)
    private val services: ArrayList<ServicesModel> = ArrayList()

    private val vm by viewModel<ServiceListActivityViewModel>()


    private var _binding: FragmentServicesListBinding? = null
    private val binding get() = _binding!!

    companion object {
        val names: MutableList<EditText> = ArrayList()
        val times: MutableList<EditText> = ArrayList()
        val prices: MutableList<EditText> = ArrayList()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentServicesListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.serList
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        val save = binding.serBtnSave

        val fab = binding.fab

        fab.setOnClickListener(View.OnClickListener {
            if (items.size > 14) return@OnClickListener
            items.add(ServicesModel())
            adapter.notifyItemInserted(items.size - 1)
        })

        vm.resultLive.observe(this, {
            items.clear()
            adapter.notifyDataSetChanged()
            it.forEach { i ->
                items.add(i)
            }
            adapter.notifyItemInserted(items.size - 1)
        })

        vm.load()

        save.setOnClickListener(View.OnClickListener {
            setServices()
            vm.save(services)
            services.clear()
            adapter.notifyDataSetChanged()
        })
        return root
    }


    private fun setServices() {

        for (i in items.indices) {
            if (ServicesListActivity.names[i].text.toString() == "" && ServicesListActivity.times[i].text.toString() == "") continue

            services.add(
                ServicesModel(
                    name = ServicesListActivity.names[i].text.toString(),
                    price = ServicesListActivity.prices[i].text.toString(),
                    timeInWork = ServicesListActivity.times[i].text.toString(),
                    info = "",
                    status = true,
                    uidServices = items[i].uidServices
                )
            )
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

