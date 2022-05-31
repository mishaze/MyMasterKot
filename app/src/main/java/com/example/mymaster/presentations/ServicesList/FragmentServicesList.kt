package com.example.mymaster.presentations.ServicesList

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Domain.models.ServicesModel
import com.example.mymaster.databinding.FragmentServicesListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

class FragmentServicesList : Fragment() {
    private var items: ArrayList<ServicesModel> = ArrayList()

    private val vm by viewModel<ServiceListViewModel>()
    private var adapter: RecyclerView.Adapter<*> = ServicesAdapter(items)
    private val services: ArrayList<ServicesModel> = ArrayList()


    private var _binding: FragmentServicesListBinding? = null
    private val binding get() = _binding!!

    companion object {
        var itemsTemp: ArrayList<ServicesModel> = ArrayList()
        val names: MutableList<EditText> = ArrayList()
        val times: MutableList<EditText> = ArrayList()
        val prices: MutableList<EditText> = ArrayList()
        val infoList: MutableList<EditText> = ArrayList()
        val btnList: MutableList<ImageButton> = ArrayList()
        val statusList: MutableList<Boolean> = ArrayList()
    }


    @SuppressLint("NotifyDataSetChanged")
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
            itemsTemp.add(ServicesModel())
            adapter.notifyItemInserted(items.size - 1)
        })

        vm.resultLive.observe(this, {
            statusList.clear()
            names.clear()
            times.clear()
            prices.clear()
            infoList.clear()
            items.clear()
            itemsTemp.clear()


            it.forEach { i ->
                if (i.status == true) {
                    items.add(i)
                    itemsTemp.add(i)
                }
            }

            adapter.notifyDataSetChanged()

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
        for (i in 0..itemsTemp.size-1) {
            if (names[i].text.toString() == "" || times[i].text.toString() == ""
                || prices[i].text.toString() == ""
            ) {
                continue
            }
            services.add(
                ServicesModel(
                    name = names[i].text.toString(),
                    price = prices[i].text.toString(),
                    timeInWork = times[i].text.toString(),
                    info = infoList[i].text.toString(),
                    status = statusList[i],
                    uidServices = itemsTemp[i].uidServices,
                    reviews = itemsTemp[i].reviews,
                    evaluation = itemsTemp[i].evaluation
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

