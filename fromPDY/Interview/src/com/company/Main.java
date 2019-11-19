package com.company;
/*AlienDictiory
public class Main {
    public static void main(String[] args) {
    String[] words = {"wrt","wrf","er","ett","rftt"};
    System.out.println(alienOrder(words));
    }
}
/*GuessNum
public class Main{
    public static void main(String[] args) {
        System.out.println(sendAndReceive("start"));
        System.out.println("Result: " + guess());
    }
}
 */
/*collatz
public class Main{
    public static void main(String args[]) {
        System.out.println((findLongestSteps(7)));
    }
}
*/
/*
public class Main {
    public static void main(String args[]) {
        int[][] arr = {{1,4,10},{1,2,5},{5,8,20}};
        Arrays.sort(arr, Comparator.comparingInt(o -> o[2]));
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
/*menu teat
public class Main {
    public static void main(String args[])
    {
        double[] prices = {2.40, 0.01, 6.00, 2.58};
        List<List<Double>> result = menuComb(prices, 2.50);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(i + "th result:");
            for (int j = 0; j < result.get(i).size(); j++) {
                System.out.println(result.get(i).get(j));
            }
        }
    }
}
*/

/*myIterator
public class Main {
    public static void main(String args[])
    {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(3));
        List<List<Integer>> vec2d = new ArrayList<>();
        vec2d.add(list1);
        vec2d.add(list2);
        //1,2
        //3
        MyIterator myIter = new MyIterator(vec2d);
        System.out.println(myIter.hasNext());
        System.out.println(myIter.next());
        System.out.println(myIter.hasNext());
        myIter.remove();
        System.out.println(myIter.next());
        for (int i = 0; i < vec2d.size(); i++) {
            if (vec2d.get(i) == null) continue;
            for (int j = 0; j < vec2d.get(i).size(); j++) {
                System.out.println("List" + i + ":" + vec2d.get(i).get(j));
            }

        }
        System.out.println(myIter.hasNext());
        System.out.println(myIter.next());
    }
}


/* find median
public class Main {
    public static void main(String args[])
    {
        int[] nums = new int[100];
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            int num = rand.nextInt(100);
            nums[i] = num;
        }
        System.out.println(findMedian(nums));
        Arrays.sort(nums);
        for (int i = 0; i < 100; i++) {
            System.out.print(nums[i] + ",");
        }
        System.out.println();
        System.out.println(nums[49]);
        System.out.println(nums[50]);
    }
}
*/

/* palindrome
public class Main {
    public static void main(String args[]) {
        String[] words = new String[]{"abcd","dcba","lls","s","sssll"};
        List<List<Integer>> res = palindromePairs(words);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }
}

 */


////Sliding Puzzle test
//public class Main {
//    public static void main(String args[]) {
//        int[][] board = {{4,1,2},{5,0,3}};
//        int res = slidingPuzzle(board);
//        System.out.println(res);
//    }
//}




//import Airbnb.fileSystem;
//
/////fileSystem test
//public class Main {
//    public static void main(String args[])
//    {
//        Runnable a = new Runnable() {
//            public void run(){
//                System.out.println("a changed");
//            }
//        };
//        Runnable b = new Runnable() {
//            public void run(){
//                System.out.println("b changed");
//            }
//        };
//        fileSystem f = new fileSystem();
//        f.create("/a", 2);
//        f.watch("/a", a);
//        f.create("/a/b", 4);
//        f.watch("/a/b", b);
//        f.create("/b/c", 1);
//        f.set("/a/b", 6);
//        f.set("/c", 5);
//        System.out.println(f.get("/a/b"));
//        f.get("/c");
//
//
//    }
//}

/*
public class Main {
    public static void main(String[] args) {
        int m = 3;
        int n = -8;
        System.out.println(f(m,n));

    }
}
*/
//public class Main {
//    public static void main(String[] args) {
//
//        List<List<Integer>> list = new ArrayList<>();
//        list.add(Arrays.asList(1, 2));
//        list.add(Arrays.asList(3));
//        list.add(Arrays.asList(4, 5));
//        list.add(Arrays.asList(1, 3));
//        Bd_TwoDIterator b = new Bd_TwoDIterator(list);
//        b.remove();
//        System.out.println(b.next());
//    }
//}

/*sk test
public class Main{
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String[] nodes = {"A,5", "B,7", "C,6", "D,2", "E,4", "F,7", "H,7", "I,3", "J,2"} ;
        String[] edges = {
                "A->B,2",
                "A->C,3",
                "B->D,5",
                "B->E,6",
                "C->E,4",
                "C->F,4",
                "D->H,7",
                "E->H,6",
                "H->I,1",
                "H->J,2",
                "F->J,3"
        } ;
        List<String> path = new ArrayList<>() ;
        int ans = maxPath(nodes, edges, "A", path) ;
        System.out.println(ans);
        System.out.println(path);
    }

}



/*
wizard
public class Main {
    public static void main(String[] args) {
        Integer[][] array = new Integer[][]{{1,2}, {2}, {3},{4,9},{5},{6},{7},{8},{9},{}};
        List<List<Integer>> input = new ArrayList<>();
        for (Integer[] arr : array) {
            input.add(Arrays.asList(arr));
        }
        System.out.println(minDis(input));
    }
}
*/




/*pourWater test
public class Main {
    public static void main(String[] args) {
        int[] heights = new int[]{2,1,0,3,2,6};
        int v = 100;
        int k = 3;
        pour(heights, k,v);
    }
}
 */




/* modules test
public class Main {
    public static void main(String[] args) {
        List<String> modules = asList("A,E,S,N","S,H,N","E,N","H","N");
        System.out.println(ModelCost(modules));
    }
}
*/


/* XML test
public class Main {
    public static void main(String[] args) {
        String s = "<a>abd/,erd</a>";
        System.out.println(validXML(s));
    }
}
*/



/*  //candy test
    public class Main {
    public static void main(String[] args) {
        int[] candy = new int[] {4, 8, 15, 1};
        int num = 5;
        System.out.println(minCandy(candy, num));
    }
}
*/

