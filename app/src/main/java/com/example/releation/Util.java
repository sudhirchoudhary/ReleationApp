package com.example.releation;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.releation.data.ApiClient;
import com.example.releation.data.ApiInterface;
import com.example.releation.model.Detail;
import com.example.releation.model.Edge;
import com.example.releation.model.Node;
import com.example.releation.model.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class Util {
    private static MutableLiveData<Boolean> dataFetched = null;
    private static Response myResponse;

    static void getData() {
        ApiInterface apiInterface = ApiClient.getInstance().create(ApiInterface.class);
        Call<Response> call = apiInterface.getData();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
                if (response.isSuccessful() && response.body() != null) {
                    dataFetched.postValue(true);
                    myResponse = response.body();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {

            }
        });
    }

    public static LiveData<Boolean> dataFetched() {
        if (dataFetched == null) {
            dataFetched = new MutableLiveData<>();
            getData();
        }
        return dataFetched;
    }

    private static Response getResponse() {
        return myResponse;
    }

    private static HashMap<Integer, List<Detail>> makeRelationGraph() {

        HashMap<Integer, List<Detail>> relationGraph = new HashMap<>();
        for(Node node: getResponse().nodes) {
            relationGraph.put(node.id, new ArrayList<>());
        }
        for(Edge edge: getResponse().edges) {
            Detail detail = new Detail();
            detail.targetNodeId = edge.target;
            detail.type = edge.data.type;
            Objects.requireNonNull(relationGraph.get(edge.source)).add(detail);
        }
        return relationGraph;
    }

    public static HashMap<Integer, Node> getAllNodes() {
        HashMap<Integer, Node> allNodes = new HashMap<>();
        for(Node node: getResponse().nodes) {
            allNodes.put(node.id, node);
        }
        return allNodes;
    }
    public static List<Detail> getAllRelatedNodes(int sourceNodeId) {
        return makeRelationGraph().get(sourceNodeId);
    }
}
