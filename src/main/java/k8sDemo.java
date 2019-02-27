import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class k8sDemo {
    public static void main(String[] args) {
        Config config = new ConfigBuilder().withMasterUrl("http://10.126.121.81:3218").build();
        KubernetesClient client = new DefaultKubernetesClient(config);

        PodList pods = client.pods().inNamespace("wcs-online").list();
        List<Pod> podList = pods.getItems();
        Iterator<Pod> podIterator = podList.iterator();

        Map<String,String> podinfoMap = new HashMap<String, String>();

        while (podIterator.hasNext()){
            Pod pod = podIterator.next();

            String podName = pod.getMetadata().getName();
            String podIp = pod.getStatus().getPodIP();

            if(podName.contains("searcher") == true){
                podinfoMap.put(podName,podIp);
            }
        }

        for(Map.Entry<String,String> entry:podinfoMap.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        System.out.println();
        System.out.println("hello.world");
    }
}
