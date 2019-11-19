package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Main {

    public static class FileSystem{
        Map<String, Integer> pathMap;
        Map<String, Runnable> callbackMap;

        public FileSystem(){
            this.pathMap = new HashMap<>();
            this.callbackMap = new HashMap<>();
            this.pathMap.put("", 0);
        }

        public boolean create(String path, Integer value) {
            if(pathMap.containsKey(path)) return false;
            int index = path.lastIndexOf("/");
            String parent = path.substring(0, index);
            if(!pathMap.containsKey(parent)) return false;
            pathMap.put(path, value);

            String curPath = path;
            while(curPath.length() > 0) {
                if(callbackMap.containsKey(curPath)) {
                    callbackMap.get(curPath).run();
                }
                int lastIndex = curPath.lastIndexOf("/");
                curPath = curPath.substring(0, lastIndex);
            }
            return true;
        }

        public boolean set(String path, Integer value) {
            if(!pathMap.containsKey(path)) {
                return false;
            }
            pathMap.put(path, value);

            while(path.length() > 0) {
                if(callbackMap.containsKey(path)) {
                    callbackMap.get(path).run();
                }
                int index = path.lastIndexOf("/");
                path = path.substring(0, index);
            }

            return true;
        }
        public int get(String path) {
            if(!pathMap.containsKey(path)) {
                throw new NoSuchElementException("there is no such path");
            } else {
                return pathMap.get(path);
            }
        }

        public boolean watch(String path, Runnable callback) {
            if(!pathMap.containsKey(path)) return false;
            callbackMap.put(path, callback);
            return true;
        }
    }


    public static void main(String[] args) {
	// write your code here
        FileSystem fs = new FileSystem();
        try {
            System.out.println(fs.get("/a"));//get false
        } catch(NoSuchElementException e) {
            System.out.println(e.toString());
        }
        System.out.println(fs.set("/a", 2));//set false
        System.out.println(fs.create("/a", 1));//create true
        System.out.println(fs.create("/b/c", 2));//create false
        System.out.println(fs.create("/a", 3));//create false
        System.out.println(fs.get("/a"));//get
        System.out.println(fs.create("/a/b", 4));
        System.out.println(fs.set("/a/b", 5));
        System.out.println(fs.watch("/a/b", new Runnable() {
            @Override
            public void run() {
                System.out.println("callback on /a/b");
            }
        }));
        System.out.println(fs.watch("/a", new Runnable(){
            @Override
            public void run() {
                System.out.println("callback on /a");
            }
        }));
        System.out.println(fs.create("/a/b/c", 7));
        System.out.println(fs.get("/a/b/c"));
        System.out.println(fs.set("/a/b/c", 8));
        System.out.println(fs.get("/a/b/c"));
    }
}
//this is my changing
