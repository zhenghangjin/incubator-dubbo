package com.alibaba.dubbo.remoting.zookeeper.zkclient;

import org.I0Itec.zkclient.*;
import org.apache.zookeeper.Watcher;

import java.util.List;

/**
 * Created by zhenghangjin on 2019/5/3.
 */
public class ZkClientTest {

    /**
     * zookeeper地址
     */
    static final String CONNECT_ADDR = "106.13.35.40:2181";
    /**
     * session超时时间
     */
    static final int SESSION_OUTTIME = 10000;//ms

    public static void main(String[] args) throws Exception {
        ZkClient zkc = new ZkClient(new ZkConnection(CONNECT_ADDR), SESSION_OUTTIME);

        //对父节点添加监听子节点变化。
        zkc.subscribeChildChanges("/super", new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                System.out.println("subscribeChildChanges -> parentPath: " + parentPath);
                System.out.println("subscribeChildChanges -> currentChilds: " + currentChilds);
            }
        });

        //对父节点添加监听子节点变化。
        zkc.subscribeDataChanges("/super", new IZkDataListener() {
            @Override
            public void handleDataDeleted(String path) throws Exception {
                System.out.println("subscribeDataChanges -> handleDataDeleted 删除的节点为:" + path);
            }

            @Override
            public void handleDataChange(String path, Object data) throws Exception {
                System.out.println("subscribeDataChanges -> handleDataChange 变更的节点为:" + path + ", 变更内容为:" + data);
            }
        });

        zkc.subscribeStateChanges(new IZkStateListener() {
            @Override
            public void handleStateChanged(Watcher.Event.KeeperState state) throws Exception {
                if (state == Watcher.Event.KeeperState.SyncConnected) {
                    System.out.println("subscribeStateChanges -> KeeperState.SyncConnected 连接成功"); // 当我重新启动后start，监听触发
                } else if (state == Watcher.Event.KeeperState.Disconnected) {
                    System.out.println("subscribeStateChanges -> KeeperState.Disconnected 连接断开"); // 当我在服务端将zk服务stop时，监听触发
                } else
                    System.out.println("subscribeStateChanges -> 其他状态" + state);
            }

            @Override
            public void handleNewSession() {
                System.out.println("subscribeStateChanges -> handleNewSession 重建session");
            }
        });

        System.out.println("start 1");
        Thread.sleep(20000);

        zkc.createPersistent("/super");
        System.out.println("start 2");
        Thread.sleep(20000);

        zkc.createPersistent("/super" + "/" + "c1", "c1内容");
        System.out.println("start 3");
        Thread.sleep(20000);

        zkc.createPersistent("/super" + "/" + "c2", "c2内容");
        System.out.println("start 4");
        Thread.sleep(20000);

        zkc.delete("/super/c2");
        System.out.println("start 5");
        Thread.sleep(20000);

        zkc.delete("/super/c1");
        System.out.println("start 5.2");
        //Thread.sleep(20000);

        zkc.deleteRecursive("/super");
        System.out.println("start 6");
        Thread.sleep(Integer.MAX_VALUE);
    }
}
