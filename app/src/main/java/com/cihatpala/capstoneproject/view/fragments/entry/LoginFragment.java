package com.cihatpala.capstoneproject.view.fragments.entry;

import static com.cihatpala.capstoneproject.helper.Helper.initGetTokenRequest;
import static com.cihatpala.capstoneproject.helper.Helper.isValidEmail;
import static com.cihatpala.capstoneproject.helper.Helper.isValidPassword;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.room.Room;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cihatpala.capstoneproject.R;
import com.cihatpala.capstoneproject.activities.EntryActivity;
import com.cihatpala.capstoneproject.activities.MainActivity;
import com.cihatpala.capstoneproject.activities.MarketActivity;
import com.cihatpala.capstoneproject.databinding.FragmentLoginBinding;
import com.cihatpala.capstoneproject.network.Services;
import com.cihatpala.capstoneproject.room.dao.UserDao;
import com.cihatpala.capstoneproject.room.db.UserDatabase;
import com.cihatpala.capstoneproject.room.entity.User;
import com.cihatpala.capstoneproject.view.fragments.CollectiveFragment;
import com.cihatpala.capstoneproject.viewmodel.CommerceViewModel;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;


public class LoginFragment extends CollectiveFragment {
    CommerceViewModel viewModel;
    FragmentLoginBinding binding;
    CallbackManager callbackManager;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String EMAIL = "email";
    UserDatabase db;
    UserDao userDao;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        viewModel = new ViewModelProvider(this).get(CommerceViewModel.class);
        db = Room.databaseBuilder(getActivity().getApplicationContext(), UserDatabase.class, "users").allowMainThreadQueries().build();
        userDao = db.userDao();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, null, false);
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        //Home'a atmalı
                        System.out.println("facebook login success");
                    }

                    @Override
                    public void onCancel() {
                        //durmalı
                        System.out.println("facebook login onCancel");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        //durmalı
                        System.out.println("facebook login onError");
                    }
                });
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
                NavDirections action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });


        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isValidAllAreas = isValidAreas((FragmentLoginBinding) binding);
                String email = binding.etMail.getText().toString();
                String password = binding.etPassword.getText().toString();
                if (!email.equals("") && !password.equals("") && isValidAllAreas) {
//                    email.equals("c@c.c") && password.equals("As1+")
                    loginUserByService(email, password);
//                    mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                        @Override
//                        public void onSuccess(AuthResult authResult) {
//                            toastMessage("Kullanıcı Oluşturuldu!");
////                            NavDirections action = LoginFragmentDirections.actionLoginFragmentToHomeFragment();
////                            Navigation.findNavController(view).navigate(action);
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            toastMessage("Giriş Başarısız: " + e.getLocalizedMessage());
//                        }
//                    });
                } else if (!isValidAllAreas) {
                    toastMessage("Lütfen alanları doğru şekilde doldurunuz.");
                } else {
                    //boş
                    toastMessage("Lütfen alanları boş bırakmayınız");
                }
            }
        });

        binding.btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(getActivity(), Arrays.asList("public_profile"));
            }
        });

        binding.btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });

    }

    public void loginUserByService(String name, String password) {
        viewModel.getToken(initGetTokenRequest(name, password), name);
        allUserNameControl(name);
//        viewModel.getUserToken().observe(getViewLifecycleOwner(), this::saveLoginnedUser);
    }

    private void allUserNameControl(String name) {
        try {
            compositeDisposable.add(userDao.getNameIsExists(name)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::allUser));
        } catch (OnErrorNotImplementedException e) {
            System.out.println("OnErrorNotImplementedException e ->" + e.getLocalizedMessage());
        }
    }

    private void allUser(Integer result) {
        if (result != 0) {
            System.out.println("allUser name is exists");
            goToMarket();
        } else {
            System.out.println("allUser name is not exists");
            viewModel.getUserToken().observe(getViewLifecycleOwner(), this::saveLoginnedUser);
        }
    }

    private void saveLoginnedUser(User user) {
        //TODO: Add token + name (email etc.) pass to user table
        System.out.println("user -> " + user.toString());
        try {
            compositeDisposable.add(userDao.insert(user)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::goToMarket));
        } catch (OnErrorNotImplementedException e) {
            System.out.println("OnErrorNotImplementedException e ->" + e.getLocalizedMessage());
        }

    }

    private void goToMarket() {
        Intent goToMarket = new Intent(getActivity(), MarketActivity.class);
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(goToMarket);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
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