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
import android.widget.Toast;

import com.cihatpala.capstoneproject.activities.EntryActivity;
import com.cihatpala.capstoneproject.activities.MainActivity;
import com.cihatpala.capstoneproject.R;
import com.cihatpala.capstoneproject.databinding.FragmentSignUpBinding;
import com.cihatpala.capstoneproject.view.fragments.CollectiveFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpFragment extends CollectiveFragment {

    FragmentSignUpBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    //User is sign in
//                    toastMessage("Zaten giriş yapıldı");
//                    NavDirections action = LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment();
//                    Navigation.findNavController(requireView()).navigate(action);
//                } else {
//                    //user is sign out
//                    toastMessage("Çıkış yapıldı");
//                }
//
//            }
//        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, null, false);
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
                String email = binding.etMail.getText().toString();
                String name = binding.etName.getText().toString();
                String password = binding.etPassword.getText().toString();

                boolean isValidAllAreas = isValidAreas((FragmentSignUpBinding) binding);

                if (!name.equals("") && !email.equals("") && !password.equals("") && isValidAllAreas) {
                    mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            toastMessage("Kullanıcı Oluşturuldu!");
//                            NavDirections action = LoginFragmentDirections.actionLoginFragmentToHomeFragment();
//                            Navigation.findNavController(view).navigate(action);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            toastMessage("Kullanıcı Eklenemedi: " + e.getLocalizedMessage());
                        }
                    });
                } else if (!isValidAllAreas) {
                    toastMessage("Lütfen alanları doğru şekilde doldurunuz.");
                } else {
                    //boş
                    toastMessage("Lütfen alanları boş bırakmayınız");
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
                    ((EntryActivity) getActivity()).onBackPressed();
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
                    binding.checkMarkName.setTag(R.drawable.ic_not_checked);
                }
                if (len >= 8) { //Db'de ilgili kullanıcı adının da olmaması şartlara eklenmeli.
                    binding.checkMarkName.setBackgroundResource(R.drawable.ic_check_mark);
                    binding.checkMarkName.setTag(R.drawable.ic_check_mark);
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
                    binding.checkMarkMail.setTag(R.drawable.ic_not_checked);
                } else { //Db'de ilgili mailin olmaması şartı da eklenmeli
                    binding.checkMarkMail.setBackgroundResource(R.drawable.ic_check_mark);
                    binding.checkMarkMail.setTag(R.drawable.ic_check_mark);
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
                    binding.checkMarkPassword.setTag(R.drawable.ic_not_checked);
                } else {
                    binding.checkMarkPassword.setBackgroundResource(R.drawable.ic_check_mark);
                    binding.checkMarkPassword.setTag(R.drawable.ic_check_mark);
                }

            }


            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }


}