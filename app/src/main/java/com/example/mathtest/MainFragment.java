package com.example.mathtest;

import android.app.Activity;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mathtest.databinding.FragmentMainBinding;


public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_main, container, false);
        ViewModel viewModel;
        FragmentMainBinding binding;
        viewModel = ViewModelProviders.of(requireActivity(),new SavedStateViewModelFactory(getActivity().getApplication(), this)).get(ViewModel.class);
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false);
        binding.setData(viewModel);
        binding.setLifecycleOwner(requireActivity());
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_mainFragment_to_testFragment);
            }
        });
        return  binding.getRoot();
    }

}