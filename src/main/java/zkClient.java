import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.ArrayList;
import java.util.List;

public class zkClient {
    private String hosts = "127.0.0.1:2181";
    private int timeout = 2000;
    private ZooKeeper zkclient = null;
    private String parent = "/server";

    //1.链接
    public void getConnect() throws Exception {
        zkclient = new ZooKeeper(hosts, timeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent.getPath() + "---" + watchedEvent.getType());
                try {
                    getNode();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void getNode() throws Exception {
        List<String> children = zkclient.getChildren(parent, true);
        List<String> list = new ArrayList<>();
        children.forEach((String item) -> {
            try {

                byte[] data = zkclient.getData(parent + "/", false, null);
                list.add(new String(data));
            } catch (Exception e) {

            }

        });
        list.forEach(item -> {
            System.out.println("dddd=="+item);
        });

    }

    //3业务
    public void bussion() throws InterruptedException {
        System.out.printf("jiepi");
        Thread.sleep(Long.MAX_VALUE);
    }

    public static void main(String[] args) throws Exception {
        zkClient client = new zkClient();
        client.getConnect();
        client.getNode();
        client.bussion();

    }
}
