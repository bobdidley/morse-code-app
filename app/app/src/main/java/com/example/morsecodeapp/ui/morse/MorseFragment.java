package com.example.morsecodeapp.ui.morse;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.ScrollView;
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
import com.example.morsecodeapp.databinding.FragmentMorseBinding;

public class MorseFragment extends Fragment {

    private MorseViewModel morseViewModel;
    private FragmentMorseBinding binding;

    ScrollView scrollMorseMsg;
    TextView txtMorseTranslate;
    TextView txtMorseMsg;
    Button btnMorsePush;
    String message;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        morseViewModel =
                new ViewModelProvider(this).get(MorseViewModel.class);

        binding = FragmentMorseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        scrollMorseMsg = binding.scrollMorseMsg;
        txtMorseMsg = binding.txtMorseMsg;
        txtMorseTranslate = binding.txtMorseTranslate;
        btnMorsePush = binding.btnMorsePush;
        message = "";

//        scrollMorseMsg.post(new Runnable() {
//            public void run() {
//                scrollMorseMsg.fullScroll(View.FOCUS_DOWN);
//            }
//        });

        // TODO: use some timing mechanism to add a space to the message
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int t = 0;
//                while((t++ * 1000) % 3000 == 0) {
//                    message += " ";
//                }
//            }
//        });

        morseViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                txtMorseTranslate.setText(s);
            }
        });

        btnMorsePush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message += ".";
                txtMorseMsg.setText(message);
                // NOTE: scrolls once
//                if(!txtMorseMsg.isFocused()) {
//                    scrollMorseMsg.fullScroll(View.FOCUS_DOWN);
//                }
                // NOTE: continues to scroll until at bottom of text view
                txtMorseMsg.post(new Runnable() {
                    public void run() {
                        scrollMorseMsg.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }
        });

        btnMorsePush.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                message += "-";
                txtMorseMsg.setText(message);
                txtMorseMsg.post(new Runnable() {
                    public void run() {
                        scrollMorseMsg.fullScroll(View.FOCUS_DOWN);
                    }
                });
                return true;
            }
        });

        txtMorseMsg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // debug
//                Log.i("MSG: ", message);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO: implement morse code library for matching and split message string by spaces
                if(message.contentEquals("..."))
                    txtMorseTranslate.setText("hello");
//                else
//                    txtMorseTranslate.setText("");
            }
        });

        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}