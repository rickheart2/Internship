import java.util.Scanner;
import java.util.Stack;

public class Calculator {
    private static int  isLegal = 1;

    public static void main(String[] args) {
        Stack<Integer> s1 = new Stack<>();//数据栈
        Stack<Character> s2 = new Stack<>();//运算符栈
        Scanner s = new Scanner(System.in);
        System.out.println("请输入表达式");
        String formula = s.nextLine();
        int result = pushStack(s1,s2,formula);
        if(isLegal==1)
            System.out.println(result);
    }

    //对表达式使用双栈处理
    public static int pushStack(Stack s1,Stack s2,String formula) {
        for (int i = 0; i < formula.length(); i++) {
            char ch = formula.charAt(i);
            if (ch == '(') {
                s2.push(ch);
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                int curPriority = getPriority(ch);
                while (true) {
                    int topPriority = 0;
                    if (!s2.isEmpty()) {
                        topPriority = getPriority((char) s2.peek());
                    }
                    if (curPriority > topPriority) {
                        s2.push(ch);
                        break;
                    } else {
                        s1.push(calculate(s1, s2));
                    }
                }
            } else if (ch == ')') {
                while (true) {
                    char topCh = (char) s2.pop();
                    if (topCh == '(') {
                        break;
                    } else {
                        s2.push(topCh);
                        s1.push(calculate(s1, s2));
                    }
                }
            } else if (ch >= '0' && ch <= '9') {
                int index = i + 1;
                while (index < formula.length()) {
                    char temp = formula.charAt(index);
                    if ((temp >= '0' && temp <= '9')) {
                        index++;
                    } else {
                        break;
                    }
                }
                    String numStr = formula.substring(i, index);
                    s1.push(Integer.parseInt(numStr));
                    i = index - 1;
                }
            else if (ch == ' ') {
                    continue;
                } else {
                    isLegal = 0;
                    System.out.println("输入字符非法");
                    break;
                }
            }
            while (true) {
                char ch;
                if (s2.isEmpty()) {
                    break;
                } else {
                    s1.push(calculate(s1, s2));
                }
            }
            int result = (int) s1.pop();
            return result;
    }
    //得到运算符的优先级
    public static int getPriority(char ch){
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '(':
            default:
                return 0;
        }
    }

    //根据栈内内容进行计算
    public static int calculate(Stack s1,Stack s2){
        char ch = ' ';
        int num1 = 0;
        int num2 = 0;
        if(!s2.isEmpty()){
            ch =(char) s2.pop();
        }
        if (!s1.isEmpty()){
            num2 = (int) s1.pop();
            num1 = (int) s1.pop();
        }
        switch (ch){
            case '+':
                return num1+num2;
            case '-':
                return num1-num2;
            case '*':
                return num1*num2;
            case '/':
                return num1/num2;
        }
        return 0;
    }
}
