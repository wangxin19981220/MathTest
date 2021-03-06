package com.example.mathtest;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mathtest.databinding.FragmentEnd2Binding;
import com.example.mathtest.databinding.FragmentEndBinding;


public class EndFragment extends Fragment {


    public EndFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_end, container, false);
        ViewModel viewModel;
        FragmentEndBinding binding;
        viewModel = ViewModelProviders.of(requireActivity(),new SavedStateViewModelFactory(requireActivity().getApplication(),this)).get(ViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_end,container,false);
        binding.setData(viewModel);
        binding.setLifecycleOwner(requireActivity());
        binding.button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_endFragment_to_mainFragment);
            }
        });
        return binding.getRoot();
    }
}