package com.cihatpala.capstoneproject.view.fragments.entry;

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

import com.cihatpala.capstoneproject.R;
import com.cihatpala.capstoneproject.databinding.FragmentForgotPasswordBinding;

public class ForgotPasswordFragment extends Fragment {

    FragmentForgotPasswordBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forgot_password, null, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        watchTexts();
        onClicks();
    }

    private void onClicks() {

        binding.chevron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToLoginFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });

        binding.btnSend.setOnClickListener(new View.OnClickListener() {
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
    }

    private void watchTexts() {
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
                    binding.tvForgotErr.setVisibility(View.INVISIBLE);
                } else if (!isValidEmail(binding.etMail.getText())) {
                    binding.checkMarkMail.setVisibility(View.VISIBLE);
                    binding.tvMail.setVisibility(View.VISIBLE);
                    binding.checkMarkMail.setBackgroundResource(R.drawable.ic_not_checked);
                    binding.tvForgotErr.setVisibility(View.VISIBLE);
                } else { //Db'de ilgili mailin olmaması şartı da eklenmeli
                    binding.checkMarkMail.setBackgroundResource(R.drawable.ic_check_mark);
                    binding.tvForgotErr.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}