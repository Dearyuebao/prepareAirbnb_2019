package Airbnb;
import java.util.*;
/*
Use HashMap to implement it, path is mapped to the val.
1.For creat function, if map already contains current
path in the key set, cannot creat it, if map doesn't
contains prefix in the keySet, cannot create it,
otherwise, the path can be created.
2. For set function, if map keySet doesn't contains
the path, cannot set the path val.
3. For get function, if the map keySet doesn't contains
the path, cannot get the path val

follow up: watch
It mean's when the path watched is change, can get
response ? Use Runnable interface to achieve it,
define a callback map, String mapped to Runnable.
For watch function, if map doesn't contains the path,
cannot watch it, otherwise put the path to the
callBackMap
For create function, if the callBackMap contains
the prefix, the run() method will be call.
For set function, if the callBackMap contains
the path, the run() method will be call.


 */
public class Ae_FileSystem {
    HashMap<String, Integer> map;
    HashMap<String, Runnable> callBackMap;
    public Ae_FileSystem() {
        this.map = new HashMap<>();
        this.callBackMap = new HashMap<>();
        this.map.put("", 0);
    }

    public boolean create (String path, int val) {
        if (map.containsKey(path)) {
                System.out.println("ERROR! Cannot create " + path + " already exists");
                return false;
        }
        int index = path.lastIndexOf("/");
        String pre = path.substring(0, index);
        if (!map.containsKey(pre)) {
            System.out.println("ERROR! Cannot create, parent path " + pre + " doesn't exist");
            return false;
        }
        while (pre.length() > 0) {
            if(callBackMap.containsKey(pre)) {
                callBackMap.get(pre).run();
            }
            int last = pre.lastIndexOf("/");
            pre = pre.substring(0, last);
        }
        map.put(path, val);
        return true;
    }

    public boolean set (String path, int val) {
        if (!map.containsKey(path)) {
            System.out.println("ERROR! Cannot set, " + path + " doesn't exist");
            return false;
        }
        String cur = path;
        while (cur.length() > 0) {
            if(callBackMap.containsKey(cur)) {
                callBackMap.get(cur).run();
            }
            int index = cur.lastIndexOf("/");
            cur = cur.substring(0, index);
        }
        map.put(cur, val);
        return true;
    }

    public Integer get(String path) {
        if(!map.containsKey(path)) {
            System.out.println("ERROR! Cannot get " + path + "doesn't exist");
        }
        return map.get(path);
    }

    public boolean watch (String path, Runnable callBack) {
        if (!map.containsKey(path)) {
            System.out.println("ERROR! Cannot watch " + path + " doesn't exist");
            return false;
        }
        callBackMap.put(path, callBack);
        return true;
    }
}

/*
class Solution2 {
    private TrieNode root;

    public Solution() {
        this.root = new TrieNode("", 0, null, new HashMap<>());
    }

    public boolean create(String key, int value) {
        //    /a/b/c/d -> "" a b c
        TrieNode node = root;
        String[] arr = key.split("/");
        for (int i = 1; i < arr.length - 1; i++) {
            String toCheck = arr[i];
            if (!node.map.containsKey(toCheck)) {
                return false;
            }
            node = node.map.get(toCheck);
        }
        String toAdd = arr[arr.length - 1];
        if (node.map.containsKey(toAdd)) {
            return false;
        }
        TrieNode newNode = new TrieNode(toAdd, value, null, new HashMap<>());
        node.map.put(toAdd, newNode);
        return true;
    }

    public int get(String key) {
        TrieNode node = root;
        String[] arr = key.split("/");
        for (int i = 1; i < arr.length; i++) {
            String next = arr[i];
            if (!node.map.containsKey(next)) {
                return -1;
            }
            node = node.map.get(next);
        }
        return node.value;
    }

    public boolean set(String key, int value) {
        TrieNode node = root;
        String[] arr = key.split("/");
        for (int i = 1; i < arr.length; i++) {
            String toCheck = arr[i];
            if (!node.map.containsKey(toCheck)) {
                return false;
            }
            node = node.map.get(toCheck);
        }
        node.value = value;
        return true;
    }

    public void watch(String key, String alert) {
         //关于watch方法，需要询问未建立的节点是否能添加watch，如果可以的话，就需要用HashMap做；
        //如果只在当前的存在的节点上添加watch的话，就直接按照路径找到节点，然后添加即可.以下做的是在现有的路径上添加watch的版本
        //另外如果需要没有error的时候才执行callback函数的话，那么需要在每个节点添加父亲节点，只有在没有error成功create和set的时候才通过parent节点反向向上执行callback
        TrieNode node = root;
        String[] arr = key.split("/");
        for (int i = 1; i < arr.length; i++) {
            String toCheck = arr[i];
            if (!node.map.containsKey(toCheck)) {
                return;
            }
            node = node.map.get(toCheck);
        }
        Runnable runnable = new Runnable(){
            public void run() {
                System.out.println(alert);
            }
        };
        node.callback = runnable;
    }
}

class TrieNode {
    String key;
    int value;
    Runnable callback;
    Map<String, TrieNode> map;
    public TrieNode(String key, int value, Runnable callback, Map<String, TrieNode> map) {
        this.key = key;
        this.value = value;
        this.callback = callback;
        this.map = map;

    }
}
 */
