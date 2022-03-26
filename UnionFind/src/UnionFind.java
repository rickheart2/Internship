import java.util.Scanner;

public class UnionFind {

    static int[] father = new int[20];
    static int[] height = new int[20];

    public static void main(String[] args) {
        System.out.println("您要进行的操作？\n1.初始化\n2.查询\n3.连接两数字\n0.结束");
        Scanner scanner = new Scanner(System.in);
        int n1,n2;
        int a = scanner.nextInt();
        while (a!=0){
            switch (a){
                case 1:
                    init();
                    break;
                case 2:
                    System.out.println("请输入要查询的两个整数");
                    n1 = scanner.nextInt();
                    n2 = scanner.nextInt();
                    System.out.println(isConnected(n1,n2));
                    break;
                case 3:
                    System.out.println("请输入要连接的两个整数");
                    n1 = scanner.nextInt();
                    n2 = scanner.nextInt();
                    union(n1,n2);
                    break;
            }
            System.out.println("您要进行的操作？\n1.初始化\n2.查询\n3.连接两数字\n0.结束");
            a = scanner.nextInt();
        }
    }

    public static void init(){
        for (int i = 0; i < 20; i++) {
            father[i] = i;
            height[i] = 1;
        }
        System.out.println("请输入整数对，最后以0 0结束");
        Scanner s = new Scanner(System.in);
        int p = s.nextInt();
        int q = s.nextInt();
        while (p>0&&q>0){
            union(p,q);
            p = s.nextInt();
            q = s.nextInt();
        }
        System.out.println("初始化完毕");
    }

    public static void union(int p,int q){
        p = findFather(p);
        q = findFather(q);
        if(p==q) return;
        else {
            if (height[p]>height[q]) father[q] = p;
            else {
                if(height[p]==height[q]) height[q]++;
                father[p]=q;
            }
        }
    }

    public static boolean isConnected(int p,int q){
        return findFather(p)== findFather(q);
    }

    public static int findFather(int p){
        if(father[p]==p) return p;
        else {
            father[p] = findFather(father[p]);
            return father[p];
        }
    }
}
