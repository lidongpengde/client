package com.boot.test;

import com.boot.HelloWorldService;
import com.boot.proxy.RPCProxyClient;
import org.junit.Test;
import org.springframework.core.NamedThreadLocal;

import java.util.*;

/**
 * Created by lidongpeng on 2018/1/31.
 */
public  class Solution {
    List<Integer> result=new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        if(root!=null){
            result.add(root.val);
        }
        if(root.left!=null){
             preorderTraversal(root.left);
        }
        if(root.right!=null){
             preorderTraversal(root.right);
        }
        return result;
    }
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        if (root == null) {
            return resultList;
        }

        List<Integer> levelStorage = new LinkedList<Integer>();
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        queue.offer(null);
        while (queue.size()>1){
            TreeNode top=queue.poll();
            if (top==null){
                resultList.add(levelStorage);
                queue.offer(null);
                levelStorage=new LinkedList<>();
            }else{
                levelStorage.add(top.val);
                if (top.left!=null){
                    queue.offer(top.left);
                }
                if (top.right!=null) {
                    queue.offer(top.right);
                }




            }
        }
        resultList.add(levelStorage);
        return resultList;

    }
    @Test
    public void test(){
/*        TreeNode root=new TreeNode(3);
        root.left=new TreeNode(1);
        root.right=new TreeNode(2);
      *//*  List<Integer> list=preorderTraversal( root);
        for (Integer integer: list) {
            System.out.println(integer);
        }*//*
        List<List<Integer>> lists=levelOrder(root);*/
        int[] nums={1,2,3,4};
        //int[] newNums=productExceptSelf( nums);
       /* Random random1 = new Random(100);

        ThreadLocal<Boolean> actualTransactionActive =
                new NamedThreadLocal<Boolean>("Actual transaction active");
        actualTransactionActive.set(false);
        System.out.println(actualTransactionActive.get());*/



        IhelloWorldService helloWorldService = (IhelloWorldService) RPCProxyClient.getProxy(HelloWorldService.class);
        helloWorldService.sayHello("test");


    }

    public int[] productExceptSelf(int[] nums) {
        int length=nums.length;
        int[] forward=new int[length];
        int[] backward=new int[length];
        int[] result =new int[length];
        forward[0]=1;backward[length-1]=1;
        for (int i = 1; i <length ; i++) {
            forward[i]=forward[i-1]*nums[i-1];
        }
        for (int i = length-2; i >=0 ; i--) {
            backward[i]=backward[i+1]*nums[i+1];
        }
        for (int i = 0; i <length ; i++) {
            result[i]=backward[i]*forward[i];
        }
        return result;

    }
}
