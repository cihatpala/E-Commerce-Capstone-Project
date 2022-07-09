package com.cihatpala.capstoneproject.databinding;
import com.cihatpala.capstoneproject.R;
import com.cihatpala.capstoneproject.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentSignUpBindingImpl extends FragmentSignUpBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.chevron, 1);
        sViewsWithIds.put(R.id.headline, 2);
        sViewsWithIds.put(R.id.signup_area, 3);
        sViewsWithIds.put(R.id.name_area, 4);
        sViewsWithIds.put(R.id.check_mark_name, 5);
        sViewsWithIds.put(R.id.tv_name, 6);
        sViewsWithIds.put(R.id.et_name, 7);
        sViewsWithIds.put(R.id.mail_area, 8);
        sViewsWithIds.put(R.id.check_mark_mail, 9);
        sViewsWithIds.put(R.id.tv_mail, 10);
        sViewsWithIds.put(R.id.et_mail, 11);
        sViewsWithIds.put(R.id.password_area, 12);
        sViewsWithIds.put(R.id.check_mark_password, 13);
        sViewsWithIds.put(R.id.tv_password, 14);
        sViewsWithIds.put(R.id.et_password, 15);
        sViewsWithIds.put(R.id.already_account_area, 16);
        sViewsWithIds.put(R.id.tv_already_account, 17);
        sViewsWithIds.put(R.id.btn_already_account, 18);
        sViewsWithIds.put(R.id.btn_signup, 19);
        sViewsWithIds.put(R.id.tv_signup_social_acount, 20);
        sViewsWithIds.put(R.id.social_account_area, 21);
        sViewsWithIds.put(R.id.btn_google, 22);
        sViewsWithIds.put(R.id.img_google, 23);
        sViewsWithIds.put(R.id.btn_facebook, 24);
        sViewsWithIds.put(R.id.img_facebook, 25);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentSignUpBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 26, sIncludes, sViewsWithIds));
    }
    private FragmentSignUpBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[16]
            , (android.widget.ImageView) bindings[18]
            , (androidx.appcompat.widget.LinearLayoutCompat) bindings[24]
            , (androidx.appcompat.widget.LinearLayoutCompat) bindings[22]
            , (androidx.appcompat.widget.AppCompatButton) bindings[19]
            , (android.widget.ImageView) bindings[9]
            , (android.widget.ImageView) bindings[5]
            , (android.widget.ImageView) bindings[13]
            , (android.widget.ImageView) bindings[1]
            , (android.widget.EditText) bindings[11]
            , (android.widget.EditText) bindings[7]
            , (android.widget.EditText) bindings[15]
            , (android.widget.TextView) bindings[2]
            , (android.widget.ImageView) bindings[25]
            , (android.widget.ImageView) bindings[23]
            , (android.widget.FrameLayout) bindings[8]
            , (android.widget.FrameLayout) bindings[4]
            , (android.widget.FrameLayout) bindings[12]
            , (android.widget.FrameLayout) bindings[3]
            , (android.widget.LinearLayout) bindings[21]
            , (android.widget.TextView) bindings[17]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[20]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}