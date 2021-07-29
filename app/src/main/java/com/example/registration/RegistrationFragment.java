package com.example.registration;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

public class RegistrationFragment extends Fragment implements Keys {
    private EditText etNewLogin;
    private EditText etNewPass;
    private EditText etNewPass2;
    private ImageView ivBack;
    private MainActivity context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = (MainActivity) getActivity();
        initElements(view);
        initListeners();

    }

    private void initListeners() {
        ivBack.setOnClickListener(view -> {
            context.changeActivityForIdentifyFragment();
        });

    }


    private void initElements(View view) {
        etNewLogin = view.findViewById(R.id.etNewLogin);
        etNewPass = view.findViewById(R.id.etNewPass);
        etNewPass2 = view.findViewById(R.id.etNewPass2);
        ivBack = view.findViewById(R.id.ivBack);
    }

    public EditText getEtNewLogin() {
        return etNewLogin;
    }

    public EditText getEtNewPass() {
        return etNewPass;
    }

    public EditText getEtNewPass2() {
        return etNewPass2;
    }
}