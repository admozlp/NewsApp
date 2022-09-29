package com.ademozalp.newsappjava.ui.science;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ademozalp.newsappjava.adapter.NewsAdapter;
import com.ademozalp.newsappjava.databinding.FragmentScienceBinding;
import com.ademozalp.newsappjava.key.ApiKey;
import com.ademozalp.newsappjava.model.Article;
import com.ademozalp.newsappjava.model.ResponseModel;
import com.ademozalp.newsappjava.rests.APIInterface;
import com.ademozalp.newsappjava.rests.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ScienceFragment extends Fragment {

    private FragmentScienceBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentScienceBinding.inflate(inflater, container, false);
        binding.artcileRv.setLayoutManager(new LinearLayoutManager(requireContext()));
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final APIInterface apiService = ApiClient.getClient().create(APIInterface.class);
        ApiKey apiKey = new ApiKey();
        Call<ResponseModel> call = apiService.getLatestNews("tr","science", apiKey.getKey());

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(response.body().getStatus().equals("ok")){
                    if(response.body().getArticles().size() > 0){
                        List<Article> articleList = response.body().getArticles();
                        NewsAdapter adapter = new NewsAdapter(articleList, requireContext());
                        binding.artcileRv.setAdapter(adapter);
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(requireContext(), t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}