package com.example.morsecodeapp.ui.english;

import android.widget.TextView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.morsecodeapp.R;
import com.example.morsecodeapp.databinding.FragmentEnglishBinding;


public class EnglishFragment extends Fragment {

    private EnglishViewModel englishViewModel;
    private FragmentEnglishBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        englishViewModel =
                new ViewModelProvider(this).get(EnglishViewModel.class);

        binding = com.example.morsecodeapp.databinding.FragmentEnglishBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textEnglish;
        englishViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}