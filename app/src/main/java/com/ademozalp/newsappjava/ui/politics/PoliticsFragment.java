package com.ademozalp.newsappjava.ui.politics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ademozalp.newsappjava.key.ApiKey;
import com.ademozalp.newsappjava.adapter.NewsAdapter;
import com.ademozalp.newsappjava.databinding.FragmentPoliticsBinding;
import com.ademozalp.newsappjava.model.Article;
import com.ademozalp.newsappjava.model.ResponseModel;
import com.ademozalp.newsappjava.rests.APIInterface;
import com.ademozalp.newsappjava.rests.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoliticsFragment extends Fragment {

    private FragmentPoliticsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPoliticsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.artcileRv.setLayoutManager(new LinearLayoutManager(requireContext()));
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final APIInterface apiService = ApiClient.getClient().create(APIInterface.class);
        ApiKey API_KEY = new ApiKey();
        Call<ResponseModel> call =  apiService.getLatestNews("tr","politics",API_KEY.getKey());

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

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
