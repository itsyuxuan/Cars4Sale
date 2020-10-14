package com.example.cars4sale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BST {
    public static List<String> list_price = new ArrayList<>();
    public static List<Integer> new_list = new ArrayList<>();
    Node root;

    public BST() {
        this.root = null;
    }

    public static List<String> getLowerPrice(Node t, Integer price) {

        if (null == t) {
            return list_price;
        } else if (t.left != null) {
            getLowerPrice(t.left, price);
        }

        if (t.key != 0) {

            if (t.key < price) {
                list_price.add(t.value);

            }

        }

        if (t.right != null) {
            getLowerPrice(t.right, price);
        }

        return list_price;
    }

    public static List<String> getHigherPrice(Node t, Integer price) {

        if (null == t) {
            return list_price;

        } else if (t.left != null) {
            getHigherPrice(t.left, price);
        }
        if (t.key != 0) {

            if (t.key > price) {
                list_price.add(t.value);
            }

        }

        if (t.right != null) {
            getHigherPrice(t.right, price);
        }

        return list_price;
    }

    public static List<Integer> list_to_list(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            BST.new_list.add(Integer.parseInt(list.get(i)));
        }
        return BST.new_list;
    }

    public static Map list_to_map(Map map, List<Integer> list) {
        Map new_map = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            new_map.put(list.get(i), map.get(list.get(i)));
        }

        return new_map;
    }

    public Node insert(Integer key, String value) {
        Node parent = null;
        Node current = this.root;
        while (current != null) {
            if (current.key.compareTo(key) < 0) {
                parent = current;
                current = current.right;

            } else if (current.key.compareTo(key) > 0) {
                parent = current;
                current = current.left;
            } else {
                parent = current;
                break;
            }
        }
        if (parent == null) {
            this.root = new Node(key, value);  // no parent = root is empty
            return root;
        } else {
            Node newNode = new Node(key, value);

            if (parent.key.compareTo(key) < 0) {
                parent.right = newNode;
                newNode.parent = parent;
            } else if (parent.key.compareTo(key) > 0) {
                parent.left = newNode;
                newNode.parent = parent;
            } else {
                parent.value = value;
                return parent;
            }
            return newNode;
        }

    }

}