package com.iwebsapp.reqresjava.ui.detail_home.view;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.iwebsapp.reqresjava.R;
import com.iwebsapp.reqresjava.databinding.FragmentHomeDetailBinding;
import com.iwebsapp.reqresjava.ui.detail_home.presenter.DetailHomePresenter;
import com.iwebsapp.reqresjava.ui.detail_home.presenter.DetailHomePresenterImpl;

public class DetailHomeFragment extends Fragment implements DetailHomeView {
    private DetailHomePresenter presenter;
    private FragmentHomeDetailBinding binding;
    private String idUser;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idUser = getArguments().getString("idUser");
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeDetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        setPresenter();
        presenter.getUser(idUser);
        return root;
    }

    private void setPresenter() {
        if (presenter == null) {
            presenter = new DetailHomePresenterImpl(this);
        }
    }

    public void setData(String id, String email, String name, String lastName, String avatar) {
        binding.textViewId.setText(id);
        binding.textViewMail.setText(email);
        binding.textViewName.setText(name);
        binding.textViewLastName.setText(lastName);
        Glide.with(requireContext())
                .load(Uri.parse(avatar))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(binding.imageView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}