import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;


public class zookeeper {


    private String hosts = "127.0.0.1:2181";
    private int timeout = 2000;
    private ZooKeeper zooKeeper = null;

    public void initZookeeper() throws Exception {
        zooKeeper = new ZooKeeper(hosts, timeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent.getPath() + "---" + watchedEvent.getType());
                try {
                    Stat isExist = zooKeeper.exists("/jiepi", true);
                    List<String> children = zooKeeper.getChildren("/", true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void create() throws KeeperException, InterruptedException {
        String node = zooKeeper.create("/jiep", "洁癖是一直低于".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.printf(node);
    }

    public void getNode() throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren("/", true);
        children.forEach((String item) -> {
            System.out.println(item);
        });
        Thread.sleep(Long.MAX_VALUE);
    }

    public void isExist() throws KeeperException, InterruptedException {
        Stat isExist = zooKeeper.exists("/jiepi", true);
        System.out.println(isExist != null ? "exist" : "not exist");
        Thread.sleep(Long.MAX_VALUE);
    }

    public static void main(String[] args) throws Exception {
        zookeeper zoo = new zookeeper();
        zoo.initZookeeper();
//     zoo.create();
        zoo.getNode();
        // zoo.isExist();

    }

}
