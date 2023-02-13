package com.microsoft.danielxin;

import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;
import java.io.File;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        KubernetesClient k8s = new KubernetesClientBuilder().build();
        File file = new File("/etc/downwardapi/annotations");
        try {
            while (true) {
                // 1 update annotation
                System.out.println("Start update annotation [modifytime] ...");
                long modifyTime = System.currentTimeMillis();
                k8s.pods().inNamespace("default").withName("downwardapi-test").edit(p -> new PodBuilder(p).editMetadata()
                        .addToAnnotations("modifytime", String.valueOf(modifyTime))
                        .endMetadata()
                        .build());
                System.out.println("Update pod [downwardapi-test] annotation [modifytime] to [" + modifyTime + "].");

                // 2 check if the annotation file is updated
                System.out.println("Start read annotation from /etc/downwardapi/annotations ...");
                while (true) {
                    if (file.exists() && !file.isDirectory()) {
                        Scanner scanner = new Scanner(file);
                        String annotation = null;
                        if (scanner.hasNext()) {
                            annotation = scanner.next();
                        }
                        long time = Long.parseLong(annotation != null ? annotation : "0");
                        if (time == modifyTime) { // annotation updated
                            long currentTime = System.currentTimeMillis();
                            System.out.println("Get [modifytime]: [" + annotation + "] Current time: [" + currentTime + "].");
                            System.out.println("Annotation [modifytime] update delay: [" + (currentTime - modifyTime) + "ms].");
                            break;
                        }
                        scanner.close();
                    }
                }

                // 3 sleep 10 seconds
                Thread.sleep(10 * 1000);
            }
        } catch (Exception e) {
            System.out.println("Update annotation meet exception!");
            System.out.println(e);
        } finally {

        }
    }
}