import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;

public class zkServer {
    private String hosts = "127.0.0.1:2181";
    private int timeout = 2000;
    private ZooKeeper zk = null;
    private String parent = "/server";

    //1.链接
    public void getConnect() throws Exception {
        zk = new ZooKeeper(hosts, timeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent.getPath()+"---"+watchedEvent.getType());
            }
        });

    }
    //2.注册
    public void register(String name) throws Exception {
        String node = zk.create(parent + "/server", name.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.printf(node);

    }
    //3业务
    public  void bussion() throws InterruptedException {
        System.out.printf("jiepi");
        Thread.sleep(Long.MAX_VALUE);
    }

    public static void main(String[] args) throws Exception {
       zkServer zkSer = new zkServer();
        zkSer.getConnect();
        zkSer.register("server3");
        zkSer.bussion();
    }
}