package com.learn.test;

import com.google.common.base.*;
import com.google.common.collect.*;
import com.google.common.math.IntMath;
import com.google.common.math.LongMath;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @Author :lwy
 * @Date : 2018/12/25 10:57
 * @Description :
 */
public class OptionTest {


    public static void main(String[] args) {
       /* Integer o1 = null;
        Integer o2 = new Integer(1);

        Optional<Integer> a = Optional.fromNullable(o1);

        Optional<Integer> b = Optional.of(o2);

        System.out.println(a.get() + b.get());*/

       //2.Preconditions
        /*try {
            //校验表达式是否正确，并使用占位符输出错误信息
            Preconditions.checkArgument(1 > 2, "%s is wrong", "1 > 2");
        } catch (IllegalArgumentException e) {
            print(e.getMessage()); // 1 > 2 is wrong

        }*/

        //3.ordering
        //testStaticOrdering();

        //4.Range
        //testRange();

        //5.Multiset
        //testMultiset();

        //6.BiMap
        //testBimapTest();

        //7.Table
        //
        //testTableTest();

        //8.Joiner
        //testJoiner();

        //9.CharMatcher
        //testCharMatcher();

        //10.CaseFormat
        //testCaseFormat();

        //11.InMath
        testInMath();
    }

    private static void print(Object obj) {
        System.out.println(String.valueOf(obj));
    }


    private static void testStaticOrdering(){
        List<String> list = Lists.newArrayList();
        list.add("peida");
        list.add("jerry");
        list.add("harry");
        list.add("eva");
        list.add("jhon");
        list.add("neron");

        System.out.println("list:"+ list);

        Ordering<String> naturalOrdering = Ordering.natural();
        Ordering<Object> usingToStringOrdering = Ordering.usingToString();
        Ordering<Object> arbitraryOrdering = Ordering.arbitrary();

        System.out.println("naturalOrdering:"+ naturalOrdering.sortedCopy(list));
        System.out.println("usingToStringOrdering:"+ usingToStringOrdering.sortedCopy(list));
        System.out.println("arbitraryOrdering:"+ arbitraryOrdering.sortedCopy(list));
    }

    public static void testRange(){
        System.out.println("open:"+Range.open(1, 10));
        System.out.println("closed:"+ Range.closed(1, 10));
        System.out.println("closedOpen:"+ Range.closedOpen(1, 10));
        System.out.println("openClosed:"+ Range.openClosed(1, 10));
        System.out.println("greaterThan:"+ Range.greaterThan(10));
        System.out.println("atLeast:"+ Range.atLeast(10));
        System.out.println("lessThan:"+ Range.lessThan(10));
        System.out.println("atMost:"+ Range.atMost(10));
        System.out.println("all:"+ Range.all());
        System.out.println("closed:"+Range.closed(10, 10));
        System.out.println("closedOpen:"+Range.closedOpen(10, 10));
        //会抛出异常
        //System.out.println("open:"+Range.open(10, 10));

        System.out.println("downTo:"+Range.downTo(4, BoundType.OPEN));
        System.out.println("upTo:"+Range.upTo(4, BoundType.CLOSED));
        System.out.println("range:"+Range.range(1, BoundType.CLOSED, 4, BoundType.OPEN));
    }


    private static void testMultiset(){
        //create a multiset collection
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("d");
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("b");
        multiset.add("b");
        multiset.add("b");
        //print the occurrence of an element
        System.out.println("Occurrence of 'b' : "+multiset.count("b"));
        //print the total size of the multiset
        System.out.println("Total Size : "+multiset.size());
        //get the distinct elements of the multiset as set
        Set<String> set = multiset.elementSet();
        //display the elements of the set
        System.out.println("Set [");
        for (String s : set) {
            System.out.println(s);
        }
        System.out.println("]");
        //display all the elements of the multiset using iterator
        Iterator<String> iterator  = multiset.iterator();
        System.out.println("MultiSet [");
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("]");
        //display the distinct elements of the multiset with their occurrence count
        System.out.println("MultiSet [");
        for (Multiset.Entry<String> entry : multiset.entrySet())
        {
            System.out.println("Element: "+entry.getElement() +", Occurrence(s): " + entry.getCount());
        }
        System.out.println("]");

        //remove extra occurrences
        multiset.remove("b",2);
        //print the occurrence of an element
        System.out.println("Occurence of 'b' : "+multiset.count("b"));

    }


    public static void testBimapTest(){
        BiMap<Integer,String> logfileMap = HashBiMap.create();
        logfileMap.put(1,"a.log");
        logfileMap.put(2,"b.log");
        logfileMap.put(3,"c.log");
        System.out.println("logfileMap:"+logfileMap);
        BiMap<String,Integer> filelogMap = logfileMap.inverse();
        System.out.println("filelogMap:"+filelogMap);
    }


