package com.iwebsapp.reqresjava.ui.home.view;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iwebsapp.reqresjava.R;
import com.iwebsapp.reqresjava.databinding.FragmentHomeBinding;
import com.iwebsapp.reqresjava.model.home.Datum;
import com.iwebsapp.reqresjava.ui.home.adapter.HomeAdapter;
import com.iwebsapp.reqresjava.ui.home.presenter.HomePresenter;
import com.iwebsapp.reqresjava.ui.home.presenter.HomePresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeView {
    private HomePresenter presenter;
    private FragmentHomeBinding binding;
    private NavController navController;
    private List<Datum> people = new ArrayList<>();
    private final List<Datum> peopleSearch = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        setPresenter();
        navController = Navigation.findNavController(view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.recyclerView.setLayoutManager(layoutManager);
        presenter.init();
    }

    private void setPresenter() {
        if (presenter == null) {
            presenter = new HomePresenterImpl(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void setDataAdapter(List<Datum> data) {
        people = data;
        HomeAdapter adapter = new HomeAdapter(data, getContext(), presenter);
        binding.recyclerView.setAdapter(adapter);
    }

    public void showImage(String urlImg, ImageView imageView) {
        Glide.with(requireContext())
                .load(Uri.parse(urlImg))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(imageView);
    }

    public void showDetail(String idUser) {
        Bundle bundle = new Bundle();
        bundle.putString("idUser", idUser);
        navController.navigate(R.id.action_nav_home_to_nav_gallery, bundle);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.m_search);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_IF_ROOM);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void search(String text) {
        peopleSearch.clear();
        if (text != null) {
            for (int i=0; i<people.size(); i++) {
                if (people.get(i).getFirst_name().contains(text) ||
                        people.get(i).getLast_name().contains(text) ||
                        people.get(i).getEmail().contains(text)) {
                    peopleSearch.add(new Datum(people.get(i).getId(),
                            people.get(i).getEmail(),
                            people.get(i).getFirst_name(),
                            people.get(i).getLast_name(),
                            people.get(i).getAvatar()));

                }
            }
            setDataAdapter(peopleSearch);
        }
    }

}