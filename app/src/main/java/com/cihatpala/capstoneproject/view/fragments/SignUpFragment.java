package com.cihatpala.capstoneproject.view.fragments;

import static com.cihatpala.capstoneproject.helper.Helper.isValidEmail;
import static com.cihatpala.capstoneproject.helper.Helper.isValidPassword;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cihatpala.capstoneproject.MainActivity;
import com.cihatpala.capstoneproject.R;
import com.cihatpala.capstoneproject.callback.TWListener;
import com.cihatpala.capstoneproject.databinding.FragmentSignUpBinding;

import java.util.ArrayList;
import java.util.List;

public class SignUpFragment extends Fragment implements TWListener {

    FragmentSignUpBinding binding;
    List<View> viewList;

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, null, false);
//        loadViewList();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onClicks();
        watchTexts();
    }

    private void onClicks() {
        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isValidEmail(binding.etMail.getText())) {
                    System.out.println("düzgün mail giriniz.");
                } else {
                    System.out.println("mail düzgün");
                    binding.checkMarkMail.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.btnAlreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment();
                Navigation.findNavController(view).navigate(action);

            }
        });

        binding.chevron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() != null) {
                    ((MainActivity) getActivity()).onBackPressed();
                }
            }
        });
    }

    private void watchTexts() {
        //Watch Name
        binding.etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int len = charSequence.length();
                if (len == 0) {
                    binding.checkMarkName.setVisibility(View.INVISIBLE);
                    binding.tvName.setVisibility(View.INVISIBLE);
                } else if (len < 8) {
                    binding.checkMarkName.setVisibility(View.VISIBLE);
                    binding.tvName.setVisibility(View.VISIBLE);
                    binding.checkMarkName.setBackgroundResource(R.drawable.ic_not_checked);
                }
                if (len >= 8) { //Db'de ilgili kullanıcı adının da olmaması şartlara eklenmeli.
                    binding.checkMarkName.setBackgroundResource(R.drawable.ic_check_mark);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Watch Mail
        binding.etMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int len = charSequence.length();
                if (len == 0) {
                    binding.checkMarkMail.setVisibility(View.INVISIBLE);
                    binding.tvMail.setVisibility(View.INVISIBLE);
                } else if (!isValidEmail(binding.etMail.getText())) {
                    binding.checkMarkMail.setVisibility(View.VISIBLE);
                    binding.tvMail.setVisibility(View.VISIBLE);
                    binding.checkMarkMail.setBackgroundResource(R.drawable.ic_not_checked);
                } else { //Db'de ilgili mailin olmaması şartı da eklenmeli
                    binding.checkMarkMail.setBackgroundResource(R.drawable.ic_check_mark);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Watch Mail
        binding.etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int len = charSequence.length();
                if (len == 0) {
                    binding.checkMarkPassword.setVisibility(View.INVISIBLE);
                    binding.tvPassword.setVisibility(View.INVISIBLE);
                } else if (!isValidPassword(String.valueOf(binding.etPassword.getText()))) { //8 karakterden büyük ve şifre özelliklerini sağlayan
                    binding.checkMarkPassword.setVisibility(View.VISIBLE);
                    binding.tvPassword.setVisibility(View.VISIBLE);
                    binding.checkMarkPassword.setBackgroundResource(R.drawable.ic_not_checked);
                } else {
                    binding.checkMarkPassword.setBackgroundResource(R.drawable.ic_check_mark);
                }

            }


            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void loadViewList() {
        viewList.add(binding.etName);
        viewList.add(binding.etMail);
        viewList.add(binding.etPassword);
        listenText(this);
    }

    @Override
    public View getWatched() {
        for (View view : viewList) {
            return view;
        }
        return null;

    }

    public void listenText(TWListener twListener) {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).listenText(twListener);
        }
    }

}