    private static void testTableTest(){
        Table<String, Integer, String> aTable = HashBasedTable.create();

        for (char a = 'A'; a <= 'C'; ++a) {
            for (Integer b = 1; b <= 3; ++b) {
                aTable.put(Character.toString(a), b, String.format("%c%d", a, b));
            }
        }

        System.out.println(aTable.column(2));
        System.out.println(aTable.row("B"));
        System.out.println(aTable.get("B", 2));

        System.out.println(aTable.contains("D", 1));
        System.out.println(aTable.containsColumn(3));
        System.out.println(aTable.containsRow("C"));
        System.out.println(aTable.columnMap());
        System.out.println(aTable.rowMap());

        System.out.println(aTable.remove("B", 3));
    }


    private static void testJoiner(){
        List<Integer> list = Lists.newArrayList(1,2,3,4,5);
        Joiner joiner = Joiner.on(",").skipNulls();
        System.out.println(joiner.join(list));

        Splitter splitter=Splitter.on(",");

        List<String> strings = splitter.splitToList(joiner.join(list));

        System.err.println(strings);
    }


    private static void testCharMatcher(){
        String str = "12312,agg  ";
        CharMatcher charMatcher1 = CharMatcher.is('1');
        CharMatcher charMatcher2 = CharMatcher.is('2');
        //两个CharMatcher或操作
        CharMatcher charMatcher3 = charMatcher1.or(charMatcher2);
        System.out.println(charMatcher3.retainFrom(str));
    }


    private static void testCaseFormat() {
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "test-data"));
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test_data"));
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "test_data"));

        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "testdata"));
        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "TestData"));
        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "testData"));
    }


    public static void testInMath() {
        // 检查两个整数相加后，是否溢出
        IntMath.checkedAdd(2, 3); // 等于5
        //IntMath.checkedAdd(Integer.MAX_VALUE, 1); // 整数最大值加1，溢出，抛出ArithmeticException
        // 检查两个整数相减后，是否溢出
        IntMath.checkedSubtract(5, 1); // 等于4
        //IntMath.checkedSubtract(Integer.MIN_VALUE, 1); // 整数最小值减1，溢出，抛出ArithmeticException
        // 检查两个整数相乘后，是否溢出
        IntMath.checkedMultiply(20, 8); // 等于160
        //IntMath.checkedMultiply(Integer.MAX_VALUE, 2); // 整数最大值乘以2，溢出，抛出ArithmeticException
        //IntMath.checkedMultiply(Integer.MIN_VALUE, 2); // 整数最小值乘以2，溢出，抛出ArithmeticException
        // 检查一个整数的N次幂（即把一个整数自乘N次），是否溢出
        IntMath.checkedPow(5, 2); // 等于25，即 5 * 5 = 25
        //IntMath.checkedPow(Integer.MAX_VALUE, 2); // 整数最大值自乘2次，溢出，抛出ArithmeticException
        //IntMath.checkedPow(Integer.MIN_VALUE, 2); // 整数最小值自乘2次，溢出，抛出ArithmeticException


        // 检查两个长整数相加后，是否溢出
        LongMath.checkedAdd(Integer.MAX_VALUE, 100); // 整数最大值加100，等于2147483747
        //LongMath.checkedAdd(Long.MAX_VALUE, 1); // 长整数最大值加1，溢出，抛出ArithmeticException
        // 检查两个长整数相减后，是否溢出
        LongMath.checkedSubtract(Integer.MIN_VALUE, 1); // 整数最小值减1，等于-2147483649
        //LongMath.checkedSubtract(Long.MIN_VALUE, 1); // 长整数最小值减1，溢出，抛出ArithmeticException
        // 检查两个长整数相乘后，是否溢出
        LongMath.checkedMultiply(Integer.MAX_VALUE, Integer.MAX_VALUE); // 两个整数最大值相乘，等于4611686014132420609
        //LongMath.checkedMultiply(Long.MAX_VALUE, 2); // 长整数最大值乘以2，溢出，抛出ArithmeticException
        //LongMath.checkedMultiply(Long.MIN_VALUE, 2); // 长整数最小值乘以2，溢出，抛出ArithmeticException
        // 检查一个长整数的N次幂（即把一个长整数自乘N次），是否溢出
        LongMath.checkedPow(Integer.MAX_VALUE, 2); // 整数的最大值自乘2次，等于4611686014132420609
        //LongMath.checkedPow(Long.MAX_VALUE, 2); // 长整数最大值自乘2次，溢出，抛出ArithmeticException
        //LongMath.checkedPow(Long.MIN_VALUE, 2); // 长整数最小值自乘2次，溢出，抛出ArithmeticException
    }
}
