package youtubeLive;

public class StackTest {
    public static void main(String[] args) {
        IStack<String> stack = new LinkedListStack<>();
        System.out.println(stack.size()+"//"+stack.isEmpty());
        stack.push("서다찬");
        stack.push("전은희");
        stack.push("유현지");


    }
}
