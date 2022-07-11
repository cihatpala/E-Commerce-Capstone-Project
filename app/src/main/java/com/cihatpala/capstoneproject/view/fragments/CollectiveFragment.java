package com.cihatpala.capstoneproject.view.fragments;

import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.cihatpala.capstoneproject.R;
import com.cihatpala.capstoneproject.databinding.FragmentLoginBinding;
import com.cihatpala.capstoneproject.databinding.FragmentSignUpBinding;

public class CollectiveFragment extends Fragment {

    public void toastMessage(String string) {
        System.out.println("toastMessage");
        Toast.makeText(getContext(), string, Toast.LENGTH_LONG).show();
        System.out.println("message -> " + string);
    }

    public boolean isValidAreas(FragmentSignUpBinding binding) {
        boolean isENPImageError = false;
        boolean nullControlForTag = binding.checkMarkName.getTag() != null &&
                binding.checkMarkMail.getTag() != null &&
                binding.checkMarkPassword.getTag() != null;
        if (nullControlForTag) {
            isENPImageError = (int) binding.checkMarkName.getTag() == R.drawable.ic_check_mark &&
                    (int) binding.checkMarkMail.getTag() == R.drawable.ic_check_mark &&
                    (int) binding.checkMarkPassword.getTag() == R.drawable.ic_check_mark;
        }
        return isENPImageError;
    }

    public boolean isValidAreas(FragmentLoginBinding binding) {
        boolean isENPImageError = false;
        boolean nullControlForTag = binding.checkMarkMail.getTag() != null &&
                binding.checkMarkPassword.getTag() != null;
        if (nullControlForTag) {
            isENPImageError = (int) binding.checkMarkMail.getTag() == R.drawable.ic_check_mark &&
                    (int) binding.checkMarkPassword.getTag() == R.drawable.ic_check_mark;
        }
        return isENPImageError;
    }
}
