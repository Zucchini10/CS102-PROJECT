import java.util.ArrayList;

public class test2 {
    public static void main(String[] args) {
        String s1 = "Hello World%n";
        String s2 = "Hello World%n";
        String s3 = "Hello World%n";

        ArrayList<String> hehe = new ArrayList<>();
        hehe.add(s1);
        hehe.add(s2);
        hehe.add(s3);


        for(String s : hehe){
            System.out.printf(s);
        }
        
    }
}
