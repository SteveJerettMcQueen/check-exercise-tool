/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.maven.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author sm6668
 */
public class Util {

    public static Set<String> symDiff(Set<String> a, Set<String> b) {
        Set<String> result = new HashSet(a);
        b.stream().filter((element) -> (!result.add(element))).forEachOrdered((element) -> {
            result.remove(element);
        });
        return result;
    }

    public static List<String> convertToJavaScriptArray(Set<String> set) {
        List<String> result = new ArrayList();
        List<String> list = new ArrayList(set);
        for (int i = 0; i < list.size(); i++) {
            result.add("\"" + list.get(i) + "\"");
        }
        return result;
    }

}
