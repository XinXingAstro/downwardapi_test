package com.microsoft.danielxin;

import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;

import java.util.Date;

public class Main {


    public static void main(String[] args) {
        Date date = new Date();
        KubernetesClient k8s = new KubernetesClientBuilder().build();
        try {
             currentTime = date.toInstant();
            k8s.pods().inNamespace("default").withName("my-pod").edit(p -> new PodBuilder(p).editMetadata()
                    .addToAnnotations("currentTime", )
                    .endMetadata()
                    .build());
        }

    }
}