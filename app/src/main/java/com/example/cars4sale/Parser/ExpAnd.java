package com.example.cars4sale.Parser;

import com.example.cars4sale.DataStructure.BSTSearch;

import java.util.HashMap;
import java.util.Map;

import static com.example.cars4sale.DataStructure.BST.getHigherPrice;
import static com.example.cars4sale.DataStructure.BST.list_to_list;
import static com.example.cars4sale.DataStructure.BST.list_to_map;
import static com.example.cars4sale.DataStructure.BSTSearch.node;
import static com.example.cars4sale.DataStructure.BSTSearch.sList;

public class ExpAnd extends Exp {

    private Exp term;
    private Exp exp;

    public ExpAnd(Exp term, Exp exp) {
        this.term = term;
        this.exp = exp;
    }

    public static void main(String[] args) {
        Map m1 = BSTSearch.getName(BSTSearch.readData_map(), "tesla");
        //Map m1 = list_to_map(BSTSearch.readData_map(), list_to_list(getHigherPrice(node(sList).root, 988934)));
        Map m2 = BSTSearch.getYear(BSTSearch.readData_map(), 2012);
        Map commonMap = new HashMap();
        for (Object i : m1.keySet()) {
            if (m2.get(i) != null) {
                if (m2.get(i).equals(m1.get(i))) {
                    commonMap.put(i, m1.get(i));
                }
            }
        }
        System.out.println(commonMap);
    }

    @Override
    public Map evaluate() {
        Map termMap = term.evaluate();
        Map expMap = exp.evaluate();
        Map commonMap = new HashMap();
        for (Object i : termMap.keySet()) {
            if (expMap.get(i) != null) {
                if (expMap.get(i).equals(termMap.get(i))) {
                    commonMap.put(i, termMap.get(i));
                }
            }
        }
        return commonMap;
    }
}
