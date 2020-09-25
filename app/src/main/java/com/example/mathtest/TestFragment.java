package com.example.mathtest;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mathtest.databinding.FragmentTestBinding;


public class TestFragment extends Fragment {


    public TestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_test, container, false);
        final ViewModel viewModel;
        final StringBuilder builder = new StringBuilder();
        viewModel = ViewModelProviders.of(requireActivity(), new SavedStateViewModelFactory(requireActivity().getApplication(), this)).get(ViewModel.class);
        viewModel.title();
        viewModel.getcurrentscore().setValue(0);
        final FragmentTestBinding binding;
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_test, container, false);
        binding.setData1(viewModel);
        binding.setLifecycleOwner(requireActivity());
        binding.textView12.setText("?");
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button1:
                        builder.append("1");
                        break;
                    case R.id.button2:
                        builder.append("2");
                        break;
                    case R.id.button3:
                        builder.append("3");
                        break;
                    case R.id.button4:
                        builder.append("4");
                        break;
                    case R.id.button5:
                        builder.append("5");
                        break;
                    case R.id.button6:
                        builder.append("6");
                        break;
                    case R.id.button7:
                        builder.append("7");
                        break;
                    case R.id.button8:
                        builder.append("8");
                        break;
                    case R.id.button9:
                        builder.append("9");
                        break;
                    case R.id.button11:
                        builder.append("0");
                        break;
                    case R.id.button10:
                        builder.setLength(0);
                        break;

                }
                if (builder.length() == 0) {
                    binding.textView13.setText(getString(R.string.answer));
                } else {
                    binding.textView13.setText(builder.toString());
                }
            }
        };
        binding.button1.setOnClickListener(listener);
        binding.button2.setOnClickListener(listener);
        binding.button3.setOnClickListener(listener);
        binding.button4.setOnClickListener(listener);
        binding.button5.setOnClickListener(listener);
        binding.button6.setOnClickListener(listener);
        binding.button7.setOnClickListener(listener);
        binding.button8.setOnClickListener(listener);
        binding.button9.setOnClickListener(listener);
        binding.button11.setOnClickListener(listener);
        binding.button10.setOnClickListener(listener);
        binding.button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (Integer.valueOf(builder.toString()).intValue() == viewModel.getanswer().getValue()) {
                        viewModel.getcurrentscore();
                        builder.setLength(0);
                        binding.textView13.setText(R.string.answer_message);
                        viewModel.End();
                        //builder.append(getString(R.string.answer_message));
                    } else {
                        NavController controller = Navigation.findNavController(v);
                        if (viewModel.win_flag) {
                            controller.navigate(R.id.action_testFragment_to_endFragment);
                            viewModel.win_flag = false;
                            viewModel.save();
                        } else {
                            controller.navigate(R.id.action_testFragment_to_end2Fragment);
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(requireContext(),"请输入答案",Toast.LENGTH_SHORT).show();

                }
            }
        });
        return binding.getRoot();
    }
}